/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.util.DebugUtil;
import org.spoofax.terms.util.TermUtils;

import static org.spoofax.interpreter.core.Context.debug;

public class PrimT extends Strategy {

    protected String name;

    protected Strategy[] svars;

    protected IStrategoTerm[] tvars;

    public PrimT(String name, Strategy[] svars, IStrategoTerm[] tvars) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
    }

    public IConstruct eval(IContext env) throws InterpreterException {

        debug("PrimT.eval() - ", env.current());

        AbstractPrimitive prim = env.lookupOperator(name);

        if (prim == null)
            throw new InterpreterException("No such function : '" + name + "'");

        IStrategoTerm[] vals = new IStrategoTerm[tvars.length];
        for(int i = 0; i < tvars.length; i++) {
            // FIXME this cast should be moved out
            IStrategoAppl t = (IStrategoAppl)tvars[i];
            vals[i] = env.lookupVar(TermUtils.toJavaStringAt(t, 0));
            if (vals[i] == null) return getHook().pop().onFailure(env);
        }


        if (vals.length != prim.getTArity())
            throw new InterpreterException("Wrong term arity when calling '" + name + "', expected " + prim.getTArity() + " got " + vals.length);

        if (svars.length != prim.getSArity())
            throw new InterpreterException("Wrong strategy arity when calling '" + name + "', expected " + prim.getSArity() + " got " + svars.length);

        if(DebugUtil.isDebugging()) {
            CallT.printStrategyCall(name, null, svars, null, vals);
        }
        
        boolean r = prim.call(env, svars, vals);
        if (r)
        	return getHook().pop().onSuccess(env);
        else
        	return getHook().pop().onFailure(env);
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("PrimT(\"" + name + "\")");
    }

}
