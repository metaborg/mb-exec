/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedImportDeclaration extends WrappedASTNode {
    
    private static final long serialVersionUID = 1L;

    private ImportDeclaration wrappee;
    public final static ECJConstructor CTOR = new ECJConstructor("ImportDeclaration", 3); 
    
    public WrappedImportDeclaration(ImportDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
        	return ECJFactory.wrapName(wrappee.getName());
        case 1:
        	return ECJFactory.wrap(wrappee.isStatic() ? 1 : 0);
        case 2: 
        	return ECJFactory.wrap(wrappee.isOnDemand() ? 1 : 0);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ImportDeclaration getWrappee() {
        return wrappee;
    }

}
