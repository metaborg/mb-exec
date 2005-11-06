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

    public boolean eval(IContext env) throws FatalError {
        debug("Build.eval() - " + env.current());

        debug(" pattern  : " + term);
        
        ATerm t = buildTerm(env, term);
        if (t == null) {
            debug(" build failed");
            return false;
        }
        Context.debug(" built : " + t);
        env.setCurrent(t);
        return true;
    }

    public ATerm buildTerm(IContext env, ATermAppl t) throws FatalError {
        PureFactory factory = env.getFactory();

        if (t.getName().equals("Anno")) {
            // FIXME: Deal with Anno
            return buildTerm(env, Tools.applAt(t, 0));
        } else if (t.getName().equals("Op")) {
            String ctr = Tools.stringAt(t, 0);
            ATermList children = (ATermList) t.getChildAt(1);

            AFun afun = factory.makeAFun(ctr, children.getLength(), false);
            ATermList kids = factory.makeList();

            for (int i = 0; i < children.getLength(); i++) {
                kids = kids.append(buildTerm(env, (ATermAppl) children
                        .elementAt(i)));
            }
            return factory.makeApplList(afun, kids);
        } else if (t.getName().equals("Int")) {
            ATermAppl x = Tools.applAt(t, 0);
            return factory.makeInt(new Integer(x.getName()));
        } else if (t.getName().equals("Real")) {
            ATermAppl x = Tools.applAt(t, 0);
            return factory.makeReal(new Double(x.getName()));
        } else if (t.getName().equals("Str")) {
            ATermAppl x = Tools.applAt(t, 0);
            return x;
        } else if (t.getName().equals("Var")) {
            String n = Tools.stringAt(t, 0);
            ATerm x = env.lookupVar(n);
            Context.debug(" lookup : " + n + " (= " + x + ")");
            return x;
        } else if (t.getName().equals("Explode")) {
            debug("Term construction");
            return buildExplode(env, Tools.applAt(t, 0), Tools.applAt(t, 1));
        }

        throw new FatalError("Unknown build constituent '" + t.getName() + "'");
    }

    private ATerm buildExplode(IContext env, ATermAppl ctor, ATermAppl applArgs)
            throws FatalError {
        PureFactory factory = env.getFactory();

        ATermAppl ctorTerm = (ATermAppl) dropAnno(ctor);
        String ctorName = Tools.stringAt(ctorTerm, 0);

        debug(" ctor : '" + ctorName + "'");
        
        if (applArgs.getName().equals("Var")) {
            String var = Tools.stringAt(applArgs, 0);
            debug(" cons : " + env.lookupVar(var));
            ATermList t = Tools.consToList(factory, (ATermAppl) env.lookupVar(var));
            debug(" val  : " + t);
            AFun afun = factory.makeAFun(ctorName, t.getChildCount(), false);
            return factory.makeApplList(afun, t);
        } else if (applArgs.getName().equals("Anno")) {
            ATermAppl explArgs = (ATermAppl) dropAnno(applArgs);
            if (explArgs.getName().equals("Op")) {
                ATermList args = Tools.listAt(explArgs, 1);
                AFun afun = factory.makeAFun(ctorName, args.getChildCount(),
                                             false);
                ATermList vals = lookupVars(env, args);
                debug(" vals : " + vals);
                return factory.makeApplList(afun, vals);
            }
        }

        throw new FatalError("Unknown Explode constitutent '"
                + applArgs.getName() + "'");
    }

    private ATermList lookupVars(IContext env, ATermList args)
            throws FatalError {
        PureFactory factory = env.getFactory();
        ATermList r = factory.makeList();

        for (int i = 0; i < args.getChildCount(); i++)
            r.append(env.lookupVar(Tools.stringAt(Tools.termAt(args, i), 0)));

        return r;
    }

    private ATerm dropAnno(ATermAppl t) {
        return Tools.termAt(t, 0);
    }

}
