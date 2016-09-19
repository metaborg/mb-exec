/*
 * Licensed under the GNU Lesser Lesser General Public License, v2.1.1
 */
package org.spoofax.interpreter.core;

import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * Exception thrown when the application exits with an fatal error message.
 *
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class InterpreterErrorExit extends InterpreterExit {

    private static final long serialVersionUID = -1230869980986338311L;

    private final IStrategoTerm term;
	private final IStrategoList trace;

    public InterpreterErrorExit(String message) {
	    this(message, null, null, null);
    }

    public InterpreterErrorExit(String message, IStrategoTerm term) {
	    this(message, term, null, null);
    }

    public InterpreterErrorExit(String message, IStrategoTerm term, Throwable cause) {
	    this(message, term, null, cause);
    }

	public InterpreterErrorExit(String message, IStrategoTerm term, IStrategoList trace) {
	    this(message, term, trace, null);
	}

	public InterpreterErrorExit(String message, IStrategoTerm term, IStrategoList trace, Throwable cause) {
		super(1, message, cause);
		this.term = term;
		this.trace = trace;
	}

    public IStrategoTerm getTerm() {
        return term;
    }

    @Override
    public String getLocalizedMessage() {
		StringBuilder sb = new StringBuilder(super.getMessage());
		IStrategoTerm term = getTerm();
		if (term != null) {
			sb.append("\n\t" + term);
		}
		if (trace != null && !trace.isEmpty()) {
			IStrategoList tail = trace;
			while(!tail.isEmpty()) {
				IStrategoTerm t = tail.head();
				sb.append("\n\t" + (t.getTermType() == IStrategoTerm.STRING ? Tools.asJavaString(t) : t));
				tail = tail.tail();
			}
		}
		return sb.toString();
    }

	public IStrategoList getTrace() {
		return trace;
	}
}
