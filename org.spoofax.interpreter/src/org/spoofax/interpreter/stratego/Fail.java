/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;

public class Fail extends Strategy {

    public boolean eval(IContext e) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("Fail.eval() - ", e.current());
        }

        return DebugUtil.traceReturn(false, e.current(), this);
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("fail");
    }
}
