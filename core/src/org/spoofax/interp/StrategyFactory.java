/*
 * Created on 17.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

import aterm.ATermAppl;

public class StrategyFactory {

    public static Strategy create(ATermAppl t) throws FatalError {
        if(t.getName().equals("SDefT"))
            return new IntStrategy(t);
        else if(t.getName().equals("ExtSDef"))
            return new ExtStrategy(t);
        
        throw new FatalError("Unknown strategy type " + t.getName());
    }
}
