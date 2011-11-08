/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedCompilationUnit extends WrappedASTNode {
    
    private static final long serialVersionUID = 1L;

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
            return ECJFactory.wrap(wrappee.getPackage());
        case 1:
            return ECJFactory.wrap(wrappee.imports());
        case 2:
            return ECJFactory.wrap(wrappee.types());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public CompilationUnit getWrappee() {
        return wrappee;
    }

}
