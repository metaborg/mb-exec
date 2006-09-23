/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.ArrayList;
import java.util.List;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.Primitive;
import org.spoofax.interpreter.library.SSL;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class PrimT extends Strategy {

    protected String name;

    protected List<Strategy> svars;

    protected List<IStrategoTerm> tvars;

    public PrimT(String name, List<Strategy> svars, List<IStrategoTerm> tvars) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
    }

    public boolean eval(IContext env) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("PrimT.eval() - ", env.current());
        }

        Primitive prim = SSL.lookup(name);

        if (prim == null)
            throw new InterpreterException("No such function : '" + name + "'");

        List<IStrategoTerm> vals = new ArrayList<IStrategoTerm>(tvars.size());
        for (IStrategoTerm t : tvars) {
            // FIXME this cast should be moved out
            vals.add(env.lookupVar(Tools.javaStringAt((IStrategoAppl)t, 0)));
        }


        if (vals.size() != prim.getTArity())
            throw new InterpreterException("Wrong aterm arity when calling '" + name + "', expected " + prim.getTArity() + " got " + vals.size());

        if (svars.size() != prim.getSArity())
            throw new InterpreterException("Wrong strategy arity when calling '" + name + "', expected " + prim.getSArity() + " got " + svars.size());

        if(DebugUtil.isDebugging()) {
            CallT.printStrategyCall(name, null, svars, null, vals);
        }
        boolean r = prim.call(env, svars, vals);

        return DebugUtil.traceReturn(r, env.current(), this);
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("PrimT(\"" + name + "\")");
    }

}
