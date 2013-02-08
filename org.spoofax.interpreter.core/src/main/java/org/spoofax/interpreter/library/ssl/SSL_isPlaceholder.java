package org.spoofax.interpreter.library.ssl;

import static org.spoofax.interpreter.terms.IStrategoTerm.*;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class SSL_isPlaceholder extends AbstractPrimitive {

    protected SSL_isPlaceholder() {
        super("SSL_isPlaceholder", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) {
        
        return tvars[0].getTermType() == PLACEHOLDER;
    }

}
