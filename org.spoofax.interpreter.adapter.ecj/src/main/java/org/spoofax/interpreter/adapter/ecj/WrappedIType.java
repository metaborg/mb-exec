/*
 * Created on 28. feb.. 2007
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedIType extends AbstractWrappedECJNode {

    private static final long serialVersionUID = 1L;

    private final IType wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("IType", 6);

    WrappedIType(IType wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrapDottedName(wrappee.getFullyQualifiedName());
        case 1:
            return ECJFactory.wrap(wrappee.hashCode());
        case 2:
            return ECJFactory.wrap(wrappee.getDeclaringType());
        case 3:
            try {
            	return resolveDottedName(wrappee.getSuperclassName());
            } catch(JavaModelException e) {
                e.printStackTrace();
                return None.INSTANCE;
            }
        case 4:
            try {
                return resolveDottedNames(wrappee.getSuperInterfaceNames());
            } catch(JavaModelException e) {
                e.printStackTrace();
                return None.INSTANCE;
            }
        case 5:
        	try {
        		return ECJFactory.wrap(wrappee.getTypeParameters());
        	} catch(JavaModelException e) {
        		e.printStackTrace();
        		return None.INSTANCE;
        	}
/*        	
        case 6:
        	try {
        		return ECJFactory.wrap(wrappee.getFields());
        	} catch(JavaModelException e) {
        		e.printStackTrace();
        		return None.INSTANCE;
        	}
*/        	
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    
    private IStrategoTerm resolveDottedNames(String[] names) throws JavaModelException {
    	final IStrategoTerm[] resolved = new IStrategoTerm[names.length];
    	for(int i = 0; i < names.length; i++)
    		resolved[i] = resolveDottedName(names[i]);
    	return new ECJGenericList(resolved);
	}

	private IStrategoTerm resolveDottedName(final String name) throws JavaModelException {
    	if(name == null)
    		return ECJFactory.wrap((String)name);
    	String[][] candidates = wrappee.resolveType(name);
    	if(candidates == null)
    		return ECJFactory.wrap((String)null);
    	if(candidates.length == 1 && candidates[0].length == 2)
    		return ECJFactory.wrapDottedName(saneConcat(candidates[0][0], candidates[0][1]));
    	return ECJFactory.wrapAmbName(saneConcat(candidates[0][0], candidates[0][1]));
	}
	
	private static String saneConcat(final String prefix, final String name) {
		if(prefix.length() > 0) {
			return prefix + "." + name;
		} else 
			return name;
	}

	@Override
    public IType getWrappee() {
        return wrappee;
    }

}
