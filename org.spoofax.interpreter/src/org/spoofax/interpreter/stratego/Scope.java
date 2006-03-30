/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.List;

import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.VarScope;

public class Scope extends Strategy {

    protected List<String> vars;
    protected Strategy body;

    public Scope(List<String> vars, Strategy body) {
        this.vars = vars;
        this.body = body;
    }

    public boolean eval(IContext env) throws InterpreterException {

//        if (Interpreter.isDebugging()) {
//            debug("Scope.eval() - ", env.current());
//        }

        VarScope newScope = new VarScope(env.getVarScope());

        newScope.addVars(vars);

        env.setVarScope(newScope);

        boolean r = body.eval(env);

        env.popVarScope();

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
