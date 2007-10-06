package org.spoofax.interpreter.adapter.asm;

import java.io.FileInputStream;
import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class Test {

	public static void main(String[] args) throws IOException {
		ClassReader cr = new ClassReader(new FileInputStream("AFun.class"));
		ClassNode cn = new ClassNode();
		cr.accept(cn, 0);
		//ASMFactory af = new ASMFactory();
		IStrategoTerm term = ASMFactory.genericWrap(cn);
		
		System.out.println(term.toString());
	}
}
