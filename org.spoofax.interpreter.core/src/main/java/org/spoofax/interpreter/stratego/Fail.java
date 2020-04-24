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
import org.spoofax.interpreter.util.DebugUtil;


import static org.spoofax.interpreter.core.Context.debug;

public class Fail extends Strategy {

    public IConstruct eval(IContext e) throws InterpreterException {

        debug("Fail.eval() - ", e.current());

        return getHook().pop().onFailure(e);
    }

    @Override
    public String toString() {
    	return "Fail()";
    }
    
    public void prettyPrint(StupidFormatter sf) {
        sf.append("fail");
    }
}
