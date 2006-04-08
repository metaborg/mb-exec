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

import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.IContext;
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

    public boolean eval(IContext env) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            debug("Build.eval() - ", env.current(), " -> !", term);
        }

        ATerm t = buildTerm(env, term);
        if (t == null) {
            if(DebugUtil.isDebugging()) {
                return DebugUtil.traceReturn(false, env.current(), this);
            } else {
                return false;
            }
        }
        env.setCurrent(t);

        if(DebugUtil.isDebugging()) {
            return DebugUtil.traceReturn(true, env.current(), this);
        } else {
            return true;
        }
    }

    public ATerm buildTerm(IContext env, ATermAppl t) throws InterpreterException {

        PureFactory factory = env.getFactory();

        if (Tools.isAnno(t, env)) {
            return buildAnno(env, t);
        }
        else if (Tools.isOp(t, env)) {
            return buildOp(env, t, factory);
        }
        else if (Tools.isInt(t, env)) {
            return buildInt(t, factory);
        }
        else if (Tools.isReal(t, env)) {
            return buildReal(t, factory);
        }
        else if (Tools.isStr(t, env)) {
            return buildStr(t);
        }
        else if (Tools.isVar(t, env)) {
            return buildVar(env, t);
        }
        else if (Tools.isExplode(t, env)) {
            return buildExplode(env, t);
        }

        throw new InterpreterException("Unknown build constituent '" + t.getName() + "'");
    }

    private ATerm buildExplode(IContext env, ATermAppl t) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            debug("buildExplode() : ", t);
        }

        PureFactory factory = env.getFactory();

        ATermAppl ctor = Tools.applAt(t, 0);
        ATermAppl args = Tools.applAt(t, 1);

        if (DebugUtil.isDebugging()) {
            debug(" ctor : ", ctor);
        }
        if (DebugUtil.isDebugging()) {
            debug(" args : ", args);
        }

        ATerm actualCtor = buildTerm(env, ctor);
        ATerm actualArgs = buildTerm(env, args);

        if (DebugUtil.isDebugging()) {
            debug(" actualCtor : ", actualCtor);
        }
        if (DebugUtil.isDebugging()) {
            debug(" actualArgs : ", actualArgs);
        }

        if (Tools.isATermInt(actualCtor) || Tools.isATermReal(actualCtor)) {
            return actualCtor;
        }
        else if (Tools.isATermString(actualCtor)) {

            if (!(Tools.isATermAppl(actualArgs))) {
                return null;
            }

            String n = ((ATermAppl)actualCtor).getName();

            boolean quoted = false;
            if (n.length() > 1 && n.charAt(0) == '"') {
                n = n.substring(1, n.length() - 1);
                quoted = true;
            }
            ATermList realArgs = Tools.consToList(env, (ATermAppl)actualArgs);
            AFun afun = factory.makeAFun(n, realArgs.getChildCount(), quoted);
            return factory.makeApplList(afun, realArgs);
        }
        else if (Tools.isATermAppl(actualCtor)
          && Tools.isNil(((ATermAppl)actualCtor), env)) {
            return actualArgs;
        }

        throw new InterpreterException("Unknown explosion combination!");
    }

    private ATerm buildVar(IContext env, ATermAppl t) throws InterpreterException {

        String n = Tools.stringAt(t, 0);
        return env.lookupVar(n);
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
            throws InterpreterException {
        String ctr = Tools.stringAt(t, 0);
        ATermList children = (ATermList) t.getChildAt(1);

        AFun afun = factory.makeAFun(ctr, children.getLength(), false);
        ATermList kids = factory.makeList();

        for (int i = 0; i < children.getLength(); i++) {
            ATerm kid = buildTerm(env, (ATermAppl) children.elementAt(i));
            if (kid == null) {
                return null;
            }
            kids = kids.append(kid);
        }

        return factory.makeApplList(afun, kids);
    }

    private ATerm buildAnno(IContext env, ATermAppl t) throws InterpreterException {
        // FIXME: Actually build annotation
        return buildTerm(env, Tools.applAt(t, 0));
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.first("Build(" + term.toString() + ")");
    }

    @Override
    protected String getTraceName() {
        return super.getTraceName() + "(" + term + ")";
    }
}
