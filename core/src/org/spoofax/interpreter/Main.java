package org.spoofax.interpreter;

import java.io.IOException;

import aterm.ATerm;

public class Main {

    public static void main(String args[]) throws IOException {

        Interpreter itp = new Interpreter();

        try {
            itp.load(args[0]);
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
            itp.invoke("main_0_0", null, null);
            System.out.println("" + itp.getCurrent());
        } catch (FatalError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}