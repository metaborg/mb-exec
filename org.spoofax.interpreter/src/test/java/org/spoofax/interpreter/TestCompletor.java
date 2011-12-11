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

	@Test
	public void test_cify_0_0() {
		assertEquals("foo_p__0_0", StrategyCompletor.cify("foo'/(0,0)"));
	}

	@Test
	public void test_cify_tick_0_0() {
		assertEquals("foo_p__0_0", StrategyCompletor.cify("foo'/(0,0)"));
	}

	@Test
	public void test_cify_hyphen_underline_1_2() {
		assertEquals("bar_foo___1_2", StrategyCompletor.cify("bar-foo_/(1,2)"));
	}

}
