package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.terms.StrategoTerm;
import org.spoofax.terms.StrategoTuple;
import org.spoofax.terms.TermFactory;
import java.io.IOException;
import java.util.Iterator;

public class StrategoImmutableMap extends StrategoTerm implements IStrategoTerm {
    public final Map.Immutable<IStrategoTerm, IStrategoTerm> backingMap;

    public StrategoImmutableMap(Map.Immutable<IStrategoTerm, IStrategoTerm> backingMap) {
        super(TermFactory.EMPTY_LIST);
        this.backingMap = backingMap;
    }

    public StrategoImmutableMap() {
        this(Map.Immutable.of());
    }

    private static class EntryAsPairIterator implements Iterator<IStrategoTerm> {
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

    @SuppressWarnings("NullableProblems") @Override public Iterator<IStrategoTerm> iterator() {
        return new EntryAsPairIterator(backingMap.entryIterator());
    }

    @Override public int getSubtermCount() {
        return 0;
    }

    @Override public IStrategoTerm getSubterm(int index) {
        throw new UnsupportedOperationException();
    }

    @Override public IStrategoTerm[] getAllSubterms() {
        return new IStrategoTerm[0];
    }

    @Override public int getTermType() {
        return IStrategoTerm.BLOB;
    }

    @Override public void prettyPrint(ITermPrinter pp) {
        pp.print(toString());
    }

    @Override protected boolean doSlowMatch(IStrategoTerm second) {
        if(!(second instanceof StrategoImmutableMap)) {
            return false;
        }

        Map.Immutable<IStrategoTerm, IStrategoTerm> secondMap = ((StrategoImmutableMap) second).backingMap;
        return backingMap.equals(secondMap);
    }

    @Override protected int hashFunction() {
        return backingMap.hashCode();
    }

    @Override public String toString(int maxDepth) {
        return backingMap.toString();
    }

    @Override public void writeAsString(Appendable output, int maxDepth) throws IOException {
        output.append(toString());
    }

    public IStrategoTerm withWrapper(ITermFactory factory) {
        return factory.makeAppl("ImmutableMap", this);
    }
}
