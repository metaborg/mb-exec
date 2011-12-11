package org.spoofax.interpreter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.spoofax.interpreter.cli.StrategyCompletor;

public class TestCompletor {

	@Test
	public void test_uncify_arity_and_tick() {
		assertEquals("znip-foo''", StrategyCompletor.uncify("znip_foo_p__p__1_2"));
	}

	@Test
	public void test_uncify_underline() {
		assertEquals("F_OK", StrategyCompletor.uncify("F__OK_0_0"));
	}

	@Test
	public void test_uncifyComplete_0_0() {
		assertEquals("F_OK/(0,0)", StrategyCompletor.uncifyComplete("F__OK_0_0"));
	}

	@Test
	public void test_uncifyComplete_3_4() {
		assertEquals("F_OK/(3,4)", StrategyCompletor.uncifyComplete("F__OK_3_4"));
	}

}
