package org.metaborg.meta.stratego.semantics.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.metaborg.meta.stratego.interpreter.StrategoCoreInterpreter;
import org.metaborg.meta.stratego.interpreter.StrategoInterpreter;
import org.spoofax.terms.TermFactory;

public class Test000 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws IOException, URISyntaxException {
		StrategoInterpreter interpreter = new StrategoCoreInterpreter();

		File dirFile = new File(this.getClass().getClassLoader()
				.getResource(".").toURI());

		// load libstratego-lib
		interpreter.load(new File(dirFile, "libstratego-lib.ctree").toPath());
		// load program
		interpreter.load(new File(dirFile, "test000.ctree").toPath());

		interpreter.setCurrentTerm(new TermFactory().makeInt(42));

		interpreter.invoke("main_0_0");

		assertEquals(new TermFactory().makeInt(42),
				interpreter.getCurrentTerm());
	}

}
