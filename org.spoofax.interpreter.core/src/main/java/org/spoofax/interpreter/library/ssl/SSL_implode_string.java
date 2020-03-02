/*
 * Created on 08.aug.2005
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
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

public class SSL_implode_string extends AbstractPrimitive {

    protected SSL_implode_string() {
        super("SSL_implode_string", 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {

        IStrategoTerm t = targs[0];
        if(!TermUtils.isList(t))
            return false;

        String result = call((IStrategoList) t);
        env.setCurrent(env.getFactory().makeString(result));
        return true;
    }

    public static String call(IStrategoList chars) {
        StringBuilder result = new StringBuilder(chars.size());

        while (!chars.isEmpty()) {
            IStrategoInt v = (IStrategoInt) chars.head();
            result.append((char) v.intValue());
            chars = chars.tail();
        }
        return result.toString();
    }
}
