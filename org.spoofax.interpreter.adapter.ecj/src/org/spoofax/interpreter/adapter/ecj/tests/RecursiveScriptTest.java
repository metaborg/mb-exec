/*
 * Created on 24. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.spoofax.interpreter.adapter.aterm.WrappedATermFactory;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.ecj.ECJLibrary;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class RecursiveScriptTest {

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

    void parse(String file) throws FileNotFoundException, IOException, InterpreterException {
        System.out.println("Reading " + file);
        parser.setSource(getBytes(file));
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        //System.out.println(cu);
        IStrategoTerm from = dataFactory.parseFromTree(cu);
        //ITermPrinter pp = new PrettyPrinter();
        //from.prettyPrint(pp);
        //System.out.println(pp.getString());
        dataFactory.setAST(cu.getAST());
        interp.setCurrent(from);
        if(interp.invoke("main_0_0") == false)
            System.out.println("Rewriting failed");
        //IStrategoTerm to = interp.current();
        //from.prettyPrint(pp);
        //to.prettyPrint(pp);
        //System.out.println(to.getClass());
        //System.out.println(pp.getString());
        
        //System.out.println(((WrappedASTNode)to).getWrappee().toString());
    }
    
    void recurse(File base) throws FileNotFoundException, IOException, InterpreterException {
        for(String s : base.list()) {
            if(s.endsWith(".java"))
                parse(base.getAbsolutePath() + "/" + s);
            else {
                File x = new File(base.getAbsolutePath() + "/" + s);
                if(x.isDirectory())
                    recurse(x);
            }
            //System.out.println(s);
        }
        
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException, InterpreterException {
        RecursiveScriptTest rst = new RecursiveScriptTest("scripts/for-check.ctree");
        rst.recurse(new File(args[0]));
        System.out.println("Finished");
    }

    private ECJFactory dataFactory;
    private WrappedATermFactory programFactory;
    private Interpreter interp;
    private ASTParser parser; 
    
    RecursiveScriptTest(String script) {
        programFactory = new WrappedATermFactory();
        dataFactory = new ECJFactory();
        interp = new Interpreter(dataFactory, programFactory);
        interp.addOperatorRegistry(ECJLibrary.REGISTRY_NAME, new ECJLibrary());
        parser = ASTParser.newParser(AST.JLS3);
        try {
            interp.load(script);
        } catch(IOException e) {
            e.printStackTrace();
        } catch (InterpreterException e) {
            e.printStackTrace();
        }
    }
    
}
