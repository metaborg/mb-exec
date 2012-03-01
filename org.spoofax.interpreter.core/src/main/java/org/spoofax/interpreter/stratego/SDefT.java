/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005-2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * Copyright (c) 2012, Tobi Vollebregt
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.stratego;

import java.util.LinkedList;
import java.util.List;

import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.StrategoSignature;
import org.spoofax.interpreter.core.VarScope;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

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
        IStrategoTerm toTerm(ITermFactory f, StrategoSignature sig);
    }

    public static class FunType implements ArgType {
        protected List<ArgType> args;

        public FunType(List<ArgType> args) {
            this.args = args;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
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
        public int hashCode() {
            return args.hashCode();
        }

        @Override
        public String toString() {
            return "FunType(" + args.toString() + ")";
        }

        public IStrategoTerm toTerm(ITermFactory f, StrategoSignature sig) {
            LinkedList<IStrategoTerm> as = new LinkedList<IStrategoTerm>();
            for(ArgType at : args) {
                as.addFirst(at.toTerm(f, sig));
            }
            return f.makeAppl(sig.CTOR_FunType, f.makeList(as), ConstType.INSTANCE.toTerm(f, sig));
        }

    }

    public static class ConstType implements ArgType {

        public static final ConstType INSTANCE = new ConstType();

        private ConstType() {
            // empty
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof ConstType;
        }

        @Override
        public String toString() {
            return "ConstType(\"a\")";
        }

        @Override
        public int hashCode() {
            return 42; // we're a singleton
        }

        public IStrategoTerm toTerm(ITermFactory f, StrategoSignature sig) {
            IStrategoTerm sort = f.makeAppl(sig.CTOR_Sugar_Sort, f.makeString("ATerm"), f.makeList());
            return f.makeAppl(sig.CTOR_ConstType, sort);
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

    public IStrategoAppl toExternalDef(ITermFactory f, StrategoSignature sig) {

        final IStrategoTerm[] sArgs = new IStrategoTerm[strategyArgs.length];
        final IStrategoTerm[] tArgs = new IStrategoTerm[termArgs.length];

        for (int i = 0; i < sArgs.length; ++i) {
            final SVar sv = strategyArgs[i];
            sArgs[i] = f.makeAppl(sig.CTOR_VarDec, f.makeString(sv.name), sv.type.toTerm(f, sig));
        }

        for (int i = 0; i < tArgs.length; ++i) {
            tArgs[i] = f.makeAppl(sig.CTOR_VarDec, f.makeString(termArgs[i]), ConstType.INSTANCE.toTerm(f, sig));
        }

        return f.makeAppl(sig.CTOR_ExtSDef, f.makeString(uncify(name)), f.makeList(sArgs), f.makeList(tArgs));
    }

    // FIXME: next 3 methods copied from org.spoofax.interpreter.cli.StrategyCompletor

    private static String unescape(String name) {
        return name.replace("_p_", "'").replace("__", "+")
                .replace('_', '-').replace("+", "_");
    }

    public static String uncify(String name) {
        return unescape(name.substring(0, indexOfArity(name)));
    }

    private static int indexOfArity(String name) {
        int underlineCount = 0;
        int i;
        for (i = name.length() - 1; i >= 0; i--) {
            if (name.charAt(i) == '_')
                underlineCount++;
            if (underlineCount == 2)
                break;
        }
        return i;
    }
}
