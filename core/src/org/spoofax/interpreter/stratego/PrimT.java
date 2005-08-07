/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;

import aterm.ATermList;

public class PrimT extends Strategy {

    protected String name;
    protected ATermList svars;
    protected ATermList tvars;
    
    public PrimT(String name, ATermList svars, ATermList tvars) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
    }

    public boolean eval(IContext e) throws FatalError {
        throw new FatalError("Unimplemented");
    }

}
