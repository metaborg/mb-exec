/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedImportDeclaration extends WrappedAppl {

    private ImportDeclaration wrappee;
    public final static ASTCtor CTOR = new ASTCtor("ImportReference", 1); 
    
    public WrappedImportDeclaration(ImportDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return WrappedECJFactory.wrapName(wrappee.getName());
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int getSubtermCount() {
        return 1;
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }

}
