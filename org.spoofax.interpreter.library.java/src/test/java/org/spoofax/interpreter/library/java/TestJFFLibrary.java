/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.java;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestJFFLibrary {

	@Test
	public void testThatCtreeExists() {
		assertNotNull(JFFLibrary.class.getClassLoader().getResourceAsStream("share/jff-library.ctree"));
	}
}
