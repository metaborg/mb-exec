/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SSL_string_to_int extends Primitive {

    protected SSL_string_to_int() {
        super("SSL_string_to_int", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> svars, List<IStrategoTerm> tvars) throws InterpreterException {
        
        if(!Tools.isTermString(tvars.get(0)))
            return false;

        String s = Tools.javaString(tvars.get(0));
        Integer i = new Integer(s);
        env.setCurrent(env.getFactory().makeInt(i.intValue()));
        return true;
    }
}
