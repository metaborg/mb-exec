/*
g * Created on 07.aug.2005
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
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.VarScope;
import org.spoofax.interpreter.stratego.SDefT.SVar;

import aterm.ATerm;
import aterm.ATermAppl;

public class CallT extends Strategy {

    protected String name;

    protected List<Strategy> svars;

    protected List<ATerm> tvars;

    private static int counter = 0;

    public CallT(String name, List<Strategy> svars, List<ATerm> tvars) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
    }

    public boolean eval(IContext env) throws InterpreterException {

        SDefT sdef = env.lookupSVar(name);

        if (sdef == null)
            throw new InterpreterException("Not found '" + name + "'");

        List<String> formalTermArgs = sdef.getTermParams();
        List<SVar> formalStrategyArgs = sdef.getStrategyParams();

        if (DebugUtil.isDebugging()) {
            printStrategyCall(name, formalStrategyArgs, svars, formalTermArgs, tvars);
        }

        if (svars.size() != formalStrategyArgs.size())
            throw new InterpreterException("Incorrect strategy arguments, expected " + formalStrategyArgs.size()
              + " got " + svars.size());

        if (tvars.size() != formalTermArgs.size())
            throw new InterpreterException("Incorrect aterm arguments, expected " + formalTermArgs.size()
              + " got " + tvars.size());

        VarScope newScope = new VarScope(sdef.getScope());

        for (int i = 0; i < svars.size(); i++) {
            SVar formal = formalStrategyArgs.get(i);
            Strategy actual = svars.get(i);

            SDefT target = null;
            if (actual instanceof CallT &&
              ((CallT)actual).getStrategyArguments().size() == 0
              && ((CallT)actual).getTermArguments().size() == 0) {
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
                List<SVar> stratArgs = new ArrayList<SVar>(0);
                List<String> termArgs = new ArrayList<String>(0);
                target = new SDefT(makeTempName(formal.name), stratArgs, termArgs, actual, env.getVarScope());
            }

            newScope.addSVar(formal.name, target);
        }

        for (int i = 0; i < tvars.size(); i++) {
            String formal = formalTermArgs.get(i);
            ATerm actual = tvars.get(i);
            // FIXME: This should not be here
            if (((ATermAppl)actual).getAFun() == env.getVarAFun())
                actual = env.lookupVar(Tools.stringAt(actual, 0));
            newScope.add(formal, actual);
        }

        VarScope oldVarScope = env.getVarScope();
        env.setVarScope(newScope);

        boolean r = sdef.eval(env);
        env.restoreVarScope(oldVarScope);

        return DebugUtil.traceReturn(r, env.current(), this);
    }

    private List<ATerm> getTermArguments() {
        return tvars;
    }

    private String makeTempName(String s) {
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
      final List<SVar> svarsFormal, final List<Strategy> svarsActual,
      final List<String> tvarsFormal, final List<ATerm> tvarsActual) {

        // Print this at the same indentation with the associated scope.
        StringBuilder sb = DebugUtil.buildIndent(DebugUtil.INDENT_STEP);

        sb.append("call : ").append(name).append("( ");

        final String svarNoName = "<s_noname_";
        for (int i = 0; i < svarsActual.size(); i++) {
            String sVarName = svarsFormal != null ? svarsFormal.get(i).name : (svarNoName + i + ">");
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(sVarName).append(" = ").append(svarsActual.get(i));
        }
        sb.append(" | ");

        final String tNoName = "<t_noname_";
        for (int i = 0; i < tvarsActual.size(); i++) {
            String termName = tvarsFormal != null ? tvarsFormal.get(i) : (tNoName + i + ">");
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(termName).append(" = ").append(tvarsActual.get(i));
        }
        sb.append(" ) ");
        debug(sb); //todo: sdef is too much
    }

    public String getTargetStrategyName() {
        return name;
    }

    public List<Strategy> getStrategyArguments() {
        return svars;
    }

    @Override
    protected String getTraceName() {
        return "call of" + "(" + name + ")";
    }
}
