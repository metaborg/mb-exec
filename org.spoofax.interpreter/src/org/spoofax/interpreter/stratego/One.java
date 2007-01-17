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

public class One extends Strategy {

    protected Strategy body;

    public One(Strategy body) {
        this.body = body;
    }

    public boolean eval(IContext env, EvaluationStack es) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            debug("One.eval() - ", env.current());
        }

        IStrategoTerm t = env.current();

        switch (t.getTermType()) {
        case IStrategoTerm.INT:
            return DebugUtil.traceReturn(false, env.current(), this);
        case IStrategoTerm.REAL:
            return DebugUtil.traceReturn(false, env.current(), this);
        case IStrategoTerm.STRING:
            return DebugUtil.traceReturn(false, env.current(), this);
        case IStrategoTerm.APPL:
            return DebugUtil.traceReturn(evalOne(env, (IStrategoAppl) t), env.current(), this);
        case IStrategoTerm.LIST:
            return DebugUtil.traceReturn(evalOne(env, (IStrategoList) t), env.current(), this);
        case IStrategoTerm.TUPLE:
            return DebugUtil.traceReturn(evalOne(env, (IStrategoTuple) t), env.current(), this);
        default:
            throw new InterpreterException("Unknown ATerm type " + t.getTermType());
        }
    }

    private boolean evalOne(IContext env, IStrategoTuple tuple) throws InterpreterException {
        IStrategoTerm[] kids = tuple.getAllSubterms();
        
        if(!updateChildren(env, kids))
            return false;
        
        env.setCurrent(env.getFactory().makeTuple(kids));
        return true;
    }

    private boolean updateChildren(IContext env, IStrategoTerm[] kids) throws InterpreterException {
        final int sz = kids.length;

        for(int i = 0; i < sz; i++) {
            env.setCurrent(kids[i]);
            if (CallT.callHelper(body, env)) {
                kids[i] = env.current();
                return true;
            }
        }
        return false;
    }

    private boolean evalOne(IContext env, IStrategoList list) throws InterpreterException {
        IStrategoTerm[] kids = list.getAllSubterms();
        
        if(!updateChildren(env, kids))
            return false;
        
        env.setCurrent(env.getFactory().makeList(kids));
        return true;
    }

    private boolean evalOne(IContext env, IStrategoAppl appl) throws InterpreterException {
        IStrategoTerm[] kids = appl.getAllSubterms();
        
        if(!updateChildren(env, kids))
            return false;
        
        env.setCurrent(env.getFactory().makeAppl(appl.getConstructor(), kids));
        return true;
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("One(\n");
        sf.bump(4);
        body.prettyPrint(sf);
        sf.append(")");
        sf.unbump(4);
    }
}
