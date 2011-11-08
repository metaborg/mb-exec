/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedNormalAnnotation extends WrappedAnnotation {

    private static final long serialVersionUID = 1L;

    private final NormalAnnotation wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("NormalAnnotation", 2);
    
    WrappedNormalAnnotation(NormalAnnotation wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrapName(wrappee.getTypeName());
        case 1:
            return ECJFactory.wrap(wrappee.values());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public NormalAnnotation getWrappee() {
        return wrappee;
    }
    
    public IExtendedModifier getModifierWrappee() {
    	return wrappee;
    }

}
