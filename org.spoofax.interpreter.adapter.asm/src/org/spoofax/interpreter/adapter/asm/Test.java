/*
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.adapter.asm;

import java.io.FileInputStream;
import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class Test {

	public static void main(String[] args) throws IOException {
		ClassReader cr = new ClassReader(new FileInputStream("bin/org/spoofax/interpreter/adapter/asm/Test.class"));
		ClassNode cn = new ClassNode();
		cr.accept(cn, ClassReader.EXPAND_FRAMES);
		//ASMFactory af = new ASMFactory();
		IStrategoTerm term = ASMFactory.genericWrap(cn);
		
		System.out.println(term.toString());
	}
}
