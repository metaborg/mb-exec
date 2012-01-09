package org.spoofax.interpreter.cli;

import static org.junit.Assert.assertEquals;
import jline.ANSIBuffer;

import org.junit.Test;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.TermFactory;

public class TestColorizer {

	@Test
	public void test_list_in_appl() {
		TermFactory f = new TermFactory();
		IStrategoInt i = f.makeInt(1);
		IStrategoList l = f.makeList(i);
		IStrategoAppl a = f.makeAppl(f.makeConstructor("Foo", 1), (IStrategoTerm)l);
		ANSIBuffer ab = new ANSIBuffer();
		ab.setAnsiEnabled(false);
		Main.colorize(ab, a);
		assertEquals(ab.toString(), a.toString());
	}
}
