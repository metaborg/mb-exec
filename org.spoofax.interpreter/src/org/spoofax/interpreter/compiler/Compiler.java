package org.spoofax.interpreter.compiler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.InterpreterExit;
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
		long loadTime = System.nanoTime();
		compiler.addOperatorRegistry("JSGLR", new JSGLRLibrary(factory));
		compiler.load("data/libstratego-lib.ctree");
		compiler.load("data/libsglr.ctree");
		compiler.load("data/libstrc.ctree");
		System.out.println("Loading time: " + (System.nanoTime() - loadTime)/1000/1000 + "ms");
	}
	
	IStrategoTerm compile(String file, String[] path) throws InterpreterException
	{
		List<IStrategoTerm> terms = new LinkedList<IStrategoTerm>();
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
		long execTime = System.nanoTime();
		boolean r = false;
		try {
			r = compiler.invoke("main_0_0");
		}
		catch (InterpreterExit e) {
			System.out.println("Return value: " + e.getValue());
		}
		System.out.println("Exec time: " + (System.nanoTime() - execTime)/1000/1000 + "ms");
		if (!r)
			return null;
		return compiler.current();
	}
}
