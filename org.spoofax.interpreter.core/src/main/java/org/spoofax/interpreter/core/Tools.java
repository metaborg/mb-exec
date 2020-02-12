/*
 * Created on 24.jun.2005
 * 
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Lesser General Public License, v2.1.1
 */
package org.spoofax.interpreter.core;

import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;

import javax.annotation.Nullable;


/**
 * Functions for working with terms.
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
     */
    @Nullable
	public static IStrategoString stringAt(IStrategoTerm t, int i) {
        IStrategoTerm subterm = t.getSubterm(i);
        return subterm instanceof IStrategoString ? (IStrategoString)subterm : null;
	}

    /**
     * Gets the constructor application subterm at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the constructor application subterm; or {@code null} when the subterm is not a constructor application term
     * @throws IndexOutOfBoundsException the index is out of bounds
     */
    @Nullable
	public static IStrategoAppl applAt(IStrategoTerm t, int i) {
        IStrategoTerm subterm = t.getSubterm(i);
        return subterm instanceof IStrategoAppl ? (IStrategoAppl)subterm : null;
	}

    /**
     * Gets the int subterm at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the int subterm; or {@code null} when the subterm is not an int term
     * @throws IndexOutOfBoundsException the index is out of bounds
     */
    @Nullable
	public static IStrategoInt intAt(IStrategoTerm t, int i) {
        IStrategoTerm subterm = t.getSubterm(i);
        return subterm instanceof IStrategoInt ? (IStrategoInt)subterm : null;
	}

    /**
     * Gets the list subterm at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the list subterm; or {@code null} when the subterm is not a list term
     * @throws IndexOutOfBoundsException the index is out of bounds
     */
    @Nullable
	public static IStrategoList listAt(IStrategoTerm t, int i) {
        IStrategoTerm subterm = t.getSubterm(i);
        return subterm instanceof IStrategoList ? (IStrategoList)subterm : null;
	}

    /**
     * Gets the real subterm at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the real subterm; or {@code null} when the subterm is not a real term
     * @throws IndexOutOfBoundsException the index is out of bounds
     */
    @Nullable
    public static IStrategoReal realAt(IStrategoTerm t, int i) {
        IStrategoTerm subterm = t.getSubterm(i);
        return subterm instanceof IStrategoReal ? (IStrategoReal)subterm : null;
    }

    /**
     * Gets the subterm at the given index in the given term.
     *
     * Note that this function will not throw an exception when the term is cast to the wrong type.
     * Instead, you will get a {@link ClassCastException} somewhere else.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the subterm
     * @throws IndexOutOfBoundsException the index is out of bounds
     */
    @SuppressWarnings("unchecked") // casting is inherently unsafe, but doesn't warrant a warning here
    public static<T extends IStrategoTerm> T termAt(IStrategoTerm t, int i) {
		return (T) t.getSubterm(i);
	}


	// Constructors

    /**
     * Determines whether the given term is a {@code Cons(_, _)} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isCons(IStrategoAppl t, IContext env) {
		return env.getStrategoSignature().getCons().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is a {@code Nil()} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isNil(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getNil().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is a {@code SDefT(_, _, _, _)} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isSDefT(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getSDefT().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is an {@code ExtSDef(_, _, _)} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isExtSDef(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getExtSDef().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is an {@code Anno(_, _)} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isAnno(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getAnno().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is an {@code Op(_, _)} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isOp(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getOp().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is a {@code Str(_)} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isStr(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getStr().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is a {@code Var(_)} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isVar(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getVar().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is an {@code Explode(_, _)} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isExplode(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getExplode().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is a {@code Wld()} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isWld(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getWld().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is an {@code As(_, _)} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isAs(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getAs().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is a {@code Real(_)} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isReal(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getReal().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is an {@code Int(_)} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isInt(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getInt().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is a {@code FunType(_, _)} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isFunType(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getFunType().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term is a {@code ConstType(_)} term.
     *
     * @param t the constructor application term to check
     * @param env the term context in which to check
     * @return {@code true} when the term has the expected constructor in the given context;
     * otherwise, {@code false}
     */
	public static boolean isConstType(IStrategoAppl t, IContext env) {
        return env.getStrategoSignature().getConstType().equals(t.getConstructor());
	}

    /**
     * Determines whether the given constructor application term has a constructor with the specified name.
     *
     * @param t the constructor application term to check
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
     * @param t the constructor application term to check
     * @param ctorName the expected constructor name
     * @param arity the expected constructor arity
     * @return {@code true} when the term has the expected constructor name and arity;
     * otherwise, {@code false}
     */
    public static boolean hasConstructor(IStrategoAppl t, String ctorName, int arity) {
        final IStrategoConstructor constructor = t.getConstructor();
        return constructor.getName().equals(ctorName) && constructor.getArity() == arity;
    }

    /**
     * Extracts the constructor name of the given term.
     *
     * @param t the term whose constructor name to extract
     * @return the constructor name; or {@code null} when the term has no constructor
     */
    @Nullable
    public static String constructorName(IStrategoTerm t) {
        return t instanceof IStrategoAppl ? ((IStrategoAppl)t).getConstructor().getName() : null;
    }



	// Term Types

    /**
     * Determines whether the given term is a String term.
     *
     * @param t the term to check
     * @return {@code true} when the term is a String term; otherwise, {@code false}
     */
    public static boolean isTermString(IStrategoTerm t) {
        return t.getTermType() == IStrategoTerm.STRING;
    }

    /**
     * Determines whether the given term is a List term.
     *
     * @param t the term to check
     * @return {@code true} when the term is a List term; otherwise, {@code false}
     */
    public static boolean isTermList(IStrategoTerm t) {
        return t.getTermType() == IStrategoTerm.LIST;
    }

    /**
     * Determines whether the given term is an Int term.
     *
     * @param t the term to check
     * @return {@code true} when the term is an Int term; otherwise, {@code false}
     */
    public static boolean isTermInt(IStrategoTerm t) {
        return t.getTermType() == IStrategoTerm.INT;
    }

    /**
     * Determines whether the given term is a Real term.
     *
     * @param t the term to check
     * @return {@code true} when the term is a Real term; otherwise, {@code false}
     */
    public static boolean isTermReal(IStrategoTerm t) {
        return t.getTermType() == IStrategoTerm.REAL;
    }

    /**
     * Determines whether the given term is a constructor application term.
     *
     * @param t the term to check
     * @return {@code true} when the term is a constructor application term; otherwise, {@code false}
     */
    public static boolean isTermAppl(IStrategoTerm t) {
        return t.getTermType() == IStrategoTerm.APPL;
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
     */
	public static int asJavaInt(IStrategoTerm term) {
		return ((IStrategoInt) term).intValue();
	}

    /** @deprecated Use {@link #asJavaInt} instead. */
    @Deprecated
    public static int javaInt(IStrategoTerm term) {
        return ((IStrategoInt) term).intValue();
    }

    /**
     * Gets the Java integer at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the Java integer
     * @throws ClassCastException the term is not an Int term
     * @throws IndexOutOfBoundsException the index is out of bounds
     */
    public static int javaIntAt(IStrategoTerm t, int i) {
        IStrategoInt result = termAt(t, i);
        return result.intValue();
    }

    /**
     * Returns the given term as a Java double.
     *
     * @param term the term
     * @return the Java double
     * @throws ClassCastException the term is not a Real term
     */
	public static double asJavaDouble(IStrategoTerm term) {
		return ((IStrategoReal) term).realValue();
	}

    /**
     * Gets the Java double at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the Java integer
     * @throws ClassCastException the term is not a Real term
     * @throws IndexOutOfBoundsException the index is out of bounds
     */
    public static double javaDoubleAt(IStrategoTerm t, int i) {
        IStrategoReal result = termAt(t, i);
        return result.realValue();
    }

    /**
     * Returns the given term as a Java string.
     *
     * @param term the term
     * @return the Java string
     * @throws ClassCastException the term is not a String term
     */
	public static String asJavaString(IStrategoTerm term) {
		return ((IStrategoString) term).stringValue();
	}

    /** @deprecated Use {@link #asJavaString} instead. */
    @Deprecated
    public static String javaString(IStrategoTerm t) {
        return ((IStrategoString) t).stringValue();
    }

    /**
     * Gets the Java string at the given index in the given term.
     *
     * @param t the term
     * @param i the index within the term's subterms
     * @return the Java string
     * @throws ClassCastException the term is not a String term
     * @throws IndexOutOfBoundsException the index is out of bounds
     */
    public static String javaStringAt(IStrategoTerm t, int i) {
        IStrategoString result = termAt(t, i);
        return result.stringValue();
    }

}
