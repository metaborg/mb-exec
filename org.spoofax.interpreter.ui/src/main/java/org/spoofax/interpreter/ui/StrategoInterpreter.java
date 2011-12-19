/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk at strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.ui;

import java.io.IOException;

import org.spoofax.interpreter.ConcreteInterpreter;
import org.spoofax.interpreter.core.InterpreterErrorExit;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.InterpreterExit;
import org.spoofax.interpreter.core.UndefinedStrategyException;
import org.spoofax.interpreter.library.eclipse.EFILibrary;
import org.spoofax.interpreter.library.java.JFFLibrary;
import org.spoofax.interpreter.ui.StrategoConsole.ConsoleIOAgent;
import org.spoofax.jsglr.client.ParseException;
import org.spoofax.jsglr.shared.BadTokenException;
import org.spoofax.jsglr.shared.SGLRException;
import org.spoofax.jsglr.shared.TokenExpectedException;

public class StrategoInterpreter {

	private final ConcreteInterpreter interpreter;
	private final ConsoleIOAgent ioAgent;

	public StrategoInterpreter(ConsoleIOAgent ioAgent) {
		this.ioAgent = ioAgent;
		interpreter = new ConcreteInterpreter(); //new ECJFactory());
		try {
			JFFLibrary.attach(interpreter);
			EFILibrary.attach(interpreter);
			interpreter.setIOAgent(ioAgent);
		} catch(IOException e) {
			throw new RuntimeException(e);
		} catch(InterpreterException e) {
			throw new RuntimeException(e);
		}

	}

	public boolean eval(String line) {
		try {
			System.out.println("EVAL " + line);
			if(!interpreter.parseAndInvoke(line)) {
				ioAgent.err.println("command failed");
			} else {
				ioAgent.out.println(interpreter.current().toString());
			}
		} catch (TokenExpectedException e) {
			ioAgent.err.println(e.getMessage());
		} catch (InterpreterErrorExit e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadTokenException e) {
			ioAgent.err.println(e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterpreterExit e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UndefinedStrategyException e) {
			ioAgent.err.println(e.getMessage());
		} catch (SGLRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterpreterException e) {
			if(e.getCause() != null)
				ioAgent.err.println(e.getCause().getMessage());
			else
				ioAgent.err.println(e.getMessage());
		} finally {
			ioAgent.out.flush();
			ioAgent.err.flush();
		}
		return true;
	}


}
