/*
 * Created on 24.jun.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import jjtraveler.TopDown;
import jjtraveler.VisitFailure;
import aterm.AFun;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;
import aterm.Visitor;
import aterm.pure.PureFactory;

public class Interpreter {

    private Map<String, Constructor> constructors;

    private Map<String, Strategy> strategies;
    private Map<String, ExtStrategy> externalStrategies;

    private ATerm current;

    private PureFactory factory;

    private Stack<Scope> scopes;

    private Scope scope;
    
    public Interpreter() {
        factory = new PureFactory();
        constructors = new HashMap<String, Constructor>();
        strategies = new HashMap<String, Strategy>();
        externalStrategies  = new HashMap<String, ExtStrategy>();
        scopes = new Stack<Scope>();

        for(ExtStrategy s : Library.getStrategies())
            externalStrategies.put(s.getName(), s);
        
        reset();
    }

    public ATerm makeTerm(String s) {
        return factory.parse(s);
    }

    ATerm makePattern(String s) {
        return factory.parse(s);
    }
    
    public void load(String path) throws IOException, FatalError {
        load(factory.readFromFile(path));
    }

    private static class MatchCollector extends Visitor {

        List<ATerm> coll = null;

        PureFactory factory;

        String needle;

        MatchCollector(PureFactory factory, String needle) {
            this.factory = factory;
            coll = new ArrayList<ATerm>();
            this.needle = needle;
        }

        @Override
        public void visitATerm(ATerm t) throws VisitFailure {

            List<ATerm> ats = t.match(needle);
            if (ats != null) {
                coll.addAll(ats);
            }
        }

        List<ATerm> getResult() {
            return coll;
        }
    }

    private ATermList collect(String needle, ATerm haystack) {

        MatchCollector c = new MatchCollector(factory, needle);
        jjtraveler.Visitor v = new TopDown(c);

        try {
            v.visit(haystack);
        } catch (VisitFailure e) {
            e.printStackTrace();
        }
        ATermList r = factory.makeList();
        for (ATerm t : c.getResult())
            r = r.append(t);
        return r;
    }

    public void load(ATerm prg) throws FatalError {
        System.out.println("load()");

        ATermList x = collect("Constructors(<term>)", prg);
        ATermList constr = Tools.listAt(x, 0);
        for (int i = 0; i < constr.getLength(); i++) {
            ATermAppl t = Tools.applAt(constr, i);
            if(t.getName().equals("OpDecl")) {
                Constructor c = new Constructor(t);
                constructors.put(c.getName(), c);
            } else if(t.getName().equals("OpDeclInj")) {
                
            } else 
                throw new FatalError("Unknown constructor type '" + t.getName() + "'");
        }

        ATermList strat = Tools.listAt(collect("Strategies(<term>)", prg), 0);
        for (int i = 0; i < strat.getLength(); i++) {
            Strategy s = StrategyFactory.create(Tools.applAt(strat, i));
            strategies.put(s.getName(), s);
        }
        System.out.println("constructors : " + constructors);
        System.out.println("strategies : " + strategies);
    }

    public void reset() {
        factory.cleanup();
        scopes.clear();
        constructors.clear();

        strategies.clear();

        for(Strategy s : externalStrategies.values())
            strategies.put(s.getName(), s);

        current = makeTerm("[]");

        enterScope();
    }

    private boolean eval(ATermAppl t) throws FatalError {
        System.out.println("Next : " + t.getName());
        String type = t.getName();
        if (type.equals("All"))
            return evalAll(t);
        else if (type.equals("One"))
            return evalOne(t);
        else if (type.equals("Some"))
            return evalSome(t);
        else if (type.equals("Prim"))
            return evalPrim(t);
        else if (type.equals("Build"))
            return evalBuild(t);
        else if (type.equals("Bagof"))
            return evalBagof(t);
        else if (type.equals("GuardedLChoice"))
            return evalGuardedLChoice(t);
        else if (type.equals("LGChoice"))
            return evalLGChoice(t);
        else if (type.equals("Seq"))
            return evalSeq(t);
        else if (type.equals("Scope"))
            return evalScope(t);
        else if (type.equals("Build"))
            return evalBuild(t);
        else if (type.equals("Id"))
            return evalId(t);
        else if (type.equals("Fail"))
            return evalFail(t);
        else if (type.equals("CallT"))
            return evalCall(t);
        else if (type.equals("Let"))
            return evalLet(t);
        else if (type.equals("Match"))
            return evalMatch(t);
        else if (type.equals("PrimT"))
            return evalPrim(t);
        
        throw new FatalError("Unknown construct '" + type + "'");
    }

    private boolean evalMatch(ATermAppl t) throws FatalError {
        debug("evalMatch");

        ATermAppl p = (ATermAppl) t.getChildAt(0);
        List<Pair<String, ATerm>> r = Tools.match(current, p);

        debug("!" + current + " ; ?" + p);
        debug("" + current.getType() + " " + t.getType());
        debug("" + current.getAnnotations() + " " + t.getAnnotations());

        if (r != null) {
            debug("" + r);
            
            return bindVars(r);
        }
        debug("no match");
        return false;
    }

    private boolean bindVars(List<Pair<String, ATerm>> r) {
        for (Pair<String, ATerm> x : r) {
            if (scope.hasVarInLocalScope(x.first)) {
                ATerm t = scope.lookup(x.first);
                boolean eq = t.match(x.second) != null;
                if(!eq)
                    debug(x.first + " already bound to " + t + ", new: " + x.second);
                return eq;
            }

            scope.add(x.first, x.second);
        }
        return true;
    }

    private boolean evalOne(ATermAppl t) {
        // TODO Auto-generated method stub
        return false;
    }

    private boolean evalAll(ATermAppl t) {
        // TODO Auto-generated method stub
        return false;
    }

    private boolean evalSome(ATermAppl t) {
        // TODO Auto-generated method stub
        return false;
    }

    private boolean evalPrim(ATermAppl t) throws FatalError {
        debug("evalPrim");
        debug("" + t);
        // Check if we have the external strategy on record

        String n = Tools.stringAt(t, 0);
        ExtStrategy s = externalStrategies.get(n);
        if(s == null)
            throw new FatalError("Calling non-existent primitive :" + n);

        ATermList actualSVars = Tools.listAt(t, 1);
        ATermList actualTVars = Tools.listAt(t, 2);

        // Lookup variables in the argument lest
        
        ATermList realTVars = factory.makeList();
        for(int i=0;i<actualTVars.getLength();i++) {
            ATermAppl appl = Tools.applAt(actualTVars, i);
            if(appl.getName().equals("Var")) {
                realTVars = realTVars.append(scope.lookup(Tools.stringAt(appl, 0))); 
            } else {
                realTVars = realTVars.append(appl);
            }
        }
        debug("" + realTVars);
        return s.invoke(this, actualSVars, realTVars);
    }

    private boolean evalBagof(ATermAppl t) {
        // TODO Auto-generated method stub
        return false;
    }

    private boolean evalGuardedLChoice(ATermAppl t) throws FatalError {
        System.out.println("evalGuardedLChoice");
        if (eval(Tools.termAt(t, 0))) {
            enterScope();
            boolean r = eval(Tools.applAt(t, 1));
            exitScope();
            return r;
        }
        else {
            enterScope();
            boolean r = eval(Tools.termAt(t, 2));
            exitScope();
            return r;
        }
    }

    private boolean evalLGChoice(ATermAppl t) {
        // TODO Auto-generated method stub
        return false;

    }

    private boolean evalSeq(ATermAppl t) throws FatalError {
        System.out.println("evalSeq");
        for (int i = 0; i < t.getChildCount(); i++) {
            if (!eval((ATerm) t.getChildAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean evalScope(ATermAppl t) throws FatalError {
        debug("evalScope");
        enterScope();
        ATermList vars = (ATermList) t.getChildAt(0);
        scope.addUndeclaredVars(vars);
        boolean r = eval((ATerm) t.getChildAt(1));
        exitScope();
        return r;
    }

    private boolean evalBuild(ATermAppl t) throws FatalError {
        debug("evalBuild");
        debug(t.toString());
        current = buildTerm((ATermAppl) t.getChildAt(0));
        debug("built : " + current);
        return true;
    }

    public ATerm buildTerm(ATermAppl t) throws FatalError {
        if (t.getName().equals("Anno")) {
            return buildTerm(Tools.applAt(t, 0));
        } else if (t.getName().equals("Op")) {
            String ctr = Tools.stringAt(t, 0);
            ATermList children = (ATermList) t.getChildAt(1);

            AFun afun = factory.makeAFun(ctr, children.getLength(), false);
            ATermList kids = factory.makeList();

            for (int i = 0; i < children.getLength(); i++) {
                kids = kids.append(buildTerm((ATermAppl) children
                        .elementAt(i)));
            }
            return factory.makeApplList(afun, kids);
        } else if (t.getName().equals("Int")) {
            ATermAppl x = (ATermAppl) t.getChildAt(0);
            return factory.makeInt(new Integer(x.getName()));
        } else if (t.getName().equals("Str")) {
            ATermAppl x = (ATermAppl) t.getChildAt(0);
            return x;
        } else if (t.getName().equals("Var")) {
            String n = Tools.stringAt(t, 0);
            debug("Lookup : " + n);
            ATerm x = lookup(n);
            debug("Found :" + x);
            return x;
        }

        throw new FatalError("Unknown build constituent '" + t.getName() + "'");
    }

    private ATerm lookup(String var) {
        return scope.lookup(var);
    }

    private void debug(String s) {
        System.out.println(s);
    }

    private boolean evalId(ATermAppl t) {
        System.out.println("evalId");
        return true;

    }

    private boolean evalFail(ATermAppl t) {
        System.out.println("evalFail");
        return false;
    }

    private boolean evalCall(ATermAppl t) throws FatalError {
        System.out.println("evalCall");
        debug("" + t);
        ATermAppl sname = Tools.applAt(Tools.applAt(t, 0), 0);
        ATermList actualSVars = Tools.listAt(t, 1);
        ATermList actualTVars = Tools.listAt(t, 2);
        Strategy s = getStrategy(sname.getName());
        System.out.println("Calling :" + s.getName());
        List<String> formalTVars = s.getTermParams();
        List<String> formalSVars = s.getStrategyParams();

        debug("" + actualSVars);
        
        enterScope();
        if(formalSVars.size() != actualSVars.getChildCount()) {
            System.out.println("Takes " + formalSVars.size());
            System.out.println("Have  " + actualSVars.getChildCount());
            
            throw new FatalError("Parameter length mismatch!");
        }
        
        for (int i = 0; i < actualSVars.getChildCount(); i++) {
            String varName = Tools.stringAt(Tools.applAt(Tools.applAt(actualSVars, i), 0), 0);
            debug("" + formalSVars.get(i) + " points to " + varName);
            scope.addSVar(formalSVars.get(i), getStrategy(varName));
        }
        
        for (int i = 0; i < actualTVars.getChildCount(); i++)
            scope.add(formalTVars.get(i), (ATerm) actualTVars
                    .getChildAt(i));

        boolean r;
        if(s instanceof IntStrategy) {
            r = eval(((IntStrategy)s).getBody());
        } else if(s instanceof ExtStrategy){
            r = ((ExtStrategy)s).invoke(this, actualSVars, actualTVars);
        } else {
            throw new FatalError("Unknown kind of strategy  " + s.getClass().getName());
        }
        exitScope();
        return r;
    }

    private void exitScope() {
        scopes.pop();
    }

    private Strategy getStrategy(String name) throws FatalError {
        Strategy s = scope.lookupSVar(name);
        if(s != null)
            return s;
        
        s = strategies.get(name);
        if(s != null)
            return s;
        
        throw new FatalError("Lookup of strategy '" + name + "' failed");
    }

    private Constructor getConstructor(String name) {
        return constructors.get(name);
    }

    private boolean evalLet(ATermAppl t) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean eval(ATerm t) throws FatalError {
        if (t.getType() == ATerm.APPL)
            return eval((ATermAppl) t);

        throw new FatalError("Internal error: Invalid term type " + t.getType()
                + " / " + t);
    }

    private Scope enterScope() {
        Scope n = new Scope(scope);
        scopes.push(n);
        scope = n;
        return n;
    }

    public ATerm getCurrent() {
        return current;
    }

    public void setCurrent(ATerm term) {
        current = term;
    }

    public ATerm makeList(String s) {
        ATermList t = (ATermList) makeTerm(s);
        ATerm l = makeTerm("Nil");
        AFun f = factory.makeAFun("Cons", 2, false);
        for (int i = t.getLength() - 1; i >= 0; i--)
            l = factory.makeAppl(f, (ATerm) t.getChildAt(i), l);
        return l;
    }

    public ATerm makeTuple(String s) {
        ATermList t = (ATermList) makeTerm(s);
        ATerm[] t2 = new ATerm[t.getLength()];
        for (int i = 0; i < t.getLength(); i++)
            t2[i] = (ATerm) t.getChildAt(i);

        AFun f = factory.makeAFun("", t2.length, false);
        return factory.makeAppl(f, t2);
    }

    public ATermAppl makeTerm(String op, ATerm a0, ATerm a1) {
        AFun fun = factory.makeAFun(op, 2, false);
        return factory.makeAppl(fun, a0, a1);
    }

    public boolean invoke(String name, ATermList svars, ATermList tvars) throws FatalError {
        debug("Calling " + name + " with " + svars.toString() + " / " + tvars.toString());
        
        return eval(makeTerm(("CallT(SVar(\"" + name + "\"), [], [])")));
    }

    public ATerm makeTerm(int i) {
        return factory.makeInt(i);
    }

}
