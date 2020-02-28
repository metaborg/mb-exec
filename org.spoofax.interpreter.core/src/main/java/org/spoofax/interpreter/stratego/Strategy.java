/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.stratego;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.spoofax.interpreter.core.Context;
import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.SDefT.ArgType;
import org.spoofax.interpreter.stratego.SDefT.ConstType;
import org.spoofax.interpreter.stratego.SDefT.FunType;
import org.spoofax.interpreter.util.DebugUtil;

/**
 * Base class for all strategies.
 */
public abstract class Strategy implements IConstruct {

    private final static ArgType type;

    static {
        List<ArgType> l = new ArrayList<ArgType>(2);
        l.add(ConstType.INSTANCE);
        type = new FunType(l);
    }

    /** @deprecated Call {@link Context#debug} directly. */
    @Deprecated
    public static void debug(Object... s) {
        if (!DebugUtil.isDebugging()) return;
        Context.debug(s);
    }

    private transient String traceName;

    public SDefT.ArgType getType() {
        return type;
    }

//    public String toString() {
//        StupidFormatter sf = new StupidFormatter();
//        prettyPrint(sf);
//        return sf.toString();
//    }

    protected String getTraceName() {
        if(traceName == null) {
            traceName = this.getClass().getSimpleName();
        }
        return traceName;
    }

    @Override
    public String toString() {
        return getTraceName();
    }

    private Stack<Hook> hook = new Stack<Hook>();

    public Stack<Hook> getHook()
    {
        return hook;
    }

    public boolean evaluate(IContext env) throws InterpreterException {
        ResultHook resultHook = new ResultHook();
        getHook().push(resultHook);
        Stack<Strategy> debugStack = null;
        boolean debug = DebugUtil.isDebugging();
        if (debug) {
             debugStack = new Stack<Strategy>();
        }
        IConstruct c = this;
        while (c != null) {
            if (debug)
                debugStack.push((Strategy)c);
            c = c.eval(env);
        }
        if (debug) {
            for (Strategy strat : debugStack) {
                if (strat.getHook().size() != 0)
                    throw new InterpreterException("There was a leak on: " + debugStack);
            }
        }

        return resultHook.result;
    }

    static class ResultHook extends Hook {
        boolean result;

        @Override
        public IConstruct onFailure(IContext env) throws InterpreterException {
            result = false;
            return null;
        }
        @Override
        public IConstruct onSuccess(IContext env) throws InterpreterException {
            result = true;
            return null;
        }
    }
}
