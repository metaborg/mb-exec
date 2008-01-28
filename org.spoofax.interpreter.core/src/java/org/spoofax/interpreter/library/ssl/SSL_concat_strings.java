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
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_concat_strings extends AbstractPrimitive {

    protected SSL_concat_strings() {
        super("SSL_concat_strings", 0, 1);
    }

    public boolean call(IContext env, Strategy[] sargs, IStrategoTerm[] targs) throws InterpreterException {

        
        if(!Tools.isTermList(targs[0]))
            return false;
        IStrategoTerm[] kids = targs[0].getAllSubterms();
        
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < kids.length; i++) {
            if(!Tools.isTermString(kids[i]))
                return false;
            sb.append(Tools.asJavaString(kids[i]));
        }
        env.setCurrent(env.getFactory().makeString(sb.toString()));
        return true;
    }
}
