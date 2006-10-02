/*
 * Created on 2. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedWhileStatement extends WrappedAppl {

    private final WhileStatement wrappee;
    private final static IStrategoConstructor CTOR = new ASTCtor("WhileStatement", 2);
    
    WrappedWhileStatement(WhileStatement wrappee) {
        super(CTOR);
        this.wrappee = wrappee;
    }
    
    @Override
    public IStrategoTerm getSubterm(int index) {
        switch(index) {
        case 0: 
            return WrappedECJFactory.wrapExpression(wrappee.getExpression());
        case 1:
            return WrappedECJFactory.wrapStatement(wrappee.getBody());
        }
        
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public ASTNode getWrappee() {
        return wrappee;
    }

    public IStrategoTerm[] getArguments() {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

}
