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

public class Seq extends Strategy {

    protected Strategy[] children;
    

    public Seq(Strategy[] strategies) {
    	children = strategies;
	}

	public boolean eval(IContext env) throws InterpreterException {

//        if (Interpreter.isDebugging()) {
//            debug("Seq.eval() - ", env.current());
//        }

        for (int i = 0; i < children.length; i++) {
            //if(children[i].eval(env) == false)
            //    return false;
            env.getChoicePointStack().addNext(children[children.length - i - 1], env.getVarScope());
            //ns.addNext(children[i]);
		}
		return true;
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.first("Seq(");
        sf.bump(4);
        sf.append("  ");
        for (int i = 0; i < (children.length - 1); i++) {
        	children[i].prettyPrint(sf);
        	sf.append(", ");
        }
        children[children.length - 1].prettyPrint(sf);
        sf.unbump(4);
        sf.line(")");
    }
}
