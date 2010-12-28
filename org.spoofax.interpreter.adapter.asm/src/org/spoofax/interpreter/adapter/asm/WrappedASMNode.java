package org.spoofax.interpreter.adapter.asm;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.terms.io.InlinePrinter;

public abstract class WrappedASMNode implements IStrategoTerm {

	private final IStrategoConstructor constructor;
	
	WrappedASMNode(final IStrategoConstructor constructor) {
		this.constructor = constructor;
	}
	
	@Override
	public String toString() {
		InlinePrinter ip = new InlinePrinter();
		prettyPrint(ip);
		return ip.getString();
	}

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
    
    public IStrategoTerm[] getAllSubterms() {
        final int sz = constructor.getArity();
        IStrategoTerm[] r = new IStrategoTerm[sz];
        for(int i = 0; i < sz; i++) {
            r[i] = getSubterm(i);
        }
        return r;
    }

    public boolean match(IStrategoTerm second) {
    	throw new NotImplementedException();
    }
    
    public int getSubtermCount() {
    	return constructor.getSubtermCount();
    }
    
    public int getTermType() {
    	return constructor.getTermType();
    }
}
