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

public class Tools {

	public static IStrategoString stringAt(IStrategoTerm t, int i) {
		return (IStrategoString) t.getSubterm(i);
	}

	public static IStrategoAppl applAt(IStrategoTerm t, int i) {
		return (IStrategoAppl) t.getSubterm(i);
	}

	public static IStrategoAppl applAt(IStrategoList t, int i) {
		return (IStrategoAppl) t.getSubterm(i);
	}

	public static IStrategoInt intAt(IStrategoTerm t, int i) {
		return (IStrategoInt) t.getSubterm(i);
	}

	public static IStrategoInt intAt(IStrategoList t, int i) {
		return (IStrategoInt) t.getSubterm(i);
	}

	/*
    public IStrategoTerm implode(ITermFactory factory, IStrategoAppl t) throws InterpreterException {
        IStrategoConstructor ctor = t.getConstructor();
        // TODO get an instance of the Stratego signature from the interpreter instance.
        StrategoSignature sign = null;

        if (ctor == sign.getAnno()) {
            return implode(factory, applAt(t, 0));
        } else if (ctor == sign.getOp()) {
            String ctorName = javaStringAt(t, 0);
            IStrategoTerm[] children = t.getSubterm(1).getAllSubterms();

            IStrategoConstructor ctr = factory.makeConstructor(ctorName, children.length);
            IStrategoList kids = factory.makeList();

            for (int i = children.length - 1; i >= 0; i--) {
                kids = factory.makeListCons(implode(factory, (IStrategoAppl) children[i]), kids);
            }
            return factory.makeAppl(ctr, kids.getAllSubterms());
        } else if (ctor == sign.getInt()) {
            IStrategoString x = (IStrategoString) t.getSubterm(0);
            return factory.makeInt(new Integer(x.stringValue()).intValue());
        } else if (ctor == sign.getStr()) {
            IStrategoAppl x = (IStrategoAppl) t.getSubterm(0);
            return x;
        }

        throw new InterpreterException("Unknown build constituent '" + t.getConstructor() + "'");
    }
	 */

	public static IStrategoList listAt(IStrategoTerm t, int i) {
		return (IStrategoList) t.getSubterm(i);
	}

	public static IStrategoList listAt(IStrategoList t, int i) {
		return (IStrategoList) t.getSubterm(i);
	}

    @SuppressWarnings("unchecked") // casting is inherently unsafe, but doesn't warrant a warning here
    public static<T extends IStrategoTerm> T termAt(IStrategoTerm t, int i) {
		return (T) t.getSubterm(i);
	}

	public static IStrategoReal realAt(IStrategoList t, int i) {
		return (IStrategoReal) t.getSubterm(i);
	}

	public static IStrategoTerm termAt(IStrategoList t, int i) {
		return t.getSubterm(i);
	}

	public static boolean isCons(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getCons();
	}

	public static boolean isTermString(IStrategoTerm t) {
		return t.getTermType() == IStrategoTerm.STRING;
	}

	public static String javaString(IStrategoTerm t) {
		return ((IStrategoString) t).stringValue();
	}

	public static boolean isTermList(IStrategoTerm t) {
		return t.getTermType() == IStrategoTerm.LIST;
	}

	public static boolean isNil(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getNil();
	}

	public static boolean isSDefT(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getSDefT();
	}

	public static boolean isExtSDef(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getExtSDef();
	}

	public static boolean isTermInt(IStrategoTerm t) {
		return t.getTermType() == IStrategoTerm.INT;
	}

	public static boolean isAnno(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getAnno();
	}

	public static boolean isOp(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getOp();
	}

	public static boolean isStr(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getStr();
	}

	public static boolean isVar(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getVar();
	}

	public static boolean isExplode(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getExplode();
	}

	public static boolean isWld(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getWld();
	}

	public static boolean isAs(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getAs();
	}

	public static boolean isReal(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getReal();
	}

	public static boolean isInt(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getInt();
	}

	public static boolean isTermReal(IStrategoTerm t) {
		return t.getTermType() == IStrategoTerm.REAL;
	}

	public static boolean isTermAppl(IStrategoTerm t) {
		return t.getTermType() == IStrategoTerm.APPL;
	}

	public static boolean isFunType(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getFunType();
	}

	public static boolean isConstType(IStrategoAppl t, IContext env) {
		return t.getConstructor() == env.getStrategoSignature().getConstType();
	}

	public static String javaStringAt(IStrategoTerm t, int i) {
		return Tools.stringAt(t, i).stringValue();
	}

	public static String javaStringAt(IStrategoAppl t, int i) {
		return Tools.stringAt(t, i).stringValue();
	}

	public static String javaStringAt(IStrategoList t, int i) {
		return Tools.stringAt(t, i).stringValue();
	}

	public static int javaIntAt(IStrategoTerm t, int i) {
		IStrategoInt result = intAt(t, i);
		return result.intValue();
	}

	public static int javaInt(IStrategoTerm term) {
		return ((IStrategoInt) term).intValue();
	}

	public static boolean hasConstructor(IStrategoAppl t, String ctorName) {
		return t.getConstructor().getName().equals(ctorName);
	}

	public static boolean hasConstructor(IStrategoAppl t, String ctorName, int arity) {
		final IStrategoConstructor constructor = t.getConstructor();
		return constructor.getName().equals(ctorName) && constructor.getArity() == arity;
	}
	
	public static String constructorName(IStrategoTerm t) {
	    if(t instanceof IStrategoAppl) {
	        return ((IStrategoAppl)t).getConstructor().getName();
	    }
	    return null;
	}

	public static boolean isTermTuple(IStrategoTerm t) {
		return t.getTermType() == IStrategoTerm.TUPLE;
	}

	public static int asJavaInt(IStrategoTerm term) {
		return ((IStrategoInt) term).intValue();
	}

	public static double asJavaDouble(IStrategoTerm term) {
		return ((IStrategoReal) term).realValue();
	}

	public static String asJavaString(IStrategoTerm term) {
		return ((IStrategoString) term).stringValue();
	}

}
