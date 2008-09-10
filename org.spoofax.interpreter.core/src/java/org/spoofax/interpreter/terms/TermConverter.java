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
        return factory.makeAppl(ctor, subTerms);
    }

    public IStrategoConstructor convert(IStrategoConstructor constructor) {
        return factory.makeConstructor(constructor.getName(), constructor.getArity());
    }

    public IStrategoInt convert(IStrategoInt term) {
        return factory.makeInt(term.intValue());
    }

    public IStrategoList convert(IStrategoList term) {
        IStrategoTerm[] subterms = convertAll(term.getAllSubterms());
        return factory.makeList(subterms);
    }

    public IStrategoReal convert(IStrategoReal term) {
        return factory.makeReal(term.realValue());
    }

    public IStrategoString convert(IStrategoString term) {
        return factory.makeString(term.stringValue());
    }

    public IStrategoTuple convert(IStrategoTuple term) {
        IStrategoTerm[] subterms = convertAll(term.getAllSubterms());
        return factory.makeTuple(subterms);
    }

}
