package com.pl.portal.service.runner;



import java.util.List;
import java.util.UUID;

/**
 * 语言执行引擎:对输入的字符串进行编译,执行
 */
public interface Engine {
    /**
     * 结果
     */
    static class Result{
        private boolean success;
        private String msg;

        @Override
        public String toString() {
            return "{ success=" + success +
                    ", msg='" + msg + '\''+"}";
        }

        public Result(boolean success, String msg) {
            this.success = success;
            this.msg = msg;
        }

        public Result() {
            this.msg="";
            this.success=true;
        }

        public Result(String msg) {
            this.msg = msg;
            this.success=false;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    /**
     * 编译和执行结果
     */
    static class ExecuteResult{
        private Result compile;
        private List<Result> test;

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        private String gid;

        public ExecuteResult(Result compile, List<Result> test) {
            this.compile = compile;
            this.test = test;
            this.gid= UUID.randomUUID().toString();
        }

        public Result getCompile() {
            return compile;
        }

        public void setCompile(Result compile) {
            this.compile = compile;
        }

        public List<Result> getTest() {
            return test;
        }

        public void setTest(List<Result> test) {
            this.test = test;
        }

        @Override
        public String toString() {
            return "ExecuteResult{" +
                    "compile=" + compile +
                    ", test=" + test +
                    ", gid='" + gid + '\'' +
                    '}';
        }


    }

    String getLang();

    /**
     * 编译
     * @param source 源码字符串
     * @return 编译结果
     */
    Result compile(String source);

    Runner.Result doCompile(String source);

    /**
     * 执行
     * @param args 参数
     * @return 执行结果
     */
    Result execute(Object[] args);
    Runner.Result doExecute(Object[] args);
    /**
     * 编译和执行
     * @param sourceCode 源码
     * @param args 参数
     * @return 结果
     */
    ExecuteResult compileAndExecute(String sourceCode, String[] args);


    default String writeArgs(String cmd, Object[] args) {
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
    /**
     * 文件保存信息,注意扩展名不带".",路径最后不带/.
     */
    class SaveInfo {
        private String path;
        private String name;
        private String extension;

        public static SaveInfo create(String ext, String name, String base) {
            SaveInfo si = new SaveInfo();
            si.setExtension(ext);
            si.setName(name);
            si.setPath(base + UUID.randomUUID().toString());
            return si;
        }

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
            if(s.contains("\\")){
                this.path=this.path.replace("/","\\");
                if(this.path.startsWith("/")){
                    this.path=this.path.substring(1);
                }
            }
        }
    }
}
