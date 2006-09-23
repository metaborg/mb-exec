/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;

public class Some extends Strategy {

    protected Strategy body;
    
    public Some(Strategy body) {
        this.body = body;
    }

    public boolean eval(IContext e) throws InterpreterException {
        throw new InterpreterException("Unimplemented");
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("Some(");
        body.prettyPrint(sf);
        sf.append(")");
    }
}
