/*
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk at strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterErrorExit;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.InterpreterExit;
import org.spoofax.interpreter.core.UndefinedStrategyException;
import org.spoofax.interpreter.stratego.SDefT;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.client.InvalidParseTableException;
import org.spoofax.jsglr.client.ParseException;
import org.spoofax.jsglr.client.ParseTable;
import org.spoofax.jsglr.client.SGLR;
import org.spoofax.jsglr.client.imploder.TreeBuilder;
import org.spoofax.jsglr.io.ParseTableManager;
import org.spoofax.jsglr.shared.BadTokenException;
import org.spoofax.jsglr.shared.SGLRException;
import org.spoofax.jsglr.shared.TokenExpectedException;
import org.spoofax.terms.ParseError;

public class ConcreteInterpreter extends Interpreter {

	private final ParseTable sugarTable;
	private final SGLR sugarParser;

	public ConcreteInterpreter() {
		try {
			load(findLibrary("stratego-lib/libstratego-lib.ctree"));
			load(findLibrary("libstrc.ctree"));
			load(new FileInputStream("frontend.ctree"));
			// load(findLibrary("libstratego-aterm.ctree"));
			// load(findLibrary("libstratego-gpp.ctree"));
			// load(findLibrary("libstratego-rtg.ctree"));
			// load(findLibrary("libstratego-sdf.ctree"));
			// load(findLibrary("libstratego-sglr.ctree"));
			// load(findLibrary("libstratego-tool-doc.ctree"));

			ParseTableManager ptm = new ParseTableManager();
			sugarTable = ptm
					.loadFromStream(new FileInputStream("bin/Stratego-Shell.tbl"));
			sugarParser = new SGLR(new TreeBuilder(), sugarTable);
			sugarParser.setUseStructureRecovery(false);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterpreterException e) {
			throw new RuntimeException(e);
		} catch (ParseError e) {
			throw new RuntimeException(e);
		} catch (InvalidParseTableException e) {
			throw new RuntimeException(e);
		}

	}

	private InputStream findLibrary(String libraryPath) {
		String shareDir = System.getProperty("user.home")
				+ "/.nix-profile/share/";
		File file = new File(shareDir + "/" + libraryPath);
		if (file.exists()) {
			try {
				return new FileInputStream(file);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		throw new RuntimeException("Failed to find Stratego library " + file.getAbsolutePath());
	}

	private InputStream findSyntax(String syntaxFile) {
		String shareDir = System.getProperty("user.home")
				+ "/.nix-profile/share/";
		File file = new File(shareDir + "/" + syntaxFile);
		if (file.exists()) {
			try {
				return new FileInputStream(file);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		throw new RuntimeException("Failed to find syntax table " + file.getAbsolutePath());
	}

	public void loadConcrete(String file, String[] path, boolean lib)
			throws IOException, InterpreterException {
	}

	private IStrategoAppl parseAndDesugar(String codeAsString, String startSymbol) throws TokenExpectedException, BadTokenException, ParseException, SGLRException, InterpreterErrorExit, InterpreterExit, UndefinedStrategyException, InterpreterException {
		IStrategoTerm tree = (IStrategoTerm) sugarParser.parse(codeAsString, "stdin", startSymbol);
		System.out.println(tree);
		IStrategoTerm old = current();
		setCurrent(tree);
		invoke("spoofax_concrete_desugar_0_0");
		IStrategoAppl ret = (IStrategoAppl) current();
		setCurrent(old);
		System.out.println(ret);
		return ret;
	}
	
	public void parseAndLoad(String codeAsString) {
        try {
        	IStrategoAppl program = parseAndDesugar(codeAsString, "Def");
            SDefT def = loader.parseSDefT(program);
            context.addSVar(def.getName(), def);
        } catch(InterpreterException e) {
        	throw new RuntimeException(e);
        } catch (TokenExpectedException e) {
        	throw new RuntimeException(e);
		} catch (BadTokenException e) {
        	throw new RuntimeException(e);
		} catch (ParseException e) {
        	throw new RuntimeException(e);
		} catch (SGLRException e) {
        	throw new RuntimeException(e);
		}
	}
	
	public boolean parseAndInvoke(String codeAsString) {
		try {
			IStrategoAppl program = parseAndDesugar(codeAsString, "Strategy");	
			if(program != null)
				return evaluate(program);
			else
				throw new RuntimeException(new InterpreterException("Failed to compile fragment"));
		} catch (ParseError e) {
			throw new RuntimeException(e);
		} catch (TokenExpectedException e) {
			throw new RuntimeException(e);
		} catch (BadTokenException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		} catch (SGLRException e) {
			throw new RuntimeException(e);
		} catch (InterpreterErrorExit e) {
			throw new RuntimeException(e);
		} catch (InterpreterExit e) {
			throw new RuntimeException(e);
		} catch (UndefinedStrategyException e) {
			throw new RuntimeException(e);
		} catch (InterpreterException e) {
			throw new RuntimeException(e);
		}
	}
}