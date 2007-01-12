package org.spoofax.interpreter;

import java.io.IOException;

import org.spoofax.DebugUtil;

public class Main {

    public static void main(String args[]) throws IOException {

        Interpreter itp = new Interpreter();
        String[] files = null;
        boolean waitForProfiler = false;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--debug")) {
                DebugUtil.setDebug(true);
            } else if (args[i].equals("-i")) {
                files = args[i + 1].split(",");
            } else if (args[i].equals("--wait-for-profiler")) {
                waitForProfiler = true;
            }
        }

        try {
            for(String fn : files) {
                System.out.println("Loading " + fn);
                itp.load(fn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterpreterException e) {
            e.printStackTrace();
        }

        try {
            itp.setCurrent(itp.getFactory().makeList());
            
            boolean r = itp.invoke("main_0_0");
            if(r) {
                System.out.println("" + itp.current());
            } else {
                System.err.println("rewriting failed");
                System.exit(-1);
            }
        } catch (InterpreterException e) {
            e.printStackTrace();
        }
        
        if(waitForProfiler)
            System.in.read();
    }
}