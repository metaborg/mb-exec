/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.BindingInfo;
import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Tools;

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
        boolean r = cond.eval(env);
        if (r) {
            return ifClause.eval(env);
        } else {
            env.setCurrent(oldCurrent);
            env.getVarScope().restoreUnboundVars(bi);
            return thenClause.eval(env);
        }
    }

}
