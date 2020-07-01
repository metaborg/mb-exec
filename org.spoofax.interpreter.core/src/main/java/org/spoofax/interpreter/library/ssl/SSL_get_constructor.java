/*
 * Created on 9. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class SSL_get_constructor extends AbstractPrimitive {

    public SSL_get_constructor() {
        super("SSL_get_constructor", 0, 1);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        ITermFactory factory = env.getFactory();
        switch(tvars[0].getType()) {
            case APPL:
                IStrategoAppl a = (IStrategoAppl) tvars[0];
                env.setCurrent(factory.makeString(a.getConstructor().getName()));
                return true;
            case INT:
                env.setCurrent(factory.makeInt(((IStrategoInt) tvars[0]).intValue()));
                return true;
            case REAL:
                env.setCurrent(factory.makeReal(((IStrategoReal) tvars[0]).realValue()));
                return true;
            case BLOB:
                env.setCurrent(factory.makeString("BLOB_" + tvars[0].toString()));
                return true;
            case LIST:
                env.setCurrent(factory.makeList());
                return true;
            case STRING:
                IStrategoString current = (IStrategoString) factory.annotateTerm(tvars[0], factory.makeList());
                env.setCurrent(factory.makeString(current.toString()));
                return true;
            case TUPLE:
                env.setCurrent(factory.makeString(""));
                return true;
            case PLACEHOLDER:
                env.setCurrent(factory.makePlaceholder(factory.makeList()));
                return true;
            default:
                throw new IllegalStateException("SSL_get_constructor failed for " + tvars[0]);
        }
    }

}
