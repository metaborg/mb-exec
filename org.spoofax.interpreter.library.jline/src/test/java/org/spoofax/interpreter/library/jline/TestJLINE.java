/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.jline;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;

public class TestJLINE {

	@Test
	public void test_jline_repl() throws IOException, InterpreterException {
		Interpreter intp = new Interpreter();
		intp.addOperatorRegistry(new JLINELibrary());
		intp.load("jline-test.ctree");
		intp.setCurrent(intp.getFactory().makeTuple());
		assertTrue("Strategy failed", intp.invoke("main_0_0"));
	}
}
