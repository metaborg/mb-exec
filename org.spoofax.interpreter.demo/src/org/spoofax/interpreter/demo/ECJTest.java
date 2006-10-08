package org.spoofax.interpreter.demo;


import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.adapters.ecj.ECJFactory;
import org.spoofax.interpreter.stratego.DebugUtil;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJTest {

    public static void main(String[] args) throws IOException, InterpreterException {
        Interpreter itp = new Interpreter(new ECJFactory());
        itp.load("str/test1.rtree");
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource("import java.util.List;\nclass X { static void main(String[] args) { int x = 1 + 0; System.out.println(\"Hello, World!\"); } }\n".toCharArray());
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        System.out.println(cu);
        ECJFactory wef = new ECJFactory();
        IStrategoTerm t = wef.parseFromTree(cu);
        itp.setCurrent(t);
        //DebugUtil.debugging = true;
        itp.invoke("main_0_0");
    }
}
