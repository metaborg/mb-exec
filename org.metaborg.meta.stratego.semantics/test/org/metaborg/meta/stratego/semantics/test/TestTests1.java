package org.metaborg.meta.stratego.semantics.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.spoofax.terms.TermFactory;
import org.strategoxt.lang.StrategoErrorExit;

import ds.entry.interpreter.StrategoCoreInterpreter;

@RunWith(Parameterized.class)
public class TestTests1 {

	private String[] ctreePaths;
	private String strategyName;

	public TestTests1(TT testTarget) {
		this.ctreePaths = testTarget.ctrees;
		this.strategyName = testTarget.sName;
	}

	@Parameters(name = "{index}: {0}")
	public static Collection<TT[]> testTargets() {
		Collection<TT[]> tests = new ArrayList<>();
		// @formatter:off
		tests.addAll(Arrays.asList(new TT[][] {
				{ new TT("main_0_0", "libstratego-lib.ctree", "test01.ctree") },
				{ new TT("main_0_0", "libstratego-lib.ctree", "test02.ctree") },
				{ new TT("main_0_0", "libstratego-lib.ctree", "test03.ctree") },
				{ new TT("main_0_0", "libstratego-lib.ctree", "test04.ctree") },
				{ new TT("main_0_0", "libstratego-lib.ctree", "test05.ctree") },
				{ new TT("main_0_0", "libstratego-lib.ctree", "test06.ctree") },
				{ new TT("main_0_0", "libstratego-lib.ctree", "test07.ctree") },
				{ new TT("main_0_0", "libstratego-lib.ctree", "test08.ctree") },
				{ new TT("main_0_0", "libstratego-lib.ctree", "test09.ctree") },
				{ new TT("main_0_0", "libstratego-lib.ctree", "test28a.ctree") },
				{ new TT("main_0_0", "libstratego-lib.ctree", "test28b.ctree") },
		}));
		// @formatter:on

		int firstTest = 10;
		int lastTest = 115;

		for (int i = firstTest; i <= lastTest; i++) {
			tests.add(new TT[] { new TT("main_0_0", "libstratego-lib.ctree", "test" + i + ".ctree") });
		}

		return tests;
	}

	@Test
	public void test() throws IOException {
		StrategoCoreInterpreter interpreter = new StrategoCoreInterpreter();
		interpreter.reset();

		// load ctrees
		for (String ctreePath : ctreePaths) {
			interpreter.loadCTree(this.getClass().getClassLoader().getResourceAsStream(ctreePath));
		}

		TermFactory tf = new TermFactory();
		
		interpreter.setCurrentTerm(tf.makeList(tf.makeString("Main")));

		try{
			interpreter.invoke(strategyName);
		} catch(StrategoErrorExit errexit) {
			fail("Application failed");
		}

	}

	public static class TT {
		public String[] ctrees;
		public String sName;

		public TT(String sName, String... ctrees) {
			this.sName = sName;
			this.ctrees = ctrees;
		}

		@Override
		public String toString() {
			return sName + " -> " + ctrees[ctrees.length - 1];
		}
	}
}
