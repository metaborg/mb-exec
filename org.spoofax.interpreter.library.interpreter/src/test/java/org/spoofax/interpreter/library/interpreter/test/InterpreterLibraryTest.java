/*
 * Copyright (c) 2012, Tobi Vollebregt
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.interpreter.test;

import java.io.IOException;

import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.interpreter.InterpreterLibrary;
import org.spoofax.interpreter.test.AbstractInterpreterTest;

/**
 * Test harness for the InterpreterLibrary.
 * Based on: org.spoofax.interpreter.test.library.LibraryTest
 *
 * @author Tobi Vollebregt
 */
public class InterpreterLibraryTest extends AbstractInterpreterTest {

	@Override
	protected void setUp() throws Exception {
		super.setUp("target/resources/share");
		itp.addOperatorRegistry(new InterpreterLibrary());
		itp.load("target/resources/share/libstratego-lib.ctree");
	}

	public void testInterpreter() throws IOException, InterpreterException {
		interpTest("interpreter-tests", "(19,0)");
	}

	public void interpTest(String test, String result) throws IOException, InterpreterException {
		super.interpTest(test, "()", result);
	}

}
