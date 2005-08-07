/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter.stratego;

import java.util.List;

import org.spoofax.interpreter.DefScope;
import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IEnvironment;
import org.spoofax.interpreter.Interpreter;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.VarScope;

import aterm.ATermList;

public class CallT extends Strategy {

    protected String name;
    protected ATermList svars;
    protected ATermList tvars;
    
    public CallT(String name, ATermList svars, ATermList tvars) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
    }

    public boolean eval(IEnvironment env) throws FatalError {
        debug("evalCall - " + env.current());
        SDefT sdef = env.lookupSVar(name);
        debug(" call : " + sdef.getName());
        List<String> formalTVars = sdef.getTermParams();
        List<String> formalSVars = sdef.getStrategyParams();

        debug(" args : " + svars);
        debug(" svars: " + formalSVars);

        VarScope newVarScope = new VarScope(env.getVarScope());
        DefScope newDefScope = new DefScope(env.getDefScope());

        if (formalSVars.size() != svars.getChildCount()) {
            System.out.println(" takes : " + formalSVars.size());
            System.out.println(" have  : " + svars.getChildCount());

            throw new FatalError("Parameter length mismatch!");
        }

        for (int i = 0; i < svars.getChildCount(); i++) {
            String varName = Tools.stringAt(Tools.applAt(Tools
                    .applAt(svars, i), 0), 0);
            debug("  " + formalSVars.get(i) + " points to " + varName);
            newVarScope.addSVar(formalSVars.get(i), env.lookupSVar(varName));
        }

        for (int i = 0; i < tvars.getChildCount(); i++)
            newVarScope.add(formalTVars.get(i), env.getVarScope().lookup(Tools
                    .stringAt(Tools.applAt(tvars, i), 0)));

        VarScope oldVarScope = env.getVarScope();
        DefScope oldDefScope = env.getDefScope();
        env.setVarScope(newVarScope);
        env.setDefScope(newDefScope);

        boolean r = sdef.getBody().eval(env);

        env.setVarScope(oldVarScope);
        env.setDefScope(oldDefScope);
        return r;
    }
}
