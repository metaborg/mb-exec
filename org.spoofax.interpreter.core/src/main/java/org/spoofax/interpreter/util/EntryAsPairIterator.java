package org.spoofax.interpreter.util;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoTuple;
import org.spoofax.terms.TermFactory;
import java.util.Iterator;

public class EntryAsPairIterator implements Iterator<IStrategoTerm> {
    private final Iterator<java.util.Map.Entry<IStrategoTerm, IStrategoTerm>> entryIterator;

    public EntryAsPairIterator(Iterator<java.util.Map.Entry<IStrategoTerm, IStrategoTerm>> entryIterator) {
        this.entryIterator = entryIterator;
    }

    @Override public boolean hasNext() {
        return entryIterator.hasNext();
    }

    @Override public IStrategoTerm next() {
        java.util.Map.Entry<IStrategoTerm, IStrategoTerm> e = entryIterator.next();
        if(e == null) {
            return null;
        }
        return new StrategoTuple(new IStrategoTerm[] {e.getKey(), e.getValue()}, TermFactory.EMPTY_LIST);
    }
}
