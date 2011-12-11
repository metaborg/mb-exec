package org.spoofax.interpreter;

import org.junit.Test;
import org.spoofax.interpreter.cli.StrategyCompletor;

public class TestCompletor {

	@Test
	public void test_uncify() {
		System.out.println(StrategyCompletor.uncify("znip_foo_p__p__1_2"));
	}
}
