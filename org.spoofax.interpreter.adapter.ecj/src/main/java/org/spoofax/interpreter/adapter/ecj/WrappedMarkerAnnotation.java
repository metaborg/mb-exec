/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedMarkerAnnotation extends WrappedAnnotation {

    private static final long serialVersionUID = 1L;

    // FIXME improve
    private final MarkerAnnotation wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("MarkerAnnotation", 1);
    
    WrappedMarkerAnnotation(MarkerAnnotation wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrapName(wrappee.getTypeName());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public MarkerAnnotation getWrappee() {
        return wrappee;
    }

    public IExtendedModifier getModifierWrappee() {
    	return wrappee;
    }
}
