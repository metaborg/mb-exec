/*
 * Copyright (c) 2011-2012, Tobi Vollebregt
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.xml.test;

import java.io.IOException;

import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.xml.XMLLibrary;
import org.spoofax.interpreter.test.AbstractInterpreterTest;

/**
 * Test harness for the XMLLibrary.
 * Based on: org.spoofax.interpreter.test.library.LibraryTest
 *
 * @author Tobi Vollebregt
 */
public class XMLLibraryTest extends AbstractInterpreterTest {

	@Override
	protected void setUp() throws Exception {
		super.setUp("target/resources/share");
		itp.addOperatorRegistry(new XMLLibrary());
		itp.load("target/resources/share/libstratego-lib.ctree");
	}

	public void testParseXml() throws IOException, InterpreterException {
		interpTest("sax-tests", "(22,0)");
	}

	public void interpTest(String test, String result) throws IOException, InterpreterException {
		super.interpTest(test, "()", result);
	}

}
