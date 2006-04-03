/*
 * Created on 29.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import java.util.Collection;

import aterm.AFun;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermList;
import org.spoofax.interpreter.stratego.DebugUtil;

public class ATermBuilder implements IATermBuilder {

    protected TermFactory factory;
    protected static TermFactory factoryShared = new TermFactory();

    protected AFun OpAFun;
    protected AFun ConsAFun;
    protected AFun NilAFun;
    protected AFun AnnoAFun;
    protected AFun IntAFun;
    protected AFun RealAFun;
    protected AFun StrAFun;
    protected AFun VarAFun;
    protected AFun ExplodeAFun;
    protected AFun ConstTypeAFun;
    protected AFun FunTypeAFun;
    protected AFun ExtSDefAFun;
    protected AFun SDefTAFun;
    protected AFun AsAFun;
    protected AFun WldAFun;

    ATermBuilder() {
        if(DebugUtil.shareFactory) {
            factory = factoryShared;
        } else {
            factory = new TermFactory();
        }
        initAFuns();
    }

//    ATerm makePattern(String s) {
//        return factory.parse(s);
//    }

    /**
     * Create the AFuns the interpretor will need to recognize.
     */
    private void initAFuns() {
        OpAFun = factory.makeAFun("Op", 2, false);
        ConsAFun = factory.makeAFun("Cons", 2, false);
        NilAFun = factory.makeAFun("Nil", 0, false);
        AnnoAFun = factory.makeAFun("Anno", 2, false);
        IntAFun = factory.makeAFun("Int", 1, false);
        RealAFun = factory.makeAFun("Real", 1, false);
        StrAFun = factory.makeAFun("Str", 1, false);
        VarAFun = factory.makeAFun("Var", 1, false);
        ExplodeAFun = factory.makeAFun("Explode", 2, false);
        ConstTypeAFun = factory.makeAFun("ConstType", 1, false);//todo
        FunTypeAFun = factory.makeAFun("FunType", 2, false);//todo
        ExtSDefAFun = factory.makeAFun("ExtSDef", 0, false);//todo
        SDefTAFun = factory.makeAFun("SDefT", 4, false);//todo
        AsAFun = factory.makeAFun("As", 2, false);//todo
        WldAFun = factory.makeAFun("Wld", 0, false);//todo
    }

    public ATerm makeList(String s) {
        ATermList t = (ATermList) makeTerm(s);

        return makeList(t);
    }

    public ATerm makeList(ATermList t) {

        ATerm l = factory.makeAppl(getNilAFun());
        AFun f = getConsAFun();

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

    public ATerm makeList(Collection<ATerm> terms) {

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

    public ATerm makeString(String name) {

        AFun f = factory.makeAFun(name, 0, true);

        return factory.makeAppl(f);
    }

    public AFun getOpAFun() {
        return OpAFun;
    }

    public AFun getConsAFun() {
        return ConsAFun;
    }

    public AFun getNilAFun() {
        return NilAFun;
    }

    public AFun getAnnoAFun() {
        return AnnoAFun;
    }

    public AFun getStrAFun() {
        return StrAFun;
    }

    public AFun getVarAFun() {
        return VarAFun;
    }

    public AFun getExplodeAFun() {
        return ExplodeAFun;
    }

    public AFun getRealAFun() {
        return RealAFun;
    }

    public AFun getIntAFun() {
        return IntAFun;
    }

    public AFun getConstTypeAFun() {
        return ConstTypeAFun;
    }

    public AFun getFunTypeAFun() {
        return FunTypeAFun;
    }

    public AFun getExtSDefAFun() {
        return ExtSDefAFun;
    }

    public AFun getSDefTAFun() {
        return SDefTAFun;
    }

    public AFun getAsAFun() {
        return AsAFun;
    }

    public AFun getWldAFun() {
        return WldAFun;
    }

    public void cleanup() {
        factory.cleanup();
    }
}
