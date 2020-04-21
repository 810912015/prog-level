package com.pl.portal.service.runner.engine;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 控制台流重定向
 */
public abstract class SysStreamRedirector {
    ByteArrayOutputStream baos;
    PrintStream ps;
    public SysStreamRedirector() {
        baos=new ByteArrayOutputStream();
        try {
            ps = new PrintStream(baos, true, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public abstract void set();
    public abstract void reset();
    public String getString(){
        String content = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        ps.close();
        return content;
    }

    public static class OutRedirector extends SysStreamRedirector{

        @Override
        public void set() {
            System.setOut(ps);
        }

        @Override
        public void reset() {
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        }
    }

    public static class ErrRedirector extends SysStreamRedirector{

        @Override
        public void set() {
           System.setErr(ps);
        }

        @Override
        public void reset() {
           System.setErr(new PrintStream(new FileOutputStream(FileDescriptor.err)));
        }
    }
}
