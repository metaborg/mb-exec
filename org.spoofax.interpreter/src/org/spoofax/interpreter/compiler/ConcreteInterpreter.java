package org.spoofax.interpreter.compiler;

import java.io.IOException;

import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.jsglr.InvalidParseTableException;

public class ConcreteInterpreter extends Interpreter {
	private Compiler compiler;

	public ConcreteInterpreter() throws IOException, InterpreterException, InvalidParseTableException
	{
		super();
		compiler = new Compiler(this.getFactory());
	}
	
    public void loadConcrete(String file, String[] path) throws IOException, InterpreterException {
        load(compiler.compile(file, path));
    }
}
