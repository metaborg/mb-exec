package org.spoofax.interpreter.compiler;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.library.jsglr.JSGLRLibrary;
import org.spoofax.interpreter.library.ssl.SSLLibrary;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.jsglr.InvalidParseTableException;

public class Compiler {
	private Interpreter compiler;
	
	Compiler(ITermFactory factory) throws IOException, InterpreterException, InvalidParseTableException
	{
		compiler = new Interpreter(factory);
		compiler.addOperatorRegistry("SSL", new SSLLibrary());
		compiler.addOperatorRegistry("JSGLR", new JSGLRLibrary(factory));
		compiler.load("data/libstratego-lib.ctree");
		compiler.load("data/libsglr.ctree");
		compiler.load("data/libstrc.ctree");
	}
	
	IStrategoTerm compile(String file, String[] path) throws InterpreterException
	{
		Collection<IStrategoTerm> terms = new LinkedList<IStrategoTerm>();
		for (String p : path) {
			terms.add(compiler.getFactory().makeString(p));
		}
		IStrategoTerm tp = compiler.getFactory().makeList(terms);
		IStrategoTerm[] tuple = {
		  compiler.getFactory().makeString(file),
		  tp
		};
		compiler.setCurrent(
		  compiler.getFactory().makeTuple(tuple)
		);
		if (!compiler.invoke("main_0_0"))
			return null;
		return compiler.current();
	}
}
