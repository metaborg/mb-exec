/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedImportDeclaration extends WrappedASTNode {

    private ImportDeclaration wrappee;
    public final static ASTCtor CTOR = new ASTCtor("ImportDeclaration", 3); 
    
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
    public int getSubtermCount() {
        return 3;
    }

    @Override
    public ImportDeclaration getWrappee() {
        return wrappee;
    }

}
