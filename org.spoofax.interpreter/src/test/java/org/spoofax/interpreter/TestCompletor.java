package org.spoofax.interpreter;

import org.junit.Test;
import org.spoofax.interpreter.cli.SpoofaxCompletor;

public class TestCompletor {

	@Test
	public void test_uncify() {
		System.out.println(SpoofaxCompletor.uncify("znip_foo_p__p__1_2"));
	}
}
