package org.spoofax.interpreter.core;

import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.util.TermUtils;

import jakarta.annotation.Nullable;
import java.util.List;


/**
 * Functions for working with terms.
 *
 * @see TermUtils
 */
public final class Tools {

    // Constructors

    /**
     * Determines whether the given term is a {@code Cons(_, _)} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isCons(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getCons());
    }

    /**
     * Determines whether the given constructor application term is a {@code Nil()} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isNil(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getNil());
    }

    /**
     * Determines whether the given constructor application term is a {@code SDefT(_, _, _, _)} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isSDefT(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getSDefT());
    }

    /**
     * Determines whether the given constructor application term is an {@code ExtSDef(_, _, _)} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isExtSDef(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getExtSDef());
    }

    /**
     * Determines whether the given constructor application term is an {@code Anno(_, _)} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isAnno(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getAnno());
    }

    /**
     * Determines whether the given constructor application term is an {@code Op(_, _)} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isOp(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getOp());
    }

    /**
     * Determines whether the given constructor application term is a {@code Str(_)} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isStr(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getStr());
    }

    /**
     * Determines whether the given constructor application term is a {@code Var(_)} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isVar(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getVar());
    }

    /**
     * Determines whether the given constructor application term is an {@code Explode(_, _)} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isExplode(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getExplode());
    }

    /**
     * Determines whether the given constructor application term is a {@code Wld()} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isWld(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getWld());
    }

    /**
     * Determines whether the given constructor application term is an {@code As(_, _)} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isAs(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getAs());
    }

    /**
     * Determines whether the given constructor application term is a {@code Real(_)} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isReal(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getReal());
    }

    /**
     * Determines whether the given constructor application term is an {@code Int(_)} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isInt(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getInt());
    }

    /**
     * Determines whether the given constructor application term is a {@code FunType(_, _)} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isFunType(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getFunType());
    }

    /**
     * Determines whether the given constructor application term is a {@code ConstType(_)} term.
     *
     * @param t   the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
    public static boolean isConstType(IStrategoAppl t, IContext env) {
        return TermUtils.isAppl(t, env.getStrategoSignature().getConstType());
    }



    // Subterms

    /** @deprecated Use {@link TermUtils#asStringAt}. */
    @Deprecated
    @Nullable
    public static IStrategoString stringAt(IStrategoTerm t, int i) {
        return TermUtils.asString(t.getSubterm(i)).orElse(null);
    }

    /** @deprecated Use {@link TermUtils#asApplAt}. */
    @Deprecated
    @Nullable
    public static IStrategoAppl applAt(IStrategoTerm t, int i) {
        return TermUtils.asAppl(t.getSubterm(i)).orElse(null);
    }

    /** @deprecated Use {@link TermUtils#asIntAt}. */
    @Deprecated
    @Nullable
    public static IStrategoInt intAt(IStrategoTerm t, int i) {
        return TermUtils.asInt(t.getSubterm(i)).orElse(null);
    }

    /** @deprecated Use {@link TermUtils#asListAt}. */
    @Deprecated
    @Nullable
    public static IStrategoList listAt(IStrategoTerm t, int i) {
        return TermUtils.asList(t.getSubterm(i)).orElse(null);
    }

    /** @deprecated Use {@link TermUtils#asRealAt}. */
    @Deprecated
    @Nullable
    public static IStrategoReal realAt(IStrategoTerm t, int i) {
        return TermUtils.asReal(t.getSubterm(i)).orElse(null);
    }

    /** @deprecated Use {@link IStrategoTerm#getSubterm(int)} and the functions of {@link TermUtils}. */
    @SuppressWarnings("unchecked") // casting is inherently unsafe, but doesn't warrant a warning here
    @Deprecated
    public static <T extends IStrategoTerm> T termAt(IStrategoTerm t, int i) {
        return (T)t.getSubterm(i);
    }


    // Constructors

    /** @deprecated Use {@link TermUtils#isAppl}. */
    @Deprecated
    public static boolean hasConstructor(IStrategoAppl t, String ctorName) {
        return TermUtils.isAppl(t, ctorName);
    }

    /** @deprecated Use {@link TermUtils#isAppl}. */
    @Deprecated
    public static boolean hasConstructor(IStrategoAppl t, String ctorName, int arity) {
        return TermUtils.isAppl(t, ctorName, arity);
    }

    /** @deprecated Use {@link TermUtils#asAppl} and map to {@link IStrategoAppl#getConstructor()}. */
    @Nullable
    @Deprecated
    public static String constructorName(IStrategoTerm t) {
        return TermUtils.asAppl(t).map(a -> a.getConstructor().getName()).orElse(null);
    }


    // Term Types

    /** @deprecated Use {@link TermUtils#isString}. */
    @Deprecated
    public static boolean isTermString(IStrategoTerm t) {
        return TermUtils.isString(t);
    }

    /** @deprecated Use {@link TermUtils#isList}. */
    @Deprecated
    public static boolean isTermList(IStrategoTerm t) {
        return TermUtils.isList(t);
    }

    /** @deprecated Use {@link TermUtils#isInt}. */
    @Deprecated
    public static boolean isTermInt(IStrategoTerm t) {
        return TermUtils.isInt(t);
    }

    /** @deprecated Use {@link TermUtils#isReal}. */
    @Deprecated
    public static boolean isTermReal(IStrategoTerm t) {
        return TermUtils.isReal(t);
    }

    /** @deprecated Use {@link TermUtils#isAppl}. */
    @Deprecated
    public static boolean isTermAppl(IStrategoTerm t) {
        return TermUtils.isAppl(t);
    }

    /** @deprecated Use {@link TermUtils#isTuple}. */
    @Deprecated
    public static boolean isTermTuple(IStrategoTerm t) {
        return TermUtils.isTuple(t);
    }


    // Java Types

    /** @deprecated Use {@link TermUtils#toJavaInt}. */
    @Deprecated
    public static int asJavaInt(IStrategoTerm term) {
        return TermUtils.toJavaInt(term);
    }

    /** @deprecated Use {@link TermUtils#toJavaInt}. */
    @Deprecated
    public static int javaInt(IStrategoTerm term) {
        return TermUtils.toJavaInt(term);
    }

    /** @deprecated Use {@link TermUtils#toJavaIntAt}. */
    @Deprecated
    public static int javaIntAt(IStrategoTerm t, int i) {
        return TermUtils.toJavaIntAt(t, i);
    }

    /** @deprecated Use {@link TermUtils#toJavaReal}. */
    @Deprecated
    public static double asJavaDouble(IStrategoTerm term) {
        return TermUtils.toJavaReal(term);
    }

    /** @deprecated Use {@link TermUtils#toJavaRealAt}. */
    @Deprecated
    public static double javaDoubleAt(IStrategoTerm t, int i) {
        return TermUtils.toJavaRealAt(t, i);
    }

    /** @deprecated Use {@link TermUtils#toJavaString}. */
    @Deprecated
    public static String asJavaString(IStrategoTerm term) {
        return TermUtils.toJavaString(term);
    }

    /** @deprecated Use {@link TermUtils#toJavaString}. */
    @Deprecated
    public static String javaString(IStrategoTerm t) {
        return TermUtils.toJavaString(t);
    }

    /** @deprecated Use {@link TermUtils#toJavaStringAt}. */
    @Deprecated
    public static String javaStringAt(IStrategoTerm t, int i) {
        return TermUtils.toJavaStringAt(t, i);
    }

    /** @deprecated Use {@link TermUtils#toJavaList}. */
    @Deprecated
    public static List<IStrategoTerm> asJavaList(IStrategoTerm term) {
        return TermUtils.toJavaList(term);
    }

    /** @deprecated Use {@link TermUtils#toJavaListAt}. */
    @Deprecated
    public static List<IStrategoTerm> javaListAt(IStrategoTerm term, int index) {
        return TermUtils.toJavaListAt(term, index);
    }

}
