/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.asm;

import org.spoofax.interpreter.terms.BasicStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;

public class None extends BasicStrategoTerm {


    private final static IStrategoTerm[] EMPTY = new IStrategoTerm[0];
    private final static IStrategoConstructor CTOR = new ASMConstructor("None", 0); 
    
    final static None INSTANCE = new None();
    
    protected None() {
        //super(CTOR);
    }
    
    @Override
    protected boolean doSlowMatch(IStrategoTerm second) {
    	return second == INSTANCE;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        throw new ArrayIndexOutOfBoundsException();
    }

    public IStrategoTerm[] getArguments() {
        return EMPTY;
    }

    public IStrategoTerm[] getAllSubterms() {
        return EMPTY;
    }

	@Override
	public int hashCode() {
		return CTOR.hashCode();
	}

	public int getSubtermCount() {
		return 0;
	}

	public int getTermType() {
		return IStrategoTerm.APPL;
	}

	public void prettyPrint(ITermPrinter pp) {
		pp.print("None()");		
	}
}
