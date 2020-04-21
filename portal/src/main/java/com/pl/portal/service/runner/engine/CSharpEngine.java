package com.pl.portal.service.runner.engine;

import com.google.common.base.Throwables;
import com.pl.portal.service.runner.ConsoleRunner;
import com.pl.portal.service.runner.Engine;
import com.pl.portal.service.runner.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
@Order(6)
@Scope("prototype")
/**
 * 微软的这个需要csproj文件,需要执行多个命令,因此不能直接集成baseRunnerEngine
 */
public class CSharpEngine implements Engine {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private Runner runner;
    protected String uid;

    public CSharpEngine(Runner runner, String uid) {
        this.runner = runner;
        this.uid = uid;
    }

    public CSharpEngine() {
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

    public SaveInfo getSaveInfo(String source) {
        return SaveInfo.create("cs", "Program", "/usr/upload-sources/csharp/");
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
        return r.convert();
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
        return r.convert();
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
