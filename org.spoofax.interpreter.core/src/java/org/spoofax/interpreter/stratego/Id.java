/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;

public class Id extends Strategy {

    public IConstruct eval(IContext e) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("Id.eval() - ", e.current());
        }

        return getHook().pop().onSuccess(e);
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("Id");
    }
}
