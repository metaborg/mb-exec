package org.spoofax.interpreter.library.ssl;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.interpreter.util.EntryAsPairIterator;
import org.spoofax.terms.StrategoTerm;
import org.spoofax.terms.TermFactory;

import io.usethesource.capsule.BinaryRelation;

import static org.spoofax.terms.AbstractTermFactory.EMPTY_TERM_ARRAY;

public class StrategoImmutableRelation extends StrategoTerm implements IStrategoTerm {
    public final BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> backingRelation;

    public StrategoImmutableRelation(BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> backingRelation) {
        super(TermFactory.EMPTY_LIST);
        this.backingRelation = backingRelation;
    }

    public StrategoImmutableRelation() {
        this(BinaryRelation.Immutable.of());
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<IStrategoTerm> iterator() {
        return new EntryAsPairIterator(backingRelation.entryIterator());
    }

    @Override
    public int getSubtermCount() {
        return 0;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public IStrategoTerm[] getAllSubterms() {
        return EMPTY_TERM_ARRAY;
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
        if(!(second instanceof StrategoImmutableRelation)) {
            return false;
        }

        BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> secondRelation =
            ((StrategoImmutableRelation) second).backingRelation;
        return backingRelation.equals(secondRelation);
    }

    @Override
    protected int hashFunction() {
        return backingRelation.hashCode();
    }

    @Override
    public String toString(int maxDepth) {
        return backingRelation.toString();
    }

    @Override
    public void writeAsString(Appendable output, int maxDepth) throws IOException {
        output.append(toString());
    }

    public IStrategoTerm withWrapper(ITermFactory factory) {
        return factory.makeAppl("ImmutableRelation", this);
    }

    public static IStrategoTerm fromRelation(BinaryRelation.Immutable<IStrategoTerm, IStrategoTerm> relation,
        ITermFactory factory) {
        return new StrategoImmutableRelation(relation).withWrapper(factory);
    }

    public static StrategoImmutableRelation union(StrategoImmutableRelation one, StrategoImmutableRelation other) {
        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> result = one.backingRelation.asTransient();
        for(Map.Entry<IStrategoTerm, IStrategoTerm> e : other.backingRelation.entrySet()) {
            result.__insert(e.getKey(), e.getValue());
        }
        return new StrategoImmutableRelation(result.freeze());
    }

    public static StrategoImmutableRelation intersect(StrategoImmutableRelation one, StrategoImmutableRelation other) {
        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> result = BinaryRelation.Transient.of();
        for(Map.Entry<IStrategoTerm, IStrategoTerm> e : one.backingRelation.entrySet()) {
            if(other.backingRelation.containsEntry(e.getKey(), e.getValue())) {
                result.__insert(e.getKey(), e.getValue());
            }
        }
        return new StrategoImmutableRelation(result.freeze());
    }

    public static StrategoImmutableRelation subtract(StrategoImmutableRelation left, StrategoImmutableRelation right) {
        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> result = left.backingRelation.asTransient();
        for(Map.Entry<IStrategoTerm, IStrategoTerm> e : right.backingRelation.entrySet()) {
            result.__remove(e.getKey(), e.getValue());
        }

        return new StrategoImmutableRelation(result.freeze());
    }

    public static StrategoImmutableRelation compose(StrategoImmutableRelation left, StrategoImmutableRelation right) {
        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> result = BinaryRelation.Transient.of();
        for(Map.Entry<IStrategoTerm, IStrategoTerm> e : left.backingRelation.entrySet()) {
            for(IStrategoTerm value : right.backingRelation.get(e.getValue())) {
                result.__insert(e.getKey(), value);
            }
        }

        return new StrategoImmutableRelation(result.freeze());
    }

    public static StrategoImmutableRelation transitiveClosure(StrategoImmutableRelation map) {
        HashSet<Map.Entry<IStrategoTerm, IStrategoTerm>> frontier1 = new HashSet<>(map.backingRelation.entrySet());
        HashSet<Map.Entry<IStrategoTerm, IStrategoTerm>> frontier2 = new HashSet<>();
        final BinaryRelation.Transient<IStrategoTerm, IStrategoTerm> result = map.backingRelation.asTransient();
        boolean done = false;
        while(!done) {
            done = true;
            for(Map.Entry<IStrategoTerm, IStrategoTerm> e : frontier1) {
                for(IStrategoTerm value : map.backingRelation.get(e.getValue())) {
                    if(!result.containsEntry(e.getKey(), value)) {
                        frontier2.add(new AbstractMap.SimpleImmutableEntry<>(e.getKey(), value));
                        result.__insert(e.getKey(), value);
                        done = false;
                    }
                }
            }
            HashSet<Map.Entry<IStrategoTerm, IStrategoTerm>> tmp = frontier1; // swap & clear for better memory perf
            frontier1 = frontier2;
            frontier2 = tmp;
            frontier2.clear();
        }
        return new StrategoImmutableRelation(result.freeze());
    }
}
