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

public class SSL_strcat extends Primitive {

    protected SSL_strcat() {
        super("SSL_strcat", 0, 2);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, IStrategoTerm[] targs) throws InterpreterException {

        if(!Tools.isTermString(targs[0]))
            return false;
        if(!Tools.isTermString(targs[1]))
            return false;

        String s = Tools.javaString(targs[0]);
        String t = Tools.javaString(targs[1]);
        
        env.setCurrent(env.getFactory().makeString(s + t));
        return true;
    }
}
