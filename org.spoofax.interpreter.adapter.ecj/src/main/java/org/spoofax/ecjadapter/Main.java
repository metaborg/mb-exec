/*
 * Created on 27. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.ecj.ECJLibrary;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.io.InlinePrinter;

public class Main {

    public static void main(String[] args) throws IOException, InterpreterException {
        
        String[] files = null;
        List<String> actualArgs = new LinkedList<String>();
        
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--debug")) {
                DebugUtil.setDebug(true);
            } else if (args[i].equals("-i")) {
                files = args[i + 1].split(",");
            } else if (args[i].equals("/trace")) {
                DebugUtil.tracing = true;
            } else {
                actualArgs.add(args[i]);
            }
        }

        if(files == null) {
            System.err.println("Usage: ecj-shell [--debug] -i program.ctree");
            System.exit(2);
        }
        
        ITermFactory data = new ECJFactory();
        ITermFactory program = new TermFactory();
        Interpreter intp = new Interpreter(data, program);
        intp.addOperatorRegistry(new ECJLibrary());
        for(String f : files)
            intp.load(f);
        
        // Compute parameters
        IStrategoTerm[] finalArgs = new IStrategoTerm[actualArgs.size()];
        for(int i = 0; i < actualArgs.size(); i++)
            finalArgs[i] = data.makeString(actualArgs.get(i));
        intp.setCurrent(data.makeList(finalArgs));
        
        if(!intp.invoke("main_0_0")) {
            System.err.println("Rewriting failed");
            System.exit(2);
        }
        ITermPrinter pp = new InlinePrinter();
        intp.current().prettyPrint(pp);
        System.out.println(pp.getString());
    }

}
