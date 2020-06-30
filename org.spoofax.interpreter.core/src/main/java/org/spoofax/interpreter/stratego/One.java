/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;
import org.spoofax.interpreter.util.DebugUtil;

import static org.spoofax.interpreter.stratego.All.*;

import static org.spoofax.interpreter.core.Context.debug;

public class One extends Strategy {

    protected Strategy body;
    
    public One(Strategy body) {
        this.body = body;
    }

    public IConstruct eval(IContext env) throws InterpreterException {
		debug("One.eval() - ", env.current());

        IStrategoTerm t = env.current();

        switch (t.getType()) {
            case INT:
            case REAL:
            case STRING:
                return getHook().pop().onFailure(env);
            case APPL:
                return eval(env, 0, t.getAllSubterms().clone());
            case LIST:
            case TUPLE:
                IStrategoTerm[] subterms = t.getAllSubterms();
                assert isCopy(t, subterms);
                return eval(env, 0, subterms);
            case BLOB:
                return getHook().pop().onFailure(env);
            default:
                throw new InterpreterException("Unknown ATerm type " + t.getType());
        }
    }

    protected IConstruct eval(IContext env, final int n, final IStrategoTerm[] list) throws InterpreterException
    {
    	final IStrategoTerm old = env.current();
    	if (n >= old.getSubtermCount()) {
    		return getHook().pop().onFailure(env);
    	}
    	final Strategy th = this;
    	env.setCurrent(list[n]);
    	body.getHook().push(new Hook(){
			@Override
			public IConstruct onFailure(IContext env) throws InterpreterException {
				env.setCurrent(old);
				return eval(env, n+1, list);
			}
			@Override
			public IConstruct onSuccess(IContext env) throws InterpreterException {
				list[n] = env.current();
				switch (old.getType()) {
					case LIST: {
						env.setCurrent(env.getFactory().replaceList(list, (IStrategoList)old));
						break ;
					}
					case APPL: {
						IStrategoAppl appl = (IStrategoAppl)old;
						env.setCurrent(env.getFactory().replaceAppl(appl.getConstructor(), list, (IStrategoAppl)old));
						break ;
					}
					case TUPLE: {
						env.setCurrent(env.getFactory().replaceTuple(list, (IStrategoTuple) old));
						break ;
					}
					default: {
						throw new InterpreterException("Children of neither a list nor an appl.");
					}
				}
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
