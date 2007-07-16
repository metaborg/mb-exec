/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_implode_string extends AbstractPrimitive {

    protected SSL_implode_string() {
        super("SSL_implode_string", 0, 1);
    }

    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {

        IStrategoTerm t = targs[0];
        if(!Tools.isTermList(t))
            return false;

        IStrategoList l = (IStrategoList) t;

        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < l.size(); i++) {
            IStrategoInt v = Tools.intAt(l, i);
            sb.append(new Character((char)v.getValue()));
        }
        env.setCurrent(env.getFactory().makeString(sb.toString()));
        return true;
    }
}
