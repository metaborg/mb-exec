package org.spoofax.interpreter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoInt;

public class TestConcreteInterpreter {

	@Test
	public void load_and_exec_id() {
		ConcreteInterpreter ci = new ConcreteInterpreter();
		ci.setCurrent(new StrategoInt(10, IStrategoTerm.IMMUTABLE));
		assertTrue(ci.parseAndInvoke("id <+ fail"));
		
	}

	@Test
	public void load_and_exec_inc() {
		ConcreteInterpreter ci = new ConcreteInterpreter();
		ci.setCurrent(new StrategoInt(10, IStrategoTerm.IMMUTABLE));
		assertTrue(ci.parseAndInvoke("inc"));
		assertEquals(new StrategoInt(11, IStrategoTerm.IMMUTABLE), ci.current());
	}

}
