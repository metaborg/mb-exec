/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;

import aterm.AFun;
import aterm.ATerm;
import aterm.ATermAppl;

public class All extends Strategy {

    protected Strategy body;
    
    public All(Strategy body) {
        this.body = body;
    }

    public boolean eval(IContext env) throws FatalError {
        
        debug("All.eval() - " + env.current());
        
        ATerm t = env.current();
        
        switch(t.getType()) {
        case ATerm.INT: return true;
        case ATerm.REAL: return true;
        case ATerm.APPL:
            return evalAll(env, (ATermAppl)t);
        default:
            throw new FatalError("Unknown ATerm type " + t.getType());
        }
        
    }

    private boolean evalAll(IContext env, ATermAppl t) throws FatalError {
        
        AFun fun = t.getAFun();
        ATerm[] xt = new ATerm[t.getChildCount()];
        
        for(int i=0;i<t.getChildCount();i++) {
            env.setCurrent(Tools.termAt(t, i));
            if(!body.eval(env)) {
                env.setCurrent(t);
                return false;
            }
            xt[i] = env.current(); 
        }
        
        ATermAppl t2 = env.getFactory().makeAppl(fun, xt);
        
        env.setCurrent(t2);
        
        return true;
    }

}
