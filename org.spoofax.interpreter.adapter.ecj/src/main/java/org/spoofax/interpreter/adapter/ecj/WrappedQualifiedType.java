/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.QualifiedType;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedQualifiedType extends WrappedType {

    private static final long serialVersionUID = 1L;

    private final QualifiedType wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("QualifiedType", 2);
    
    WrappedQualifiedType(QualifiedType wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getName());
        case 1:
            return ECJFactory.wrapType(wrappee.getQualifier());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public QualifiedType getWrappee() {
        return wrappee;
    }
}
