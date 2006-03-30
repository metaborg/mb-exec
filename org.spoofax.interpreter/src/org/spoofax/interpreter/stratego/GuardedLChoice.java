/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.BindingInfo;
import org.spoofax.interpreter.InterpreterException;
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

    public boolean eval(IContext env) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("GuardedLChoice.eval() - ", env.current());
        }

        BindingInfo bi = env.getVarScope().saveUnboundVars();
        ATerm oldCurrent = env.current();

        if (cond.eval(env))
            return DebugUtil.traceReturn(ifClause.eval(env), env.current(), this);

        env.setCurrent(oldCurrent);
        env.getVarScope().restoreUnboundVars(bi);

        return DebugUtil.traceReturn(thenClause.eval(env), env.current(), this);
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.first("GuardedLChoice(");
        sf.bump(15);
        sf.append("  ");
        sf.bump(2);
        cond.prettyPrint(sf);
        sf.unbump(2);
        sf.append(", ");
        sf.bump(2);
        ifClause.prettyPrint(sf);
        sf.unbump(2);
        sf.append(", ");
        sf.bump(2);
        thenClause.prettyPrint(sf);
        sf.unbump(2);
        sf.unbump(15);
        sf.line(")");
        
    }
}
