package org.spoofax.ecjadapter.tests;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.spoofax.ecjadapter.adapter.ECJFactory;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.PrettyPrinter;

/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */

public class SimpleTest {

    public static void main(String[] args) {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource("import java.util.List;\nclass X { static void main(String[] args) { int x = 1 + 0; System.out.println(\"Hello, World!\"); } }\n".toCharArray());
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        System.out.println(cu);
        ECJFactory wef = new ECJFactory();
        IStrategoTerm t = wef.parseFromTree(cu);
        PrettyPrinter pp = new PrettyPrinter();
        t.prettyPrint(pp);
        System.out.println(pp.getString());
    }
    
}
