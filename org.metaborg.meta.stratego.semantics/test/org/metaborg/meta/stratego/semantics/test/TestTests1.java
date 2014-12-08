package org.metaborg.meta.stratego.semantics.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.metaborg.meta.interpreter.framework.InterpreterExitException;
import org.metaborg.meta.stratego.interpreter.StrategoCoreInterpreter;
import org.metaborg.meta.stratego.interpreter.StrategoInterpreter;
import org.spoofax.terms.TermFactory;
import org.strategoxt.lang.StrategoErrorExit;

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
		tests.addAll(Arrays
				.asList(new TT[][] {
						{ new TT("main_0_0", "libstratego-lib.ctree",
								"test01.ctree") },
						{ new TT("main_0_0", "libstratego-lib.ctree",
								"test02.ctree") },
						{ new TT("main_0_0", "libstratego-lib.ctree",
								"test03.ctree") },
						{ new TT("main_0_0", "libstratego-lib.ctree",
								"test04.ctree") },
						{ new TT("main_0_0", "libstratego-lib.ctree",
								"test05.ctree") },
						{ new TT("main_0_0", "libstratego-lib.ctree",
								"test06.ctree") },
						{ new TT("main_0_0", "libstratego-lib.ctree",
								"test07.ctree") },
						{ new TT("main_0_0", "libstratego-lib.ctree",
								"test08.ctree") },
						{ new TT("main_0_0", "libstratego-lib.ctree",
								"test09.ctree") },
						{ new TT("main_0_0", "libstratego-lib.ctree",
								"test28a.ctree") },
						{ new TT("main_0_0", "libstratego-lib.ctree",
								"test28b.ctree") }, }));
		List<Integer> skips = Arrays.asList(new Integer[] { 26, 42, 44, 48, 50,
				52, 55, 66, 71, 83 });

		// @formatter:on

		int firstTest = 10;
		int lastTest = 115;

		for (int i = firstTest; i <= lastTest; i++) {
			if (!skips.contains(i)) {
				String fn = "test" + (i < 10 ? "0" + i : i) + ".ctree";
				tests.add(new TT[] { new TT("main_0_0",
						"libstratego-lib.ctree", fn) });
			}
		}

		return tests;
	}

	@Test
	public void test() throws IOException {
		StrategoInterpreter interpreter = new StrategoCoreInterpreter();

		// load ctrees
		for (String ctreePath : ctreePaths) {
			interpreter.load(this.getClass().getClassLoader()
					.getResourceAsStream(ctreePath));
		}

		TermFactory tf = new TermFactory();

		interpreter.setCurrentTerm(tf.makeList(tf.makeString("Main")));

		try {
			interpreter.invoke(strategyName);
		} catch (InterpreterExitException iex) {
			assertEquals("Application failed", 0, iex.getValue());
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
