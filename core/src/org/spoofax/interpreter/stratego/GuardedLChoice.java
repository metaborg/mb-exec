/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.BindingInfo;
import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;

import aterm.ATerm;

public class GuardedLChoice extends Strategy {

    protected Strategy cond;
    protected Strategy ifClause;
    protected Strategy thenClause;
    
    public GuardedLChoice(Strategy cond, Strategy ifclause, Strategy thenclause) {
        this.cond = cond;
        ifClause = ifclause;
        thenClause = thenclause;
    }

    public boolean eval(IContext env) throws FatalError {
        
        debug("GuardedLChoice.eval() - " + env.current());
        
        BindingInfo bi = env.getVarScope().saveUnboundVars();
        ATerm oldCurrent = env.current();
        
        if (cond.eval(env))
            return ifClause.eval(env);
        
        env.setCurrent(oldCurrent);
        env.getVarScope().restoreUnboundVars(bi);
        
        return thenClause.eval(env);
    }

}
