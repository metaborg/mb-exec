/*
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.core;

import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * Exception thrown when the application exits with an fatal error message.
 *
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class InterpreterErrorExit extends InterpreterExit {

    private static final long serialVersionUID = -1230869980986338311L;

    private final IStrategoTerm term;

    public InterpreterErrorExit(String message, IStrategoTerm term) {
        this(message, term, null);
    }

    public InterpreterErrorExit(String message, IStrategoTerm term, Throwable cause) {
        super(1, message, cause);
        this.term = term;
    }

    public IStrategoTerm getTerm() {
        return term;
    }

    @Override
    public String getLocalizedMessage() { // used for toString() and stacktraces
        return getMessage() + (getTerm() == null ? "" : "\n\t" + getTerm());
    }

}
