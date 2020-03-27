package com.pl.admin.service.runner.engine;



import com.google.common.base.Throwables;
import com.pl.admin.service.runner.Engine;
import com.pl.admin.service.runner.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对输入的字符串,进行java编译,执行
 * 实现的功能:
 * 1.动态编译
 * 2.获取编译结果:是否成功,失败原因
 * 3.编译结果放入内存
 * 4.动态执行
 */
public class JavaFlyEngine implements Engine {

    private static Logger logger = LoggerFactory.getLogger(JavaFlyEngine.class);

    private String clazzName;
    private ClassFileManager fileManager;
    private JavaCompiler compiler;

    public JavaFlyEngine(){
        try {
            compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, null);
            fileManager = new ClassFileManager(fm);
        }catch (Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
        }
    }

    @Override
    public String getLang() {
        return "Java";
    }

    @Override
    public Result compile(String source) {
        try {
            clazzName = StringSourceJavaObject.getClassName(source);
            StringSourceJavaObject sourceObject = new StringSourceJavaObject(clazzName, source);
            Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(sourceObject);


            DiagnosticListener dl = new DiagnosticCollector();

            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, dl, null, null, fileObjects);
            boolean result = task.call();
            Result r = new Result();
            r.setSuccess(result);
            if (!result) {
                StringBuilder sb = new StringBuilder();
                for (Object d : ((DiagnosticCollector) dl).getDiagnostics()) {
                    sb.append(d.toString());
                    sb.append(System.lineSeparator());
                }
                r.setMsg(sb.toString());
            } else {
                r.setMsg("编译成功");
            }

            return r;
        } catch (Throwable e) {
            return byErr(e);
        }
    }

    @Override
    public Runner.Result doCompile(String source) {
        return null;
    }

    private Result byErr(Throwable e) {
        String es=Throwables.getStackTraceAsString(e);
        logger.error(es);
        Result r = new Result(e.toString());
        return r;
    }
    Class<?> clazz;
    @Override
    public Result execute(Object[] args) {
        try {
            if(clazz==null) {
                ClassLoader cl = fileManager.getClassLoader(null);
                clazz = cl.loadClass(clazzName);
            }
            SysStreamRedirector.OutRedirector or = new SysStreamRedirector.OutRedirector();
            SysStreamRedirector.ErrRedirector er = new SysStreamRedirector.ErrRedirector();
            or.set();
            er.set();
            Method method = clazz.getMethod("main", String[].class);
            Object ra=args == null ? new Object[]{args} : args;
            Object value = method.invoke(null, ra);

            String os = or.getString();
            String es = er.getString();
            or.reset();
            er.reset();
            boolean suc = StringUtils.isEmpty(es);
            Result r = new Result();
            r.setSuccess(suc);
            r.setMsg(suc ? os : es);
            return r;
        } catch (Throwable e) {
            return byErr(e);
        }

    }

    @Override
    public Runner.Result doExecute(Object[] args) {
        return null;
    }

    @Override
    public ExecuteResult compileAndExecute(String source, String[] args) {
        Result result = compile(source);
        if (!result.isSuccess()) {
            return new ExecuteResult(result, null);
        }
        Result er = execute(args);
        return new ExecuteResult(result, Arrays.asList(er));
    }

    /**
     * 编译器使用的文件管理器
     */
    public static class ClassFileManager extends ForwardingJavaFileManager {

        private JavaClassFileObject classFileObject;

        /**
         * Creates a new instance of ForwardingJavaFileManager.
         *
         * @param fileManager delegate to this file manager
         */
        public ClassFileManager(JavaFileManager fileManager) {
            super(fileManager);
        }

        /**
         * Gets a JavaFileObject file object for output
         * representing the specified class of the specified kind in the given location.
         */
        @Override
        public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind,
                                                   FileObject sibling) throws IOException {
            classFileObject = new JavaClassFileObject(className, kind);
            return classFileObject;
        }

        @Override
        //获得一个定制ClassLoader，返回我们保存在内存的类
        public ClassLoader getClassLoader(Location location) {
            return new ClassLoader() {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    byte[] classBytes = classFileObject.getClassBytes();
                    return super.defineClass(name, classBytes, 0, classBytes.length);
                }
            };
        }
    }


    /**
     * il代码保存到内存中
     */
    public static class JavaClassFileObject extends SimpleJavaFileObject {
        //用于存储class字节
        ByteArrayOutputStream outputStream;

        public JavaClassFileObject(String className, Kind kind) {
            super(URI.create("string:///" + className.replace('.', '/') + kind.extension), kind);
            outputStream = new ByteArrayOutputStream();
        }

        @Override
        public OutputStream openOutputStream() throws IOException {
            return outputStream;
        }

        public byte[] getClassBytes() {
            return outputStream.toByteArray();
        }
    }


    /**
     * 源码保存到内存中
     */
    public static class StringSourceJavaObject extends SimpleJavaFileObject {

        private String content = null;

        public StringSourceJavaObject(String name, String content) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }

        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return content;
        }

        static final Pattern CLASS_NAME_PATTERN = Pattern.compile("class(.*?)\\{");
        static final Pattern PKG_PATTERN = Pattern.compile("package(.*?);");

        /**
         * 从源码中解析类名
         *
         * @param content 源码
         * @return 类名
         */
        public static String getClassName(String content) {
            String pkg = null;
            Matcher m = PKG_PATTERN.matcher(content);
            if (m.find()) {
                pkg = m.group(1);
            }
            String cn = null;
            Matcher cm = CLASS_NAME_PATTERN.matcher(content);
            if (cm.find()) {
                cn = cm.group(1);
            }
            StringBuilder sb = new StringBuilder();
            if (pkg != null) {
                sb.append(pkg.trim());
                sb.append(".");
            }

            if (cn != null) {
                sb.append(cn.trim());
            }
            return sb.toString();
        }
    }
}
