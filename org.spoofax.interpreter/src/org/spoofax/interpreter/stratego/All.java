/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;

public class All extends Strategy {

    protected Strategy body;

    public All(Strategy body) {
        this.body = body;
    }

    public boolean eval(IContext env) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("All.eval() - ", env.current());
        }

        IStrategoTerm t = env.current();

        switch (t.getTermType()) {
            case IStrategoTerm.INT:
                return DebugUtil.traceReturn(true, env.current(), this);
            case IStrategoTerm.REAL:
                return DebugUtil.traceReturn(true, env.current(), this);
            case IStrategoTerm.STRING:
                return DebugUtil.traceReturn(true, env.current(), this);
            case IStrategoTerm.APPL:
                return DebugUtil.traceReturn(evalAll(env, (IStrategoAppl)t), env.current(), this);
            case IStrategoTerm.LIST:
                return DebugUtil.traceReturn(evalAll(env, (IStrategoList)t), env.current(), this);
            case IStrategoTerm.TUPLE:
                return DebugUtil.traceReturn(evalAll(env, (IStrategoTuple)t), env.current(), this);
            default:
                throw new InterpreterException("Unknown ATerm type " + t.getTermType());
        }

    }

    private boolean evalAll(IContext env, IStrategoList list) throws InterpreterException {

        // FIXME collapse with other evalAll
        
        IStrategoTerm[] r = new IStrategoTerm[list.size()];
        
        for(int i = 0; i < r.length; i++) {
            env.setCurrent(list.get(i));
            if(!body.eval(env)) {
                env.setCurrent(list);
                debug("Child traversal failed at ", list.get(i), ", current = ", env.current());
                return false;
            }
            r[i] = env.current(); 
        }
        
        IStrategoList r2 = env.getFactory().makeList(r);
        
        env.setCurrent(r2);
        
        return true;
    }

    private boolean evalAll(IContext env, IStrategoTuple tuple) throws InterpreterException {

        // FIXME collapse with other evalAll
        
        IStrategoTerm[] r = new IStrategoTerm[tuple.size()];
        
        for(int i = 0; i < r.length; i++) {
            env.setCurrent(tuple.get(i));
            if(!body.eval(env)) {
                env.setCurrent(tuple);
                debug("Child traversal failed at ", tuple.get(i), ", current = ", env.current());
                return false;
            }
            r[i] = env.current(); 
        }
        
        IStrategoList r2 = env.getFactory().makeList(r);
        
        env.setCurrent(r2);
        
        return true;
    }

    private boolean evalAll(IContext env, IStrategoAppl t) throws InterpreterException {
        
        IStrategoConstructor ctor = t.getConstructor();
        IStrategoTerm[] xt = new IStrategoTerm[t.getSubtermCount()];
        
        for(int i = 0; i < t.getSubtermCount(); i++) {
            env.setCurrent(Tools.termAt(t, i));
            if(!body.eval(env)) {
                env.setCurrent(t);
                debug("Child traversal failed at ", Tools.termAt(t, i), ", current = ", env.current());
                return false;
            }
            xt[i] = env.current(); 
        }
        
        IStrategoAppl t2 = env.getFactory().makeAppl(ctor, xt);
        
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
