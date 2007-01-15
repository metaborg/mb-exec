/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.BindingInfo;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Pair;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class GuardedLChoice extends Strategy {

	private Pair<Strategy,Strategy>[] children;
    
    public GuardedLChoice(Pair<Strategy,Strategy>[] strs) {
    	children = strs;
    }

    public Evaluator eval(IContext env) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            debug("GuardedLChoice.eval() - ", env.current());
        }

        for (int i = 0; i < (children.length - 1); i++) {
        	BindingInfo bi = env.getVarScope().saveUnboundVars();
        	IStrategoTerm oldCurrent = env.current();

        	if (children[i].first.eval(env))
        		return DebugUtil.traceReturn(children[i].second.eval(env), env.current(), this);

        	env.setCurrent(oldCurrent);
        	env.getVarScope().restoreUnboundVars(bi);
        }
        
        return DebugUtil.traceReturn(children[children.length - 1].first.eval(env), env.current(), this);
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.first("GuardedLChoice(");
        sf.bump(15);
        sf.append("  ");
        sf.bump(2);
        //cond.prettyPrint(sf);
        sf.unbump(2);
        sf.append(", ");
        sf.bump(2);
        //ifClause.prettyPrint(sf);
        sf.unbump(2);
        sf.append(", ");
        sf.bump(2);
        //thenClause.prettyPrint(sf);
        sf.unbump(2);
        sf.unbump(15);
        sf.line(")");
        
    }
}
