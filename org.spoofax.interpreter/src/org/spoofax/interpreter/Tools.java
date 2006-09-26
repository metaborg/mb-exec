/*
 * Created on 24.jun.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 *
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.terms.StrategoSignature;

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

    public IStrategoTerm implode(ITermFactory factory, IStrategoAppl t) throws InterpreterException {
        IStrategoConstructor ctor = t.getConstructor();
        StrategoSignature sign = null;
        
        if (ctor.equals(sign.getAnno())) {
            return implode(factory, applAt(t, 0));
        } else if (ctor.equals(sign.getOp())) {
            String ctorName = javaStringAt(t, 0);
            IStrategoList children = (IStrategoList) t.getSubterm(1);

            IStrategoConstructor ctr = factory.makeConstructor(ctorName, children.size(), false);
            IStrategoList kids = factory.makeList();

            for (int i = 0; i < children.size(); i++) {
                kids = kids.append(implode(factory, (IStrategoAppl) children
                        .getSubterm(i)));
            }
            return factory.makeAppl(ctr, kids);
        } else if (ctor.equals(sign.getInt())) {
            IStrategoString x = (IStrategoString) t.getSubterm(0);
            return factory.makeInt(new Integer(x.getValue()).intValue());
        } else if (ctor.equals(sign.getStr())) {
            IStrategoAppl x = (IStrategoAppl) t.getSubterm(0);
            return x;
        }

        throw new InterpreterException("Unknown build constituent '" + t.getConstructor() + "'");
    }

    public static IStrategoList listAt(IStrategoTerm t, int i) {
        return (IStrategoList) t.getSubterm(i);
    }

    public static IStrategoList listAt(IStrategoList t, int i) {
        return (IStrategoList) t.getSubterm(i);
    }

    public static IStrategoTerm termAt(IStrategoTerm t, int i) {
        return t.getSubterm(i);
    }

    public static IStrategoReal realAt(IStrategoList t, int i) {
        return (IStrategoReal) t.getSubterm(i);
    }

    public static IStrategoTerm termAt(IStrategoList t, int i) {
        return t.getSubterm(i);
    }

    public static IStrategoList consToList(IContext env, IStrategoAppl cons) {
        if (Tools.isNil(cons, env))
            return env.getFactory().makeList();
        IStrategoList tail = consToList(env, Tools.applAt(cons, 1));
        IStrategoTerm head = Tools.termAt(cons, 0);

        return tail.insert(head);
    }

//    public static IStrategoList consToListDeep(IContext env, IStrategoAppl cons) {
//        if (Tools.isNil(cons, env))
//            return env.getFactory().makeList();
//
//        IStrategoList tail = consToListDeep(env, Tools.applAt(cons, 1));
//
//        IStrategoTerm head = Tools.termAt(cons, 0);
//        if (head.getTermType() == IStrategoTerm.APPL && Tools.isCons((IStrategoAppl)head, env))
//            head = consToListDeep(env, (IStrategoAppl) head);
//
//        return tail.insert(head);
//    }

    public static boolean isCons(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getCons());
    }

    public static boolean isTermString(IStrategoTerm t) {
        return t instanceof IStrategoString;
    }

    public static String javaString(IStrategoTerm t) {
        return ((IStrategoString)t).getValue();
    }

    public static boolean isTermList(IStrategoTerm t) {
        return t instanceof IStrategoList;
    }

    public static boolean isNil(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getNil());
    }

    public static boolean isSDefT(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getSDefT());
    }

    public static boolean isExtSDef(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getExtSDef());
    }

    public static boolean isTermInt(IStrategoTerm t) {
        return t instanceof IStrategoInt;
    }

    public static boolean isAnno(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getAnno());
    }

    public static boolean isOp(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getOp());
    }

    public static boolean isStr(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getStr());
}

    public static boolean isVar(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getVar());
    }

    public static boolean isExplode(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getExplode());
    }

    public static boolean isWld(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getWld());
    }

    public static boolean isAs(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getAs());
    }

    public static boolean isReal(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getReal());
    }

    public static boolean isInt(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getInt());
    }

    public static boolean isTermReal(IStrategoTerm t) {
        return t instanceof IStrategoReal;
    }

    public static boolean isTermAppl(IStrategoTerm t) {
        return t instanceof IStrategoAppl;
    }

    public static boolean isFunType(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getFunType());
    }

    public static boolean isConstType(IStrategoAppl t, IContext env) {
        return t.getConstructor().equals(env.getStrategoSignature().getConstType());
    }

    public static String javaStringAt(IStrategoAppl t, int i) {
        return Tools.stringAt(t, i).getValue();
    }

    public static String javaStringAt(IStrategoList t, int i) {
        return Tools.stringAt(t, i).getValue();
    }

    public static int javaInt(IStrategoTerm term) {
        return ((IStrategoInt)term).getValue();
    }

    public static boolean hasConstructor(IStrategoAppl t, String ctorName) {
        return t.getConstructor().getName().equals(ctorName);
    }

}
