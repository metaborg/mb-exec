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

public class Seq extends Strategy {

    protected Strategy s0;
    protected Strategy s1;

    public Seq(Strategy s0, Strategy s1) {
        this.s0 = s0;
        this.s1 = s1;
    }

    public boolean eval(IContext env) throws FatalError {

        if (Interpreter.isDebugging()) {
            debug("Seq.eval() - ", env.current());
        }

        return s0.eval(env) && s1.eval(env);
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.first("Seq(");
        sf.bump(4);
        sf.append("  ");
        s0.prettyPrint(sf);
        sf.append(", ");
        s1.prettyPrint(sf);
        sf.unbump(4);
        sf.line(")");
    }
}
