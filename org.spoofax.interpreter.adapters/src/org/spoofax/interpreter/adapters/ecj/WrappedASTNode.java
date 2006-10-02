package org.spoofax.interpreter.adapters.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class WrappedASTNode implements IStrategoTerm {

    public abstract IStrategoTerm getSubterm(int index);

    public abstract int getSubtermCount();

    public abstract int getTermType();

    public abstract boolean match(IStrategoTerm second);

    public abstract ASTNode getWrappee();
    
}
