package org.spoofax.interpreter.core;

import org.spoofax.interpreter.terms.*;
import org.spoofax.terms.util.TermUtils;

import javax.annotation.Nullable;
import java.util.List;


/**
 * Functions for working with terms.
 *
 * @see TermUtils
 */
public final class Tools {

    // Subterms

    /**
     * Gets the string subterm at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the string subterm; or {@code null} when the subterm is not a string term
     * @throws IndexOutOfBoundsException the index is out of bounds
     * @deprecated Use {@link TermUtils#asStringAt}.
     */
    @Deprecated
    @Nullable
    public static IStrategoString stringAt(IStrategoTerm t, int i) {
        return TermUtils.asString(t.getSubterm(i)).orElse(null);
    }

    /**
     * Gets the constructor application subterm at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the constructor application subterm; or {@code null} when the subterm is not a constructor application term
     * @throws IndexOutOfBoundsException the index is out of bounds
     * @deprecated Use {@link TermUtils#asApplAt}.
     */
    @Deprecated
    @Nullable
    public static IStrategoAppl applAt(IStrategoTerm t, int i) {
        return TermUtils.asAppl(t.getSubterm(i)).orElse(null);
    }

    /**
     * Gets the int subterm at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the int subterm; or {@code null} when the subterm is not an int term
     * @throws IndexOutOfBoundsException the index is out of bounds
     * @deprecated Use {@link TermUtils#asIntAt}.
     */
    @Deprecated
    @Nullable
    public static IStrategoInt intAt(IStrategoTerm t, int i) {
        return TermUtils.asInt(t.getSubterm(i)).orElse(null);
    }

    /**
     * Gets the list subterm at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the list subterm; or {@code null} when the subterm is not a list term
     * @throws IndexOutOfBoundsException the index is out of bounds
     * @deprecated Use {@link TermUtils#asListAt}.
     */
    @Deprecated
    @Nullable
    public static IStrategoList listAt(IStrategoTerm t, int i) {
        return TermUtils.asList(t.getSubterm(i)).orElse(null);
    }

    /**
     * Gets the real subterm at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the real subterm; or {@code null} when the subterm is not a real term
     * @throws IndexOutOfBoundsException the index is out of bounds
     * @deprecated Use {@link TermUtils#asRealAt}.
     */
    @Deprecated
    @Nullable
    public static IStrategoReal realAt(IStrategoTerm t, int i) {
        return TermUtils.asReal(t.getSubterm(i)).orElse(null);
    }

    /**
     * Gets the subterm at the given index in the given term.
     * <p>
     * Note that this function will not throw an exception when the term is cast to the wrong type.
     * Instead, you will get a {@link ClassCastException} somewhere else.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the subterm
     * @throws IndexOutOfBoundsException the index is out of bounds
     */
    @SuppressWarnings("unchecked") // casting is inherently unsafe, but doesn't warrant a warning here
    public static <T extends IStrategoTerm> T termAt(IStrategoTerm t, int i) {
        return (T)t.getSubterm(i);
    }


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

    /**
     * Determines whether the given constructor application term has a constructor with the specified name.
     *
     * @param t        the constructor application term to check
     * @param ctorName the expected constructor name
     * @return {@code true} when the term has the expected constructor name;
     * otherwise, {@code false}
     */
    public static boolean hasConstructor(IStrategoAppl t, String ctorName) {
        return t.getConstructor().getName().equals(ctorName);
    }

    /**
     * Determines whether the given constructor application term has a constructor with the specified name.
     * and arity
     *
     * @param t        the constructor application term to check
     * @param ctorName the expected constructor name
     * @param arity    the expected constructor arity
     * @return {@code true} when the term has the expected constructor name and arity;
     * otherwise, {@code false}
     * @deprecated Use {@link TermUtils#isAppl}.
     */
    @Deprecated
    public static boolean hasConstructor(IStrategoAppl t, String ctorName, int arity) {
        return TermUtils.isAppl(t, ctorName, arity);
    }

    /**
     * Extracts the constructor name of the given term.
     *
     * @param t the term whose constructor name to extract
     * @return the constructor name; or {@code null} when the term has no constructor
     * @deprecated Use {@link TermUtils#asAppl} and map to {@link IStrategoAppl#getConstructor()}.
     */
    @Nullable
    @Deprecated
    public static String constructorName(IStrategoTerm t) {
        return TermUtils.asAppl(t).map(a -> a.getConstructor().getName()).orElse(null);
    }


    // Term Types

    /**
     * Determines whether the given term is a String term.
     *
     * @param t the term to check
     * @return {@code true} when the term is a String term; otherwise, {@code false}
     * @deprecated Use {@link TermUtils#isString}.
     */
    @Deprecated
    public static boolean isTermString(IStrategoTerm t) {
        return TermUtils.isString(t);
    }

    /**
     * Determines whether the given term is a List term.
     *
     * @param t the term to check
     * @return {@code true} when the term is a List term; otherwise, {@code false}
     * @deprecated Use {@link TermUtils#isList}.
     */
    @Deprecated
    public static boolean isTermList(IStrategoTerm t) {
        return TermUtils.isList(t);
    }

    /**
     * Determines whether the given term is an Int term.
     *
     * @param t the term to check
     * @return {@code true} when the term is an Int term; otherwise, {@code false}
     * @deprecated Use {@link TermUtils#isInt}.
     */
    @Deprecated
    public static boolean isTermInt(IStrategoTerm t) {
        return TermUtils.isInt(t);
    }

    /**
     * Determines whether the given term is a Real term.
     *
     * @param t the term to check
     * @return {@code true} when the term is a Real term; otherwise, {@code false}
     * @deprecated Use {@link TermUtils#isReal}.
     */
    @Deprecated
    public static boolean isTermReal(IStrategoTerm t) {
        return TermUtils.isReal(t);
    }

    /**
     * Determines whether the given term is a constructor application term.
     *
     * @param t the term to check
     * @return {@code true} when the term is a constructor application term; otherwise, {@code false}
     * @deprecated Use {@link TermUtils#isAppl}.
     */
    @Deprecated
    public static boolean isTermAppl(IStrategoTerm t) {
        return TermUtils.isAppl(t);
    }

    /**
     * Determines whether the given term is a Tuple term.
     *
     * @param t the term to check
     * @return {@code true} when the term is a tuple term; otherwise, {@code false}
     */
    public static boolean isTermTuple(IStrategoTerm t) {
        return t.getTermType() == IStrategoTerm.TUPLE;
    }


    // Java Types

    /**
     * Returns the given term as a Java integer.
     *
     * @param term the term
     * @return the Java string
     * @throws ClassCastException the term is not an Int term
     * @deprecated Use {@link TermUtils#toJavaInt}.
     */
    @Deprecated
    public static int asJavaInt(IStrategoTerm term) {
        return TermUtils.toJavaInt(term);
    }

    /** @deprecated Use {@link TermUtils#toJavaInt} instead. */
    @Deprecated
    public static int javaInt(IStrategoTerm term) {
        return TermUtils.toJavaInt(term);
    }

    /**
     * Gets the Java integer at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the Java integer
     * @throws ClassCastException        the term is not an Int term
     * @throws IndexOutOfBoundsException the index is out of bounds
     * @deprecated Use {@link TermUtils#toJavaIntAt}.
     */
    @Deprecated
    public static int javaIntAt(IStrategoTerm t, int i) {
        return TermUtils.toJavaIntAt(t, i);
    }

    /**
     * Returns the given term as a Java double.
     *
     * @param term the term
     * @return the Java double
     * @throws ClassCastException the term is not a Real term
     * @deprecated Use {@link TermUtils#toJavaReal}.
     */
    @Deprecated
    public static double asJavaDouble(IStrategoTerm term) {
        return TermUtils.toJavaReal(term);
    }

    /**
     * Gets the Java double at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the Java integer
     * @throws ClassCastException        the term is not a Real term
     * @throws IndexOutOfBoundsException the index is out of bounds
     * @deprecated Use {@link TermUtils#toJavaRealAt}.
     */
    @Deprecated
    public static double javaDoubleAt(IStrategoTerm t, int i) {
        return TermUtils.toJavaRealAt(t, i);
    }

    /**
     * Returns the given term as a Java string.
     *
     * @param term the term
     * @return the Java string
     * @throws ClassCastException the term is not a String term
     * @deprecated Use {@link TermUtils#toJavaString}.
     */
    @Deprecated
    public static String asJavaString(IStrategoTerm term) {
        return TermUtils.toJavaString(term);
    }

    /** @deprecated Use {@link TermUtils#toJavaString}. */
    @Deprecated
    public static String javaString(IStrategoTerm t) {
        return TermUtils.toJavaString(t);
    }

    /**
     * Gets the Java string at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the Java string
     * @throws ClassCastException        the term is not a String term
     * @throws IndexOutOfBoundsException the index is out of bounds
     * @deprecated Use {@link TermUtils#toJavaStringAt}.
     */
    @Deprecated
    public static String javaStringAt(IStrategoTerm t, int i) {
        return TermUtils.toJavaStringAt(t, i);
    }

    /**
     * Returns the given term as a Java list.
     *
     * @param term the term
     * @return the Java unmodifiable list
     * @throws ClassCastException the term is not a List term
     * @deprecated Use {@link TermUtils#toJavaList}.
     */
    @Deprecated
    public static List<IStrategoTerm> asJavaList(IStrategoTerm term) {
        return TermUtils.toJavaList(term);
    }

    /**
     * Gets the Java list at the given index in the given term.
     *
     * @param term  the term
     * @param index the index within the term's subterms
     * @return the Java unmodifiable list
     * @throws ClassCastException        the term is not a List term
     * @throws IndexOutOfBoundsException the index is out of bounds
     * @deprecated Use {@link TermUtils#toJavaListAt}.
     */
    @Deprecated
    public static List<IStrategoTerm> javaListAt(IStrategoTerm term, int index) {
        return TermUtils.toJavaListAt(term, index);
    }

}
