/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IEnvironment;

public class Seq extends Strategy {

    protected Strategy s0;
    protected Strategy s1; 
    
    public Seq(Strategy s0, Strategy s1) {
        this.s0 = s0;
        this.s1 = s1;
    }

    public boolean eval(IEnvironment env) throws FatalError {
        return s0.eval(env) && s1.eval(env);
    }

}
