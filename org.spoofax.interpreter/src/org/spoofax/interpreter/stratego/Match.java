/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.ArrayList;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Pair;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoRef;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTermList;
import org.spoofax.interpreter.terms.IStrategoTuple;

public class Match extends Strategy {

    protected IStrategoAppl pattern;

    public Match(IStrategoAppl pattern) {
        this.pattern = pattern;
    }

    public boolean eval(IContext env) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            debug("Match.eval() - ", " !", env.current(), " ; ?", pattern);
        }

        IStrategoTerm current = env.current();

        Results r = match(env, current, pattern);

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

    public Results matchApplAnno(IContext env, IStrategoAppl t,
            IStrategoAppl anno) throws InterpreterException {
        // FIXME: Actually process anno
        IStrategoAppl p = Tools.applAt(anno, 0);
        return match(env, t, p);
    }

    public Results matchAppl(IContext env, IStrategoAppl t,
            IStrategoAppl p) throws InterpreterException {

        if (Tools.isAnno(p, env)) {
            return matchApplAnno(env, t, p);
        }
        else if (Tools.isOp(p, env)) {
            return matchApplOp(env, t, p);
        }
        else if (Tools.isInt(p, env)) {
            return matchApplInt(env, t, p);
        }
        else if (Tools.isStr(p, env)) {
            return matchApplStr(t, p);
        }
        else if (Tools.isVar(p, env)) {
            return matchApplVar(t, p);
        }
        else if (Tools.isExplode(p, env)) {
            return matchAnyExplode(env, t, p);
        }
        else if (Tools.isAs(p, env)) {
            return matchApplAs(env, t, p);
        }
        else if (Tools.isWld(p, env)) {
            return emptyList();
        }

        throw new InterpreterException("Unknown Appl case '" + p + "'");
    }

    protected Results matchApplInt(IContext env, IStrategoTerm t,
            IStrategoAppl p) throws InterpreterException {
        if (Tools.isTermInt(t))
            return match(env, Tools.intAt(t, 0), Tools.applAt(p, 0));
        return null;
    }

    protected Results matchApplStr(IStrategoTerm t, IStrategoTerm p) {

        throw new NotImplementedException();

        //if (t.equals(p))
        //    return emptyList();
        //return null;
    }

    protected Results matchApplVar(IStrategoAppl t, IStrategoAppl p) {
        String varName = Tools.javaStringAt(p, 0);
        return newResult(new Binding(varName, t));
    }

    protected Results matchApplAs(IContext env, IStrategoAppl t,
            IStrategoAppl p) throws InterpreterException {

        Results r = match(env, t, Tools.applAt(p, 1));

        if (r == null)
            return null;

        if (DebugUtil.isDebugging()) {
            debug("matching ApplAs", p);
        }

        String varName = Tools.javaStringAt(Tools.applAt(p, 0), 0);
        r.add(new Binding(varName, t));

        return r;
    }

    protected Results matchApplOp(IContext env, IStrategoAppl t,
            IStrategoAppl p) throws InterpreterException {


        String c = Tools.javaStringAt(p, 0);
        if(c.equals("")) {
            return matchApplTuple(env, t, p);
        } else if(c.equals("Nil")) {
            return matchApplNil(env, t);
        } else if(c.equals("Cons")) {
            return matchApplCons(env, t, p);
        }

        IStrategoTermList ctorArgs = Tools.listAt(p, 1);

        // Check if arity of the pattern matches that
        // of the term
        if (ctorArgs.getSubtermCount() != t.getSubtermCount())
            return null;

        // Check if the constructor name in the pattern
        // matches that of the term
        System.out.println(p);
        if (!t.getConstructor().getName().equals(c))
            return null;

        // Recursively match all arguments to term
        Results r = emptyList();
        for (int i = 0; i < ctorArgs.size(); i++) {
            Results m = match(env, t.getSubterm(i),
                              (IStrategoAppl) ctorArgs
                              .getSubterm(i));
            if (m != null)
                r.addAll(m);
            else
                return null;
        }

        return r;
    }

    private Results matchApplCons(IContext env, IStrategoAppl t, IStrategoAppl p) {
        throw new NotImplementedException();
    }

    private Results matchApplNil(IContext env, IStrategoAppl t) {
        throw new NotImplementedException();
    }

    private Results matchApplTuple(IContext env, IStrategoAppl t, IStrategoAppl p) {
        throw new NotImplementedException();
    }

    private Results emptyList() {
        return new Results();
    }


    protected Results matchInt(IContext env, IStrategoInt t,
            IStrategoAppl p) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("matching Int");
        }

        if (Tools.isAnno(p, env)) {
            return matchIntAnno(env, t, p);
        }
        else if (Tools.isInt(p, env)) {
            return matchIntInt(t, p);
        }
        else if (Tools.isReal(p, env)) {
            return null;
        }
        else if (Tools.isVar(p, env)) {
            return matchIntVar(t, p);
        }
        else if (Tools.isOp(p, env)) {
            return null;
        }
        else if (Tools.isExplode(p, env)) {
            return matchAnyExplode(env, t, p);
        }
        else if (Tools.isWld(p, env)) {
            return matchIntWld(p);
        }
        else if (Tools.isAs(p, env)) {
            return matchIntAs(t, p);
        }

        throw new InterpreterException("Unknown Int case '" + p + "'");
    }

    protected Results matchReal(IContext env, IStrategoReal t,
            IStrategoAppl p) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("matching Real");
        }

        if (Tools.isAnno(p, env)) {
            return matchRealAnno(env, t, p);
        }
        else if (Tools.isInt(p, env)) {
            return null;
        }
        else if (Tools.isReal(p, env)) {
            return matchRealReal(t, p);
        }
        else if (Tools.isVar(p, env)) {
            return matchRealVar(t, p);
        }
        else if (Tools.isOp(p, env)) {
            return null;
        }
        else if (Tools.isExplode(p, env)) {
            return matchAnyExplode(env, t, p);
        }
        else if (Tools.isWld(p, env)) {
            return matchRealWld(p);
        }
        else if (Tools.isAs(p, env)) {
            return matchRealAs(t, p);
        }

        throw new InterpreterException("Unknown Real case '" + p + "'");
    }

    private Results matchRealReal(IStrategoReal t, IStrategoAppl p) {

        Double realVal = new Double(Tools.javaStringAt(p, 0));

        if (realVal == t.getValue())
            return emptyList();

        return null;
    }

    private Results matchRealWld(IStrategoAppl p) {
        return emptyList();
    }

    private Results matchIntWld(IStrategoAppl p) {
        return emptyList();
    }

    protected Results matchIntAnno(IContext env, IStrategoInt t,
            IStrategoAppl p) throws InterpreterException {
        // FIXME: Do real match of annotations
        return match(env, t, Tools.applAt(p, 0));
    }

    protected Results matchRealAnno(IContext env, IStrategoReal t,
            IStrategoAppl p) throws InterpreterException {
        // FIXME: Do real match of annotations
        return match(env, t, Tools.applAt(p, 0));
    }
    /*
    protected List<Pair<String, IStrategoTerm>> matchRealAnno(IContext env,
            IStrategoReal t, IStrategoAppl p) throws InterpreterException {
        return match(env, t, Tools.applAt(p, 0));
    }
     */

    protected Results matchIntInt(IStrategoInt t, IStrategoAppl p) {
        Integer intVal = new Integer(Tools.javaStringAt(p, 0));
        if (intVal == t.getValue())
            return emptyList();

        return null;
    }

    protected Results matchIntVar(IStrategoInt t, IStrategoAppl p) {
        String varName = Tools.javaStringAt(p, 0);
        return newResult(new Binding(varName, t));
    }

    protected Results matchRealVar(IStrategoReal t, IStrategoAppl p) {
        String varName = Tools.javaStringAt(p, 0);
        return newResult(new Binding(varName, t));
    }

    protected Results matchIntAs(IStrategoInt t, IStrategoAppl p) {
        String varName = Tools.javaStringAt(Tools.applAt(p, 0), 0);
        return newResult(new Binding(varName, t));
    }

    @SuppressWarnings("serial")
    public static final class Results extends ArrayList<Binding> {
    }

    public static final class Binding extends Pair<String, IStrategoTerm> {
        public Binding(String first, IStrategoTerm second) {
            super(first, second);
        }
    }

    protected Results matchRealAs(IStrategoReal t, IStrategoAppl p) {
        String varName = Tools.javaStringAt(Tools.applAt(p, 0), 0);
        return newResult(new Binding(varName, t));
    }

    private Results newResult(Binding initial) {
        Results r = new Results();
        r.add(initial);
        return r;
    }

    protected Results matchAnyExplode(IContext env, IStrategoTerm t,
            IStrategoAppl p) throws InterpreterException {

        IStrategoAppl opPattern = Tools.applAt(p, 0);
        IStrategoAppl argsPattern = Tools.applAt(p, 1);

        IStrategoTerm op = getTermConstructor(env, t);
        IStrategoTerm args = getTermArguments(env, t);

        Results opResult = match(env, op, opPattern);
        Results argsResult = match(env, args, argsPattern);

        if (opResult == null || argsResult == null)
            return null;

        opResult.addAll(argsResult);

        return opResult;
    }

    private IStrategoTerm getTermArguments(IContext env, IStrategoTerm t) throws InterpreterException {

        if (Tools.isTermInt(t) || Tools.isTermReal(t))
            return env.getFactory().makeList();
        else if (Tools.isTermAppl(t)) {
            IStrategoAppl a = (IStrategoAppl)t;
            if (Tools.isNil(a, env) || Tools.isCons(a, env))
                return t;
            else
                return env.getFactory().makeList(a.getArguments());
        }

        throw new InterpreterException("Unknown term '" + t + "'");
    }

    private IStrategoTerm getTermConstructor(IContext env, IStrategoTerm t) throws InterpreterException {

        if (Tools.isTermInt(t) || Tools.isTermReal(t)) {
            return t;
        } else if (Tools.isTermString(t)) {
            return env.getFactory().makeString("\"" + ((IStrategoString) t).getValue() + "\"");
        } else if (Tools.isTermAppl(t)) {
            IStrategoAppl a = (IStrategoAppl)t;
            if (Tools.isCons(a, env) || Tools.isNil(a, env))
                return env.getFactory().makeAppl(env.getStrategoSignature().getNil());
            else
                return env.getFactory().makeString(((IStrategoAppl)t).getConstructor().getName());
        }

        throw new InterpreterException("Unknown term '" + t + "'");
    }

    public Results match(IContext env, IStrategoTerm t, IStrategoAppl p)
    throws InterpreterException {

        switch (t.getTermType()) {
        case IStrategoTerm.APPL:
            return matchAppl(env, (IStrategoAppl) t, p);
        case IStrategoTerm.INT:
            return matchInt(env, (IStrategoInt) t, p);
        case IStrategoTerm.REAL:
            return matchReal(env, (IStrategoReal) t, p);
        case IStrategoTerm.STRING:
            return matchString(env, (IStrategoString) t, p);
        case IStrategoTerm.LIST:
            return matchList(env, (IStrategoTermList) t, p);
        case IStrategoTerm.TUPLE:
            return matchTuple(env, (IStrategoTuple) t, p);
        case IStrategoTerm.REF:
            return matchRef(env, (IStrategoRef)t, p);
        default:
            throw new InterpreterException("Unsupported term type : "
                                           + t.getClass().toString() + " [" + t.getTermType() + "]");
        }
    }

    private Results matchRef(IContext env, IStrategoRef ref, IStrategoAppl p) {
        throw new NotImplementedException();
    }

    private Results matchTuple(IContext env, IStrategoTuple tuple, IStrategoAppl p) {
        if (DebugUtil.isDebugging()) {
            debug("matching Tuple");
        }
        throw new NotImplementedException();
    }

    protected Results matchList(IContext env, IStrategoTermList t,
            IStrategoAppl p) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("matching List");
        }

        if (Tools.isAnno(p, env)) {
            return matchListAnno(env, t, p);
        }
        else if (Tools.isInt(p, env)) {
            return null;
        }
        else if (Tools.isReal(p, env)) {
            return null;
        }
        else if (Tools.isVar(p, env)) {
            return matchListVar(t, p);
        }
        else if (Tools.isOp(p, env)) {
            throw new NotImplementedException();
        }
        else if (Tools.isExplode(p, env)) {
            return matchAnyExplode(env, t, p);
        }
        else if (Tools.isWld(p, env)) {
            return matchListWld(p);
        }
        else if (Tools.isAs(p, env)) {
            return matchListAs(t, p);
        }

        throw new InterpreterException("Unknown List case '" + p + "'");
    }

    private Results matchListVar(IStrategoTermList t, IStrategoAppl p) {
        String varName = Tools.javaStringAt(p, 0);
        return newResult(new Binding(varName, t));
    }

    private Results matchListAs(IStrategoTermList t, IStrategoAppl p) {
        String varName = Tools.javaStringAt(Tools.applAt(p, 0), 0);
        return newResult(new Binding(varName, t));
    }

    private Results matchListWld(IStrategoAppl p) {
        return emptyList();
    }

    private Results matchListAnno(IContext env, IStrategoTermList t, IStrategoAppl p) throws InterpreterException {
        // FIXME: Do real match of annotations
        return match(env, t, Tools.applAt(p, 0));
    }

    private Results matchString(IContext env, IStrategoString string, IStrategoAppl p) {
        if (DebugUtil.isDebugging()) {
            debug("matching String");
        }
        throw new NotImplementedException();
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.first("Match(" + pattern.toString() + ")");
    }

    @Override
    protected String getTraceName() {
        return super.getTraceName() + "(" + pattern + ")";
    }
}
