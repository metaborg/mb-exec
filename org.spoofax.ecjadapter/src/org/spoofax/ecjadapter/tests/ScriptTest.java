/*
 * Created on 24. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.tests;

import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.spoofax.ecjadapter.adapter.ECJFactory;
import org.spoofax.ecjadapter.adapter.WrappedASTNode;
import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.interpreter.terms.PrettyPrinter;

public class ScriptTest {

    private static void setupData(ECJFactory wef, Interpreter intp) {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource("import java.util.List;\nclass X { int f() { return 0; } }\n".toCharArray());
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        System.out.println(cu);
        wef.setAST(cu.getAST());
        intp.setCurrent(wef.parseFromTree(cu));
    }
    
    public static void main(String[] args) {
        
        ECJFactory factory = new ECJFactory();
        Interpreter interp = new Interpreter(factory);
        try {
            interp.load("scripts/rewrite-return.ctree");
            setupData(factory, interp);
            interp.invoke("main_0_0");
            IStrategoTerm t = interp.current();
            ITermPrinter pp = new PrettyPrinter();
            t.prettyPrint(pp);
            System.out.println(t.getClass());
            System.out.println(pp.getString());
            System.out.println(((WrappedASTNode)interp.current()).getWrappee().toString());
        } catch(IOException e) {
            e.printStackTrace();
        } catch (InterpreterException e) {
            e.printStackTrace();
        }
    }
    
}
