/*
g * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.ChoicePointStack;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.VarScope;
import org.spoofax.interpreter.ChoicePointStack.Entry;
import org.spoofax.interpreter.stratego.SDefT.SVar;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class CallT extends Strategy {

    protected String name;

    protected IConstruct[] svars;

    protected IStrategoTerm[] tvars;

    private static int counter = 0;

    public CallT(String name, IConstruct[] svars, IStrategoTerm[] tvars) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
    }

    static int depth = 0;
    
    public boolean eval(IContext env) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("CallT.eval() - ", env.current());
        }

        //VarScope oldVarScope = env.getVarScope();
        
        //boolean r = prepareCall(env).eval(env);
        if(DebugUtil.debugging) {
            DebugUtil.bump();
        }
        prepareCall(env, env.getChoicePointStack());
        
        if(DebugUtil.debugging) {
            DebugUtil.unbump();
        }
        //env.restoreVarScope(oldVarScope);

        //return DebugUtil.traceReturn(r, env.current(), this);
        return true;
    }

    private SDefT prepareCall(IContext env, ChoicePointStack es) throws InterpreterException {
        
        SDefT sdef = env.lookupSVar(name);

        if (sdef == null)
            throw new InterpreterException("Not found '" + name + "'");

        if(DebugUtil.tracing) {
            System.err.println("[" + depth + "] - " + sdef.name);
        }

        depth++;
    
        String[] formalTermArgs = sdef.getTermParams();
        SVar[] formalStrategyArgs = sdef.getStrategyParams();

        if (DebugUtil.isDebugging()) {
            printStrategyCall(sdef.getName(), formalStrategyArgs, svars, formalTermArgs, tvars);
        }

        if (svars.length != formalStrategyArgs.length)
            throw new InterpreterException("Incorrect strategy arguments to '" + name + "', expected " + formalStrategyArgs.length
              + " got " + svars.length);

        if (tvars.length != formalTermArgs.length)
            throw new InterpreterException("Incorrect aterm arguments to '" + name + "', expected " + formalTermArgs.length
              + " got " + tvars.length);

        VarScope newScope = new VarScope(sdef.getScope());

        for (int i = 0; i < svars.length; i++) {
            SVar formal = formalStrategyArgs[i];
            IConstruct actual = svars[i];

            SDefT target = null;
            if (actual instanceof CallT &&
              ((CallT)actual).getStrategyArguments().length == 0
              && ((CallT)actual).getTermArguments().length == 0) {
                String n = ((CallT)actual).getTargetStrategyName();
                target = env.lookupSVar(n);
                if (target == null) {
                    if (DebugUtil.isDebugging()) {
                        debug(env.getVarScope().dump(" "));
                    }
                    System.out.println(env.getVarScope());
                    throw new InterpreterException("No strategy '" + n + "'");
                }
            }
            else {
                SVar[] stratArgs = new SVar[0];
                String[] termArgs = new String[0];
                target = new SDefT(makeTempName(formal.name), stratArgs, termArgs, actual, env.getVarScope());
            }

            newScope.addSVar(formal.name, target);
        }

        for (int i = 0; i < tvars.length; i++) {
            String formal = formalTermArgs[i];
            IStrategoTerm actual = tvars[i];
            // FIXME: This should not be here
            if (Tools.isVar(((IStrategoAppl)actual), env))
                actual = env.lookupVar(Tools.javaStringAt((IStrategoAppl)actual, 0));
            newScope.add(formal, actual);
        }

        es.addNext(sdef, newScope);
                
        //env.setVarScope(newScope);

        depth--;

        return sdef;
    }
    
    public boolean evalWithArgs(IContext env, IConstruct[] actualSVars, IStrategoTerm[] actualTVars) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("CallT.eval() - ", env.current());
        }

        SDefT sdef = env.lookupSVar(name);

        if (sdef == null)
            throw new InterpreterException("Not found '" + name + "'");

    
        String[] formalTermArgs = sdef.getTermParams();
        SVar[] formalStrategyArgs = sdef.getStrategyParams();

        if (DebugUtil.isDebugging()) {
            printStrategyCall(sdef.getName(), formalStrategyArgs, actualSVars, formalTermArgs, actualTVars);
        }

        if (actualSVars.length != formalStrategyArgs.length)
            throw new InterpreterException("Incorrect strategy arguments, expected " + formalStrategyArgs.length
              + " got " + actualSVars.length);

        if (actualTVars.length != formalTermArgs.length)
            throw new InterpreterException("Incorrect aterm arguments, expected " + formalTermArgs.length
              + " got " + actualTVars.length);

        VarScope newScope = new VarScope(sdef.getScope());

        for (int i = 0; i < actualSVars.length; i++) {
            SVar formal = formalStrategyArgs[i];
            IConstruct actual = actualSVars[i];

            SDefT target = null;
            if (actual instanceof CallT &&
              ((CallT)actual).getStrategyArguments().length == 0
              && ((CallT)actual).getTermArguments().length == 0) {
                String n = ((CallT)actual).getTargetStrategyName();
                target = env.lookupSVar(n);
                if (target == null) {
                    if (DebugUtil.isDebugging()) {
                        debug(env.getVarScope().dump(" "));
                    }
                    System.out.println(env.getVarScope());
                    throw new InterpreterException("No strategy '" + n + "'");
                }
            }
            else {
                SVar[] stratArgs = new SVar[0];
                String[] termArgs = new String[0];
                target = new SDefT(makeTempName(formal.name), stratArgs, termArgs, actual, env.getVarScope());
            }

            newScope.addSVar(formal.name, target);
        }

        for (int i = 0; i < actualTVars.length; i++) {
            newScope.add(formalTermArgs[i], actualTVars[i]);
        }

        VarScope oldVarScope = env.getVarScope();
        env.setVarScope(newScope);

        //boolean r = sdef.eval(env);
        if(DebugUtil.debugging) {
            DebugUtil.bump();
        }
        
        boolean r = CallT.callHelper(sdef, env);
        
        if(DebugUtil.debugging) {
            DebugUtil.unbump();
        }
        
        //es.addNext(sdef, newScope);
        env.restoreVarScope(oldVarScope);

        return DebugUtil.traceReturn(r, env.current(), this);
    }

    private IStrategoTerm[] getTermArguments() {
        return tvars;
    }

    private static String makeTempName(String s) {
        return "<anon_" + s + "_" + counter + ">";
    }

    @Override
    public String toString() {
        return "CallT(\"" + name + "\"," + svars + "," + tvars + ")";
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.append("CallT(\n");
        sf.bump(6);
        sf.append("  \"" + name + "\"\n");
        sf.append(", " + svars + "\n");
        sf.append(", " + tvars + "\n");
        sf.append(")");
    }

    /**
     * Renders a string describing a strategy call. <br>
     * The format is something like: <i>do_this_1_1(s = id | t = 1)</i>
     *
     * @param name strategy name
     * @param svarsFormal strategy parameters (formal)
     * @param svarsActual strategy arguments (actual)
     * @param tvarsFormal term parameters (formal)
     * @param tvarsActual term arguments (actual)
     */
    /*package*/
    static void printStrategyCall(final String name,
      final SVar[] svarsFormal, final IConstruct[] svarsActual,
      final String[] tvarsFormal, final IStrategoTerm[] tvarsActual) {

        // Print this at the same indentation with the associated scope.
        StringBuilder sb = DebugUtil.buildIndent(DebugUtil.INDENT_STEP);

        sb.append("call : ").append(name).append("( ");

        final String svarNoName = "<s_noname_";
        for (int i = 0; i < svarsActual.length; i++) {
            String sVarName = svarsFormal != null ? svarsFormal[i].name : (svarNoName + i + ">");
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(sVarName).append(" = ").append(svarsActual[i]);
        }
        sb.append(" | ");

        final String tNoName = "<t_noname_";
        for (int i = 0; i < tvarsActual.length; i++) {
            String termName = tvarsFormal != null ? tvarsFormal[i] : (tNoName + i + ">");
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(termName).append(" = ").append(tvarsActual[i]);
        }
        sb.append(" ) ");
        debug(sb); //todo: sdef is too much
    }

    public String getTargetStrategyName() {
        return name;
    }

    public IConstruct[] getStrategyArguments() {
        return svars;
    }

    @Override
    protected String getTraceName() {
        return "call of" + "(" + name + ")";
    }

    public static boolean callHelper(IConstruct c, IContext env) throws InterpreterException {
        ChoicePointStack cs = env.getChoicePointStack();
        Entry old = cs.newChoicePoint();
        boolean r = c.eval(env);
        VarScope vs = env.getVarScope();
        while(r && cs.hasMore()) {
            Entry e = cs.getNext();
            env.setVarScope(e.getScope());
            r = e.getConstruct().eval(env);
        }
        cs.restoreChoicePoint(old);
        env.restoreVarScope(vs);
        return r;
    }
}
