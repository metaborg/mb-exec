/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedPackageDeclaration extends WrappedAppl {

    private static final IStrategoConstructor CTOR = new ASTCtor("PackageDeclaration", 3); 
    private final PackageDeclaration wrappee;
    
    protected WrappedPackageDeclaration(PackageDeclaration wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0:
            return ECJFactory.wrap(wrappee.getJavadoc());
        case 1:
            return ECJFactory.wrap(wrappee.annotations());
        case 2:
            return ECJFactory.wrapName(wrappee.getName());
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }
}
