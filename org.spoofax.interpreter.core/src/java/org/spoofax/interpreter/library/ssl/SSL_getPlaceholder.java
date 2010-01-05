package org.spoofax.interpreter.library.ssl;

import static org.spoofax.interpreter.terms.IStrategoTerm.*;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoPlaceholder;
import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class SSL_getPlaceholder extends AbstractPrimitive {

    protected SSL_getPlaceholder() {
        super("SSL_getPlaceholder", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) {
        
        if (tvars[0].getTermType() == PLACEHOLDER) {
            env.setCurrent(((IStrategoPlaceholder) tvars[0]).getTemplate());
            return true;
        } else {
            return false;
        }
    }

}
