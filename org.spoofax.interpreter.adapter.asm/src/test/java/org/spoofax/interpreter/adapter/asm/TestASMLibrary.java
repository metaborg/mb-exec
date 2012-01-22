/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class TestASMLibrary {

	@Test
	public void shouldParseClassAndPrettyPrintWithoutExceptions() throws FileNotFoundException, IOException {
		ClassReader cr = new ClassReader(new FileInputStream("bin/org/spoofax/interpreter/adapter/asm/TestASMLibrary.class"));
		ClassNode cn = new ClassNode();
		cr.accept(cn, ClassReader.EXPAND_FRAMES);
		IStrategoTerm term = ASMFactory.genericWrap(cn);
		String s = term.toString();
		assertTrue(s.length() > 100);
		System.out.println(s);
	}
}
