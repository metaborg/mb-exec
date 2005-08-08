/*
 * Created on 29.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import aterm.AFun;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;
import aterm.pure.PureFactory;

public class ATermBuilder implements IATermBuilder {

    protected PureFactory factory;

    ATermBuilder() {
        factory = new PureFactory();
    }
    
    ATerm makePattern(String s) {
        return factory.parse(s);
    }

    public ATerm makeList(String s) {
        ATermList t = (ATermList) makeTerm(s);
        return makeList(t);
    }
    
    public ATerm makeList(ATermList t) {
        ATerm l = makeTerm("Nil");
        AFun f = factory.makeAFun("Cons", 2, false);
        for (int i = t.getLength() - 1; i >= 0; i--)
            l = factory.makeAppl(f, (ATerm) t.getChildAt(i), l);
        return l;
    }

    public ATerm makeList(ATerm... terms) {
        ATermList l = factory.makeList();
        for(ATerm t : terms)
            l = l.append(t);
        return makeList(l);
    }

    public ATerm makeTuple(String s) {
        ATermList t = (ATermList) makeTerm(s);
        return makeTuple(t);
    }
    
    public ATerm makeTuple(ATermList t) {
        ATerm[] t2 = new ATerm[t.getLength()];
        for (int i = 0; i < t.getLength(); i++)
            t2[i] = (ATerm) t.getChildAt(i);
    
        AFun f = factory.makeAFun("", t2.length, false);
        return factory.makeAppl(f, t2);
    }

    public ATerm makeTuple(ATerm... terms) {
        ATermList l = factory.makeList();
        for(ATerm t : terms)
            l = l.append(t);
        return makeTuple(l);
    }

    public ATerm makeTerm(String s) {
        return factory.parse(s);
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
