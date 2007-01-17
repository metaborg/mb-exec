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
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;

public class Some extends Strategy {

    protected Strategy body;
    
    public Some(Strategy body) {
        this.body = body;
    }

    public boolean eval(IContext env, EvaluationStack es) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            debug("Some.eval() - ", env.current());
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
                return DebugUtil.traceReturn(evalSome(env, (IStrategoAppl)t), env.current(), this);
            case IStrategoTerm.LIST:
                return DebugUtil.traceReturn(evalSome(env, (IStrategoList)t), env.current(), this);
            case IStrategoTerm.TUPLE:
                return DebugUtil.traceReturn(evalSome(env, (IStrategoTuple)t), env.current(), this);
            default:
                throw new InterpreterException("Unknown ATerm type " + t.getTermType());
        }
    }

	private boolean evalSome(IContext env, IStrategoList list) throws InterpreterException {
        
	    IStrategoTerm[] kids = list.getAllSubterms();
        
	    if(updateChildren(env, kids)) {
	        IStrategoList t2 = env.getFactory().makeList(kids);            
	        env.setCurrent(t2);
            return true;
        }

	    return false;
    }

    private boolean evalSome(IContext env, IStrategoAppl appl) throws InterpreterException {
        
        IStrategoTerm[] kids = appl.getAllSubterms();
        
        if(updateChildren(env, kids)) {
            IStrategoConstructor ctor = appl.getConstructor();
            env.setCurrent(env.getFactory().makeAppl(ctor, kids));            
            return true;
        }

        return false;
    }

    private boolean evalSome(IContext env, IStrategoTuple tuple) throws InterpreterException {
        
        IStrategoTerm[] kids = tuple.getAllSubterms();
        
        if(updateChildren(env, kids)) {
            env.setCurrent(env.getFactory().makeTuple(kids));            
            return true;
        }

        return false;
    }

	private boolean updateChildren(IContext env, IStrategoTerm[] kids) throws InterpreterException {
        final int sz = kids.length;
        boolean success = false;
        
        for(int i = 0; i < sz; i++) {
        	env.setCurrent(kids[i]);
            if(CallT.callHelper(body, env)) {
            	kids[i] = env.current();
            	success = true;
            }
        }

        return success;
	}

    public void prettyPrint(StupidFormatter sf) {
        sf.append("Some(");
        body.prettyPrint(sf);
        sf.append(")");
    }
}
