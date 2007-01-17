/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class All extends Strategy {

    protected Strategy body;

    public All(Strategy body) {
        this.body = body;
    }

    public IConstruct eval(IContext env) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            debug("All.eval() - ", env.current());
        }

        IStrategoTerm t = env.current();

        switch (t.getTermType()) {
            case IStrategoTerm.INT:
            case IStrategoTerm.REAL:
            case IStrategoTerm.STRING:
                return getHook().pop().onSuccess(env);
            case IStrategoTerm.APPL:
            case IStrategoTerm.LIST:
            case IStrategoTerm.TUPLE:
                return evalAll(env, 0, t.getSubterms());
            default:
                throw new InterpreterException("Unknown ATerm type " + t.getTermType());
        }

    }
    
    private IConstruct evalAll(IContext env, final int i, final IStrategoTerm[] list) throws InterpreterException
    {
    	final IStrategoTerm old = env.current();
    	if (i >= old.getSubtermCount()) {
    		switch (old.getTermType()) {
    		case IStrategoTerm.APPL:
    			env.setCurrent(env.getFactory().makeAppl(((IStrategoAppl)old).getConstructor(), list));
    			break ;
    		case IStrategoTerm.LIST:
    			env.setCurrent(env.getFactory().makeList(list));
    			break ;
    		case IStrategoTerm.TUPLE:
    			env.setCurrent(env.getFactory().makeTuple(list));        			
    		}
    		return getHook().pop().onSuccess(env);
    	}
    	IStrategoTerm child = list[i];
    	env.setCurrent(child);
    	final All th = this;
    	body.getHook().push(new Hook(){
    		IStrategoTerm oldterm = old;
    		IConstruct onSuccess(IContext env) throws InterpreterException
    		{
    			list[i] = env.current();
    			env.setCurrent(oldterm);
    			return evalAll(env, i + 1, list);
    		}
    		IConstruct onFailure() throws InterpreterException
    		{
    			return th.getHook().pop().onFailure();
    		}
    	});
    	return body.eval(env);
    }

    public String toString(){
    	return "All(" + body.toString() + ")";
    }
    
    public void prettyPrint(StupidFormatter sf) {
        sf.append("all(");
        sf.bump(4);
        body.prettyPrint(sf);
        sf.append(")");
        sf.unbump(4);
        
    }
}
