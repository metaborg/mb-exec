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

public class GuardedLChoice extends Strategy {

    protected Strategy cond;
    protected Strategy ifClause;
    protected Strategy thenClause;
    
    public GuardedLChoice(Strategy cond, Strategy ifclause, Strategy thenclause) {
        this.cond = cond;
        ifClause = ifclause;
        thenClause = thenclause;
    }

    public boolean eval(IEnvironment e) throws FatalError {
        throw new FatalError("Unimplemented");
    }

}
