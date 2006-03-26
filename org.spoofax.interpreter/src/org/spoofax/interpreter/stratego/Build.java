/*
 * Evaluation of the StrategoCore Build term
 * 
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 * 
 * Part of Spoofax
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.FatalError;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Context;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.Interpreter;

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
        if (Interpreter.isDebugging()) {
            debug("Build.eval() - ", env.current());
        }

        if (Interpreter.isDebugging()) {
            debug(" pattern  : ", term);
        }

        ATerm t = buildTerm(env, term);
        if (t == null) {
            if (Interpreter.isDebugging()) {
                debug(" build failed");
            }
            return false;
        }
        Context.debug(" built : ", t);

        env.setCurrent(t);

        return true;
    }

    public ATerm buildTerm(IContext env, ATermAppl t) throws FatalError {
        
        PureFactory factory = env.getFactory();

        if (Tools.isAnno(t)) {
            return buildAnno(env, t);
        } else if (Tools.isOp(t)) {
            return buildOp(env, t, factory);
        } else if (Tools.isInt(t)) {
            return buildInt(t, factory);
        } else if (Tools.isReal(t)) {
            return buildReal(t, factory);
        } else if (Tools.isStr(t)) {
            return buildStr(t);
        } else if (Tools.isVar(t)) {
            return buildVar(env, t);
        } else if (Tools.isExplode(t)) {
            return buildExplode(env, t);
        }

        throw new FatalError("Unknown build constituent '" + t.getName() + "'");
    }

    private ATerm buildExplode(IContext env, ATermAppl t) throws FatalError {
        if (Interpreter.isDebugging()) {
            debug("buildExplode() : ", t);
        }

        PureFactory factory = env.getFactory();

        ATermAppl ctor = Tools.applAt(t, 0);
        ATermAppl args = Tools.applAt(t, 1);

        if (Interpreter.isDebugging()) {
            debug(" ctor : ", ctor);
        }
        if (Interpreter.isDebugging()) {
            debug(" args : ", args);
        }

        ATerm actualCtor = buildTerm(env, ctor);
        ATerm actualArgs = buildTerm(env, args);

        if (Interpreter.isDebugging()) {
            debug(" actualCtor : ", actualCtor);
        }
        if (Interpreter.isDebugging()) {
            debug(" actualArgs : ", actualArgs);
        }

        if (Tools.isATermInt(actualCtor) || Tools.isATermReal(actualCtor)) {
            return actualCtor;
        }
        else if (Tools.isATermString(actualCtor)) {

            if (!Tools.isATermAppl(actualArgs))
                return null;

            String n = ((ATermAppl)actualCtor).getName();

            boolean quoted = false;
            if (n.length() > 1 && n.charAt(0) == '"') {
                n = n.substring(1, n.length() - 1);
                quoted = true;
            }
            ATermList realArgs = Tools.consToList(factory,
              (ATermAppl)actualArgs);
            AFun afun = factory.makeAFun(n, realArgs.getChildCount(), quoted);
            return factory.makeApplList(afun, realArgs);
        }
        else if (Tools.isATermAppl(actualCtor)
          && Tools.isNil((ATermAppl)actualCtor)) {
            return actualArgs;
        }

        throw new FatalError("Unknown explosion combination!");
    }

    private ATerm buildVar(IContext env, ATermAppl t) throws FatalError {
        
        String n = Tools.stringAt(t, 0);
        ATerm x = env.lookupVar(n);
        
        Context.debug(" lookup : ", n, " (= ", x, ")");
        
        return x;
    }

    private ATerm buildStr(ATermAppl t) {
        ATermAppl x = Tools.applAt(t, 0);
        
        return x;
    }

    private ATerm buildReal(ATermAppl t, PureFactory factory) {
        ATermAppl x = Tools.applAt(t, 0);
        
        return factory.makeReal(new Double(x.getName()));
    }

    private ATerm buildInt(ATermAppl t, PureFactory factory) {
        ATermAppl x = Tools.applAt(t, 0);

        return factory.makeInt(new Integer(x.getName()));
    }

    private ATerm buildOp(IContext env, ATermAppl t, PureFactory factory)
            throws FatalError {
        String ctr = Tools.stringAt(t, 0);
        ATermList children = (ATermList) t.getChildAt(1);

        AFun afun = factory.makeAFun(ctr, children.getLength(), false);
        ATermList kids = factory.makeList();

        for (int i = 0; i < children.getLength(); i++) {
            ATerm kid = buildTerm(env, (ATermAppl) children.elementAt(i));
            if (kid == null)
                return null;
            kids = kids.append(kid);
        }
        
        return factory.makeApplList(afun, kids);
    }

    private ATerm buildAnno(IContext env, ATermAppl t) throws FatalError {
        // FIXME: Actually build annotation
        return buildTerm(env, Tools.applAt(t, 0));
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.first("Build(" + term.toString() + ")");
    }
}
