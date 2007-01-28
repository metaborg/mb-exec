/*
 * Created on 4. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;

public class WrappedGenericAppl implements IStrategoAppl {

    private IStrategoConstructor ctor;
    private IStrategoTerm[] children;
    

    WrappedGenericAppl(IStrategoConstructor ctor, IStrategoTerm[] children) {
        this.ctor = ctor;
        this.children = children;
    }
    
    public IStrategoTerm[] getArguments() {
        return children;
    }

    public IStrategoConstructor getConstructor() {
        return ctor;
    }

    public IStrategoTerm getSubterm(int index) {
        return children[index];
    }
    
    public IStrategoTerm[] getAllSubterms() {
        return children;
    }

    public int getSubtermCount() {
        return children.length;
    }

    public int getTermType() {
        return IStrategoTerm.APPL;
    }

    public boolean match(IStrategoTerm second) {
        if(!(second instanceof IStrategoAppl))
            return false;
        
        IStrategoAppl snd = (IStrategoAppl)second;
        
        if(!snd.getConstructor().equals(getConstructor()))
            return false;
        
        for(int i = 0, sz = getConstructor().getArity(); i < sz; i++) {
            if(!getSubterm(i).equals(snd.getSubterm(i)))
                return false;
        }
        
        return true;
    }

    public void prettyPrint(ITermPrinter pp) {
        pp.print(ctor.getName());
        
        int arity = ctor.getArity();
        if(arity > 0) {
            pp.println("(", false);
            pp.indent(ctor.getName().length() + 1);
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
            pp.outdent(ctor.getName().length() + 1);
            
        }
    }

}
