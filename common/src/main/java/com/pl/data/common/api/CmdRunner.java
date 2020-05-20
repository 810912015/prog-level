package com.pl.data.common.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;

public class CmdRunner {

    public static void runCmd(String cmd){
        CompletableFuture.runAsync(()->{
            try {
                Runtime runtime = Runtime.getRuntime();
                try {
                    Process process = runtime.exec(cmd);
                    BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    BufferedReader stderrReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    String line;
                    System.out.println("OUTPUT");
                    while ((line = stdoutReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    System.out.println("ERROR");
                    while ((line = stderrReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    int exitVal = process.waitFor();
                    System.out.println("process exit value is " + exitVal);
                } catch (IOException e) {
                    System.out.println(e);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
}
