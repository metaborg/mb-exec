/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter.stratego;

import java.util.List;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.VarScope;

import aterm.ATermList;

public class Scope extends Strategy {

    protected List<String> vars;
    protected Strategy body;
    
    public Scope(List<String> vars, Strategy body) {
        this.vars = vars;
        this.body = body;
    }

    public boolean eval(IContext env) throws FatalError {
        debug("Scope.eval()");
        
        VarScope oldScope = env.getVarScope();
        VarScope newScope = new VarScope(oldScope);
        for(String s : vars)
            newScope.addSVar(s, null);
        env.setVarScope(newScope);
        return body.eval(env);
    }

}
