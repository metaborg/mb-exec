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
import org.spoofax.interpreter.stratego.SDefT.FunType;
import org.spoofax.interpreter.stratego.SDefT.ArgType;
import org.spoofax.interpreter.stratego.SDefT.ConstType;
import org.spoofax.interpreter.stratego.SDefT.SVar;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;

public class Interpreter extends ATermBuilder {

    protected Context context;
    private static boolean debugging;
    
    public Interpreter() {
        debugging = false;
        context = new Context();
        factory = context.factory;
    }
    
    public static void debug(Object... strings) {

        if (!debugging) {
            return;
        }

        String toPrint = "";
        if (strings.length > 1) {
            for (Object s : strings) {
                if(s.getClass().isArray()) {
                    Object ss[] = (Object[])s;
                    for (Object o : ss) {
                        toPrint += o;
                    }
                } else {
                    toPrint += s; //pay the price
                }
            }
        }
        else {
            toPrint = (strings[0]).toString();
        }
        if (toPrint.length() < 20000) {
            System.out.println(toPrint);
        }
    }
    
    public void load(String path) throws IOException, FatalError {

        ATerm prg = context.getFactory().readFromFile(path);
        ATerm sign = Tools.applAt(Tools.listAt(prg, 0), 0);
        ATerm strats = Tools.applAt(Tools.listAt(prg, 0), 1);

        if (Interpreter.isDebugging()) {
            debug(prg);
        }

        loadConstructors(Tools
          .listAt(Tools.applAt(Tools.listAt(sign, 0), 0), 0));
        loadStrategies(Tools.listAt(strats, 0));
    }

    private void loadConstructors(ATermList list) {
        for (int i = 0; i < list.getChildCount(); i++) {
            String name = Tools.stringAt(Tools.applAt(list, i), 0);
            context.addOpDecl(name, new OpDecl(name));
        }
    }

    private void loadStrategies(ATermList list) throws FatalError {
        for (int i = 0; i < list.getChildCount(); i++) {
            ATermAppl t = Tools.applAt(list, i);
            if(Tools.isSDefT(t)) {
                SDefT def = parseSDefT(t);
                context.addSVar(def.getName(), def);
            } else if(Tools.isExtSDef(t)) {
                ExtSDef def = parseExtSDef(t);
                context.addSVar(def.getName(), def);
                // FIXME: Come up with a good solution for external
                // definitions
                throw new FatalError("Illegal ExtSDef in StrategoCore file");
            }
        }

    }

    private ExtSDef parseExtSDef(ATermAppl t) {

        String name = Tools.stringAt(t, 0);
        ATermList svars = Tools.listAt(t, 1);
        ATermList tvars = Tools.listAt(t, 2);

        if (Interpreter.isDebugging()) {
            debug("name  : ", name);
        }

        List<SVar> realsvars = makeSVars(svars);
        List<String> realtvars = makeVars(tvars);

        VarScope oldScope = context.getVarScope();
        VarScope newScope = new VarScope(oldScope);

        return new ExtSDef(name, realsvars, realtvars, newScope);
    }

    private Strategy parseStrategy(ATermAppl appl) throws FatalError {
        
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

        throw new FatalError("Unknown op '" + op + "'");
    }

    private Some makeSome(ATermAppl t) throws FatalError {
        Strategy body = parseStrategy(Tools.applAt(t, 0));
        return new Some(body);
    }

    private One makeOne(ATermAppl t) throws FatalError {
        Strategy body = parseStrategy(Tools.applAt(t, 0));
        return new One(body);
    }

    private All makeAll(ATermAppl t) throws FatalError {
        Strategy body = parseStrategy(Tools.applAt(t, 0));
        return new All(body);
    }

    private Strategy makeFail(ATermAppl appl) {
        return new Fail();
    }

    private Let parseLet(ATermAppl t) throws FatalError {
        
        ATermList l = Tools.listAt(t, 0);
        List<SDefT> defs = new ArrayList<SDefT>();

        for (int i = 0; i < l.getChildCount(); i++) {
            defs.add(parseSDefT(Tools.applAt(l, i)));
        }
        
        Strategy body = parseStrategy(Tools.applAt(t, 1));

        return new Let(defs, body);
    }

    private SDefT parseSDefT(ATermAppl t) throws FatalError {
        if (Interpreter.isDebugging()) {
            debug("parseSDefT()");
        }

        String name = Tools.stringAt(t, 0);
        ATermList svars = Tools.listAt(t, 1);
        ATermList tvars = Tools.listAt(t, 2);

        if (Interpreter.isDebugging()) {
            debug(" name  : ", name);
        }

        if (Interpreter.isDebugging()) {
            debug(" svars : ", svars);
        }
        List<SVar> realsvars = makeSVars(svars);
        if (Interpreter.isDebugging()) {
            debug(" svars : ", realsvars);
        }

        if (Interpreter.isDebugging()) {
            debug(" tvars : ", tvars);
        }
        List<String> realtvars = makeVars(tvars);
        if (Interpreter.isDebugging()) {
            debug(" tvars : ", realtvars);
        }

        VarScope newScope = new VarScope(context.getVarScope());

        context.setVarScope(newScope);
        Strategy body = parseStrategy(Tools.applAt(t, 3));

        context.popVarScope();

        if (Interpreter.isDebugging()) {
            debug(" +name: ", name);
        }

        return new SDefT(name, realsvars, realtvars, body, newScope);
    }

    private List<String> makeVars(ATermList svars) {

        List<String> realsvars = new ArrayList<String>(svars.getChildCount());

        if (Interpreter.isDebugging()) {
            debug(" vars  : ", svars);
        }

        for (int j = 0; j < svars.getChildCount(); j++) {
            realsvars.add(Tools.stringAt(Tools.applAt(svars, j), 0));
        }

        return realsvars;
    }

    private List<SVar> makeSVars(ATermList svars) {
        if (Interpreter.isDebugging()) {
            debug("makeSVars()");
        }

        List<SVar> realsvars = new ArrayList<SVar>(svars.getChildCount());

        if (Interpreter.isDebugging()) {
            debug(" vars  : ", svars);
        }

        for (int j = 0; j < svars.getChildCount(); j++) {
            ATermAppl t = Tools.applAt(svars, j);
            ArgType type = parseArgType(Tools.applAt(t, 1));
            String name = Tools.stringAt(t, 0);
            realsvars.add(new SVar(name, type));
        }

        if (Interpreter.isDebugging()) {
            debug("       : ", realsvars);
        }
        return realsvars;
    }

    private ArgType parseArgType(ATermAppl t) {
        if(Tools.isFunType(t)) {
            ATermList l = Tools.listAt(t, 0);
            List<ArgType> ch = new ArrayList<ArgType>();
            for (int i = 0; i < l.getChildCount(); i++) {
                ch.add(parseArgType(Tools.applAt(l, i)));
            }
            return new FunType(ch);
        } else if(Tools.isConstType(t)) {
            return new ConstType();
        }
        return null;
    }

    private PrimT parsePrimT(ATermAppl t) throws FatalError {
        
        String name = Tools.stringAt(t, 0);
        List<Strategy> svars = parseStrategyList(Tools.listAt(t, 1));
        List<ATerm> tvars = parseTermList(Tools.listAt(t, 2));

        return new PrimT(name, svars, tvars);
    }

    private Strategy parseCallT(ATermAppl t) throws FatalError {

        if (Interpreter.isDebugging()) {
            debug("parseCallT()");
        }
        String name = Tools.stringAt(Tools.applAt(t, 0), 0);

        if (Interpreter.isDebugging()) {
            debug(" name  : ", name);
        }

        ATermList svars = Tools.listAt(t, 1);
        List<Strategy> realsvars = parseStrategyList(svars);

        List<ATerm> realtvars = parseTermList(Tools.listAt(t, 2));

        if (Interpreter.isDebugging()) {
            debug(" -svars : ", realsvars);
        }
        if (Interpreter.isDebugging()) {
            debug(" -tvars : ", realtvars);
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

    private List<Strategy> parseStrategyList(ATermList svars) throws FatalError {
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

    private GuardedLChoice parseGuardedLChoice(ATermAppl t) throws FatalError {

        Strategy cond = parseStrategy(Tools.applAt(t, 0));
        Strategy ifclause = parseStrategy(Tools.applAt(t, 1));
        Strategy thenclause = parseStrategy(Tools.applAt(t, 2));

        return new GuardedLChoice(cond, ifclause, thenclause);
    }

    private Seq parseSeq(ATermAppl t) throws FatalError {
        
        Strategy s0 = parseStrategy(Tools.applAt(t, 0));
        Strategy s1 = parseStrategy(Tools.applAt(t, 1));
        
        return new Seq(s0, s1);
    }

    private Scope parseScope(ATermAppl t) throws FatalError {
     
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

    public boolean invoke(String name) throws FatalError {
        SDefT def = context.lookupSVar(name);

        if (def == null) {
            throw new FatalError("Definition '" + name + "' not found");
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

    public void setDebug(boolean b) {
        debugging = b;
    }

    public static boolean isDebugging() {
        return debugging;
    }

    public String prettyPrint() throws FatalError {
        StupidFormatter sf = new StupidFormatter();
        SDefT s = context.lookupSVar("main_0_0");
        s.prettyPrint(sf);
        return sf.toString();
    }
}
