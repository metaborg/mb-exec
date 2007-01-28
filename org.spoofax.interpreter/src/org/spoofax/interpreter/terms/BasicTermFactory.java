/*
 * Created on 27. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import org.spoofax.NotImplementedException;

public class BasicTermFactory implements ITermFactory {

    public IStrategoTerm parseFromFile(String path) throws IOException {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoTerm parseFromStream(InputStream inputStream) throws IOException {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoTerm parseFromString(String text) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoTerm replaceAppl(IStrategoConstructor constructor, IStrategoTerm[] kids,
            IStrategoTerm old) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public void unparseToFile(IStrategoTerm t, OutputStream ous) throws IOException {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public boolean hasConstructor(String s, int i) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoList kids) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoTerm... terms) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoConstructor makeConstructor(String string, int arity, boolean quoted) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoInt makeInt(int i) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoList makeList(IStrategoTerm... terms) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoList makeList(Collection<IStrategoTerm> terms) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoReal makeReal(double d) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoString makeString(String s) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoTuple makeTuple(IStrategoTerm... terms) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

}
