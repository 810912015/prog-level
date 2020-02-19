package com.pl.admin.service.runner;

import com.google.common.base.Throwables;
import com.pl.admin.service.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Collections;
import java.util.UUID;

/**
 * 使用执行控制台命令实现的执行引擎.
 * 执行步骤:1.保存文件;2.执行编译;3.执行测试;
 * 这样任何一门语言的执行都形式化为以上三个步骤.
 */
public abstract class BaseRunnerEngine implements Engine {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private Runner runner;
    protected String uid;

    public BaseRunnerEngine(Runner runner, String uid) {
        this.runner = runner;
        this.uid = uid;
    }

    public BaseRunnerEngine() {
        this(new ConsoleRunner(), UUID.randomUUID().toString());
    }


    public static Engine.Result convert(Runner.Result r) {
        r.prettify();
        Engine.Result rr = new Engine.Result();
        boolean suc = StringUtils.isEmpty(r.getErr());
        rr.setSuccess(suc);
        rr.setMsg(suc ? r.getOut() : r.getErr());
        return rr;
    }

    /**
     * 文件保存信息,注意扩展名不带".",路径最后不带/.
     */
    public static class SaveInfo {
        private String path;
        private String name;
        private String extension;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String toSavePath() {
            return String.format("%s/%s.%s", path, name, extension);
        }

        public void modify(){
            String s=this.getClass().getResource("").getFile();
            this.path=(s+this.path);
        }
    }

    /**
     * 生成保存信息
     *
     * @param source 要保存的源码内容
     * @return 保存信息
     */
    public abstract SaveInfo getSaveInfo(String source);

    /**
     * 获取编译命令
     *
     * @param si 文件信息
     * @return 字符串, 编译命令
     */
    public abstract String getCompileCmd(SaveInfo si);

    /**
     * 获取执行时执行的命令
     *
     * @param si   保存的文件信息
     * @param args 参数
     * @return 要执行的命令
     */
    public abstract String getExecuteCmd(SaveInfo si, Object[] args);

    public SaveInfo saveToFile(String source) {
        try {
            SaveInfo si = getSaveInfo(source);
            si.modify();
            String path = si.toSavePath();
            save(source, path);
            return si;
        } catch (Exception e) {
            logger.error(Throwables.getStackTraceAsString(e));
            return null;
        }
    }

    public static void save(String source, String path) throws IOException {
        File f = new File(path);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
        os.write(source.getBytes());
        os.flush();
    }

    private SaveInfo si;

    @Override
    public Result compile(String source) {
        si = saveToFile(source);
        if (si == null) {
            return new Result(false, "保存文件失败");
        }
        if (!needCompile()) {
            return new Engine.Result(true, "");
        }
        String cmd = getCompileCmd(si);
        Runner.Result r = runner.run(cmd);
        return convert(r);
    }
    @Override
    public Runner.Result doCompile(String source){
        si = saveToFile(source);
        if (si == null) {
            return new Runner.Result("", "保存文件失败");
        }
        if (!needCompile()) {
            return new Runner.Result("", "");
        }
        String cmd = getCompileCmd(si);
        Runner.Result r = runner.run(cmd);
        return r;
    }
    @Override
    public Runner.Result doExecute(Object[] args){
        String cmd = getExecuteCmd(si, args);
        Runner.Result r = runner.run(cmd);
        return r;
    }
    public boolean needCompile() {
        return true;
    }

    @Override
    public Result execute(Object[] args) {
        String cmd = getExecuteCmd(si, args);
        Runner.Result r = runner.run(cmd);
        return convert(r);
    }

    @Override
    public ExecuteResult compileAndExecute(String sourceCode, String[] args) {
        Result cr = compile(sourceCode);
        Result er = execute(args);
        return new ExecuteResult(cr, Collections.singletonList(er));
    }
}
