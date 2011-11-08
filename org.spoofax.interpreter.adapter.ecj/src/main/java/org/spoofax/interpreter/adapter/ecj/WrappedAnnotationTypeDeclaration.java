/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedAnnotationTypeDeclaration extends WrappedAbstractTypeDeclaration {
    
    private static final long serialVersionUID = 1L;

    private final AnnotationTypeDeclaration wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("AnnotationTypeDeclaration", 3);
    
    WrappedAnnotationTypeDeclaration(AnnotationTypeDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.modifiers());
        case 1:
            return ECJFactory.wrap(wrappee.getName());
        case 2:
            return ECJFactory.wrap(wrappee.bodyDeclarations());
        }
        throw new ArrayIndexOutOfBoundsException();
    }
    
    @Override
    public AnnotationTypeDeclaration getWrappee() {
        return wrappee;
    }

}
