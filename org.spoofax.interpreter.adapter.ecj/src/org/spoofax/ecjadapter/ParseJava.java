/*
 * Created on 27. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.spoofax.DebugUtil;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.InlinePrinter;

public class ParseJava {

    public static void main(String[] args) throws IOException, InterpreterException {
        
    	String inputFile = null;
        
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--debug")) {
                DebugUtil.setDebug(true);
            } else if (args[i].equals("-i")) {
                inputFile = args[i + 1];
            } 
        }

        if(inputFile == null) {
            System.err.println("Usage: ecj-parse-java [--debug] -i file.java");
            System.exit(2);
        }

        IStrategoTerm r = parseJava(inputFile);
        if(r == null) {
        	System.err.println("Failed to open and read file " + inputFile);
        	System.exit(3);
        }

        InlinePrinter ip = new InlinePrinter();
        r.prettyPrint(ip);
        System.out.println(ip.getString());
    }
 
    private static IStrategoTerm parseJava(String fileName) throws FileNotFoundException, IOException {
    
    	ASTParser parser = ASTParser.newParser(AST.JLS3);
    	parser.setSource(getBytes(fileName));
    
    	ASTNode ast = parser.createAST(null);
    	ECJFactory f = new ECJFactory();
    	return f.parseFromTree(ast);
    }

    private static char[] getBytes(String fileName) throws FileNotFoundException, IOException {

        BufferedReader r = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String s = r.readLine();
        while(s != null) {
            sb.append(s);
            s = r.readLine();
        }
            
        return sb.toString().toCharArray();
    }

}
