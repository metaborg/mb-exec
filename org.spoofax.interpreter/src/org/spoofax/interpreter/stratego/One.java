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
import org.spoofax.interpreter.terms.IStrategoTerm;

public class One extends Strategy {

    protected Strategy body;
    
    public One(Strategy body) {
        this.body = body;
    }

    public IConstruct eval(IContext env) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            debug("One.eval() - ", env.current());
        }

        IStrategoTerm t = env.current();

        switch (t.getTermType()) {
            case IStrategoTerm.INT:
            case IStrategoTerm.REAL:
            case IStrategoTerm.STRING:
                return getHook().pop().onFailure();
            case IStrategoTerm.APPL:
            case IStrategoTerm.LIST:
            case IStrategoTerm.TUPLE:
                return eval(env, 0, t.getSubterms());
            default:
                throw new InterpreterException("Unknown ATerm type " + t.getTermType());
        }
    }

    private IConstruct eval(final IContext env, final int n, final IStrategoTerm[] list) throws InterpreterException
    {
    	final IStrategoTerm old = env.current();
    	if (n >= old.getSubtermCount()) {
    		return getHook().pop().onFailure();
    	}
    	final Strategy th = this;
    	env.setCurrent(list[n]);
    	body.getHook().push(new Hook(){
			@Override
			IConstruct onFailure() throws InterpreterException {
				env.setCurrent(old);
				return eval(env, n+1, list);
			}
			@Override
			IConstruct onSuccess(IContext env) throws InterpreterException {
				old.setSubterm(n, env.current());
				env.setCurrent(old);
				return th.getHook().pop().onSuccess(env);
			}
    	});
		return body;
    }
    
	public void prettyPrint(StupidFormatter sf) {
        sf.append("One(\n");
        sf.bump(4);
        body.prettyPrint(sf);
        sf.append(")");
        sf.unbump(4);
    }
}
