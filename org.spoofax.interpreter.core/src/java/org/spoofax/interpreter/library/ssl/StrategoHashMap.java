package org.spoofax.interpreter.library.ssl;

import java.util.LinkedHashMap;

import org.spoofax.interpreter.terms.BasicTermFactory;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;

public class StrategoHashMap extends LinkedHashMap<IStrategoTerm, IStrategoTerm> implements IStrategoTerm {

    private static final long serialVersionUID = -8193582031891397734L;

    public StrategoHashMap(int initialSize, int maxLoad) {
        super(initialSize, 1.0f * maxLoad / 100);
    }

    public IStrategoTerm[] getAllSubterms() {
        return BasicTermFactory.EMPTY;
    }

    public IStrategoList getAnnotations() {
        return null;
    }

    public int getStorageType() {
        return MUTABLE;
    }

    public IStrategoTerm getSubterm(int index) {
        throw new UnsupportedOperationException();
    }

    public int getSubtermCount() {
        return 0;
    }

    public int getTermType() {
        return BLOB;
    }

    public boolean match(IStrategoTerm second) {
        return second == this;
    }
    
    public int hashCode() {
        return System.identityHashCode(this);
    }

    public void prettyPrint(ITermPrinter pp) {
        pp.print(toString());
    }
    
    @Override
    public String toString() {
        return String.valueOf(hashCode());
    }

}
