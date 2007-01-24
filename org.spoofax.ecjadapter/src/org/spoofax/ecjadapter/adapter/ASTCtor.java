/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.interpreter.terms.PrettyPrinter;

public class ASTCtor implements IStrategoConstructor {

    private final String name;
    private final int arity;

    ASTCtor(String name, int arity) {
        this.name = name;
        this.arity = arity;
    }
    
    public int getArity() {
        return arity;
    }

    public String getName() {
        return name;
    }

    public IStrategoAppl instantiate(ITermFactory factory, IStrategoTerm... kids) {
        return new WrappedGenericAppl(this, kids);
    }

    public IStrategoAppl instantiate(ITermFactory factory, IStrategoList kids) {
        IStrategoTerm[] children = new IStrategoTerm[kids.size()];
        for(int i = 0; i < children.length; i++) 
            children[i] = kids.get(i); 
        return new WrappedGenericAppl(this, children); 
        //throw new NotImplementedException();
    }

    public IStrategoTerm getSubterm(int index) {
        throw new NotImplementedException();
    }

    public int getSubtermCount() {
        throw new NotImplementedException();
    }

    public int getTermType() {
        return IStrategoTerm.CTOR;
    }

    public boolean match(IStrategoTerm second) {
        throw new NotImplementedException();
    }

    public void prettyPrint(ITermPrinter pp) {
        pp.print(name + "##" + arity);
    }

    public IStrategoTerm[] getAllSubterms() {
        throw new NotImplementedException();
    }

}
