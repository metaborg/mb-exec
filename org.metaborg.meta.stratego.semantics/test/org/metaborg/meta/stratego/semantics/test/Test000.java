package org.metaborg.meta.stratego.semantics.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.metaborg.meta.stratego.semantics.StrategoCoreInterpreter;
import org.spoofax.terms.TermFactory;

public class Test000 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws IOException {
		StrategoCoreInterpreter interpreter = new StrategoCoreInterpreter();
		interpreter.reset();

		// load libstratego-lib
		interpreter.loadCTree(this.getClass().getClassLoader().getResourceAsStream("libstratego-lib.ctree"));
		// load program
		interpreter.loadCTree(this.getClass().getClassLoader().getResourceAsStream("test000.ctree"));

		interpreter.setCurrentTerm(new TermFactory().makeInt(42));

		interpreter.invoke("main_0_0");

		assertEquals(new TermFactory().makeInt(42), interpreter.getCurrentTerm());
	}

}
