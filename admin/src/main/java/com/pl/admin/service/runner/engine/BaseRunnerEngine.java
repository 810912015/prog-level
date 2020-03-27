package com.pl.admin.service.runner.engine;

import com.google.common.base.Throwables;
import com.pl.admin.service.runner.ConsoleRunner;
import com.pl.admin.service.runner.Engine;
import com.pl.admin.service.runner.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        os.write(JavaEngine.trim160(source).getBytes());
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
        return r.convert();
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
        return r.convert();
    }

    @Override
    public ExecuteResult compileAndExecute(String sourceCode, String[] args) {
        Result cr = compile(sourceCode);
        Result er = execute(args);
        return new ExecuteResult(cr, Collections.singletonList(er));
    }
}
