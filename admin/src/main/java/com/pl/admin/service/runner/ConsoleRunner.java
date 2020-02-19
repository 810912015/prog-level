package com.pl.admin.service.runner;



import com.google.common.base.Throwables;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * 控制台执行器
 */
public class ConsoleRunner implements Runner {
    static ExecutorService es=new ThreadPoolExecutor(5,5,1000, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),
            new ThreadFactoryBuilder().setNameFormat("runner-%s").build()
            );
    public Result run(String strCmd) {
        return inThread(()-> {
            try {
                Process process = Runtime.getRuntime().exec(strCmd);
                return new Result(write(process.getInputStream()),
                        write(process.getErrorStream()));
            } catch (Throwable e) {

                return new Result("", Throwables.getStackTraceAsString(e));
            }
        });
    }

    private Result inThread(Supplier<Result> c){
        try {
            return CompletableFuture.supplyAsync(c).get(5,TimeUnit.SECONDS);
        }catch (Exception e){
            return new Result("",Throwables.getStackTraceAsString(e));
        }
    }

    private String write(InputStream ips) throws IOException {
        BufferedReader strCon = new BufferedReader(new InputStreamReader(ips));
        StringBuilder sb=new StringBuilder();
        String line;
        while ((line = strCon.readLine()) != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public Result run(List<String> commands) {
        return inThread(()-> {
            List<String> rspList = new ArrayList<String>();
            Runtime run = Runtime.getRuntime();
            try {
                Process proc = run.exec("/bin/bash", null, null);
                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
                for (String line : commands) {
                    out.println(line);
                }
                out.println("exit");

                proc.waitFor();
                Result r = new Result(write(proc.getInputStream()),
                        write(proc.getErrorStream()));
                in.close();
                out.close();
                proc.destroy();
                return r;
            } catch (Exception e1) {
                return new Result("", Throwables.getStackTraceAsString(e1));
            }
        });
    }

}
