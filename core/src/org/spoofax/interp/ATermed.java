/*
 * Created on 29.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

import aterm.AFun;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;
import aterm.pure.PureFactory;

public class ATermed {

    protected PureFactory factory;

    public ATerm makeTerm(String s) {
        return factory.parse(s);
    }

    ATerm makePattern(String s) {
        return factory.parse(s);
    }

    public ATerm makeList(String s) {
        ATermList t = (ATermList) makeTerm(s);
        ATerm l = makeTerm("Nil");
        AFun f = factory.makeAFun("Cons", 2, false);
        for (int i = t.getLength() - 1; i >= 0; i--)
            l = factory.makeAppl(f, (ATerm) t.getChildAt(i), l);
        return l;
    }

    public ATerm makeTuple(String s) {
        ATermList t = (ATermList) makeTerm(s);
        ATerm[] t2 = new ATerm[t.getLength()];
        for (int i = 0; i < t.getLength(); i++)
            t2[i] = (ATerm) t.getChildAt(i);
    
        AFun f = factory.makeAFun("", t2.length, false);
        return factory.makeAppl(f, t2);
    }

    public ATermAppl makeTerm(String op, ATerm a0, ATerm a1) {
        AFun fun = factory.makeAFun(op, 2, false);
        return factory.makeAppl(fun, a0, a1);
    }

    public ATerm makeTerm(int i) {
        return factory.makeInt(i);
    }

    public ATerm makeTerm(double d) {
        return factory.makeReal(d);
    }

}
