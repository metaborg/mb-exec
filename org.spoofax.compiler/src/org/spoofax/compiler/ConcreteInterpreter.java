package org.spoofax.compiler;

import java.io.IOException;

import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.jsglr.InvalidParseTableException;

import aterm.pure.StrATermFactory;

public class ConcreteInterpreter extends Interpreter {
	private Compiler compiler;

	public ConcreteInterpreter() throws IOException, InterpreterException, InvalidParseTableException
	{
		super(new StrATermFactory());
		compiler = new Compiler(this.getFactory());
	}
	
    public void loadConcrete(String file, String[] path, boolean lib) throws IOException, InterpreterException {
        load(compiler.compile(file, path, lib));
    }
}
