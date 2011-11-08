/*
 * Created on 15 oct. 2008
 *
 * Copyright (c) 2008, Karl Trygve Kalleberg <karltk@strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedIField extends AbstractWrappedECJNode {
    
    private static final long serialVersionUID = 1L;

    private final IField wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("IField", 3);

    WrappedIField(IField wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrapDottedName(wrappee.getElementName());
        case 1:
            return ECJFactory.wrap(wrappee.hashCode());
        case 2:
            try {
            	return ECJFactory.wrap(Signature.getReturnType(wrappee.getTypeSignature()));
            } catch (IllegalArgumentException e) {
            	return None.INSTANCE;
            } catch (JavaModelException e) {
            	return None.INSTANCE;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

	@Override
    public IField getWrappee() {
        return wrappee;
    }

}
