/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.ArrayList;
import java.util.List;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.VarScope;

public class Let extends Strategy {

    protected List<SDefT> defs;
    protected Strategy body;

    public Let(List<SDefT> defs, Strategy body) {
        assert defs != null;
        this.defs = defs;
        this.body = body;
    }

    public IConstruct eval(final IContext env) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("Let.eval() - ", env.current());
        }

        VarScope newScope = new VarScope(env.getVarScope());

        List<SDefT> newDefs = new ArrayList<SDefT>(defs.size());

        for (SDefT def : defs) {
            SDefT newDef = new SDefT(def.getName(),
              def.getStrategyParams(),
              def.getTermParams(),
              def.getBody(),
              newScope);
            newDefs.add(newDef);
        }

        newScope.addSVars(newDefs);
        env.setVarScope(newScope);
        final Strategy th = this;
        body.getHook().push(new Hook(){
			@Override
			IConstruct onFailure() throws InterpreterException {
				env.popVarScope();
				return th.getHook().pop().onFailure();
			}
			@Override
			IConstruct onSuccess(IContext env) throws InterpreterException {
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
