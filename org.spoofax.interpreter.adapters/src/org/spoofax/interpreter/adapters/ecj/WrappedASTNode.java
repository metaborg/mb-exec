package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;

public abstract class WrappedASTNode extends AbstractECJAppl {

    protected WrappedASTNode(IStrategoConstructor constructor) {
        super(constructor);
    }

    public abstract ASTNode getWrappee();
}
