package org.spoofax.interpreter.library.ssl;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.spoofax.interpreter.terms.*;
import org.spoofax.terms.AbstractSimpleTerm;
import org.spoofax.terms.AbstractTermFactory;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.attachments.ITermAttachment;
import org.spoofax.terms.attachments.TermAttachmentType;

public class StrategoHashMap extends LinkedHashMap<IStrategoTerm, IStrategoTerm> implements IStrategoTerm {

    private static final long serialVersionUID = -8193582031891397734L;
    
    // I already burned my base class here, so I use encapsulation for attachments
    private final AbstractSimpleTerm attachmentContainer = new AbstractSimpleTerm() {
        @Override public boolean isList() {
            return false;
        }

        @Override public int getSubtermCount() {
            return 0;
        }

        @Override public ISimpleTerm getSubterm(int i) {
            throw new IndexOutOfBoundsException("" + i);
        }
    };

    public StrategoHashMap() {
        super();
    }
    
    public StrategoHashMap(int initialSize, int maxLoad) {
        super(initialSize, 1.0f * maxLoad / 100);
    }

    @Override public List<IStrategoTerm> getSubterms() {
        return Collections.emptyList();
    }

    @Override public IStrategoList getAnnotations() {
        return TermFactory.EMPTY_LIST;
    }

    @Override public IStrategoTerm getSubterm(int index) {
        throw new UnsupportedOperationException();
    }

    @Override public IStrategoTerm[] getAllSubterms() {
        return TermFactory.EMPTY_TERM_ARRAY;
    }

    @Override public int getSubtermCount() {
        return 0;
    }

    @Deprecated
    @Override public int getTermType() {
        return getType().getValue();
    }

    @Override public TermType getType() {
        return TermType.BLOB;
    }

    @Override public boolean match(IStrategoTerm second) {
        return second == this;
    }
    
    @Override public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override public void prettyPrint(ITermPrinter pp) {
        pp.print(toString());
    }
    
    @Override public String toString() {
        return String.valueOf(hashCode());
    }

    @Override public String toString(int maxDepth) {
        return toString();
    }

    @Override public void writeAsString(Appendable output, int maxDepth)
            throws IOException {
        output.append(toString());
    }

    @Override public<T extends ITermAttachment> T getAttachment(TermAttachmentType<T> type) {
        return attachmentContainer.getAttachment(type);
    }

    @Override public void putAttachment(ITermAttachment attachment) {
        attachmentContainer.putAttachment(attachment);
    }

    @Override public ITermAttachment removeAttachment(TermAttachmentType<?> type) {
        return attachmentContainer.removeAttachment(type);
    }

    @Override public boolean isList() {
        return false;
    }

    @SuppressWarnings("NullableProblems")
    @Override public Iterator<IStrategoTerm> iterator() {
        return this.values().iterator();
    }

}
