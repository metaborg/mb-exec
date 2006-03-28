/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Interpreter;

public class Id extends Strategy {

    public boolean eval(IContext e) throws InterpreterException {

        if (Interpreter.isDebugging()) {
            debug("Id.eval() - ", e.current());
        }

        return true;
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("Id");
    }
}
