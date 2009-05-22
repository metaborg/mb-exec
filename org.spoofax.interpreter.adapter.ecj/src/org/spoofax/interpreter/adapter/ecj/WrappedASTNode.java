package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.ASTNode;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class WrappedASTNode extends AbstractECJAppl {

    protected WrappedASTNode(IStrategoConstructor constructor) {
        super(constructor);
    }

    public abstract ASTNode getWrappee();
    
    @Override
    public boolean match(IStrategoTerm second) {
    	if(!(second instanceof WrappedASTNode))
    		return false;
    	return getWrappee().subtreeMatch(ECJFactory.getMatcher(), ((WrappedASTNode)second).getWrappee());
    }
}
