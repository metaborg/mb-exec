package org.spoofax.interpreter.library.java;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestJFFLibrary {

	@Test
	public void testThatCtreeExists() {
		assertNotNull(JFFLibrary.class.getClassLoader().getResourceAsStream("share/jff-library.ctree"));
	}
}
