/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Interpreter;

public class Fail extends Strategy {

    public boolean eval(IContext e) throws FatalError {

        if (Interpreter.isDebugging()) {
            debug("Fail.eval() - ", e.current());
        }

        return false;
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("fail");
    }
}
