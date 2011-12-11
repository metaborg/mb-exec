/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt.org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.stratego;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.core.UndefinedStrategyException;
import org.spoofax.interpreter.core.VarScope;
import org.spoofax.interpreter.stratego.SDefT.SVar;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class CallT extends Strategy {

    private final String name;

    private final Strategy[] svars;

    private final IStrategoTerm[] tvars;

    public CallT(String name, Strategy[] svars, IStrategoTerm[] tvars) {
        this.name = name;
        this.svars = svars;
        this.tvars = tvars;
    }

    static int depth = 0;
    
    public IConstruct eval(final IContext env) throws InterpreterException {

        if (DebugUtil.isDebugging()) {
            debug("CallT.eval() - ", env.current());
        }

    	SDefT sdef = env.lookupSVar(name);
    	
        if (sdef == null)
            throw new UndefinedStrategyException("Not found '" + name + "'", name);
        
        boolean isCompiledStrategy = sdef.isCompiledStrategy();
        if (!isCompiledStrategy)
            env.getStackTracer().push(name);

        if(DebugUtil.tracing) {
            System.err.println("[" + depth + "] - " + sdef.getName());
            depth++;
        }

        /* UNDONE: getParametrizedBody is trouble
        if (svars.length == 0) {
            Strategy result = sdef.getParametrizedBody(svars, tvars);
            if (result != null)
                return addHook(result, env.getVarScope());
        }
        */
    
        String[] formalTermArgs = sdef.getTermParams();
        SVar[] formalStrategyArgs = sdef.getStrategyParams();

        if (DebugUtil.isDebugging()) {
            printStrategyCall(sdef.getName(), formalStrategyArgs, svars, formalTermArgs, tvars);
        }

        if (svars.length != formalStrategyArgs.length)
            throw new InterpreterException("Incorrect strategy arguments calling '" + name + "', expected " + formalStrategyArgs.length
              + " got " + svars.length);

        if (tvars.length != formalTermArgs.length)
            throw new InterpreterException("Incorrect aterm arguments calling '" + name + "' , expected " + formalTermArgs.length
              + " got " + tvars.length);

        VarScope newScope = new VarScope(sdef.getScope());

        for (int i = 0; i < svars.length; i++) {
            SVar formal = formalStrategyArgs[i];
            Strategy actual = svars[i];

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
                target = new SDefT(SDefT.createAnonymousName(formal.name), stratArgs, termArgs, actual, env.getVarScope());
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

        final VarScope oldVarScope = env.getVarScope();
        env.setVarScope(newScope);
        
        return addHook(sdef.getBody(), isCompiledStrategy, oldVarScope);
    }

    public Strategy evalWithArgs(IContext env, Strategy[] sv, IStrategoTerm[] actualTVars) throws InterpreterException {

        // System.err.println(actualTVars.length);
        
        if (DebugUtil.isDebugging()) {
            debug("CallT.eval() - ", env.current());
        }

        SDefT sdef = env.lookupSVar(name); //getsdef(env);

        if (sdef == null)
            throw new UndefinedStrategyException("Not found '" + name + "'", name);

        boolean isCompiledStrategy = sdef.isCompiledStrategy();
        if (!isCompiledStrategy)
            env.getStackTracer().push(name);

        /* UNDONE: getParametrizedBody is trouble
        if (sv.length == 0) {
            Strategy result = sdef.getParametrizedBody(sv, actualTVars);
            if (result != null)
                return addHook(result, env.getVarScope());
        }
        */
    
        String[] formalTermArgs = sdef.getTermParams();
        SVar[] formalStrategyArgs = sdef.getStrategyParams();

        if (DebugUtil.isDebugging()) {
            printStrategyCall(sdef.getName(), formalStrategyArgs, sv, formalTermArgs, actualTVars);
        }

        if (sv.length != formalStrategyArgs.length)
            throw new InterpreterException("Incorrect strategy arguments, expected " + formalStrategyArgs.length
              + " got " + sv.length);

        if (actualTVars.length != formalTermArgs.length)
            throw new InterpreterException("Incorrect aterm arguments, expected " + formalTermArgs.length
              + " got " + actualTVars.length);

        VarScope newScope = new VarScope(sdef.getScope());

        for (int i = 0; i < sv.length; ++i) {
            SVar formal = formalStrategyArgs[i];
            Strategy actual = sv[i];

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
                target = new SDefT(SDefT.createAnonymousName(formal.name), stratArgs, termArgs, actual, env.getVarScope());
            }

            newScope.addSVar(formal.name, target);
        }

        for (int i = 0; i < actualTVars.length; i++) {
            String formal = formalTermArgs[i];
            newScope.add(formal, actualTVars[i]);
        }

        final VarScope oldVarScope = env.getVarScope();
        env.setVarScope(newScope);
        
        return addHook(sdef.getBody(), isCompiledStrategy, oldVarScope);
    }

    private Strategy addHook(Strategy strategy, final boolean isCompiledStrategy, final VarScope oldVarScope) {
        strategy.getHook().push(new Hook() {
            
        	@Override
            public IConstruct onSuccess(IContext env) throws InterpreterException {
                env.restoreVarScope(oldVarScope);
                if (!isCompiledStrategy)
                    env.getStackTracer().popOnSuccess();
        		return CallT.this.getHook().pop().onSuccess(env);
        	}
        	
        	@Override
            public IConstruct onFailure(IContext env) throws InterpreterException {
        		env.restoreVarScope(oldVarScope);
                if (!isCompiledStrategy)
                    env.getStackTracer().popOnFailure();
        		return CallT.this.getHook().pop().onFailure(env);
        	}
        });
        return strategy;
    }

    private IStrategoTerm[] getTermArguments() {
        return tvars;
    }

    @Override
    public String toString() {
        return "CallT(\"" + getTargetStrategyName() + "\"," + svars + "," + tvars + ")";
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
     * @param formalStrategyArgs strategy parameters (formal)
     * @param sv strategy arguments (actual)
     * @param formalTermArgs term parameters (formal)
     * @param tvarsActual term arguments (actual)
     */
    /*package*/
    static void printStrategyCall(final String name,
      final SVar[] formalStrategyArgs, final IConstruct[] sv,
      final String[] formalTermArgs, final IStrategoTerm[] tvarsActual) {

        // Print this at the same indentation with the associated scope.
        StringBuilder sb = DebugUtil.buildIndent(DebugUtil.INDENT_STEP);

        sb.append("call : ").append(name).append("( ");

        final String svarNoName = "<s_noname_";
        for (int i = 0; i < sv.length; i++) {
            String sVarName = formalStrategyArgs != null ? formalStrategyArgs[i].name : (svarNoName + i + ">");
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(sVarName).append(" = ").append(sv[i]);
        }
        sb.append(" | ");

        final String tNoName = "<t_noname_";
        for (int i = 0; i < tvarsActual.length; i++) {
            String termName = formalTermArgs != null ? formalTermArgs[i] : (tNoName + i + ">");
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

    public Strategy[] getStrategyArguments() {
        return svars;
    }

    @Override
    protected final String getTraceName() {
        return "call of" + "(" + getTargetStrategyName() + ")";
    }

	public boolean evaluateWithArgs(IContext env, Strategy[] sv, IStrategoTerm[] tv) throws InterpreterException {
	    ResultHook hook = new ResultHook();
    	getHook().push(hook);

    	IConstruct c = evalWithArgs(env, sv, tv);
    	while (c != null) {
    		c = c.eval(env);
    	}
		return hook.result;
	}
}
