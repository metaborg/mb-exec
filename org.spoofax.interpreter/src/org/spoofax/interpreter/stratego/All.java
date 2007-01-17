/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.EvaluationStack;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;

public class All extends Strategy {

    protected Strategy body;

    public All(Strategy body) {
        this.body = body;
    }

    public boolean eval(IContext env, EvaluationStack es) throws InterpreterException {

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

        IStrategoTerm[] kids = list.getAllSubterms();
        
        if(!updateChildren(env, kids))
            return false;
        
        env.setCurrent(env.getFactory().makeList(kids));
        return true;
    }

    private boolean evalAll(IContext env, IStrategoTuple tuple) throws InterpreterException {

        IStrategoTerm[] kids = tuple.getAllSubterms();

        if(!updateChildren(env, kids))
            return false;
        
        env.setCurrent(env.getFactory().makeTuple(kids));
        return true;
    }

    private boolean evalAll(IContext env, IStrategoAppl t) throws InterpreterException {
        
        IStrategoTerm[] kids = t.getArguments();
        
        if(!updateChildren(env, kids))
            return false;
        
        env.setCurrent(env.getFactory().makeAppl(t.getConstructor(), kids));
        return true;
    }

    private boolean updateChildren(IContext env, IStrategoTerm[] children) throws InterpreterException {
        final int sz = children.length; 
        for(int i = 0; i < sz; i++) {
            env.setCurrent(children[i]);
            if(!CallT.callHelper(body, env)) {
                if(DebugUtil.isDebugging()) {
                    debug("Child traversal failed at ", children[i], ", current = ", env.current());
                }
                return false;
            }
            children[i] = env.current(); 
        }
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
