package com.pl.admin.service.runner;


import com.alibaba.druid.sql.visitor.functions.Char;
import com.pl.admin.service.JavaEngine;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Scope("prototype")
public class JavaRunner extends BaseRunnerEngine {

    public JavaRunner(String uid) {
        super(new ConsoleRunner(), uid);
    }

    public JavaRunner() {
    }

    private static final String BASE_DIR = "/usr/upload-sources/java/";
    private static final String EXTENSION = "java";
    private static final String COMPILE_FORMAT = "javac %s";
    private static final String EXECUTE_START = "java -cp ";

    @Override
    public SaveInfo getSaveInfo(String source) {
        SaveInfo si = new SaveInfo();

        si.setName(trim160(JavaEngine.StringSourceJavaObject.getClassName(source)));
        si.setExtension(EXTENSION);
        si.setPath(BASE_DIR + uid);
        return si;
    }

    public static String trim160(String source){
        char c=(char)160;
        char s=(char)32;
        return source.replace(c,s).trim();
    }

    @Override
    public String getCompileCmd(SaveInfo si) {
        return String.format(COMPILE_FORMAT, si.toSavePath());
    }

    @Override
    public String getExecuteCmd(SaveInfo si, Object[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(EXECUTE_START);
        sb.append(si.getPath());
        sb.append("/ ");
        sb.append(si.getName());
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                sb.append(" ");
                sb.append(args[i]);
            }
        }
        return sb.toString();
    }

    @Override
    public String getLang() {
        return "java";
    }
}
