package com.pl.admin.service.runner;

import com.google.common.base.Throwables;
import com.pl.admin.service.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.pl.admin.service.runner.BaseRunnerEngine.convert;


public class LangRunners {
    private LangRunners() {
    }

    static BaseRunnerEngine.SaveInfo makeSaveInfo(String ext, String name, String base) {
        BaseRunnerEngine.SaveInfo si = new BaseRunnerEngine.SaveInfo();
        si.setExtension(ext);
        si.setName(name);
        si.setPath(base + UUID.randomUUID().toString());
        return si;
    }

    static String writeArgs(String cmd, Object[] args) {
        if (args == null || args.length == 0) {
            return cmd;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cmd);
        for (int i = 0; i < args.length; i++) {
            sb.append(" ");
            sb.append(args[i].toString());
        }
        return sb.toString();
    }

    @Component
    @Order(2)
    @Scope("prototype")
    public static class C extends BaseRunnerEngine {
        private static final String BASE_DIR = "/usr/upload-sources/c/";
        private static final String EXTENSION = "c";
        private static final String COMPILE_FORMAT = "gcc -o solution %s";

        @Override
        public SaveInfo getSaveInfo(String source) {
            return makeSaveInfo(EXTENSION, "solution", BASE_DIR);
        }

        @Override
        public String getCompileCmd(SaveInfo si) {
            return String.format(COMPILE_FORMAT, si.toSavePath());
        }

        @Override
        public String getExecuteCmd(SaveInfo si, Object[] args) {
            return writeArgs("./solution ", args);
        }

        @Override
        public String getLang() {
            return "c";
        }
    }

    @Component
    @Order(3)
    @Scope("prototype")
    public static class Cpp extends BaseRunnerEngine {

        private static final String BASE_DIR = "/usr/upload-sources/cpp/";
        private static final String EXTENSION = "cpp";
        private static final String COMPILE_FORMAT = "g++ -o solution %s";

        @Override
        public SaveInfo getSaveInfo(String source) {
            return makeSaveInfo(EXTENSION, "solution", BASE_DIR);
        }

        @Override
        public String getCompileCmd(SaveInfo si) {
            return String.format(COMPILE_FORMAT, si.toSavePath());
        }

        @Override
        public String getExecuteCmd(SaveInfo si, Object[] args) {
            return writeArgs("./solution", args);
        }

        @Override
        public String getLang() {
            return "c++";
        }
    }

    @Component
    @Order(4)
    @Scope("prototype")
    public static class Js extends BaseRunnerEngine {

        @Override
        public SaveInfo getSaveInfo(String source) {
            return makeSaveInfo("js", "solution", "/usr/upload-sources/js/");
        }

        @Override
        public String getCompileCmd(SaveInfo si) {
            return null;
        }

        @Override
        public String getExecuteCmd(SaveInfo si, Object[] args) {
            String s = writeArgs("node " + si.toSavePath(), args);
            return s;
        }

        @Override
        public boolean needCompile() {
            return false;
        }

        @Override
        public String getLang() {
            return "javascript";
        }
    }

    @Component
    @Order(5)
    @Scope("prototype")
    public static class Python extends BaseRunnerEngine {

        @Override
        public SaveInfo getSaveInfo(String source) {
            return makeSaveInfo("py", "solution", "/usr/upload-sources/python/");
        }

        @Override
        public String getCompileCmd(SaveInfo si) {
            return null;
        }

        @Override
        public String getExecuteCmd(SaveInfo si, Object[] args) {
            return writeArgs("python " + si.toSavePath(), args);
        }

        @Override
        public boolean needCompile() {
            return false;
        }

        @Override
        public String getLang() {
            return "python";
        }
    }

    @Component
    @Order(6)
    @Scope("prototype")
    /**
     * 微软的这个需要csproj文件,需要执行多个命令,因此不能直接集成baseRunnerEngine
     */
    public static class Csharp implements Engine {
        protected Logger logger = LoggerFactory.getLogger(getClass());
        private Runner runner;
        protected String uid;

        public Csharp(Runner runner, String uid) {
            this.runner = runner;
            this.uid = uid;
        }

        public Csharp() {
            this(new ConsoleRunner(), UUID.randomUUID().toString());
        }

        public void saveToFile(String source) {
            try {
                si = getSaveInfo(source);
                si.modify();
                BaseRunnerEngine.save(source, si.toSavePath());
                String proj = "<Project Sdk=\"Microsoft.NET.Sdk\">\n" +
                        "\n" +
                        "  <PropertyGroup>\n" +
                        "    <OutputType>Exe</OutputType>\n" +
                        "    <TargetFramework>netcoreapp2.2</TargetFramework>\n" +
                        "  </PropertyGroup>\n" +
                        "\n" +
                        "</Project>";

                BaseRunnerEngine.save(proj, si.getPath() + "/myapp.csproj");
            } catch (IOException e) {
                logger.error("保存csproj:" + Throwables.getStackTraceAsString(e));
            }
        }

        public BaseRunnerEngine.SaveInfo getSaveInfo(String source) {
            return makeSaveInfo("cs", "Program", "/usr/upload-sources/csharp/");
        }


        public List<String> getCompileCmd(BaseRunnerEngine.SaveInfo si) {
            return Arrays.asList("cd " + si.getPath(), " dotnet build");
        }


        public List<String> getExecuteCmd(BaseRunnerEngine.SaveInfo si, Object[] args) {

            return Arrays.asList("cd " + si.getPath(), writeArgs(" dotnet run ", args));
        }

        @Override
        public String getLang() {
            return "c#";
        }

        private BaseRunnerEngine.SaveInfo si;

        @Override
        public Result compile(String source) {
            saveToFile(source);
            if (si == null) {
                return new Result(false, "保存文件失败");
            }

            List<String> cmd = getCompileCmd(si);
            Runner.Result r = runner.run(cmd);
            return convert(r);
        }

        @Override
        public Runner.Result doCompile(String source) {
            saveToFile(source);
            if (si == null) {
                return new Runner.Result("", "保存文件失败");
            }

            List<String> cmd = getCompileCmd(si);
            Runner.Result r = runner.run(cmd);
            return r;
        }

        @Override
        public Result execute(Object[] args) {
            List<String> cmd = getExecuteCmd(si, args);
            Runner.Result r = runner.run(cmd);
            return convert(r);
        }

        @Override
        public Runner.Result doExecute(Object[] args) {
            List<String> cmd = getExecuteCmd(si, args);
            Runner.Result r = runner.run(cmd);
            return r;
        }

        @Override
        public ExecuteResult compileAndExecute(String sourceCode, String[] args) {
            Result cr = compile(sourceCode);
            Result er = execute(args);
            return new ExecuteResult(cr, Collections.singletonList(er));
        }
    }

    @Component
    @Order(7)
    @Scope("prototype")
    public static class Go extends BaseRunnerEngine {

        @Override
        public SaveInfo getSaveInfo(String source) {
            return makeSaveInfo("go", "solution", "/usr/upload-sources/go/");
        }

        @Override
        public String getCompileCmd(SaveInfo si) {
            return null;
        }

        @Override
        public String getExecuteCmd(SaveInfo si, Object[] args) {
            // 这里路径是因为某种原因找不到go
            return writeArgs("go run " + si.toSavePath(), args);
        }

        @Override
        public boolean needCompile() {
            return false;
        }

        @Override
        public String getLang() {
            return "go";
        }
    }
}
