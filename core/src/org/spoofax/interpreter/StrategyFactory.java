/*
 * Created on 17.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import aterm.ATermAppl;

public class StrategyFactory {

    public static OldStrategy create(ATermAppl t, DefScope defScope, VarScope varScope) throws FatalError {
        if(t.getName().equals("SDefT"))
            return new IntStrategy(t, defScope, varScope);
        else if(t.getName().equals("ExtSDef"))
            throw new FatalError("Cannot work with external SDefs");
        
        throw new FatalError("Unknown strategy type " + t.getName());
    }
}
