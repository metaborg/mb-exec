/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.InterpreterException;
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

    public boolean eval(IContext env) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("All.eval() - ", env.current());
        }

        ATerm t = env.current();

        switch (t.getType()) {
            case ATerm.INT:
                return DebugUtil.traceReturn(true, env.current(), this);
            case ATerm.REAL:
                return DebugUtil.traceReturn(true, env.current(), this);
            case ATerm.APPL:
                return DebugUtil.traceReturn(evalAll(env, (ATermAppl)t), env.current(), this);
            default:
                throw new InterpreterException("Unknown ATerm type " + t.getType());
        }

    }

    private boolean evalAll(IContext env, ATermAppl t) throws InterpreterException {
        
        AFun fun = t.getAFun();
        ATerm[] xt = new ATerm[t.getChildCount()];
        
        for(int i=0;i<t.getChildCount();i++) {
            env.setCurrent(Tools.termAt(t, i));
            if(!body.eval(env)) {
                env.setCurrent(t);
                debug("Child traversal failed at ", Tools.termAt(t, i), ", current = ", env.current());
                return false;
            }
            xt[i] = env.current(); 
        }
        
        ATermAppl t2 = env.getFactory().makeAppl(fun, xt);
        
        env.setCurrent(t2);
        
        return true;
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("all(");
        sf.bump(4);
        body.prettyPrint(sf);
        sf.append(")");
        sf.unbump(4);
        
    }
}
