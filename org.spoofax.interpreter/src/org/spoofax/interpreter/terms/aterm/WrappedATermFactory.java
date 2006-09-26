/*
 * Created on 29.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms.aterm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTuple;
import org.spoofax.interpreter.terms.ITermFactory;

import aterm.AFun;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermInt;
import aterm.ATermList;
import aterm.ATermReal;

public class WrappedATermFactory implements ITermFactory {

    private TrackingATermFactory realFactory;
    
    public WrappedATermFactory() {
        this.realFactory = new TrackingATermFactory();
    }
    
    public boolean hasConstructor(String name, int arity) {
        return realFactory.hasAFun(name, arity);
    }

    public IStrategoTerm parseFromFile(String path) throws IOException {
        ATerm t = realFactory.readFromFile(path);
        return wrapTerm(t);
    }

    public IStrategoTerm parseFromStream(InputStream inputStream) throws IOException {
        ATerm t = realFactory.readFromFile(inputStream);
        return wrapTerm(t);
    }

    public IStrategoTerm parseFromString(String text) {
        ATerm t = realFactory.parse(text);
        return wrapTerm(t);
    }

    static WrappedATerm wrapTerm(ATerm t) {
        switch(t.getType()) {
        case ATerm.AFUN:
            return new WrappedAFun((AFun)t);
        case ATerm.REAL:
            return new WrappedATermReal((ATermReal)t);
        case ATerm.INT:
            return new WrappedATermInt((ATermInt)t);
        case ATerm.LIST:
            return new WrappedATermList((ATermList)t);
        case ATerm.APPL:
            ATermAppl a = (ATermAppl)t;
            if(a.isQuoted() && a.getArity() == 0)
                return new WrappedATermString(a);
            else if(a.getName().equals("")) // FIXME use AFun
                return new WrappedATermTuple(a);
            else  
                return new WrappedATermAppl(a);
        }
        throw new WrapperException();
    }

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoList kids) {
        return ctr.instantiate(kids);
    }

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoTerm... kids) {
        return ctr.instantiate(kids);
    }

    public IStrategoConstructor makeConstructor(String name, int arity, boolean isQuoted) {
        return new WrappedAFun(realFactory.makeAFun(name, arity, isQuoted));
    }

    public IStrategoInt makeInt(int i) {
        return new WrappedATermInt(realFactory.makeInt(i));
    }

    public IStrategoList makeList(IStrategoTerm... terms) {
        ATermList l = realFactory.makeList();
        
        for(IStrategoTerm t : terms) {
            if(t instanceof WrappedATerm) {
                l = l.append(((WrappedATerm)t).getATerm());
            } else {
                throw new WrapperException();
            }
        }
        
        return new WrappedATermList(l);
    }

    public IStrategoList makeList(Collection<IStrategoTerm> terms) {
        ATermList l = realFactory.makeList();
        
        for(IStrategoTerm t : terms) {
            if(t instanceof WrappedATerm) {
                l = l.append(((WrappedATerm)t).getATerm());
            } else {
                throw new WrapperException();
            }
        }
        
        return new WrappedATermList(l);
    }

    public IStrategoReal makeReal(double d) {
        return new WrappedATermReal(realFactory.makeReal(d));
    }

    public IStrategoString makeString(String s) {
        return new WrappedATermString(realFactory.makeString(s));
    }

    public IStrategoTuple makeTuple(IStrategoTerm... terms) {
        ATerm[] args = new ATerm[terms.length];
        int pos = 0;
        for(IStrategoTerm t : terms) {
            args[pos++] = ((WrappedATerm)t).getATerm();
        }
        AFun afun = realFactory.makeAFun("", terms.length, false);
        return new WrappedATermTuple(realFactory.makeAppl(afun, args));
    }

    public void unparseToFile(IStrategoTerm t, OutputStream ous) throws IOException {
        throw new NotImplementedException();
    }
}
