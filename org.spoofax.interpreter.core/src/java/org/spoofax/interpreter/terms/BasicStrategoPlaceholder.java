package org.spoofax.interpreter.terms;

/**
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class BasicStrategoPlaceholder extends BasicStrategoAppl implements IStrategoPlaceholder {

    public BasicStrategoPlaceholder(IStrategoConstructor ctor, IStrategoTerm template) {
        super(ctor, new IStrategoTerm[] { template });
    }
    
    public IStrategoTerm getTemplate() {
        return getSubterm(0);
    }
    
    @Override
    public int getTermType() {
        return PLACEHOLDER;
    }

    @Override
    public String toString() {
        return "<" + getTemplate() + ">";
    }
    
    @Override
    public void prettyPrint(ITermPrinter pp) {
        pp.print("<");
        getTemplate().prettyPrint(pp);
        pp.print(">");
    }
}
