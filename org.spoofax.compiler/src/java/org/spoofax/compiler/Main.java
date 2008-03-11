package org.spoofax.compiler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.InlinePrinter;
import org.spoofax.jsglr.InvalidParseTableException;

public class Main {
    public static void main(String[] args) throws IOException, InterpreterException, InvalidParseTableException {
    
        List<String> includes = new LinkedList<String>();
        String toCompile = null;
        String outFile = null;
        int skip = 0;
        
        for(int i = 0; i < args.length; i++) {
            if(skip > 0) {
                skip--;
                continue;
            }
            final String arg = args[i];
            if(arg.equals("-I")) {
                skip++;
                includes.add(args[i+1]);
            } else if(arg.equals("-i")) {
                toCompile = args[i+1];
                skip++;
            } else if(args.equals("-o")) {
                outFile = args[i+1];
                skip++;
            }
            else if(arg.startsWith("-")) {
                System.err.println("Unknown option " + arg);
                System.exit(-12);
            } else {
                
            }
            
        }
        
        Compiler c = new Compiler();
        if(toCompile == null) {
        	System.err.println("No files to compile");
        	return;
        }
        IStrategoTerm t = c.compile(toCompile, includes.toArray(new String[0]), false);
        InlinePrinter ip = new InlinePrinter();
        t.prettyPrint(ip);
        OutputStream os = outFile == null ? System.out : new FileOutputStream(outFile);
        os.write(ip.getString().getBytes());
    }
}