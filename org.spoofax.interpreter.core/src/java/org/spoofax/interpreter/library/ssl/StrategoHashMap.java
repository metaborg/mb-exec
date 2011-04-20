package org.spoofax.interpreter.library.ssl;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.spoofax.interpreter.terms.ISimpleTerm;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.terms.AbstractSimpleTerm;
import org.spoofax.terms.AbstractTermFactory;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.attachments.ITermAttachment;
import org.spoofax.terms.attachments.TermAttachmentType;

public class StrategoHashMap extends LinkedHashMap<IStrategoTerm, IStrategoTerm> implements IStrategoTerm {

    private static final long serialVersionUID = -8193582031891397734L;
    
    // I already burned my base class here, so I use encapsulation for attachments
    private final AbstractSimpleTerm attachmentContainer = new AbstractSimpleTerm() {
        public boolean isList() {
            return false;
        }
        
        public int getSubtermCount() {
            return 0;
        }
        
        public ISimpleTerm getSubterm(int i) {
            throw new IndexOutOfBoundsException("" + i);
        }
    };

    public StrategoHashMap(int initialSize, int maxLoad) {
        super(initialSize, 1.0f * maxLoad / 100);
    }

    public IStrategoTerm[] getAllSubterms() {
        return AbstractTermFactory.EMPTY;
    }

    public IStrategoList getAnnotations() {
        return TermFactory.EMPTY_LIST;
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
    
    @Override
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
    
    public String toString(int maxDepth) {
        return toString();
    }
    
    public void writeAsString(Appendable output, int maxDepth)
            throws IOException {
        output.append(toString());
    }
    
    public<T extends ITermAttachment> T getAttachment(TermAttachmentType<T> type) {
        return attachmentContainer.getAttachment(type);
    }
    
    public void putAttachment(ITermAttachment attachment) {
        attachmentContainer.putAttachment(attachment);
    }
    
    public ITermAttachment removeAttachment(TermAttachmentType<?> type) {
        return attachmentContainer.removeAttachment(type);
    }
    
    public boolean isList() {
        return false;
    }

}
