/*
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk at strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.cli;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.StyledEditorKit.UnderlineAction;

import jline.ConsoleReader;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.ConcreteInterpreter;
import org.spoofax.interpreter.core.InterpreterErrorExit;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.InterpreterExit;
import org.spoofax.interpreter.core.UndefinedStrategyException;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.client.InvalidParseTableException;
import org.spoofax.jsglr.client.ParseException;
import org.spoofax.jsglr.shared.BadTokenException;
import org.spoofax.jsglr.shared.SGLRException;
import org.spoofax.jsglr.shared.TokenExpectedException;

public class Main {

	public static void main(String[] args) throws IOException  {

		ConcreteInterpreter intp = new ConcreteInterpreter();

		List<String> includes = new LinkedList<String>();
		List<String> toCompile = new LinkedList<String>();
		String outFile = null;
		int skip = 0;

		for (int i = 0; i < args.length; i++) {
			if (skip > 0) {
				skip--;
				continue;
			}
			final String arg = args[i];
			if (arg.equals("-I")) {
				skip++;
				includes.add(args[i + 1]);
			} else if (arg.equals("-i")) {
				toCompile.add(args[i + 1]);
				skip++;
			} else if (args.equals("-o")) {
				outFile = args[i + 1];
				skip++;
			} else if (arg.startsWith("-")) {
				System.err.println("Unknown option " + arg);
				System.exit(-12);
			} else {
				throw new NotImplementedException();
			}
		}

		if (toCompile.size() == 0) {
			PrintWriter out = new PrintWriter(System.out);
			ConsoleReader consoleReader = new ConsoleReader();
			consoleReader.setBellEnabled(false);
			consoleReader.addCompletor(new SpoofaxCompletor(intp.getContext()));
			String line;
			while ((line = consoleReader.readLine("stratego> ")) != null) {
				if (line.equals(":quit"))
					break;
				IStrategoTerm old = intp.current();
				try {
					if (intp.parseAndInvoke(line)) {
						out.println(intp.current());
						out.flush();
					} else {
						intp.setCurrent(old);
						out.println("command failed");
						out.flush();
					}
				} catch (UndefinedStrategyException e) {
					handleUndefinedStrategyException(out, e);
				} catch (TokenExpectedException e) {
					e.printStackTrace(out);
				} catch (BadTokenException e) {
					e.printStackTrace(out);
				} catch (ParseException e) {
					e.printStackTrace(out);
				} catch (SGLRException e) {
					e.printStackTrace(out);
				} catch (InterpreterErrorExit e) {
					e.printStackTrace(out);
				} catch (InterpreterExit e) {
					e.printStackTrace(out);
				} catch (InterpreterException e) {
					if(e.getCause() instanceof UndefinedStrategyException) {
						handleUndefinedStrategyException(out, (UndefinedStrategyException)e.getCause());
					}
				}
				out.flush();
			}
		}
	}
	
	private static void handleUndefinedStrategyException(PrintWriter out,
			UndefinedStrategyException e) {
		out.println("undefined strategy "
				+ SpoofaxCompletor.uncify(e.getStrategyName()));
	}
}