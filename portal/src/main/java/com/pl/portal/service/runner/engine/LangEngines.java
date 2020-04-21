package com.pl.portal.service.runner.engine;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


public class LangEngines {
    private LangEngines() {
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
            return SaveInfo.create(EXTENSION, "solution", BASE_DIR);
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
            return SaveInfo.create(EXTENSION, "solution", BASE_DIR);
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
            return SaveInfo.create("js", "solution", "/usr/upload-sources/js/");
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
            return SaveInfo.create("py", "solution", "/usr/upload-sources/python/");
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
    @Order(7)
    @Scope("prototype")
    public static class Go extends BaseRunnerEngine {

        @Override
        public SaveInfo getSaveInfo(String source) {
            return SaveInfo.create("go", "solution", "/usr/upload-sources/go/");
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
