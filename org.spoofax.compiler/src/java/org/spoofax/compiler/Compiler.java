package org.spoofax.compiler;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.adapter.aterm.WrappedATermFactory;
import org.spoofax.interpreter.library.jsglr.JSGLRLibrary;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.InvalidParseTableException;

public class Compiler {
	private Interpreter compiler;

    public Compiler() throws IOException, InterpreterException, InvalidParseTableException {
        init(new WrappedATermFactory());
    }
    
	Compiler(WrappedATermFactory factory) throws IOException, InterpreterException, InvalidParseTableException {
        init(factory);
    }
    
    private void init(WrappedATermFactory factory) throws IOException, InterpreterException, InvalidParseTableException
    {
		compiler = new Interpreter(factory);
		compiler.addOperatorRegistry("JSGLR", new JSGLRLibrary(factory));
		compiler.load("data/libstratego-lib.ctree");
		compiler.load("data/libstratego-sglr.ctree");
		compiler.load("data/libstrc.ctree");
		compiler.load("data/jstrc.ctree");
	}
	
	IStrategoTerm compile(String file, String[] path, boolean lib) throws InterpreterException
	{
		Collection<IStrategoTerm> terms = new LinkedList<IStrategoTerm>();
		for (String p : path) {
			terms.add(compiler.getFactory().makeString(p));
		}
		IStrategoTerm tp = compiler.getFactory().makeList(terms);
		IStrategoTerm[] tuple = {
		  compiler.getFactory().makeString(file),
		  tp,
		  compiler.getFactory().makeInt(lib?1:0)
		};
		compiler.setCurrent(
		  compiler.getFactory().makeTuple(tuple)
		);
		if (!compiler.invoke("main_0_0"))
			return null;
		return compiler.current();
	}
}
