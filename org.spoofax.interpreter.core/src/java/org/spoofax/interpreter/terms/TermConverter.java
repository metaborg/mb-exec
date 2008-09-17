package org.spoofax.interpreter.terms;

import static org.spoofax.interpreter.terms.IStrategoTerm.*;

/**
 * Copies terms created by one {@link ITermFactory} to
 * terms created by another (or the same) factory. 
 * 
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class TermConverter {
    
    private final ITermFactory factory;
    
    public TermConverter(ITermFactory factory) {
        this.factory = factory;
    }
    
    public IStrategoTerm[] convertAll(IStrategoTerm[] terms) {
        IStrategoTerm[] results = new IStrategoTerm[terms.length];
        for (int i = 0; i < terms.length; i++) {
            results[i] = convert(terms[i]);
        }
        return results;
    }
    
    public IStrategoTerm convert(IStrategoTerm term) {
        switch (term.getTermType()) {
            case APPL: return convert((IStrategoAppl) term);
            case CTOR: return convert((IStrategoConstructor) term);
            case INT: return convert((IStrategoInt) term);
            case LIST: return convert((IStrategoList) term);
            case REAL: return convert((IStrategoReal) term);
            case STRING: return convert((IStrategoString) term);
            case TUPLE: return convert((IStrategoTuple) term);
            default:
                throw new IllegalStateException("Unkown term type: " + term.getClass().getSimpleName());
        }
    }

    public IStrategoAppl convert(IStrategoAppl term) {
        IStrategoTerm[] subTerms = convertAll(term.getAllSubterms());
        IStrategoConstructor ctor = convert(term.getConstructor());
        return annotate(factory.makeAppl(ctor, subTerms), term);
    }

    public IStrategoConstructor convert(IStrategoConstructor constructor) {
        return factory.makeConstructor(constructor.getName(), constructor.getArity());
    }

    public IStrategoInt convert(IStrategoInt term) {
        return annotate(factory.makeInt(term.intValue()), term);
    }

    public IStrategoList convert(IStrategoList term) {
        IStrategoTerm[] subterms = convertAll(term.getAllSubterms());
        return annotate(factory.makeList(subterms), term);
    }

    public IStrategoReal convert(IStrategoReal term) {
        return annotate(factory.makeReal(term.realValue()), term);
    }

    public IStrategoString convert(IStrategoString term) {
        return annotate(factory.makeString(term.stringValue()), term);
    }

    public IStrategoTuple convert(IStrategoTuple term) {
        IStrategoTerm[] subterms = convertAll(term.getAllSubterms());
        return annotate(factory.makeTuple(subterms), term);
    }
    
    @SuppressWarnings("unchecked")
    protected<T extends IStrategoTerm> T annotate(T term, T input) {
        IStrategoList annotations = input.getAnnotations();
        if (annotations.isEmpty()) {
            return term;
        } else {
            return (T) factory.annotateTerm(term, convert(annotations));
        }
    }

}
