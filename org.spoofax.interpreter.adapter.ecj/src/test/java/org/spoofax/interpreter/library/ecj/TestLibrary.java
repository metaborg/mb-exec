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
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.terms.TermFactory;

/**
 * Must be run as Eclipse JUnit Plug-in test
 *
 */
public class TestLibrary {

    private ECJFactory wef;
    private Interpreter interp;

    @Before
    public void setUp() throws IOException, InterpreterException, CoreException {
        wef = new ECJFactory();
        interp = new Interpreter(wef, new TermFactory());
        ECJLibrary.attach(interp);
        interp.load(System.getenv("HOME") + "/.nix-profile/share/stratego-lib/libstratego-lib.ctree");
        ProjectUtils pu = new ProjectUtils();
        IJavaProject p = pu.createDummyJavaProject();
        pu.createCompilationUnit(p.getProject(), "Foo.java", "package src; class Foo {}");
    }

    @Test
    public void open_parse_match_resolve_method_and_type() throws IOException, InterpreterException, CoreException {
        interp.load(TestLibrary.class.getResourceAsStream("/api-open-parse-match-resolve-method-and-type.ctree"));
        interp.setCurrent(wef.makeString("Foo.java"));
        assertTrue(interp.invoke("main_0_0"));
    }

    @Test
    public void open_parse_match_resolve_method() throws IOException, InterpreterException, CoreException {
        interp.load(TestLibrary.class.getResourceAsStream("/api-open-parse-match-resolve-method.ctree"));
        interp.setCurrent(wef.makeString("Foo.java"));
        assertTrue(interp.invoke("main_0_0"));
    }

    @Test
    public void open_parse_match_resolve_type() throws IOException, InterpreterException, CoreException {
        interp.load(TestLibrary.class.getResourceAsStream("/api-open-parse-match-resolve-type.ctree"));
        interp.setCurrent(wef.makeString("Foo.java"));
        assertTrue(interp.invoke("main_0_0"));
    }

    @Test
    public void open_parse_then_topdown_match_typedeclaration() throws IOException, InterpreterException, CoreException {
        interp.load(TestLibrary.class.getResourceAsStream("/api-parse-then-topdown-match-typedeclaration.ctree"));
        interp.setCurrent(wef.makeString("Foo.java"));
        assertTrue(interp.invoke("main_0_0"));
    }

}
