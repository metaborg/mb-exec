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

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.EvaluationStack;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class Build extends Strategy {

    private IStrategoAppl term;

    public Build(IStrategoAppl t) {
        term = t;
    }

    public boolean eval(IContext env, EvaluationStack es) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            debug("Build.eval() - ", env.current(), " -> !", term);
        }

        IStrategoTerm t = buildTerm(env, term);
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

    public IStrategoTerm buildTerm(IContext env, IStrategoAppl t) throws InterpreterException {

        ITermFactory factory = env.getFactory();

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

        throw new InterpreterException("Unknown build constituent '" + t.getConstructor() + "'");
    }

    private IStrategoTerm buildExplode(IContext env, IStrategoAppl t) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            debug("buildExplode() : ", t);
        }

        ITermFactory factory = env.getFactory();

        IStrategoAppl ctor = Tools.applAt(t, 0);
        IStrategoAppl args = Tools.applAt(t, 1);

        if (DebugUtil.isDebugging()) {
            debug(" ctor : ", ctor);
        }
        if (DebugUtil.isDebugging()) {
            debug(" args : ", args);
        }

        IStrategoTerm actualCtor = buildTerm(env, ctor);
        IStrategoTerm actualArgs = buildTerm(env, args);

        if(actualCtor == null || actualArgs == null)
            return null;
        
        if (DebugUtil.isDebugging()) {
            debug(" actualCtor : ", actualCtor);
        }
        if (DebugUtil.isDebugging()) {
            debug(" actualArgs : ", actualArgs);
        }

        if (Tools.isTermInt(actualCtor) || Tools.isTermReal(actualCtor)) {
            return actualCtor;
        }
        else if (Tools.isTermString(actualCtor)) {
            return doBuildExplode(factory, actualCtor, actualArgs);
        }
        else if (Tools.isTermList(actualCtor)) {
            return actualArgs;
        }

        throw new InterpreterException("Unknown term explosion : '" + t + "' " + t.getTermType()
                                       + " : " + actualCtor + " / " + actualArgs);
    }

    private IStrategoTerm doBuildExplode(ITermFactory factory, IStrategoTerm actualCtor, IStrategoTerm actualArgs) throws InterpreterException {
        if (!(Tools.isTermList(actualArgs))) {
            throw new InterpreterException("");
        }

        String n = ((IStrategoString)actualCtor).getValue();
        IStrategoList argList = (IStrategoList)actualArgs;
        IStrategoTerm[] realArgs = new IStrategoTerm[argList.size()];
        
        for(int i = 0; i < argList.size(); i++) {
            realArgs[i] = argList.get(i);
        }
        
        if (n.equals(""))
            return factory.makeTuple(realArgs);
        
        boolean quoted = false;
        if (n.length() > 1 && n.charAt(0) == '"') {
            n = n.substring(1, n.length() - 1);
            quoted = true;
        }

        if(quoted && realArgs.length == 0) {
            return factory.makeString(n);
        }
        
        IStrategoConstructor afun = factory.makeConstructor(n, argList.size(), quoted);
        return factory.makeAppl(afun, realArgs);
    }

    private IStrategoTerm buildVar(IContext env, IStrategoAppl t) throws InterpreterException {

        String n = Tools.javaStringAt(t, 0);
        return env.lookupVar(n);
    }

    private IStrategoString buildStr(IStrategoAppl t) {
        IStrategoString x = Tools.stringAt(t, 0);

        return x;
    }

    private IStrategoReal buildReal(IStrategoAppl t, ITermFactory factory) {
        String x = Tools.javaStringAt(t, 0);

        return factory.makeReal(new Double(x));
    }

    private IStrategoInt buildInt(IStrategoAppl t, ITermFactory factory) {
        String x = Tools.javaStringAt(t, 0);

        return factory.makeInt(new Integer(x).intValue());
    }

    private IStrategoTerm buildOp(IContext env, IStrategoAppl t, ITermFactory factory)
            throws InterpreterException {
        // FIXME memoize constructors
        
        String ctr = Tools.javaStringAt(t, 0);
        
        if(ctr.equals("")) {
            return buildTuple(env, t);
        } else if(ctr.equals("Nil")) {
            return buildNil(env);
        } else if(ctr.equals("Cons")) {
            return buildCons(env, t);
        } else {
            return buildOp(ctr, env, t, factory);
        }
    }
    
    private IStrategoList buildNil(IContext env) {
        return env.getFactory().makeList();
    }

    private IStrategoTerm buildOp(String ctr, IContext env, IStrategoAppl t, ITermFactory factory) 
    throws  InterpreterException {
        
        IStrategoTerm[] children = t.getSubterm(1).getAllSubterms();

        IStrategoConstructor ctor = factory.makeConstructor(ctr, children.length, false);
        IStrategoList kids = factory.makeList();

        for (int i = children.length -1 ; i >= 0; i--) {
            IStrategoTerm kid = buildTerm(env, (IStrategoAppl) children[i]);
            if (kid == null) {
                return null;
            }
            kids = kids.prepend(kid);
        }

        return factory.makeAppl(ctor, kids);
    }

    private IStrategoList buildCons(IContext env, IStrategoAppl t) throws InterpreterException {

        IStrategoList children = (IStrategoList) t.getSubterm(1);
        
        IStrategoAppl headPattern = (IStrategoAppl) children.get(0);
        IStrategoAppl tailPattern = (IStrategoAppl) children.get(1);
        
        IStrategoList tail = (IStrategoList) buildList(env, tailPattern); 
        IStrategoTerm head = buildTerm(env, headPattern);
        
        if(tail == null || head == null)
            return null;
        
        return tail.prepend(head);
    }

    private IStrategoList buildList(IContext env, IStrategoAppl t) throws InterpreterException {
        
        // FIXME improve! this is an Anno!
        if(Tools.isAnno(t, env)) {
            t = Tools.applAt(t, 0);
        
            String c = Tools.javaStringAt(t, 0);
            if(c.equals("Nil")) 
                return buildNil(env);
            else if(c.equals("Cons"))
                return buildCons(env, t);
        }
        else if(Tools.isVar(t, env)) {
            IStrategoTerm r = buildVar(env, t);
            if(r instanceof IStrategoList) {
                return (IStrategoList) r;
            }
            throw new InterpreterException("List tail must always be a list -- got " + r);
        }

        throw new InterpreterException("List tail must always be a list!");
    }


    private IStrategoTerm buildTuple(IContext env, IStrategoAppl t) throws InterpreterException {
        
        IStrategoTerm[] children = t.getSubterm(1).getAllSubterms();
        IStrategoTerm[] kids = new IStrategoTerm[children.length];

        for (int i = 0; i < children.length; i++) {
            IStrategoTerm kid = kids[i] = buildTerm(env, (IStrategoAppl) children[i]);
            if (kid == null) {
                return null;
            }
        }

        return env.getFactory().makeTuple(kids);
    }

    private IStrategoTerm buildAnno(IContext env, IStrategoAppl t) throws InterpreterException {
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
