/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Context;
import org.spoofax.interpreter.Tools;

import aterm.AFun;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;
import aterm.pure.PureFactory;

public class Build extends Strategy {

    private ATermAppl term;
    
    public Build(ATermAppl t) {
        term = t;
    }
    
    public boolean eval(IContext e) throws FatalError {
        debug("Build.eval()");
        
        ATerm t = buildTerm(e, term);
        if(t == null)
            return false;
        Context.debug("" + t);
        e.setCurrent(t);
        return true;
    }
    
    public ATerm buildTerm(IContext env, ATermAppl t) throws FatalError {
        PureFactory factory = env.getFactory();
        
        if (t.getName().equals("Anno")) {
            return buildTerm(env, Tools.applAt(t, 0));
        } else if (t.getName().equals("Op")) {
            String ctr = Tools.stringAt(t, 0);
            ATermList children = (ATermList) t.getChildAt(1);

            AFun afun = factory.makeAFun(ctr, children.getLength(), false);
            ATermList kids = factory.makeList();

            for (int i = 0; i < children.getLength(); i++) {
                kids = kids
                        .append(buildTerm(env, (ATermAppl) children.elementAt(i)));
            }
            return factory.makeApplList(afun, kids);
        } else if (t.getName().equals("Int")) {
            ATermAppl x = Tools.applAt(t, 0);
            return factory.makeInt(new Integer(x.getName()));
        } else if (t.getName().equals("Str")) {
            ATermAppl x = Tools.applAt(t, 0);
            return x;
        } else if (t.getName().equals("Var")) {
            String n = Tools.stringAt(t, 0);
            ATerm x = env.lookupVar(n);
            Context.debug(" lookup : " + n + " (= " + x + ")");
            return x;
        }

        throw new FatalError("Unknown build constituent '" + t.getName() + "'");
    }
    
}
