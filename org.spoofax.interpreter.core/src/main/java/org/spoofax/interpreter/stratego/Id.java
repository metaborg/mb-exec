/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;

public class Id extends Strategy {

    public IConstruct eval(IContext e) throws InterpreterException {

        debug("Id.eval() - ", e.current());

        return getHook().pop().onSuccess(e);
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("Id");
    }
}
