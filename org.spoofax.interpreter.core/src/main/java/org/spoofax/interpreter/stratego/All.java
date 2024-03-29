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

import jakarta.annotation.Nullable;

public class All extends Strategy {

    protected Strategy body;

    public All(Strategy body) {
        this.body = body;
    }
    
    // TODO: Optimize 'all', based on SRTS_all.java, which doesn't do all the copying and recursion
    //       (especially for lists!)

    public IConstruct eval(IContext env) throws InterpreterException {
        debug("All.eval() - ", env.current());

        IStrategoTerm t = env.current();

        switch (t.getType()) {
            case INT:
            case REAL:
            case STRING:
                return getHook().pop().onSuccess(env);
            case APPL:
                return evalAll(env, true, false, 0, t.getAllSubterms().clone());
            case LIST:
            case TUPLE:
                // TODO: Optimize - treat IStrategoList as linked list or use iterator?
                //       (same for some, all)
                IStrategoTerm[] subterms = t.getAllSubterms();
                assert isCopy(t, subterms);
                return evalAll(env, false, false, 0, subterms);
            case BLOB:
                return getHook().pop().onSuccess(env);
            default:
                throw new InterpreterException("Unknown ATerm type " + t.getType());
        }

    }
    
    static boolean isCopy(IStrategoTerm parent, IStrategoTerm[] kids) {
        if (kids.length > 0) {
            kids[0] = null;
            @Nullable IStrategoTerm subterm = parent.getSubtermCount() > 0 ? parent.getSubterm(0) : null;
            if (subterm == null) return false;
            kids[0] = subterm;
        }
        return true;
    }
    
    protected IConstruct evalAll(IContext env, final boolean needsClone, final boolean madeChanges, final int i, final IStrategoTerm[] list) throws InterpreterException
    {
    	final IStrategoTerm old = env.current();
    	if (i >= old.getSubtermCount()) {
    	    if (madeChanges) {
        		switch (old.getType()) {
        		case APPL:
        			env.setCurrent(env.getFactory().replaceAppl(((IStrategoAppl)old).getConstructor(), list, (IStrategoAppl)old));
        			break ;
        		case LIST:
        			env.setCurrent(env.getFactory().replaceList(list, (IStrategoList)old));
        			break ;
        		case TUPLE:
        			env.setCurrent(env.getFactory().replaceTuple(list, (IStrategoTuple)old));        			
        		}
    	    }
    		return getHook().pop().onSuccess(env);
    	}
    	final IStrategoTerm child = list[i];
    	env.setCurrent(child);
    	final All th = this;
    	body.getHook().push(new Hook(){
    		IStrategoTerm oldterm = old;
    		@Override
            public IConstruct onSuccess(IContext env) throws InterpreterException
    		{
    			IStrategoTerm newChild = env.current();
    			if (newChild != child) {
    			    IStrategoTerm[] newList = needsClone ? list.clone() : list;
    			    newList[i] = newChild;
    			    env.setCurrent(oldterm);
    			    return evalAll(env, false, true, i + 1, newList);
    			} else {
                    env.setCurrent(oldterm);
                    return evalAll(env, needsClone, madeChanges, i + 1, list);
    			}
    		}
    		@Override
            public IConstruct onFailure(IContext env) throws InterpreterException
    		{
    			return th.getHook().pop().onFailure(env);
    		}
    	});
    	return body.eval(env);
    }

    @Override
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
