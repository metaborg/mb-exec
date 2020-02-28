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
import org.spoofax.interpreter.core.VarScope;
import org.spoofax.interpreter.util.DebugUtil;


import static org.spoofax.interpreter.core.Context.debug;

public class Let extends Strategy {

    protected SDefT[] defs;
    protected Strategy body;

    public Let(SDefT[] defs2, Strategy body) {
        assert defs2 != null;
        this.defs = defs2;
        this.body = body;
    }

    public IConstruct eval(final IContext env) throws InterpreterException {

        debug("Let.eval() - ", env.current());

        VarScope newScope = new VarScope(env.getVarScope());

        SDefT[] newDefs = new SDefT[defs.length];

        for (int i = 0; i < defs.length; ++i) {
        	SDefT def = defs[i];
            SDefT newDef = new SDefT(def.getName(),
              def.getStrategyParams(),
              def.getTermParams(),
              def.getBody(),
              newScope);
            newDefs[i] = newDef;
        }

        newScope.addSVars(newDefs);
        env.setVarScope(newScope);
        final Strategy th = this;
        body.getHook().push(new Hook(){
			@Override
            public IConstruct onFailure(IContext env) throws InterpreterException {
				env.popVarScope();
				return th.getHook().pop().onFailure(env);
			}
			@Override
            public IConstruct onSuccess(IContext env) throws InterpreterException {
		        env.popVarScope();
				return th.getHook().pop().onSuccess(env);
			}
        	
        });
        return body;
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("Let(\n");
        sf.bump(4);
        sf.append(", " + defs + "\n");
        sf.append(", ");
        body.prettyPrint(sf);
        sf.append(")");
        sf.unbump(4);
    }
}
