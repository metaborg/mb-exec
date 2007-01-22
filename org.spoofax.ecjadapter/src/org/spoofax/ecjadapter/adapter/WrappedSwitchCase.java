/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.eclipse.jdt.core.dom.SwitchCase;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedSwitchCase extends WrappedStatement {

    // FIXME default
    
    private final SwitchCase wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("SwitchCase", 1);
    
    WrappedSwitchCase(SwitchCase wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        if(index == 0)
            return ECJFactory.wrapExpression(wrappee.getExpression());
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public SwitchCase getWrappee() {
        return wrappee;
    }
}
