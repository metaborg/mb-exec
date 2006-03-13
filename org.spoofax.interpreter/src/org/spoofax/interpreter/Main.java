package org.spoofax.interpreter;

import java.io.IOException;

import aterm.ATerm;

public class Main {

    public static void main(String args[]) throws IOException {

        Interpreter itp = new Interpreter();
        String file = "";

        for(String s : args) 
            System.out.println(s);
        
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--debug")) {
                itp.setDebug(true);
            } else if (args[i].equals("-i")) {
                file = args[i + 1];
            }
        }

        try {
            itp.load(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FatalError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ATerm inp = itp.makeList("[]");
        try {
            itp.setCurrent(inp);
            itp.invoke("main_0_0");
            System.out.println("" + itp.current());
        } catch (FatalError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}