/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.ITypeBinding;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedITypeBinding extends AbstractWrappedBinding {

    private static final long serialVersionUID = 1L;

    private final ITypeBinding wrappee;
    private final static IStrategoConstructor CTOR = new ECJConstructor("TypeBinding", 7);
    
    WrappedITypeBinding(ITypeBinding wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    public ITypeBinding getWrappee() {
        return wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            if(wrappee.getPackage() == null)
                return None.INSTANCE;
            else 
                return ECJFactory.wrap(wrappee.getPackage().getNameComponents());
        case 1:
            return ECJFactory.wrap(wrappee.getQualifiedName());
        case 2:
        	final ITypeBinding[] tb = wrappee.getTypeArguments();
        	final String[] ss = new String[tb.length];
        	for(int i = 0; i < tb.length; i++)
        		ss[i] = tb[i].getQualifiedName();
            return ECJFactory.wrap(ss);
        case 3:
            if(wrappee.getSuperclass() == null)
                return None.INSTANCE;
            else 
                return ECJFactory.wrap(wrappee.getSuperclass());
        case 4:
            return ECJFactory.wrap(wrappee.getInterfaces());
        case 5:
            return ECJFactory.wrap(wrappee.getDimensions());
        case 6:
            return ECJFactory.wrap(wrappee.getElementType());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

}
