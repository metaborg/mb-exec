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
    
    public static void debug(String s) {
        if(debugging == false)
            return;
        if(s.length() < 20000)
            System.out.println(s);
    }
    
    public void load(String path) throws IOException, FatalError {
        ATerm prg = context.getFactory().readFromFile(path);
        debug("" + prg);
        ATerm sign = Tools.applAt(Tools.listAt(prg, 0), 0);
        ATerm strats = Tools.applAt(Tools.listAt(prg, 0), 1);

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
            }
        }

    }

    private ExtSDef parseExtSDef(ATermAppl t) {
        String name = Tools.stringAt(t, 0);
        ATermList svars = Tools.listAt(t, 1);
        ATermList tvars = Tools.listAt(t, 2);

        debug("name  : " + name);

        List<String> realsvars = makeVars(svars);
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
        for (int i = 0; i < l.getChildCount(); i++)
            defs.add(parseSDefT(Tools.applAt(l, i)));
        Strategy body = parseStrategy(Tools.applAt(t, 1));

        return new Let(defs, body);
    }

    private SDefT parseSDefT(ATermAppl t) throws FatalError {
        String name = Tools.stringAt(t, 0);
        ATermList svars = Tools.listAt(t, 1);
        ATermList tvars = Tools.listAt(t, 2);

        debug("name  : " + name);
        
        VarScope oldScope = context.getVarScope();
        VarScope newScope = new VarScope(oldScope);
        context.setVarScope(newScope);
        Strategy body = parseStrategy(Tools.applAt(t, 3));
        
        debug("svars : " + svars);
        List<String> realsvars = makeVars(svars);
        debug("svars : " + realsvars);

        debug("tvars : " + tvars);
        List<String> realtvars = makeVars(tvars);
        debug("tvars : " + realtvars);
        
        context.setVarScope(oldScope);
        return new SDefT(name, realsvars, realtvars, body, newScope);
    }

    private List<String> makeVars(ATermList svars) {
        List<String> realsvars = new ArrayList<String>(svars.getChildCount());
        debug("svars : " + svars);
        for(int j=0;j<svars.getChildCount();j++) {
            realsvars.add(Tools.stringAt(Tools.applAt(svars, j),0));
        }
        return realsvars;
    }

    private PrimT parsePrimT(ATermAppl t) throws FatalError {
        String name = Tools.stringAt(t, 0);
        List<Strategy> svars = parseStrategyList(Tools.listAt(t, 1));
        List<ATerm> tvars = parseTermList(Tools.listAt(t, 2));

        return new PrimT(name, svars, tvars);
    }

    private Strategy parseCallT(ATermAppl t) throws FatalError {
        debug("makeCallT()");
        String name = Tools.stringAt(Tools.applAt(t, 0), 0);
        debug(" name  : " + name);
        
        ATermList svars = Tools.listAt(t, 1);
        List<Strategy> realsvars = parseStrategyList(svars);
        
        List<ATerm> realtvars = parseTermList(Tools.listAt(t, 2));
        
        debug(" svars : " + realsvars);
        debug(" tvars : " + realtvars);
        return new CallT(name, realsvars, realtvars);
    }

    private List<ATerm> parseTermList(ATermList tvars) {
        List<ATerm> v = new ArrayList<ATerm>(tvars.getChildCount());
        for(int i=0;i<tvars.getChildCount();i++)
            v.add(Tools.termAt(tvars, i));
        return v;
    }

    private List<Strategy> parseStrategyList(ATermList svars) throws FatalError {
        List<Strategy> v = new ArrayList<Strategy>(svars.getChildCount());

        for(int i=0;i<svars.getChildCount();i++)
            v.add(parseStrategy(Tools.applAt(svars, i)));
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
        for(int i=0;i<vars.getChildCount();i++)
            realvars.add(Tools.stringAt(vars, i));
        Strategy body = parseStrategy(Tools.applAt(t, 1));
        return new Scope(realvars, body);
    }

    private Build parseBuild(ATermAppl t) {
        ATermAppl u = Tools.applAt(t, 0);
        return new Build(u);
    }

    public boolean invoke(String name) throws FatalError {
        SDefT def = context.lookupSVar(name);
        if(def == null)
            throw new FatalError("Definition '" + name + "' not found");
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

}
