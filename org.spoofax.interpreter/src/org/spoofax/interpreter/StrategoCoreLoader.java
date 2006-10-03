/*
 * Created on 3. okt.. 2006
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
import org.spoofax.interpreter.stratego.DebugUtil;
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
import org.spoofax.interpreter.stratego.SDefT.ArgType;
import org.spoofax.interpreter.stratego.SDefT.ConstType;
import org.spoofax.interpreter.stratego.SDefT.FunType;
import org.spoofax.interpreter.stratego.SDefT.SVar;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.terms.StrategoSignature;

public class StrategoCoreLoader {

    private ITermFactory factory;
    private Context context;
    
    StrategoCoreLoader(Context context) {
        this.context = context;
        factory = context.getProgramFactory();
    }
    
    private ExtSDef parseExtSDef(IStrategoAppl t) {

        String name = Tools.javaStringAt(t, 0);
        IStrategoList svars = Tools.listAt(t, 1);
        IStrategoList tvars = Tools.listAt(t, 2);

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug("name  : ", name);
        }

        List<SVar> realsvars = makeSVars(svars);
        List<String> realtvars = makeVars(tvars);

        VarScope oldScope = context.getVarScope();
        VarScope newScope = new VarScope(oldScope);

        return new ExtSDef(name, realsvars, realtvars, newScope);
    }

    private Strategy parseStrategy(IStrategoAppl appl) throws InterpreterException {

        IStrategoConstructor ctor = appl.getConstructor();
        StrategoSignature sign = context.getStrategoSignature();

        if (ctor.equals(sign.getBuild())) {
            return parseBuild(appl);
        } else if (ctor.equals(sign.getScope())) {
            return parseScope(appl);
        } else if (ctor.equals(sign.getSeq())) {
            return parseSeq(appl);
        } else if (ctor.equals(sign.getGuardedLChoice())) {
            return parseGuardedLChoice(appl);
        } else if (ctor.equals(sign.getMatch())) {
            return parseMatch(appl);
        } else if (ctor.equals(sign.getId())) {
            return parseId(appl);
        } else if (ctor.equals(sign.getCallT())) {
            return parseCallT(appl);
        } else if (ctor.equals(sign.getPrimT())) {
            return parsePrimT(appl);
        } else if (ctor.equals(sign.getLet())) {
            return parseLet(appl);
        } else if (ctor.equals(sign.getFail())) {
            return makeFail(appl);
        } else if (ctor.equals(sign.getId())) {
            return makeId(appl);
        } else if (ctor.equals(sign.getAll())) {
            return makeAll(appl);
        } else if (ctor.equals(sign.getOne())) {
            return makeOne(appl);
        } else if (ctor.equals(sign.getSome())) {
            return makeSome(appl);
        }

        throw new InterpreterException("Unknown op '" + ctor + "'");
    }

    private Strategy makeId(IStrategoAppl appl) {
        return new Id();
    }

    private Some makeSome(IStrategoAppl t) throws InterpreterException {
        Strategy body = parseStrategy(Tools.applAt(t, 0));
        return new Some(body);
    }

    private One makeOne(IStrategoAppl t) throws InterpreterException {
        Strategy body = parseStrategy(Tools.applAt(t, 0));
        return new One(body);
    }

    private All makeAll(IStrategoAppl t) throws InterpreterException {
        Strategy body = parseStrategy(Tools.applAt(t, 0));
        return new All(body);
    }

    private Strategy makeFail(IStrategoAppl appl) {
        return new Fail();
    }

    private Let parseLet(IStrategoAppl t) throws InterpreterException {

        IStrategoList l = Tools.listAt(t, 0);
        List<SDefT> defs = new ArrayList<SDefT>();

        for (int i = 0; i < l.size(); i++) {
            defs.add(parseSDefT(Tools.applAt(l, i)));
        }

        Strategy body = parseStrategy(Tools.applAt(t, 1));

        return new Let(defs, body);
    }

    private SDefT parseSDefT(IStrategoAppl t) throws InterpreterException {
        if (DebugUtil.isDebugging()) {
            DebugUtil.debug("parseSDefT()");
        }

        String name = Tools.javaStringAt(t, 0);
        IStrategoList svars = Tools.listAt(t, 1);
        IStrategoList tvars = Tools.listAt(t, 2);

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

    private List<String> makeVars(IStrategoList svars) {

        List<String> realsvars = new ArrayList<String>(svars.size());

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" vars  : ", svars);
        }

        for (int j = 0; j < svars.size(); j++) {
            realsvars.add(Tools.javaStringAt(Tools.applAt(svars, j), 0));
        }

        return realsvars;
    }

    private List<SVar> makeSVars(IStrategoList svars) {
        if (DebugUtil.isDebugging()) {
            DebugUtil.debug("makeSVars()");
        }

        List<SVar> realsvars = new ArrayList<SVar>(svars.size());

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" vars  : ", svars);
        }

        for (int j = 0; j < svars.size(); j++) {
            IStrategoAppl t = Tools.applAt(svars, j);
            ArgType type = parseArgType(Tools.applAt(t, 1));
            String name = Tools.javaStringAt(t, 0);
            realsvars.add(new SVar(name, type));
        }

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug("       : ", realsvars);
        }
        return realsvars;
    }

    private ArgType parseArgType(IStrategoAppl t) {
        if(Tools.isFunType(t, context)) {
            IStrategoList l = Tools.listAt(t, 0);
            List<ArgType> ch = new ArrayList<ArgType>();
            for (int i = 0; i < l.size(); i++) {
                ch.add(parseArgType(Tools.applAt(l, i)));
            }
            return new FunType(ch);
        } else if (Tools.isConstType(t, context)) {
            return new ConstType();
        }
        return null;
    }

    private PrimT parsePrimT(IStrategoAppl t) throws InterpreterException {

        String name = Tools.javaStringAt(t, 0);
        List<Strategy> svars = parseStrategyList(Tools.listAt(t, 1));
        List<IStrategoTerm> tvars = parseTermList(Tools.listAt(t, 2));

        return new PrimT(name, svars, tvars);
    }

    private Strategy parseCallT(IStrategoAppl t) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug("parseCallT()");
        }
        String name = Tools.javaStringAt(Tools.applAt(t, 0), 0);

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" name  : ", name);
        }

        IStrategoList svars = Tools.listAt(t, 1);
        List<Strategy> realsvars = parseStrategyList(svars);

        List<IStrategoTerm> realtvars = parseTermList(Tools.listAt(t, 2));

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" -svars : ", realsvars);
        }
        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(" -tvars : ", realtvars);
        }
        return new CallT(name, realsvars, realtvars);
    }

    private List<IStrategoTerm> parseTermList(IStrategoList tvars) {
        List<IStrategoTerm> v = new ArrayList<IStrategoTerm>(tvars.size());
        for (int i = 0; i < tvars.size(); i++) {
            v.add(Tools.termAt(tvars, i));
        }
        return v;
    }

    private List<Strategy> parseStrategyList(IStrategoList svars) throws InterpreterException {
        List<Strategy> v = new ArrayList<Strategy>(svars.size());
        for (int i = 0; i < svars.size(); i++) {
            v.add(parseStrategy(Tools.applAt(svars, i)));
        }
        return v;
    }

    private Id parseId(IStrategoAppl t) {
        return new Id();
    }

    private Match parseMatch(IStrategoAppl t) {
        IStrategoAppl u = Tools.applAt(t, 0);
        return new Match(u);
    }

    private GuardedLChoice parseGuardedLChoice(IStrategoAppl t) throws InterpreterException {

        Strategy cond = parseStrategy(Tools.applAt(t, 0));
        Strategy ifclause = parseStrategy(Tools.applAt(t, 1));
        Strategy thenclause = parseStrategy(Tools.applAt(t, 2));

        return new GuardedLChoice(cond, ifclause, thenclause);
    }

    private Seq parseSeq(IStrategoAppl t) throws InterpreterException {

        Strategy s0 = parseStrategy(Tools.applAt(t, 0));
        Strategy s1 = parseStrategy(Tools.applAt(t, 1));

        return new Seq(s0, s1);
    }

    private Scope parseScope(IStrategoAppl t) throws InterpreterException {

        IStrategoList vars = Tools.listAt(t, 0);
        List<String> realvars = new ArrayList<String>(vars.size());

        for (int i = 0; i < vars.size(); i++) {
            realvars.add(Tools.javaStringAt(vars, i));
        }

        Strategy body = parseStrategy(Tools.applAt(t, 1));

        return new Scope(realvars, body);
    }

    private Build parseBuild(IStrategoAppl t) {
        IStrategoAppl u = Tools.applAt(t, 0);
        return new Build(u);
    }
    
    public void load(String path) throws IOException, InterpreterException {

        IStrategoTerm prg = factory.parseFromFile(path);
        IStrategoAppl sign = Tools.applAt(Tools.listAt(prg, 0), 0);
        IStrategoAppl strats = Tools.applAt(Tools.listAt(prg, 0), 1);

        if (DebugUtil.isDebugging()) {
            DebugUtil.debug(prg);
        }

        loadConstructors(Tools.listAt(Tools.applAt(Tools.listAt(sign, 0), 0), 0));
        loadStrategies(Tools.listAt(strats, 0));
    }

    private void loadConstructors(IStrategoList list) {
        for (int i = 0; i < list.size(); i++) {
            String name = Tools.javaStringAt(Tools.applAt(list, i), 0);
            context.addOpDecl(name, new OpDecl(name));
        }
    }

    private void loadStrategies(IStrategoList list) throws InterpreterException {
        for (int i = 0; i < list.size(); i++) {
            IStrategoAppl t = Tools.applAt(list, i);
            if(Tools.isSDefT(t, context)) {
                SDefT def = parseSDefT(t);
                context.addSVar(def.getName(), def);
            } else if(Tools.isExtSDef(t, context)) {
                ExtSDef def = parseExtSDef(t);
                context.addSVar(def.getName(), def);
                // FIXME: Come up with a good solution for external
                // definitions
                throw new InterpreterException("Illegal ExtSDef in StrategoCore file");
            }
        }

    }

}
