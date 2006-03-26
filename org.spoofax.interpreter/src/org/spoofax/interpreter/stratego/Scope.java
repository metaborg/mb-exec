/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.List;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.VarScope;
import org.spoofax.interpreter.Interpreter;

public class Scope extends Strategy {

    protected List<String> vars;
    protected Strategy body;

    public Scope(List<String> vars, Strategy body) {
        this.vars = vars;
        this.body = body;
    }

    public boolean eval(IContext env) throws FatalError {

        if (Interpreter.isDebugging()) {
            debug("Scope.eval() - ", env.current());
        }

        StringBuffer sb = new StringBuffer();

        VarScope oldScope = env.getVarScope();
        VarScope newScope = new VarScope(oldScope);

        // oldScope.dump("o - ");

        for (String s : vars) {
            newScope.add(s, null);
            sb.append(s + ",");
        }

        if (Interpreter.isDebugging()) {
            debug(" vars : [", sb, "]");
        }

        env.setVarScope(newScope);

        boolean r = body.eval(env);

        env.setVarScope(oldScope);

        if (Interpreter.isDebugging()) {
            debug("<scope, dropped : [", sb, "]");
        }

        return r;
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("Scope(\n");
        sf.bump(6);
        sf.append(", " + vars);
        sf.append(", ");
        body.prettyPrint(sf);
        sf.append(")");
        sf.unbump(6);
    }

}
