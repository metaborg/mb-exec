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
		super.setUp("tests/data");
		itp.addOperatorRegistry(new XMLLibrary());
	}

	public void testParseXml() throws IOException, InterpreterException {
		interpTest("parse-xml-test", "(22,0)");
	}

	public void interpTest(String test, String result) throws IOException, InterpreterException {
		super.interpTest(test, "()", result);
	}

}
