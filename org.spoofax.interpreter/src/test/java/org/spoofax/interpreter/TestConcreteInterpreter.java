package org.spoofax.interpreter;

import static org.junit.Assert.*;

import org.junit.Test;
import org.spoofax.interpreter.core.InterpreterErrorExit;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.InterpreterExit;
import org.spoofax.interpreter.core.UndefinedStrategyException;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.client.ParseException;
import org.spoofax.jsglr.shared.BadTokenException;
import org.spoofax.jsglr.shared.SGLRException;
import org.spoofax.jsglr.shared.TokenExpectedException;
import org.spoofax.terms.StrategoInt;

public class TestConcreteInterpreter {

	@Test
	public void load_and_exec_id() throws TokenExpectedException, InterpreterErrorExit, BadTokenException, ParseException, InterpreterExit, UndefinedStrategyException, SGLRException, InterpreterException {
		ConcreteInterpreter ci = new ConcreteInterpreter();
		ci.setCurrent(new StrategoInt(10, IStrategoTerm.IMMUTABLE));
		assertTrue(ci.parseAndInvoke("id <+ fail"));
		
	}

	@Test
	public void load_and_exec_inc() throws TokenExpectedException, InterpreterErrorExit, BadTokenException, ParseException, InterpreterExit, UndefinedStrategyException, SGLRException, InterpreterException {
		ConcreteInterpreter ci = new ConcreteInterpreter();
		ci.setCurrent(new StrategoInt(10, IStrategoTerm.IMMUTABLE));
		assertTrue(ci.parseAndInvoke("inc"));
		assertEquals(new StrategoInt(11, IStrategoTerm.IMMUTABLE), ci.current());
	}

	@Test
	public void load_and_exec_a_strategy_def() throws TokenExpectedException, InterpreterErrorExit, BadTokenException, ParseException, InterpreterExit, UndefinedStrategyException, SGLRException, InterpreterException {
		ConcreteInterpreter ci = new ConcreteInterpreter();
		ci.setCurrent(new StrategoInt(10, IStrategoTerm.IMMUTABLE));
		ci.parseAndLoad("zz = inc");
		assertTrue(ci.parseAndInvoke("zz"));
		assertEquals(new StrategoInt(11, IStrategoTerm.IMMUTABLE), ci.current());
	}

	@Test
	public void load_and_exec_a_higher_order_strategy_def() throws TokenExpectedException, InterpreterErrorExit, BadTokenException, ParseException, InterpreterExit, UndefinedStrategyException, SGLRException, InterpreterException {
		ConcreteInterpreter ci = new ConcreteInterpreter();
		ci.setCurrent(new StrategoInt(10, IStrategoTerm.IMMUTABLE));
		ci.parseAndLoad("zz(s) = s");
		assertTrue(ci.parseAndInvoke("zz(inc)"));
		assertEquals(new StrategoInt(11, IStrategoTerm.IMMUTABLE), ci.current());
	}


	@Test
	public void load_and_exec_a_build() throws TokenExpectedException, InterpreterErrorExit, BadTokenException, ParseException, InterpreterExit, UndefinedStrategyException, SGLRException, InterpreterException {
		ConcreteInterpreter ci = new ConcreteInterpreter();
		ci.setCurrent(new StrategoInt(10, IStrategoTerm.IMMUTABLE));
		assertTrue(ci.parseAndInvoke("!Foo(1,2,3)"));
		assertEquals(IStrategoTerm.APPL, ci.current().getTermType());
		System.out.println(ci.current());
	}

}
