/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;

public class Seq extends Strategy {

    protected Strategy[] children;
    

    public Seq(Strategy[] strategies) {
    	children = strategies;
	}

	public IConstruct eval(IContext env) throws InterpreterException {

//        if (Interpreter.isDebugging()) {
//            debug("Seq.eval() - ", env.current());
//        }
		return eval(env, 0);
    }

	protected IConstruct eval(IContext env, final int n) throws InterpreterException {
		if (n == children.length) {
			return getHook().pop().onSuccess(env);
		}
		
		final Strategy th = this;
		children[n].getHook().push(new Hook(){
			@Override
			public IConstruct onFailure(IContext env) throws InterpreterException {
				return th.getHook().pop().onFailure(env);
			}
			@Override
			public IConstruct onSuccess(IContext env) throws InterpreterException {
				return eval(env, n+1);
			}
		});
		return children[n];
	}
    public void prettyPrint(StupidFormatter sf) {
        sf.first("Seq(");
        sf.bump(4);
        sf.append("  ");
        for (int i = 0; i < (children.length - 1); i++) {
        	children[i].prettyPrint(sf);
        	sf.append(", ");
        }
        children[children.length - 1].prettyPrint(sf);
        sf.unbump(4);
        sf.line(")");
    }
}
