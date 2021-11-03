package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.terms.util.TermUtils;

public class SSL_constructor_hash extends AbstractPrimitive {

    protected SSL_constructor_hash() {
        super("SSL_constructor_hash", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) throws InterpreterException {
        IStrategoTerm term = tvars[0];
        if(TermUtils.isAppl(term)) {
            IStrategoConstructor cons = TermUtils.toAppl(term).getConstructor();
            env.setCurrent(env.getFactory().makeInt(cons.hashCode()));
            return true;
        } else {
            return false;
        }
    }
}
