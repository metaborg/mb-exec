/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.ThisExpression;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedThisExpression extends WrappedExpression {

    private static final long serialVersionUID = 1L;
    
    private final ThisExpression wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("ThisExpression", 1);
    
    WrappedThisExpression(ThisExpression wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrapName(wrappee.getQualifier());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ThisExpression getWrappee() {
        return wrappee;
    }
}
