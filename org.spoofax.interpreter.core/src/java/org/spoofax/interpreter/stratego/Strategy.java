/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.core.Context;
import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.SDefT.ArgType;
import org.spoofax.interpreter.stratego.SDefT.ConstType;
import org.spoofax.interpreter.stratego.SDefT.FunType;


abstract public class Strategy implements IConstruct {

    private final static ArgType type;
    
    static {
        List<ArgType> l = new ArrayList<ArgType>(2);
        l.add(new ConstType());
        type = new FunType(l);
    }
    
    public static void debug(Object... s) {
        Context.debug(s);
    }
    
    public SDefT.ArgType getType() {
        return type;
    }

//    public String toString() {
//        StupidFormatter sf = new StupidFormatter();
//        prettyPrint(sf);
//        return sf.toString();
//    }

    protected String getTraceName() {
        return this.getClass().getSimpleName();
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
    	if (DebugUtil.isDebugging()) {
    		 debugStack = new Stack<Strategy>();
    	}
    	IConstruct c = this;
    	boolean debug = DebugUtil.isDebugging();
		while (c != null) {
			if (debug)
				debugStack.push((Strategy)c);
			c = c.eval(env);
		}
    	if (DebugUtil.isDebugging()) {
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
