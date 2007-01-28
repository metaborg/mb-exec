package org.spoofax.interpreter.library.ecj;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.terms.IStrategoTerm;

/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */

public class ECJParseFile {

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

    static void parse(String prg, String file) throws FileNotFoundException, IOException, InterpreterException {

        Interpreter itp = new Interpreter(new ECJFactory());
        itp.addOperatorRegistry(ECJLibrary.REGISTRY_NAME, new ECJLibrary());
        itp.load(prg);
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource(getBytes(file));
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        //System.out.println(cu);
        ECJFactory wef = new ECJFactory();
        IStrategoTerm t = wef.parseFromTree(cu);
        itp.setCurrent(t);
        itp.invoke("main_0_0");

    }
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterpreterException {
       if(args.length > 1)
            parse(args[0], args[1]);
        else
            parse("str/parse-and-dump.rtree", args[0]);
    }
    
}
