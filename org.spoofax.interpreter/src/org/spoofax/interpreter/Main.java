package org.spoofax.interpreter;

import java.io.IOException;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class Main {

    public static void main(String args[]) throws IOException {

        Interpreter itp = new Interpreter();
        String file = "";
        boolean waitForProfiler = false;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--debug")) {
                DebugUtil.setDebug(true);
            } else if (args[i].equals("-i")) {
                file = args[i + 1];
            } else if (args[i].equals("--wait-for-profiler")) {
                waitForProfiler = true;
            }
        }

        try {
            itp.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterpreterException e) {
            e.printStackTrace();
        }

        IStrategoTerm inp = itp.getFactory().makeList();
        try {
            itp.setCurrent(inp);
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