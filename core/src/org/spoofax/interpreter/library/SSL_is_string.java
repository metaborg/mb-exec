/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.List;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.AFun;
import aterm.ATerm;
import aterm.ATermAppl;

public class SSL_is_string extends Primitive {

    protected SSL_is_string() {
        super("SSL_is_string", 0, 1);
    }
    
    public boolean call(IContext env, List<Strategy> sargs, List<ATerm> targs) throws FatalError {
        debug("SSL_is_string");
        
        ATerm t = targs.get(0);
        if(t.getType() == ATerm.APPL) {
            AFun f = ((ATermAppl)t).getAFun();
            return f.isQuoted() && f.getChildCount() == 0;
        }
        return false;
    }
}
