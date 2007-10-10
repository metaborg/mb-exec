package org.spoofax.test;

import java.io.IOException;

import org.spoofax.test.CompilerTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllCompilerTests {

	public static Test suite() throws IOException
	{
		TestSuite suite = new TestSuite("Test for org.spoofax.interpreter.test");
		suite.addTestSuite(CompilerTest.class);
		return suite;
	}
}
