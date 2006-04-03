/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.spoofax.interpreter.stratego.All;
import org.spoofax.interpreter.stratego.Build;
import org.spoofax.interpreter.stratego.CallT;
import org.spoofax.interpreter.stratego.ExtSDef;
import org.spoofax.interpreter.stratego.Fail;
import org.spoofax.interpreter.stratego.GuardedLChoice;
import org.spoofax.interpreter.stratego.Id;
import org.spoofax.interpreter.stratego.Let;
import org.spoofax.interpreter.stratego.Match;
import org.spoofax.interpreter.stratego.One;
import org.spoofax.interpreter.stratego.OpDecl;
import org.spoofax.interpreter.stratego.PrimT;
import org.spoofax.interpreter.stratego.SDefT;
import org.spoofax.interpreter.stratego.Scope;
import org.spoofax.interpreter.stratego.Seq;
import org.spoofax.interpreter.stratego.Some;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.stratego.StupidFormatter;
import org.spoofax.interpreter.stratego.DebugUtil;
import org.spoofax.interpreter.stratego.SDefT.FunType;
import org.spoofax.interpreter.stratego.SDefT.ArgType;
import org.spoofax.interpreter.stratego.SDefT.ConstType;
import org.spoofax.interpreter.stratego.SDefT.SVar;
import org.spoofax.interpreter.library.SSL;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;
import aterm.AFun;

public class Interpreter extends ATermBuilder {

    protected Context context;

    public Interpreter() {
        DebugUtil.setDebug(false);
        Context.indentation = 0;
        context = new Context();
        factory = context.factory;
        if(DebugUtil.resetSSL) {
            SSL.init();//todo: temporary to verify the hypothesis that the global state causes trouble.
        }
    }

    public void load(String path) throws IOException, InterpreterException {

        ATerm prg = context.getFactory().readFromFile(path);
        ATerm sign = Tools.applAt(Tools.listAt(prg, 0), 0);
        ATerm strats = Tools.applAt(Tools.listAt(prg, 0), 1);

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(prg);
        }

        loadConstructors(Tools.listAt(Tools.applAt(Tools.listAt(sign, 0), 0), 0));
        loadStrategies(Tools.listAt(strats, 0));
    }

    private void loadConstructors(ATermList list) {
        for (int i = 0; i < list.getChildCount(); i++) {
            String name = Tools.stringAt(Tools.applAt(list, i), 0);
            context.addOpDecl(name, new OpDecl(name));
        }
    }

    private void loadStrategies(ATermList list) throws InterpreterException {
        for (int i = 0; i < list.getChildCount(); i++) {
            ATermAppl t = Tools.applAt(list, i);
            if(t.getAFun() == context.getSDefTAFun()) {
                SDefT def = parseSDefT(t);
                context.addSVar(def.getName(), def);
            } else if(t.getAFun() == context.getExtSDefAFun()) {
                ExtSDef def = parseExtSDef(t);
                context.addSVar(def.getName(), def);
                // FIXME: Come up with a good solution for external
                // definitions
                throw new InterpreterException("Illegal ExtSDef in StrategoCore file");
            }
        }

    }

    private ExtSDef parseExtSDef(ATermAppl t) {

        String name = Tools.stringAt(t, 0);
        ATermList svars = Tools.listAt(t, 1);
        ATermList tvars = Tools.listAt(t, 2);

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug("name  : ", name);
        }

        List<SVar> realsvars = makeSVars(svars);
        List<String> realtvars = makeVars(tvars);

        VarScope oldScope = context.getVarScope();
        VarScope newScope = new VarScope(oldScope);

        return new ExtSDef(name, realsvars, realtvars, newScope);
    }

    private Strategy parseStrategy(ATermAppl appl) throws InterpreterException {

        String op = appl.getName();

        if (op.equals("Build")) {
            return parseBuild(appl);
        } else if (op.equals("Scope")) {
            return parseScope(appl);
        } else if (op.equals("Seq")) {
            return parseSeq(appl);
        } else if (op.equals("GuardedLChoice")) {
            return parseGuardedLChoice(appl);
        } else if (op.equals("Match")) {
            return parseMatch(appl);
        } else if (op.equals("Id")) {
            return parseId(appl);
        } else if (op.equals("CallT")) {
            return parseCallT(appl);
        } else if (op.equals("PrimT")) {
            return parsePrimT(appl);
        } else if (op.equals("Let")) {
            return parseLet(appl);
        } else if (op.equals("Fail")) {
            return makeFail(appl);
        } else if (op.equals("All")) {
            return makeAll(appl);
        } else if (op.equals("One")) {
            return makeOne(appl);
        } else if (op.equals("Some")) {
            return makeSome(appl);
        }

        throw new InterpreterException("Unknown op '" + op + "'");
    }

    private Some makeSome(ATermAppl t) throws InterpreterException {
        Strategy body = parseStrategy(Tools.applAt(t, 0));
        return new Some(body);
    }

    private One makeOne(ATermAppl t) throws InterpreterException {
        Strategy body = parseStrategy(Tools.applAt(t, 0));
        return new One(body);
    }

    private All makeAll(ATermAppl t) throws InterpreterException {
        Strategy body = parseStrategy(Tools.applAt(t, 0));
        return new All(body);
    }

    private Strategy makeFail(ATermAppl appl) {
        return new Fail();
    }

    private Let parseLet(ATermAppl t) throws InterpreterException {

        ATermList l = Tools.listAt(t, 0);
        List<SDefT> defs = new ArrayList<SDefT>();

        for (int i = 0; i < l.getChildCount(); i++) {
            defs.add(parseSDefT(Tools.applAt(l, i)));
        }

        Strategy body = parseStrategy(Tools.applAt(t, 1));

        return new Let(defs, body);
    }

    private SDefT parseSDefT(ATermAppl t) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            DebugUtil.debug("parseSDefT()");
        }

        String name = Tools.stringAt(t, 0);
        ATermList svars = Tools.listAt(t, 1);
        ATermList tvars = Tools.listAt(t, 2);

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" name  : ", name);
        }

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" svars : ", svars);
        }
        List<SVar> realsvars = makeSVars(svars);
        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" svars : ", realsvars);
        }

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" tvars : ", tvars);
        }
        List<String> realtvars = makeVars(tvars);
        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" tvars : ", realtvars);
        }

        VarScope newScope = new VarScope(context.getVarScope());

        context.setVarScope(newScope);
        Strategy body = parseStrategy(Tools.applAt(t, 3));

        context.popVarScope();

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" +name: ", name);
        }

        return new SDefT(name, realsvars, realtvars, body, newScope);
    }

    private List<String> makeVars(ATermList svars) {

        List<String> realsvars = new ArrayList<String>(svars.getChildCount());

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" vars  : ", svars);
        }

        for (int j = 0; j < svars.getChildCount(); j++) {
            realsvars.add(Tools.stringAt(Tools.applAt(svars, j), 0));
        }

        return realsvars;
    }

    private List<SVar> makeSVars(ATermList svars) {
        if (DebugUtil.isDebugging()) {
            DebugUtil.debug("makeSVars()");
        }

        List<SVar> realsvars = new ArrayList<SVar>(svars.getChildCount());

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" vars  : ", svars);
        }

        for (int j = 0; j < svars.getChildCount(); j++) {
            ATermAppl t = Tools.applAt(svars, j);
            ArgType type = parseArgType(Tools.applAt(t, 1));
            String name = Tools.stringAt(t, 0);
            realsvars.add(new SVar(name, type));
        }

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug("       : ", realsvars);
        }
        return realsvars;
    }

    private ArgType parseArgType(ATermAppl t) {
        if(t.getAFun() == context.getFunTypeAFun()) {
            ATermList l = Tools.listAt(t, 0);
            List<ArgType> ch = new ArrayList<ArgType>();
            for (int i = 0; i < l.getChildCount(); i++) {
                ch.add(parseArgType(Tools.applAt(l, i)));
            }
            return new FunType(ch);
        } else if(t.getAFun() == context.getConstTypeAFun()) {
            return new ConstType();
        }
        return null;
    }

    private PrimT parsePrimT(ATermAppl t) throws InterpreterException {

        String name = Tools.stringAt(t, 0);
        List<Strategy> svars = parseStrategyList(Tools.listAt(t, 1));
        List<ATerm> tvars = parseTermList(Tools.listAt(t, 2));

        return new PrimT(name, svars, tvars);
    }

    private Strategy parseCallT(ATermAppl t) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug("parseCallT()");
        }
        String name = Tools.stringAt(Tools.applAt(t, 0), 0);

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" name  : ", name);
        }

        ATermList svars = Tools.listAt(t, 1);
        List<Strategy> realsvars = parseStrategyList(svars);

        List<ATerm> realtvars = parseTermList(Tools.listAt(t, 2));

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" -svars : ", realsvars);
        }
        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" -tvars : ", realtvars);
        }
        return new CallT(name, realsvars, realtvars);
    }

    private List<ATerm> parseTermList(ATermList tvars) {
        List<ATerm> v = new ArrayList<ATerm>(tvars.getChildCount());
        for (int i = 0; i < tvars.getChildCount(); i++) {
            v.add(Tools.termAt(tvars, i));
        }
        return v;
    }

    private List<Strategy> parseStrategyList(ATermList svars) throws InterpreterException {
        List<Strategy> v = new ArrayList<Strategy>(svars.getChildCount());
        for (int i = 0; i < svars.getChildCount(); i++) {
            v.add(parseStrategy(Tools.applAt(svars, i)));
        }
        return v;
    }

    private Id parseId(ATermAppl t) {
        return new Id();
    }

    private Match parseMatch(ATermAppl t) {
        ATermAppl u = Tools.applAt(t, 0);
        return new Match(u);
    }

    private GuardedLChoice parseGuardedLChoice(ATermAppl t) throws InterpreterException {

        Strategy cond = parseStrategy(Tools.applAt(t, 0));
        Strategy ifclause = parseStrategy(Tools.applAt(t, 1));
        Strategy thenclause = parseStrategy(Tools.applAt(t, 2));

        return new GuardedLChoice(cond, ifclause, thenclause);
    }

    private Seq parseSeq(ATermAppl t) throws InterpreterException {

        Strategy s0 = parseStrategy(Tools.applAt(t, 0));
        Strategy s1 = parseStrategy(Tools.applAt(t, 1));

        return new Seq(s0, s1);
    }

    private Scope parseScope(ATermAppl t) throws InterpreterException {

        ATermList vars = Tools.listAt(t, 0);
        List<String> realvars = new ArrayList<String>(vars.getChildCount());

        for (int i = 0; i < vars.getChildCount(); i++) {
            realvars.add(Tools.stringAt(vars, i));
        }

        Strategy body = parseStrategy(Tools.applAt(t, 1));

        return new Scope(realvars, body);
    }

    private Build parseBuild(ATermAppl t) {
        ATermAppl u = Tools.applAt(t, 0);
        return new Build(u);
    }

    public boolean invoke(String name) throws InterpreterException {
        SDefT def = context.lookupSVar(name);

        if (def == null) {
            throw new InterpreterException("Definition '" + name + "' not found");
        }

        return def.getBody().eval(context);
    }

    public IContext getContext() {
        return context;
    }

    public void setCurrent(ATerm inp) {
        context.setCurrent(inp);
    }

    public ATerm current() {
        return context.current();
    }

    public String prettyPrint() throws InterpreterException {
        StupidFormatter sf = new StupidFormatter();
        SDefT s = context.lookupSVar("main_0_0");
        s.prettyPrint(sf);
        return sf.toString();
    }

    public void shutdown() {
        //todo perf: this takes 2 secs overall
        if(DebugUtil.cleanupInShutdown) {
            context.cleanup();
        }
    }

    public AFun getOpAFun() {
        return context.getOpAFun();
    }

    public AFun getConsAFun() {
        return context.getConsAFun();
    }

    public AFun getNilAFun() {
        return context.getNilAFun();
    }

    public AFun getAnnoAFun() {
        return context.getAnnoAFun();
    }

    public AFun getStrAFun() {
        return context.getStrAFun();
    }

    public AFun getVarAFun() {
        return context.getVarAFun();
    }

    public AFun getExplodeAFun() {
        return context.getExplodeAFun();
    }

    public AFun getRealAFun() {
        return context.getRealAFun();
    }

    public AFun getIntAFun() {
        return context.getIntAFun();
    }

    public AFun getConstTypeAFun() {
        return context.getConstTypeAFun();
    }

    public AFun getFunTypeAFun() {
        return context.getFunTypeAFun();
    }

    public AFun getExtSDefAFun() {
        return context.getExtSDefAFun();
    }

    public AFun getSDefTAFun() {
        return context.getSDefTAFun();
    }

    public AFun getAsAFun() {
        return context.getAsAFun();
    }

    public AFun getWldAFun() {
        return context.getWldAFun();
    }
}
