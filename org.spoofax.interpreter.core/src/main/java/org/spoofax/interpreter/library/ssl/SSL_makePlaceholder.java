package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class SSL_makePlaceholder extends AbstractPrimitive {

    protected SSL_makePlaceholder() {
        super("SSL_makePlaceholder", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) {
        
        env.setCurrent(env.getFactory().makePlaceholder(tvars[0]));
        return true;
    }

}
