/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.PrettyPrinter;

public abstract class WrappedAppl extends WrappedASTNode implements IStrategoAppl {

    private final IStrategoConstructor constructor;
    
    protected WrappedAppl(IStrategoConstructor constructor) {
        this.constructor = constructor;
    }
    
    @Override
    public int getTermType() {
        return IStrategoTerm.APPL;
    }

    public IStrategoConstructor getConstructor() {
        return constructor;
    }

    @Override
    public int getSubtermCount() {
        return constructor.getArity();
    }
 

    public IStrategoTerm[] getArguments() {
        IStrategoTerm[] r = new IStrategoTerm[constructor.getArity()];
        for(int i = 0; i < r.length; i++) {
            r[i] = getSubterm(i);
        }
        return r;
    }
    
    @Override
    public boolean match(IStrategoTerm second) {
        throw new NotImplementedException();
    }

    public void prettyPrint(PrettyPrinter pp) {
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
}
