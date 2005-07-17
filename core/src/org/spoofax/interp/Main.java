package org.spoofax.interp;

import java.io.IOException;
import java.util.List;

import aterm.ATerm;
import aterm.pure.PureFactory;

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

        ATerm r = itp.makeList("[1,2,3]");
        System.out.println(r);
        ATerm s = itp.makeTuple("[1,2,3]");
        System.out.println(s);
        
        /*
        itp.load(prg);
        try {
            if (itp.eval(itp.makeTerm("CallT(SVar(\"main_0_0\"), [], [])"))) {
                System.out.println("result : " + itp.getCurrent());
            } else {
                System.out.println("result : evaluation failed!");
            }
        } catch (FatalError e) {
            e.printStackTrace();
        }
        */
    }
}