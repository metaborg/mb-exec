/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.spoofax.interpreter.stratego.Build;
import org.spoofax.interpreter.stratego.CallT;
import org.spoofax.interpreter.stratego.GuardedLChoice;
import org.spoofax.interpreter.stratego.Id;
import org.spoofax.interpreter.stratego.Let;
import org.spoofax.interpreter.stratego.Match;
import org.spoofax.interpreter.stratego.OpDecl;
import org.spoofax.interpreter.stratego.PrimT;
import org.spoofax.interpreter.stratego.SDefT;
import org.spoofax.interpreter.stratego.Scope;
import org.spoofax.interpreter.stratego.Seq;
import org.spoofax.interpreter.stratego.Strategy;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;
import aterm.pure.PureFactory;

public class Interpreter extends ATermed implements IEnvironment {

    protected ATerm current;

    protected VarScope varScope;

    protected DefScope defScope;

    public Interpreter() {
        super();

        varScope = new VarScope(null);
        defScope = new DefScope(null);
    }

    public ATerm current() {
        return current;
    }

    public void setCurrent(ATerm term) {
        current = term;
    }

    public void load(String path) throws IOException, FatalError {
        ATerm prg = factory.readFromFile(path);
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
            defScope.add(name, new OpDecl(name));
        }
    }

    private void loadStrategies(ATermList list) throws FatalError {
        for (int i = 0; i < list.getChildCount(); i++) {
            ATermAppl t = Tools.applAt(list, i);
            String name = Tools.stringAt(t, 0);
            ATermList svars = Tools.listAt(t, 1);
            ATermList tvars = Tools.listAt(t, 2);
            Strategy body = makeStrategy(Tools.applAt(t, 3));

            debug("name  : " + name);
            
            List<String> realsvars = new ArrayList<String>(svars.getChildCount());
            debug("svars : " + svars);
            for(int j=0;j<svars.getChildCount();j++) {
                realsvars.add(Tools.stringAt(Tools.applAt(svars, j),0));
            }
            debug("svars : " + realsvars);

            List<String> realtvars = new ArrayList<String>(tvars.getChildCount());
            debug("tvars : " + tvars);
            for(int j=0;j<tvars.getChildCount();j++) {
                realtvars.add(Tools.stringAt(tvars, 0));
            }
            debug("tvars : " + realtvars);
            
            defScope.add(name, new SDefT(name, realsvars, realtvars, body));
        }

    }

    private Strategy makeStrategy(ATermAppl appl) throws FatalError {
        String op = appl.getName();
        if (op.equals("Build")) {
            return makeBuild(appl);
        } else if (op.equals("Scope")) {
            return makeScope(appl);
        } else if (op.equals("Seq")) {
            return makeSeq(appl);
        } else if (op.equals("GuardedLChoice")) {
            return makeGuardedLChoice(appl);
        } else if (op.equals("Match")) {
            return makeMatch(appl);
        } else if (op.equals("Id")) {
            return makeId(appl);
        } else if (op.equals("CallT")) {
            return makeCallT(appl);
        } else if (op.equals("PrimT")) {
            return makePrimT(appl);
        } else if (op.equals("Let")) {
            return makeLet(appl);
        }

        throw new FatalError("Unknown op '" + op + "'");
    }

    private Let makeLet(ATermAppl t) throws FatalError {
        ATermList l = Tools.listAt(t, 0);
        List<SDefT> defs = new ArrayList<SDefT>();
        for (int i = 0; i < l.getChildCount(); i++)
            defs.add(makeSDefT(Tools.applAt(l, i)));
        Strategy body = makeStrategy(Tools.applAt(t, 1));

        return new Let(defs, body);
    }

    private SDefT makeSDefT(ATermAppl appl) {
        // TODO Auto-generated method stub
        return null;
    }

    private PrimT makePrimT(ATermAppl t) {
        String name = Tools.stringAt(t, 0);
        ATermList svars = Tools.listAt(t, 1);
        ATermList tvars = Tools.listAt(t, 2);

        return new PrimT(name, svars, tvars);
    }

    private Strategy makeCallT(ATermAppl t) {
        String name = Tools.stringAt(Tools.applAt(t, 0), 0);
        ATermList svars = Tools.listAt(t, 1);
        ATermList tvars = Tools.listAt(t, 2);
        return new CallT(name, svars, tvars);
    }

    private Id makeId(ATermAppl t) {
        return new Id();
    }

    private Match makeMatch(ATermAppl t) {
        ATermAppl u = Tools.applAt(t, 0);
        return new Match(u);
    }

    private GuardedLChoice makeGuardedLChoice(ATermAppl t) throws FatalError {
        Strategy cond = makeStrategy(Tools.applAt(t, 0));
        Strategy ifclause = makeStrategy(Tools.applAt(t, 1));
        Strategy thenclause = makeStrategy(Tools.applAt(t, 2));

        return new GuardedLChoice(cond, ifclause, thenclause);
    }

    private Seq makeSeq(ATermAppl t) throws FatalError {
        Strategy s0 = makeStrategy(Tools.applAt(t, 0));
        Strategy s1 = makeStrategy(Tools.applAt(t, 1));
        return new Seq(s0, s1);
    }

    private Scope makeScope(ATermAppl t) throws FatalError {
        ATermList vars = Tools.listAt(t, 0);
        List<String> realvars = new ArrayList<String>(vars.getChildCount());
        for(int i=0;i<vars.getChildCount();i++)
            realvars.add(Tools.stringAt(vars, i));
        Strategy body = makeStrategy(Tools.applAt(t, 1));
        return new Scope(realvars, body);
    }

    private Build makeBuild(ATermAppl t) {
        ATermAppl u = Tools.applAt(t, 0);
        return new Build(u);
    }

    public static void debug(String s) {
        System.out.println(s);
    }

    public boolean invoke(String name, Object object, Object object2)
            throws FatalError {
        SDefT s = lookupSVar(name);
        return s.getBody().eval(this);
    }

    public void reset() {
    }

    public ATerm lookupVar(String n) throws FatalError {
        return varScope.lookup(n);
    }

    public SDefT lookupSVar(String n) throws FatalError {
        return defScope.lookupSDefT(n);
    }

    public PureFactory getFactory() {
        return factory;
    }

    public boolean bindVars(List<Pair<String, ATerm>> r) {
        for (Pair<String, ATerm> x : r) {
            VarScope s = varScope.scopeOf(x.first);
            if (s == null) {
                varScope.add(x.first, x.second);
            } else if (s.hasVarInLocalScope(x.first)) {
                ATerm t = s.lookup(x.first);
                boolean eq = t.match(x.second) != null;
                if (!eq) {
                    debug(x.first + " already bound to " + t + ", new: "
                            + x.second);
                    return eq;
                }
            } else {
                s.add(x.first, x.second);
            }
        }
        return true;
    }

    public void dumpScope(String prefix) {
        varScope.dump(prefix);
    }

    public VarScope getVarScope() {
        return varScope;
    }

    public DefScope getDefScope() {
        return defScope;
    }

    public void setVarScope(VarScope newVarScope) {
        varScope = newVarScope;
    }

    public void setDefScope(DefScope newDefScope) {
        defScope = newDefScope;   
    }

}
