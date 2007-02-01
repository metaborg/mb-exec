/*
 * Created on 27. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter;

import java.io.IOException;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.adapter.aterm.WrappedATermFactory;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.interpreter.terms.InlinePrinter;

public class Main {

    public static void main(String[] args) throws IOException, InterpreterException {
        
        String[] files = null;
        
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--debug")) {
                DebugUtil.setDebug(true);
            } else if (args[i].equals("-i")) {
                files = args[i + 1].split(",");
            } else if (args[i].equals("/trace")) {
                DebugUtil.tracing = true;
            }
        }

        if(files == null) {
            System.err.println("Usage: ecj-shell [--debug] -i program.ctree");
            System.exit(2);
        }
        
        ITermFactory data = new ECJFactory();
        ITermFactory program = new WrappedATermFactory();
        Interpreter intp = new Interpreter(data, program);
        for(String f : files)
            intp.load(f);
        
        if(!intp.invoke("main_0_0")) {
            System.err.println("Rewriting failed");
            System.exit(2);
        }
        ITermPrinter pp = new InlinePrinter();
        intp.current().prettyPrint(pp);
        System.out.println(pp.getString());
    }

}
