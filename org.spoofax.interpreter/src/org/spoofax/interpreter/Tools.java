/*
 * Created on 24.jun.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import aterm.AFun;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermInt;
import aterm.ATermList;
import aterm.ATermReal;
import aterm.pure.PureFactory;

public class Tools {

    public static String stringAt(ATerm t, int i) {
        return ((ATermAppl) t.getChildAt(i)).getName();
    }

    public static ATermAppl applAt(ATerm t, int i) {
        return (ATermAppl) t.getChildAt(i);
    }

    public static ATermAppl applAt(ATermList t, int i) {
        return (ATermAppl) t.getChildAt(i);
    }

    public static ATermInt intAt(ATerm t, int i) {
        return (ATermInt) t.getChildAt(i);
    }

    public static ATermInt intAt(ATermList t, int i) {
        return (ATermInt) t.getChildAt(i);
    }

    public ATerm implode(PureFactory factory, ATermAppl t) throws InterpreterException {
        if (t.getName().equals("Anno")) {
            return implode(factory, applAt(t, 0));
        } else if (t.getName().equals("Op")) {
            String ctr = stringAt(t, 0);
            ATermList children = (ATermList) t.getChildAt(1);

            AFun afun = factory.makeAFun(ctr, children.getLength(), false);
            ATermList kids = factory.makeList();

            for (int i = 0; i < children.getLength(); i++) {
                kids = kids.append(implode(factory, (ATermAppl) children
                        .elementAt(i)));
            }
            return factory.makeApplList(afun, kids);
        } else if (t.getName().equals("Int")) {
            ATermAppl x = (ATermAppl) t.getChildAt(0);
            return factory.makeInt(new Integer(x.getName()));
        } else if (t.getName().equals("Str")) {
            ATermAppl x = (ATermAppl) t.getChildAt(0);
            return x;
        }

        throw new InterpreterException("Unknown build constituent '" + t.getName() + "'");
    }

    public static ATermList listAt(ATerm t, int i) {
        return (ATermList) t.getChildAt(i);
    }

    public static ATermList listAt(ATermList t, int i) {
        return (ATermList) t.getChildAt(i);
    }

    public static ATerm termAt(ATermAppl t, int i) {
        return (ATerm) t.getChildAt(i);
    }

    public static ATermReal realAt(ATermList tvars, int i) {
        return (ATermReal) tvars.getChildAt(i);
    }

    public static ATerm termAt(ATermList tvars, int i) {
        return (ATerm) tvars.getChildAt(i);
    }

    public static boolean termType(ATermAppl p, String n) {
        return p.getName().equals(n);
    }

    public static ATermList consToList(IContext env, ATermAppl cons) {
        if (cons.getAFun() == env.getNilAFun())
            return env.getFactory().makeList();
        ATermList tail = consToList(env, Tools.applAt(cons, 1));
        ATerm head = Tools.termAt(cons, 0);

        return tail.insert(head);
    }

    public static ATermList consToListDeep(IContext env, ATermAppl cons) {
        if (cons.getAFun() == env.getNilAFun())
            return env.getFactory().makeList();

        ATermList tail = consToListDeep(env, Tools.applAt(cons, 1));

        ATerm head = Tools.termAt(cons, 0);
        if (head.getType() == ATerm.APPL && Tools.isCons((ATermAppl)head, env))
            head = consToListDeep(env, (ATermAppl) head);

        return tail.insert(head);
    }

    public static boolean isCons(ATermAppl t, IContext env) {
        return t.getAFun() == env.getConsAFun();
    }

    public static boolean isATermString(ATerm t) {
        if (t.getType() == ATerm.APPL) {
            AFun f = ((ATermAppl) t).getAFun();
            return f.isQuoted() && f.getChildCount() == 0;
        }
        return false;
    }

    public static String getATermString(ATerm t) {
        AFun f = ((ATermAppl) t).getAFun();
        return f.getName();
    }

    public static boolean isATermList(ATerm t) {
        return t.getType() == ATerm.LIST;
    }

    public static boolean isNil(ATermAppl t, IContext env) {
        return t.getAFun() == env.getNilAFun();
    }

    public static boolean isSDefT(ATermAppl t, IContext env) {
        return t.getAFun() == env.getSDefTAFun();
    }

    public static boolean isExtSDef(ATermAppl t, IContext env) {
        return t.getAFun() == env.getExtSDefAFun();
    }

    public static boolean isATermInt(ATerm t) {
        return t.getType() == ATerm.INT;
    }

    public static boolean isAnno(ATermAppl t, IContext env) {
        return t.getAFun() == env.getAnnoAFun();
    }
    
    public static boolean isOp(ATermAppl t, IContext env) {
        return t.getAFun() == env.getOpAFun();
    }

//    private static boolean isATermConstructor(ATermAppl t, String opName) {
//        return t.getType() == ATerm.APPL
//        && ((ATermAppl) t).getName().equals(opName);
//    }

    public static boolean isStr(ATermAppl t, IContext env) {
        return t.getAFun() == env.getStrAFun();
    }

    public static boolean isVar(ATermAppl t, IContext env) {
        return t.getAFun() == env.getVarAFun();
    }

    public static boolean isExplode(ATermAppl t, IContext env) {
        return t.getAFun() == env.getExplodeAFun();
    }

    public static boolean isWld(ATermAppl t, IContext env) {
        return t.getAFun() == env.getWldAFun();
    }

    public static boolean isAs(ATermAppl t, IContext env) {
        return t.getAFun() == env.getAsAFun();
    }

    public static boolean isReal(ATermAppl t, IContext env) {
        return t.getAFun() == env.getRealAFun();
    }

    public static boolean isInt(ATermAppl t, IContext env) {
        return t.getAFun() == env.getIntAFun();
    }

    public static boolean isATermReal(ATerm t) {
        return t.getType() == ATerm.REAL;
    }

    public static boolean isATermAppl(ATerm t) {
        return t.getType() == ATerm.APPL;
    }

    public static int getATermInt(ATermInt t) {
        return t.getInt();
    }

    public static boolean isFunType(ATermAppl t, IContext env) {
        return t.getAFun() == env.getFunTypeAFun();
    }

    public static boolean isConstType(ATermAppl t, IContext env) {
        return t.getAFun() == env.getConstTypeAFun();
    }

}
