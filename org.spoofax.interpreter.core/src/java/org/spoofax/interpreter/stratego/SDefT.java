/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

import java.util.List;

import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.VarScope;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SDefT implements IConstruct {

    private static int counter = 0;
    
    private String name;

    private SVar[] strategyArgs;

    private String[] termArgs;

    private Strategy body;

    private VarScope scope;

    public static class SVar {
        public final String name;
        public final ArgType type;
        public SVar(String name, ArgType type) {
            this.name = name;
            this.type = type;
        }
        public ArgType getType() {
            return type;
        }
    }
    
    public interface ArgType {
        // empty
    }

    public static class FunType implements ArgType {
        protected List<ArgType> args;

        public FunType(List<ArgType> args) {
            this.args = args;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof FunType))
                return false;
            FunType other = (FunType) obj;
            if (args.size() != other.args.size())
                return false;
            for (int i = 0; i < args.size(); i++)
                if (!args.get(i).equals(other.args.get(i)))
                    return false;
            return true;
        }
        
        @Override
        public String toString() {
            return "FunType(" + args.toString() + ")";
        }
    }

    public static class ConstType implements ArgType {
        
        @Override
        public boolean equals(Object obj) {
            return obj instanceof ConstType;
        }
        
        @Override
        public String toString() {
            return "ConstType(\"a\")";
        }
    }

    public SDefT(String name, SVar[] realsvars, String[] realtvars, Strategy actual, VarScope scope) {
        this.name = name;
        this.strategyArgs = realsvars;
        this.termArgs = realtvars;
        this.body = actual;
        this.scope = scope;
    }
    
    protected SDefT(VarScope scope) {
        this.scope = scope;
    }

    public  IConstruct eval(IContext e) throws InterpreterException {
        //e.getVarScope().dump("", true);
    	return body;
    }

    public String getName() {
        return name;
    }
    
    protected void setName(String name) {
        this.name = name;
    }

    public Strategy getBody() {
        return body;
    }
    
    public Strategy getParametrizedBody(Strategy[] sargs, IStrategoTerm[] targs) {
        return null; // overridden InteropSDefT for compiled strategies
    }
    
    public boolean isCompiledStrategy() {
        return false; // overridden InteropSDefT for compiled strategies
    }
    
    protected void setBody(Strategy body) {
        this.body = body;
    }

    public String[] getTermParams() {
        return termArgs;
    }
    
    protected void setTermParams(String[] termArgs) {
        this.termArgs = termArgs;
    }

    public SVar[] getStrategyParams() {
        return strategyArgs;
    }
    
    protected void setStrategyParams(SVar[] strategyArgs) {
        this.strategyArgs = strategyArgs;
    }

    public VarScope getScope() {
        return scope;
    }

    @Override
    public String toString() {
        return "SDefT(\"" + getName() + "\", " + getBody() + ")";
    }

    public void setScope(VarScope newScope) {
        this.scope = newScope;
    }

    public void prettyPrint(StupidFormatter sf) {
        sf.first("SDefT(");
        sf.bump(6);
        sf.line("  \"" + getName() + "\"");
        sf.line(", " + strategyArgs);
        sf.line(", " + termArgs);
        sf.append(", ");
        sf.bump(2);
        body.prettyPrint(sf);
        sf.unbump(2);
        sf.append(")");
        sf.unbump(6);
    }

	public boolean evaluate(IContext env) throws InterpreterException {
		return body.evaluate(env);
	}

    protected static String createAnonymousName(String s) {
        return "<anon_" + s + "_" + (counter++) + ">";
    }
}
