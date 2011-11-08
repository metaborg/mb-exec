package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;

public abstract class WrappedASTNode extends AbstractECJAppl {
    
    private static final long serialVersionUID = 1L;

    protected WrappedASTNode(IStrategoConstructor constructor) {
        super(constructor);
    }

    public abstract ASTNode getWrappee();
    
}
