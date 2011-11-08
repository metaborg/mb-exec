/*
 * Created on 24. jan.. 2007
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Before;
import org.junit.Test;
import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.TermFactory;

public class TestScripting {

    private ECJFactory wef;
    private Interpreter interp;

    @Before
    public void setUp() {
        wef = new ECJFactory();
        interp = new Interpreter(wef, new TermFactory());
    }

    private CompilationUnit parseCompilationUnit() {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource("class Foo { /** foo */ void dataInvariant() {} int f() { return 0; } }".toCharArray());
        return (CompilationUnit) parser.createAST(null);
    }
    
    private void setupData() {
        CompilationUnit cu = parseCompilationUnit();
        wef.setAST(cu.getAST());
        interp.setCurrent(wef.parseFromTree(cu));
    }

    @Test
    public void traverse_topdown_id() throws IOException, InterpreterException {
        interp.load("bin/topdown-id.ctree");
        setupData();
        assertTrue(interp.invoke("main_0_0"));
    }

    @Test
    public void traverse_topdown_deconstruct() throws IOException, InterpreterException {
        interp.load("bin/topdown-deconstruct.ctree");
        setupData();
        assertTrue(interp.invoke("main_0_0"));
    }

    @Test
    public void collect_all_typedeclarations() throws IOException, InterpreterException {
        interp.load("bin/collect-all-typedeclarations.ctree");
        setupData();
        assertTrue(interp.invoke("main_0_0"));
        assertListLongerThan(0);
    }

    private void assertListLongerThan(int minLength) {
        IStrategoList t = (IStrategoList) interp.current();
        assertTrue(t.getSubtermCount() > minLength);
    }

    @Test
    public void collect_all_ctors() throws IOException, InterpreterException {
        interp.load("bin/collect-all-ctors.ctree");
        setupData();
        assertTrue(interp.invoke("main_0_0"));
        assertListLongerThan(10);
    }

    @Test
    public void build_minimal_cu() throws IOException, InterpreterException {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource("".toCharArray());
        wef.setAST(parser.createAST(null).getAST());
        interp.setCurrent(wef.makeInt(0));
        interp.load("bin/build-minimal-cu.ctree");
        assertTrue(interp.invoke("main_0_0"));
        IStrategoTerm t = interp.current();
        assertTrue(t instanceof WrappedCompilationUnit);
        assertEquals(ASTNode.COMPILATION_UNIT, ((WrappedCompilationUnit)t).getWrappee().getNodeType());
    }
}
