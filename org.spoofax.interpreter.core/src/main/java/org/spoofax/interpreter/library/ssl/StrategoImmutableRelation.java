package org.spoofax.interpreter.library.ssl;

import io.usethesource.capsule.BinaryRelation;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.interpreter.util.EntryAsPairIterator;
import org.spoofax.terms.StrategoTerm;
import org.spoofax.terms.TermFactory;
import java.io.IOException;
import java.util.Iterator;

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
        throw new UnsupportedOperationException();
    }

    @Override
    public IStrategoTerm[] getAllSubterms() {
        return new IStrategoTerm[0];
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
}
