/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedCompilationUnit extends WrappedAppl {

    private final CompilationUnit wrappee;
    private static final IStrategoConstructor CTOR = new ASTCtor("CompilationUnit", 3); 
    
    public WrappedCompilationUnit(CompilationUnit wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return WrappedECJFactory.wrap(wrappee.getPackage());
        case 1:
            return WrappedECJFactory.wrap(wrappee.imports());
        case 2:
            return WrappedECJFactory.wrap(wrappee.types());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public IStrategoTerm[] getArguments() {
        IStrategoTerm[] r = new IStrategoTerm[3];
        r[0] = getSubterm(0);
        r[1] = getSubterm(1);
        r[2] = getSubterm(2);
        return r;
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }

}
