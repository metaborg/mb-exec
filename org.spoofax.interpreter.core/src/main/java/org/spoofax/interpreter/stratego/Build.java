package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.ssl.SSLLibrary;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.util.TermUtils;

import javax.annotation.Nullable;

/**
 * Build strategy, for building terms.
 */
public class Build extends Strategy {

    private IStrategoAppl pattern;

    /**
     * Initializes a new instance of the {@link Build} strategy
     *
     * @param pattern the pattern of the term to be built
     */
    public Build(IStrategoAppl pattern) {
        this.pattern = pattern;
    }

    @Override
    public IConstruct eval(IContext env) throws InterpreterException {
        debug("Build.eval() - ", env.current(), " -> !", pattern);

        @Nullable IStrategoTerm newTerm = buildTerm(this.pattern, env);
        if (newTerm == null) {
            // Building the term failed
        	return getHook().pop().onFailure(env);
        }
        env.setCurrent(newTerm);

        return getHook().pop().onSuccess(env);
    }

    /**
     * Builds the term.
     *
     * @param env the strategy context
     * @param pattern the pattern of the term to build
     * @return the built term; or {@code null} when it failed
     */
    public @Nullable IStrategoTerm buildTerm(IStrategoAppl pattern, IContext env) {
        if (Tools.isAnno(pattern, env)) {
            // Anno(_, _)
            return buildAnno(pattern, env);
        } else if (Tools.isOp(pattern, env)) {
            // Op(_, _)
            return buildOp(pattern, env);
        } else if (Tools.isInt(pattern, env)) {
            // Int(_)
            return buildInt(pattern, env);
        } else if (Tools.isReal(pattern, env)) {
            // Real(_)
            return buildReal(pattern, env);
        } else if (Tools.isStr(pattern, env)) {
            // Str(_)
            return buildStr(pattern, env);
        } else if (Tools.isVar(pattern, env)) {
            // Var(_)
            return buildVar(pattern, env);
        } else if (Tools.isExplode(pattern, env)) {
            // Explode(_, _)
            return buildExplode(pattern, env);
        } else {
            // ?
            throw new IllegalStateException("Unknown build constituent '" + pattern.getConstructor() + "'");
        }
    }

    /**
     * Builds a term from the specified constructor name and list of arguments.
     *
     * @param pattern the pattern
     * @param env the term context
     * @return the resulting term; or {@code null} when it failed
     */
    private @Nullable IStrategoTerm buildExplode(IStrategoAppl pattern, IContext env) {
        debug("buildExplode() : ", pattern);

        IStrategoAppl ctorPattern = TermUtils.toApplAt(pattern, 0);
        IStrategoAppl argsPattern = TermUtils.toApplAt(pattern, 1);

        debug(" ctor : ", ctorPattern);
        debug(" args : ", argsPattern);

        @Nullable IStrategoTerm constructorName = buildTerm(ctorPattern, env);
        @Nullable IStrategoTerm argumentList = buildTerm(argsPattern, env);

        if (constructorName == null || argumentList == null)
            return null;

        debug(" actualCtor : ", constructorName);
        debug(" actualArgs : ", argumentList);

        if (TermUtils.isInt(constructorName) || TermUtils.isReal(constructorName)) {
            return constructorName;
        } else if (TermUtils.isString(constructorName)) {
            return doBuildExplode(constructorName, argumentList, env);
        } else if (TermUtils.isList(constructorName)) {
            return argumentList;
        } else {
            // According to STR-626 non-string constructor term implosion should fail not crash
            return null;
        }
    }

    /**
     * Builds a term from the specified constructor name and list of arguments.
     *
     * @param constructorName the constructor name
     * @param argumentList the list of arguments
     * @param env the term context
     * @return the resulting term
     */
    private IStrategoTerm doBuildExplode(IStrategoTerm constructorName, IStrategoTerm argumentList, IContext env) {
        ITermFactory factory = env.getFactory();
        String name = TermUtils.toJavaString(constructorName);
        IStrategoTerm[] realArgs = TermUtils.toList(argumentList).getAllSubterms();

        if (name.isEmpty()) {
            // Tuples have a constructor that has an empty name.
            return factory.makeTuple(realArgs);
        }

        if (name.length() >= 2 && name.charAt(0) == '"' && name.charAt(name.length() - 1) == '"') {
            // Remove the quotes around the name.
            name = name.substring(1, name.length() - 1);

            if(realArgs.length == 0) {
                // It was quoted and has no arguments, so it must be a string.
                return factory.makeString(name);
            }
        }
        
        IStrategoConstructor afun = factory.makeConstructor(name, realArgs.length);
        return factory.makeAppl(afun, realArgs);
    }

    /**
     * Gets the term assigned to the variable represented by a Var(name) term.
     *
     * @param pattern the pattern
     * @param env the term context
     * @return the resulting term; or {@code null} when no value was assigned
     */
    private @Nullable IStrategoTerm buildVar(IStrategoAppl pattern, IContext env) {
        String name = TermUtils.toJavaStringAt(pattern, 0);
        try {
            return env.lookupVar(name);
        } catch (InterpreterException e) {
            // This should not be able to happen
            throw new RuntimeException(e);
        }
    }

    /**
     * Builds a String term from a Str(x) pattern.
     *
     * @param pattern the pattern
     * @param env the term context
     * @return the resulting term
     */
    private IStrategoString buildStr(IStrategoAppl pattern, IContext env) {
        return TermUtils.toStringAt(pattern, 0);
    }

    /**
     * Builds a Real term from a Real(x) pattern.
     *
     * @param pattern the pattern
     * @param env the term context
     * @return the resulting term
     */
    private IStrategoReal buildReal(IStrategoAppl pattern, IContext env) {
        String x = TermUtils.toJavaStringAt(pattern, 0);
        return env.getFactory().makeReal(new Double(x));
    }

    /**
     * Builds an Int term from an Int(x) pattern.
     *
     * @param pattern the pattern
     * @param env the term context
     * @return the resulting term
     */
    private IStrategoInt buildInt(IStrategoAppl pattern, IContext env) {
        String x = TermUtils.toJavaStringAt(pattern, 0);
        return env.getFactory().makeInt(new Integer(x));
    }

    /**
     * Builds a constructor application term from an Op(ctor, args) pattern.
     *
     * @param pattern the pattern
     * @param env the term context
     * @return the resulting term; or {@code null} when it failed
     */
    private @Nullable IStrategoTerm buildOp(IStrategoAppl pattern, IContext env) {
        // FIXME memoize constructors
        
        String ctr = TermUtils.toJavaStringAt(pattern, 0);
        IStrategoList children = TermUtils.toListAt(pattern, 1);
        
        if(ctr.length() == 0) {
            return buildTuple(pattern, env);
        } else if(matchesConstructor(ctr, children.getSubtermCount(), env.getStrategoSignature().CTOR_Nil)) {
            return buildNil(pattern, env);
        } else if(matchesConstructor(ctr, children.getSubtermCount(), env.getStrategoSignature().CTOR_Cons)) {
            return buildCons(pattern, env);
        } else {
            return doBuildOp(ctr, pattern, env);
        }
    }

    /**
     * Builds a constructor application term from an Op(ctor, args) pattern.
     *
     * @param ctr the constructor name
     * @param pattern the pattern
     * @param env the term context
     * @return the resulting term; or {@code null} when it failed
     */
    private @Nullable IStrategoTerm doBuildOp(String ctr, IStrategoAppl pattern, IContext env) {
        IStrategoList children = TermUtils.toListAt(pattern, 1);

        IStrategoConstructor ctor = env.getFactory().makeConstructor(ctr, children.size());
        IStrategoTerm[] kids = new IStrategoTerm[children.size()];

        for (int i = children.size() - 1; i >= 0; i--) {
            @Nullable IStrategoTerm kid = buildTerm(TermUtils.toApplAt (children, i), env);
            if (kid == null) {
                // Building the kid failed
                return null;
            }
            kids[i] = kid;
        }

        return env.getFactory().makeAppl(ctor, kids);
    }

    /**
     * Checks whether the specified name and arity match that of the specified constructor.
     *
     * @param name the constructor name to check
     * @param arity the constructor arity to check
     * @param constructor the constructor to check against
     * @return {@code true} when name and arity match; otherwise, {@code false}
     */
    private boolean matchesConstructor(String name, int arity, IStrategoConstructor constructor) {
        return constructor.getName().equals(name) && constructor.getArity() == arity;
    }

    /**
     * Builds a Nil() term from an Nil() pattern.
     *
     * @param pattern the pattern
     * @param env the term context
     * @return the resulting term
     */
    private IStrategoList buildNil(IStrategoAppl pattern, IContext env) {
        return env.getFactory().makeList();
    }

    /**
     * Builds a Cons(head, tail) term from a Cons(head, tail) pattern.
     *
     * @param pattern the pattern
     * @param env the term context
     * @return the resulting term
     */
    private @Nullable IStrategoList buildCons(IStrategoAppl pattern, IContext env) {
        IStrategoList children = TermUtils.toListAt(pattern, 1);
        
        IStrategoAppl headPattern = TermUtils.toApplAt(children, 0);
        IStrategoAppl tailPattern = TermUtils.toApplAt(children, 1);

        @Nullable IStrategoTerm head = buildTerm(headPattern, env);
        @Nullable IStrategoList tail = buildList(tailPattern, env);
        
        if(head == null || tail == null) {
            // Building the head or tail failed.
            return null;
        }
        
        return env.getFactory().makeListCons(head, tail);
    }

    /**
     * Builds a list from a list pattern.
     *
     * @param pattern the pattern
     * @param env the term context
     * @return the resulting term
     */
    private @Nullable IStrategoList buildList(IStrategoAppl pattern, IContext env) {

        // FIXME improve! this is an Anno!
        if (Tools.isAnno(pattern, env)) {
            pattern = TermUtils.toApplAt(pattern, 0);

            String constructorName = TermUtils.toJavaStringAt(pattern, 0);

            if (env.getStrategoSignature().CTOR_Nil.getName().equals(constructorName)) {
                return buildNil(pattern, env);
            } else if (env.getStrategoSignature().CTOR_Cons.getName().equals(constructorName)) {
                return buildCons(pattern, env);
            }
        }

        if (Tools.isVar(pattern, env)) {
            @Nullable IStrategoTerm r = buildVar(pattern, env);
            if (r == null) return null;
            if (TermUtils.isList(r)) {
                return TermUtils.toList(r);
            } else {
                SSLLibrary.instance(env).getIOAgent().printError("Warning: trying to build list with illegal tail: " + pattern.toString());
                return null;
            }
        }

        @Nullable IStrategoTerm r = buildTerm(pattern, env);
        SSLLibrary.instance(env).getIOAgent().printError("Warning: trying to build list with illegal tail: " + r);
        return null;
    }

    /**
     * Builds a tuple term from an Op("", args) pattern.
     *
     * @param pattern the pattern
     * @param env the term context
     * @return the resulting term; or {@code null} when it failed
     */
    private @Nullable IStrategoTerm buildTuple(IStrategoAppl pattern, IContext env) {
        IStrategoList children = TermUtils.toListAt(pattern, 1);
        IStrategoTerm[] kids = new IStrategoTerm[children.size()];

        for (int i = 0; i < children.size(); i++) {
            IStrategoTerm kid = kids[i] = buildTerm(TermUtils.toApplAt(children, i), env);
            if (kid == null) {
                // Building failed.
                return null;
            }
        }

        return env.getFactory().makeTuple(kids);
    }

    /**
     * Builds an annotated term from an Anno(term, annotations) pattern.
     *
     * @param pattern the pattern
     * @param env the term context
     * @return the resulting term; or {@code null} when it failed
     */
    private @Nullable IStrategoTerm buildAnno(IStrategoAppl pattern, IContext env) {
        IStrategoTerm term = buildTerm(TermUtils.toApplAt(pattern, 0), env);
        if (term == null) return null;
        
        IStrategoAppl annos = TermUtils.toApplAt(pattern, 1);
        if (term.getAnnotations().size() == 0 &&
                env.getStrategoSignature().CTOR_Op.getName().equals(annos.getConstructor().getName()) &&
                env.getStrategoSignature().CTOR_Nil.getName().equals(TermUtils.toJavaStringAt(annos, 0))) {
            return term;
        } else {
            IStrategoTerm annoList = buildTerm(annos, env);
            if (annoList == null) return null;
            if (!TermUtils.isList(annoList)) {
                // Make a singleton list
                annoList = env.getFactory().makeList(annoList);
            }
            
            if (annoList.equals(term.getAnnotations())) {
                return term;
            } else {
                return env.getFactory().annotateTerm(term, (IStrategoList) annoList);
            }
        }
    }

    @Override
    public void prettyPrint(StupidFormatter sf) {
        sf.first(toString());
    }

    @Override
    public String toString() {
    	return "Build(" + pattern + ")";
    }
    
    @Override
    protected String getTraceName() {
        return super.getTraceName() + "(" + pattern + ")";
    }
}
