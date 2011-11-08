/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.terms.TermFactory;

public abstract class AbstractECJAppl extends AbstractECJNode implements IStrategoAppl {

    private static final long serialVersionUID = 1L;
    
    private final IStrategoConstructor constructor;
    
    protected AbstractECJAppl(IStrategoConstructor constructor) {
        super(TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
        this.constructor = constructor;
    }

    @Override
    public int getTermType() {
        return IStrategoTerm.APPL;
    }

    @Override
    public IStrategoConstructor getConstructor() {
        return constructor;
    }

    @Override
    public int getSubtermCount() {
        return constructor.getArity();
    }
 
/*
    @Override
    public IStrategoTerm[] getArguments() {
        IStrategoTerm[] r = new IStrategoTerm[constructor.getArity()];
        for(int i = 0; i < r.length; i++) {
            r[i] = getSubterm(i);
        }
        return r;
    }
*/    
    @Override
    public void prettyPrint(ITermPrinter pp) {
        pp.print(constructor.getName());
        
        int arity = constructor.getArity();
        if(arity > 0) {
            pp.println("(", false);
            pp.indent(constructor.getName().length() + 1);
            pp.print("  ");
            pp.nextIndentOff();
            getSubterm(0).prettyPrint(pp);
            pp.println("");
            for(int i = 1; i < arity; i++) {
                pp.print(", ");
                pp.nextIndentOff();
                getSubterm(i).prettyPrint(pp);
                pp.println("");
            }
            pp.print(")");
            pp.outdent(constructor.getName().length() + 1);
            
        }
    }
    
    public abstract IStrategoTerm getSubterm(int index);

    @Override
    public IStrategoTerm[] getAllSubterms() {
        final int sz = getConstructor().getArity();
        IStrategoTerm[] r = new IStrategoTerm[sz];
        for(int i = 0; i < sz; i++) {
            r[i] = getSubterm(i);
        }
        return r;
    }

    @Override
    protected int hashFunction() {
        throw new NotImplementedException();
    }
    
    @Override
    public String getName() {
        return constructor.getName();
    }
    
    @Override
    protected boolean doSlowMatch(IStrategoTerm second, int commonStorageType) {
        throw new NotImplementedException();
    }
}
