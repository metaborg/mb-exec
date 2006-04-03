/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.ArrayList;
import java.util.List;

import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.Pair;
import org.spoofax.interpreter.Tools;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermInt;
import aterm.ATermList;
import aterm.ATermReal;
import aterm.pure.PureFactory;

public class Match extends Strategy {

    protected ATermAppl pattern;

    public Match(ATermAppl pattern) {
        this.pattern = pattern;
    }

    public boolean eval(IContext env) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            debug("Match.eval() - ", " !", env.current(), " ; ?", pattern);
        }

        ATerm current = env.current();

        List<Pair<String, ATerm>> r = match(env, current, pattern);

        if (r == null) {
            if (DebugUtil.isDebugging()) {
                return DebugUtil.traceReturn(false, env.current(), this);
            }
            return false;
        }
        else {
            boolean b = env.bindVars(r);

            if (DebugUtil.isDebugging()) {
                debug("Bindings: " + r); //todo: unclear
                return DebugUtil.traceReturn(b, env.current(), this);
            }
            return b;
        }
    }

    public List<Pair<String, ATerm>> emptyList() {
        return new ArrayList<Pair<String, ATerm>>();
    }

    public List<Pair<String, ATerm>> matchApplAnno(IContext env, ATermAppl t,
            ATermAppl anno) throws InterpreterException {
        // FIXME: Actually process anno
        ATermAppl p = Tools.applAt(anno, 0);
        return match(env, t, p);
    }

    public List<Pair<String, ATerm>> matchAppl(IContext env, ATermAppl t,
            ATermAppl p) throws InterpreterException {

        if (p.getAFun() == env.getAnnoAFun()) {
            return matchApplAnno(env, t, p);
        } else if (p.getAFun() == env.getOpAFun()) {
            return matchApplOp(env, t, p);
        } else if (p.getAFun() == env.getIntAFun()) {
            return matchApplInt(env, t, p);
        } else if (p.getAFun() == env.getStrAFun()) {
            return matchApplStr(t, p);
        } else if (p.getAFun() == env.getVarAFun()) {
            return matchApplVar(t, p);
        } else if (p.getAFun() == env.getExplodeAFun()) {
            return matchAnyExplode(env, t, p);
        } else if (p.getAFun() == env.getAsAFun()) {
            return matchApplAs(env, t, p);
        } else if (p.getAFun() == env.getWldAFun()) {
            return emptyList();
        }

        throw new InterpreterException("Unknown Appl case '" + p + "'");
    }

    protected List<Pair<String, ATerm>> matchApplInt(IContext env, ATermAppl t,
            ATermAppl p) throws InterpreterException {
        if (t.getType() == ATerm.INT)
            return match(env, Tools.intAt(t, 0), Tools.applAt(p, 0));
        return null;
    }

    protected List<Pair<String, ATerm>> matchApplStr(ATermAppl t, ATermAppl p) {
        if (t.getName().equals(Tools.stringAt(p, 0)))
            return emptyList();
        return null;
    }

    protected List<Pair<String, ATerm>> matchApplVar(ATermAppl t, ATermAppl p) {
        List<Pair<String, ATerm>> r = new ArrayList<Pair<String, ATerm>>();
        r.add(new Pair<String, ATerm>(Tools.stringAt(p, 0), t));
        return r;
    }

    protected List<Pair<String, ATerm>> matchApplAs(IContext env, ATermAppl t,
            ATermAppl p) throws InterpreterException {

        List<Pair<String, ATerm>> r = match(env, t, Tools.applAt(p, 1));

        if (r == null)
            return null;

        if (DebugUtil.isDebugging()) {
            debug("matching ApplAs", p);
        }

        String varName = Tools.stringAt(Tools.applAt(p, 0), 0);
        r.add(new Pair<String, ATerm>(varName, t));

        return r;
    }

    protected List<Pair<String, ATerm>> matchApplOp(IContext env, ATermAppl t,
            ATermAppl p) throws InterpreterException {

        ATermList ctorArgs = Tools.listAt(p, 1);

        // Check if arity of the pattern matches that
        // of the term
        if (ctorArgs.getChildCount() != t.getChildCount())
            return null;

        // Check if the constructor name in the pattern
        // matches that of the term
        ATermAppl c = Tools.applAt(p, 0);
        if (!t.getName().equals(c.getName()))
            return null;

        // Recursively match all arguments to term
        List<Pair<String, ATerm>> r = new ArrayList<Pair<String, ATerm>>();
        for (int i = 0; i < t.getChildCount(); i++) {
            List<Pair<String, ATerm>> m = match(env, (ATerm) t.getChildAt(i),
                                                (ATermAppl) ctorArgs
                                                        .getChildAt(i));
            if (m != null)
                r.addAll(m);
            else
                return null;
        }

        return r;
    }

    protected List<Pair<String, ATerm>> matchInt(IContext env, ATermInt t,
            ATermAppl p) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("matching Int");
        }

        if (p.getAFun() == env.getAnnoAFun()) {
            return matchIntAnno(env, t, p);
        }
        else if (p.getAFun() == env.getIntAFun()) {
            return matchIntInt(t, p);
        }
        else if (p.getAFun() == env.getRealAFun()) {
            return null;
        }
        else if (p.getAFun() == env.getVarAFun()) {
            return matchIntVar(t, p);
        }
        else if (p.getAFun() == env.getOpAFun()) {
            return null;
        }
        else if (p.getAFun() == env.getExplodeAFun()) {
            return matchAnyExplode(env, t, p);
        }
        else if (p.getAFun() == env.getWldAFun()) {
            return matchIntWld(p);
        }
        else if (p.getAFun() == env.getAsAFun()) {
            return matchIntAs(t, p);
        }

        throw new InterpreterException("Unknown Int case '" + p + "'");
    }

    protected List<Pair<String, ATerm>> matchReal(IContext env, ATermReal t,
            ATermAppl p) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("matching Real");
        }

        if (p.getAFun() == env.getAnnoAFun()) {
            return matchRealAnno(env, t, p);
        }
        else if (p.getAFun() == env.getIntAFun()) {
            return null;
        }
        else if (p.getAFun() == env.getRealAFun()) {
            return matchRealReal(t, p);
        }
        else if (p.getAFun() == env.getVarAFun()) {
            return matchRealVar(t, p);
        }
        else if (p.getAFun() == env.getOpAFun()) {
            return null;
        }
        else if (p.getAFun() == env.getExplodeAFun()) {
            return matchAnyExplode(env, t, p);
        }
        else if (p.getAFun() == env.getWldAFun()) {
            return matchRealWld(p);
        }
        else if (p.getAFun() == env.getAsAFun()) {
            return matchRealAs(t, p);
        }

        throw new InterpreterException("Unknown Real case '" + p + "'");
    }

    private List<Pair<String, ATerm>> matchRealReal(ATermReal t, ATermAppl p) {

        Double realVal = new Double(Tools.stringAt(p, 0));

        if (realVal == t.getReal())
            return emptyList();

        return null;
    }

    private List<Pair<String, ATerm>> matchRealWld(ATermAppl p) {
        return emptyList();
    }

    private List<Pair<String, ATerm>> matchIntWld(ATermAppl p) {
        return emptyList();
    }

    protected List<Pair<String, ATerm>> matchIntAnno(IContext env, ATermInt t,
            ATermAppl p) throws InterpreterException {
        // FIXME: Do real match of annotations
        return match(env, t, Tools.applAt(p, 0));
    }

    protected List<Pair<String, ATerm>> matchRealAnno(IContext env, ATermInt t,
            ATermAppl p) throws InterpreterException {
        // FIXME: Do real match of annotations
        return match(env, t, Tools.applAt(p, 0));
    }

    protected List<Pair<String, ATerm>> matchRealAnno(IContext env,
            ATermReal t, ATermAppl p) throws InterpreterException {
        return match(env, t, Tools.applAt(p, 0));
    }

    protected List<Pair<String, ATerm>> matchIntInt(ATermInt t, ATermAppl p) {
        Integer intVal = new Integer(Tools.stringAt(p, 0));
        if (intVal == t.getInt())
            return emptyList();

        return null;
    }

    protected List<Pair<String, ATerm>> matchIntVar(ATermInt t, ATermAppl p) {

        List<Pair<String, ATerm>> r = new ArrayList<Pair<String, ATerm>>();
        r.add(new Pair<String, ATerm>(((ATermAppl) p.getChildAt(0)).getName(),
                                      t));

        return r;
    }

    protected List<Pair<String, ATerm>> matchRealVar(ATermReal t, ATermAppl p) {

        List<Pair<String, ATerm>> r = new ArrayList<Pair<String, ATerm>>();
        r.add(new Pair<String, ATerm>(((ATermAppl) p.getChildAt(0)).getName(),
                                      t));

        return r;
    }

    protected List<Pair<String, ATerm>> matchIntAs(ATermInt t, ATermAppl p) {

        List<Pair<String, ATerm>> r = new ArrayList<Pair<String, ATerm>>();
        String varName = Tools.stringAt(Tools.applAt(p, 0), 0);

        r.add(new Pair<String, ATerm>(varName, t));

        return r;
    }

    protected List<Pair<String, ATerm>> matchRealAs(ATermReal t, ATermAppl p) {

        List<Pair<String, ATerm>> r = new ArrayList<Pair<String, ATerm>>();
        String varName = Tools.stringAt(Tools.applAt(p, 0), 0);

        r.add(new Pair<String, ATerm>(varName, t));

        return r;
    }

    protected List<Pair<String, ATerm>> matchAnyExplode(IContext env, ATerm t,
            ATermAppl p) throws InterpreterException {

        ATermAppl opPattern = Tools.applAt(p, 0);
        ATermAppl argsPattern = Tools.applAt(p, 1);

        ATerm op = getTermConstructor(env, t);
        ATerm args = getTermArguments(env, t);

        List<Pair<String, ATerm>> opResult = match(env, op, opPattern);
        List<Pair<String, ATerm>> argsResult = match(env, args, argsPattern);

        if (opResult == null || argsResult == null)
            return null;

        opResult.addAll(argsResult);

        return opResult;
    }

    private ATerm getTermArguments(IContext env, ATerm t) throws InterpreterException {

        if (t.getType() == ATerm.INT || t.getType() == ATerm.REAL)
            return env.makeList(emptyATermList(env));
        else if (t.getType() == ATerm.APPL) {
            ATermAppl a = (ATermAppl)t;
            if(a.getAFun() == env.getNilAFun() || a.getAFun() == env.getConsAFun())
                return t;
            else
                return env.makeList(a.getArguments());
        }

        throw new InterpreterException("Unknown term '" + t + "'");
    }

    private ATermList emptyATermList(IContext env) {

        PureFactory factory = env.getFactory();

        return factory.makeList();
    }

    private ATerm getTermConstructor(IContext env, ATerm t) throws InterpreterException {

        if (t.getType() == ATerm.INT || t.getType() == ATerm.REAL) {
            return t;
        } else if (Tools.isATermString(t)) {
            return env.makeString("\"" + ((ATermAppl) t).getName() + "\"");
        } else if (t.getType() == ATerm.APPL) {
            ATermAppl a = (ATermAppl)t;
            if(a.getAFun() == env.getConsAFun() || a.getAFun() == env.getNilAFun())
                return env.getFactory().makeAppl(env.getNilAFun());
            else
                return env.makeString(((ATermAppl) t).getName());
        }

        throw new InterpreterException("Unknown term '" + t + "'");
    }

    public List<Pair<String, ATerm>> match(IContext env, ATerm t, ATermAppl p)
            throws InterpreterException {

        switch (t.getType()) {
        case ATerm.APPL:
            return matchAppl(env, (ATermAppl) t, p);
        case ATerm.INT:
            return matchInt(env, (ATermInt) t, p);
        case ATerm.REAL:
            return matchReal(env, (ATermReal) t, p);
        default:
            throw new InterpreterException("Unsupported term type : "
                    + t.getClass().toString() + " [" + t.getType() + "]");
        }
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.first("Match(" + pattern.toString() + ")");
    }

    @Override
    protected String getTraceName() {
        return super.getTraceName() + "(" + pattern + ")";
    }
}
