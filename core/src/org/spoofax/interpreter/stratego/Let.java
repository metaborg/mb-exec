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
import org.spoofax.interpreter.IntStrategy;
import org.spoofax.interpreter.Tools;

import aterm.ATermAppl;
import aterm.ATermList;

public class Let extends Strategy {

    protected List<SDefT> defs;
    protected Strategy body;
    
    public Let(List<SDefT> defs, Strategy body) {
        assert defs != null;
        this.defs = defs;
        this.body = body;
    }
    
    public boolean eval(IEnvironment env) throws FatalError {
        debug("evalLet() - " + env.current());
        DefScope oldScope = env.getDefScope();
        env.setDefScope(new DefScope(oldScope));
        env.getVarScope().dump(" ");
        env.getDefScope().add(defs);
        boolean r = body.eval(env);
        env.setDefScope(oldScope);
        return r;
    }
}
