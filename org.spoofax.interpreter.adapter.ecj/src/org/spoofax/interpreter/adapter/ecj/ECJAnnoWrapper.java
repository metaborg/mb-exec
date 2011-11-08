package org.spoofax.interpreter.adapter.ecj;

import java.io.IOException;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.terms.attachments.ITermAttachment;
import org.spoofax.terms.attachments.TermAttachmentType;

public class ECJAnnoWrapper implements IStrategoTerm, IStrategoList,
        IStrategoAppl, IStrategoInt, IStrategoReal, IStrategoString,
        IStrategoTuple {

    private static final long serialVersionUID = 1L;

    private final IStrategoTerm wrappee;
    private final IStrategoList annotations;

    public ECJAnnoWrapper(IStrategoTerm wrappee, IStrategoList annotations) {
        this.wrappee = wrappee;
        this.annotations = annotations;
    }

    @Override
    public int getStorageType() {
        return MUTABLE; // does not cache its hashCode() method
    }

    @Override
    public IStrategoTerm[] getAllSubterms() {
        return wrappee.getAllSubterms();
    }

    @Override
    public IStrategoList getAnnotations() {
        return annotations;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        return wrappee.getSubterm(index);
    }

    @Override
    public int getSubtermCount() {
        return wrappee.getSubtermCount();
    }

    @Override
    public int getTermType() {
        return wrappee.getTermType();
    }

    @Override
    public boolean match(IStrategoTerm second) {
        return wrappee.match(second)
                && annotations.match(second.getAnnotations());
    }

    @Override
    public void prettyPrint(ITermPrinter pp) {
        wrappee.prettyPrint(pp);
        pp.print("{");
        annotations.prettyPrint(pp);
        pp.print("}");
    }

    @Override
    public IStrategoTerm get(int index) {
        return ((IStrategoList) wrappee).getSubterm(index);
    }

    @Override
    public IStrategoTerm head() {
        return ((IStrategoList) wrappee).head();
    }

    @Override
    public boolean isEmpty() {
        return ((IStrategoList) wrappee).isEmpty();
    }

    @Override
    public IStrategoList prepend(IStrategoTerm prefix) {
        return ((IStrategoList) wrappee).prepend(prefix);
    }

    @Override
    public int size() {
        return ((IStrategoList) wrappee).size();
    }

    @Override
    public IStrategoList tail() {
        return ((IStrategoList) wrappee).tail();
    }

    @Deprecated
    public IStrategoTerm[] getArguments() {
        return ((IStrategoAppl) wrappee).getAllSubterms();
    }

    @Override
    public IStrategoConstructor getConstructor() {
        return ((IStrategoAppl) wrappee).getConstructor();
    }

    @Override
    public int intValue() {
        return ((IStrategoInt) wrappee).intValue();
    }

    @Override
    public double realValue() {
        return ((IStrategoReal) wrappee).realValue();
    }

    @Override
    public String stringValue() {
        return ((IStrategoString) wrappee).stringValue();
    }

    public IStrategoTerm getWrappee() {
        return wrappee;
    }

    @Override
    public <T extends ITermAttachment> T getAttachment(
            TermAttachmentType<T> type) {
        throw new NotImplementedException();
    }

    @Override
    public void putAttachment(ITermAttachment resourceAttachment) {
        throw new NotImplementedException();
    }

    @Override
    public ITermAttachment removeAttachment(TermAttachmentType<?> attachmentType) {
        throw new NotImplementedException();
    }

    @Override
    public boolean isList() {
        throw new NotImplementedException();
    }

    @Override
    public String getName() {
        throw new NotImplementedException();
    }

    @Override
    public boolean isUniqueValueTerm() {
        throw new NotImplementedException();
    }

    @Override
    public String toString(int maxDepth) {
        throw new NotImplementedException();
    }

    @Override
    public void writeAsString(Appendable output, int maxDepth)
            throws IOException {
        throw new NotImplementedException();
    }

}
