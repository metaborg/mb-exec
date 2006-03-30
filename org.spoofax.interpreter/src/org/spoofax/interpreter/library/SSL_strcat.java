/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;

public class SSL_strcat extends Primitive {

    protected SSL_strcat() {
        super("SSL_strcat", 0, 2);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws InterpreterException {

        if (!Tools.isATermString(targs.get(0)))
            return false;
        if (!Tools.isATermString(targs.get(1)))
            return false;

        String s = Tools.getATermString(targs.get(0));
        String t = Tools.getATermString(targs.get(1));

        env.setCurrent(env.makeString(s + t));
        return true;
    }
}
