package org.spoofax.compiler;

import java.io.IOException;

import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.adapter.aterm.WrappedATermFactory;
import org.spoofax.jsglr.InvalidParseTableException;

public class ConcreteInterpreter extends Interpreter {
	private Compiler compiler;

	public ConcreteInterpreter() throws IOException, InterpreterException, InvalidParseTableException
	{
		super(new WrappedATermFactory());
		compiler = new Compiler(this.getFactory());
	}
	
    public void loadConcrete(String file, String[] path, boolean lib) throws IOException, InterpreterException {
        load(compiler.compile(file, path, lib));
    }
    
    @Override
    public WrappedATermFactory getFactory() {
    	return (WrappedATermFactory)super.getFactory();
    }
}
