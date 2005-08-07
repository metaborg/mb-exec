/*
 * Created on 17.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter;

import aterm.ATermAppl;

public class StrategyFactory {

    public static Strategy create(ATermAppl t, DefScope defScope, VarScope varScope) throws FatalError {
        if(t.getName().equals("SDefT"))
            return new IntStrategy(t, defScope, varScope);
        else if(t.getName().equals("ExtSDef"))
            throw new FatalError("Cannot work with external SDefs");
        
        throw new FatalError("Unknown strategy type " + t.getName());
    }
}
