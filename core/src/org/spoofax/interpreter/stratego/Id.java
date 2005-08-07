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

public class Id extends Strategy {

    public boolean eval(IEnvironment e) throws FatalError {
        throw new FatalError("Unimplemented");
    }

}
