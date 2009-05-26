/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.List;

import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.VarScope;

public class Scope extends Strategy {

    protected List<String> vars;
    protected Strategy body;

    public Scope(List<String> vars, Strategy body) {
        this.vars = vars;
        this.body = body;
    }

    public IConstruct eval(final IContext env) throws InterpreterException {

//        if (Interpreter.isDebugging()) {
//            debug("Scope.eval() - ", env.current());
//        }

        VarScope newScope = new VarScope(env.getVarScope());

        newScope.addVars(vars);

        env.setVarScope(newScope);
        final Strategy th = this;
        body.getHook().push(new Hook(){
			@Override
			public IConstruct onFailure(IContext env) throws InterpreterException {
				env.popVarScope();
				return th.getHook().pop().onFailure(env);
			}
			@Override
			public IConstruct onSuccess(IContext env) throws InterpreterException {
		        env.popVarScope();
				return th.getHook().pop().onSuccess(env);
			}
        	
        });
        return body;
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
