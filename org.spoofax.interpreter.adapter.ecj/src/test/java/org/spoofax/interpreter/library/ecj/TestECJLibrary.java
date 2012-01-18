/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */

package org.spoofax.interpreter.library.ecj;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.junit.Before;
import org.junit.Test;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterErrorExit;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.InterpreterExit;
import org.spoofax.interpreter.core.UndefinedStrategyException;
import org.spoofax.terms.TermFactory;

/**
 * Must be run as Eclipse JUnit Plug-in test
 *
 */
public class TestECJLibrary {

    private ECJFactory wef;
    private Interpreter interp;

    @Before
    public void setUp() throws IOException, InterpreterException, CoreException {
        wef = new ECJFactory();
        interp = new Interpreter(wef, new TermFactory());
        ECJLibrary.attach(interp);
        interp.load(System.getProperty("user.home") + "/.nix-profile/share/stratego-lib/libstratego-lib.ctree");
        interp.load("target/resources/share/ecj-tests.ctree");
        ProjectUtils pu = new ProjectUtils();
        IJavaProject p = pu.createJavaProject("DummyProject", true);
        pu.createCompilationUnit(p.getProject(), "Foo.java", "package src; class Foo {}");
    }

    @Test
    public void open_parse_match_resolve_method_and_type() throws IOException, InterpreterException, CoreException {
        interp.setCurrent(wef.makeString("Foo.java"));
        assertTrue(interp.invoke("test_open_parse_match_resolve_method_and_type_0_0"));
    }
    
    @Test
    public void test_collect_all_ctors() throws InterpreterErrorExit, InterpreterExit, UndefinedStrategyException, InterpreterException {
        interp.setCurrent(wef.makeString("Foo.java"));
        assertTrue(interp.invoke("test_collect_all_ctors_0_0"));
    }

    @Test
    public void test_build_minimal_cu() throws InterpreterErrorExit, InterpreterExit, UndefinedStrategyException, InterpreterException {
        interp.setCurrent(wef.makeString("Foo.java"));
        assertTrue(interp.invoke("test_build_minimal_cu_0_0"));
    }

    @Test
    public void test_collect_all_typedeclarations() throws InterpreterErrorExit, InterpreterExit, UndefinedStrategyException, InterpreterException {
        interp.setCurrent(wef.makeString("Foo.java"));
        assertTrue(interp.invoke("test_collect_all_typedeclarations_0_0"));
    }

    @Test
    public void test_topdown_decontruct() throws InterpreterErrorExit, InterpreterExit, UndefinedStrategyException, InterpreterException {
        interp.setCurrent(wef.makeString("Foo.java"));
        assertTrue(interp.invoke("test_topdown_decontruct_0_0"));
    }

    @Test
    public void test_topdown_id() throws InterpreterErrorExit, InterpreterExit, UndefinedStrategyException, InterpreterException {
        interp.setCurrent(wef.makeString("Foo.java"));
        assertTrue(interp.invoke("test_topdown_id_0_0"));
    }
    
    
    
    
    @Test
    public void open_parse_match_resolve_method() throws IOException, InterpreterException, CoreException {
        interp.setCurrent(wef.makeString("Foo.java"));
        assertTrue(interp.invoke("test_open_parse_match_resolve_method_0_0"));
    }

    @Test
    public void open_parse_match_resolve_type() throws IOException, InterpreterException, CoreException {
        interp.setCurrent(wef.makeString("Foo.java"));
        assertTrue(interp.invoke("test_open_parse_match_resolve_type_0_0"));
    }

    @Test
    public void open_parse_then_topdown_match_typedeclaration() throws IOException, InterpreterException, CoreException {
        interp.setCurrent(wef.makeString("Foo.java"));
        assertTrue(interp.invoke("test_parse_then_topdown_match_typedeclaration_0_0"));
    }
}
