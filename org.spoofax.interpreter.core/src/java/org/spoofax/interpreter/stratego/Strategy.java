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

    public String toString() {
        return getTraceName();
    }
    
    private Stack<Hook> hook = new Stack<Hook>();
      
    public Stack<Hook> getHook()
    {
    	return hook;
    }
    
    public boolean evaluate(IContext env) throws InterpreterException {
    	class Finished extends InterpreterException {
			private static final long serialVersionUID = -857185250056951094L;
			boolean result;
    		Finished(boolean b)
    		{
    			super("Finished");
    			result = b;
    		}
    	}
    	getHook().push(new Hook(){
			@Override
			IConstruct onFailure(IContext env) throws InterpreterException {
				throw new Finished(false);
			}
			@Override
			IConstruct onSuccess(IContext env) throws InterpreterException {
				throw new Finished(true);
			}
    	});
    	Stack<Strategy> s = null;
    	if (DebugUtil.isDebugging()) {
    		 s = new Stack<Strategy>();
    	}
    	IConstruct c = this;
    	boolean debug = DebugUtil.isDebugging();
    	boolean result = false;
    	try {
    		while (true) {
    			if (debug)
    				s.push((Strategy)c);
    			c = c.eval(env);
    		}
    	}
    	catch (Finished f) {
    		result = f.result;
    	}
    	if (DebugUtil.isDebugging()) {
    		for (Strategy strat : s) {
    			if (strat.getHook().size() != 0)
    				throw new InterpreterException("There was a leak on: " + s);
    		}
    	}
    	
		return result;
    }
}
