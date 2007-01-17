/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.spoofax.DebugUtil;
import org.spoofax.interpreter.Context;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
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

    public String toString() {
        return getTraceName();
    }
    
    private Stack<Hook> hook = new Stack<Hook>();
      
    public Stack<Hook> getHook()
    {
    	return hook;
    }
    
    public boolean evaluate(IContext env) throws InterpreterException {
    	class Result implements  IConstruct {
    		boolean result;
    		Result(boolean result) {
    			this.result = result;
    		}
			public IConstruct eval(IContext e) throws InterpreterException {
				return null;
			}
			public void prettyPrint(StupidFormatter fmt) {
			
			}
			public boolean evaluate(IContext env) throws InterpreterException {
				return false;
			}
    	}
    	getHook().push(new Hook(){
			@Override
			IConstruct onFailure() throws InterpreterException {
				return new Result(false);
			}
			@Override
			IConstruct onSuccess(IContext env) throws InterpreterException {
				return new Result(true);
			}
    	});
    	Stack<Strategy> s = null;
    	if (DebugUtil.isDebugging()) {
    		 s = new Stack<Strategy>();
    	}
    	IConstruct c = this;
    	while (!(c instanceof Result)) {
    		if (DebugUtil.isDebugging())
    			s.push((Strategy)c);
        	c = c.eval(env);
    	}
    	if (DebugUtil.isDebugging()) {
    		for (Strategy strat : s) {
    			if (strat.getHook().size() != 0)
    				throw new InterpreterException("There was a leak on: " + s);
    		}
    	}
    	
		return ((Result)c).result;
    }
}
