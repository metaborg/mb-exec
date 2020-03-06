package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.Map;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.interpreter.util.EntryAsPairIterator;
import org.spoofax.terms.StrategoTerm;
import org.spoofax.terms.TermFactory;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StrategoImmutableMap extends StrategoTerm implements IStrategoTerm {
    public final Map.Immutable<IStrategoTerm, IStrategoTerm> backingMap;

    public StrategoImmutableMap(Map.Immutable<IStrategoTerm, IStrategoTerm> backingMap) {
        super(TermFactory.EMPTY_LIST);
        this.backingMap = backingMap;
    }

    public StrategoImmutableMap() {
        this(Map.Immutable.of());
    }

    @Override
    public int getSubtermCount() {
        return 0;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IStrategoTerm[] getAllSubterms() {
        return new IStrategoTerm[0];
    }

    @Override
    public List<IStrategoTerm> getSubterms() {
        return Collections.emptyList();
    }

    @Override
    public int getTermType() {
        return IStrategoTerm.BLOB;
    }

    @Override
    public void prettyPrint(ITermPrinter pp) {
        pp.print(toString());
    }

    @Override
    protected boolean doSlowMatch(IStrategoTerm second) {
        if(!(second instanceof StrategoImmutableMap)) {
            return false;
        }

        Map.Immutable<IStrategoTerm, IStrategoTerm> secondMap = ((StrategoImmutableMap) second).backingMap;
        return backingMap.equals(secondMap);
    }

    @Override
    protected int hashFunction() {
        return backingMap.hashCode();
    }

    @Override
    public String toString(int maxDepth) {
        return backingMap.toString();
    }

    @Override
    public void writeAsString(Appendable output, int maxDepth) throws IOException {
        output.append(toString());
    }

    public IStrategoTerm withWrapper(ITermFactory factory) {
        return factory.makeAppl("ImmutableMap", this);
    }

    public static IStrategoTerm fromMap(Map.Immutable<IStrategoTerm, IStrategoTerm> map, ITermFactory factory) {
        return new StrategoImmutableMap(map).withWrapper(factory);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<IStrategoTerm> iterator() {
        return new EntryAsPairIterator(backingMap.entryIterator());
    }
}
