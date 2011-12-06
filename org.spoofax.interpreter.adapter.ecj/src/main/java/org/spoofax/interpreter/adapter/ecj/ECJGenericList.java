/*
 * Created on 4. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.attachments.ITermAttachment;
import org.spoofax.terms.attachments.TermAttachmentType;
import org.spoofax.terms.skeleton.SkeletonStrategoList;

public class ECJGenericList extends SkeletonStrategoList {

    private static final long serialVersionUID = 1L;
    
    private IStrategoTerm[] kids;

    ECJGenericList(IStrategoTerm[] kids) {
        super(TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
        this.kids = kids;
    }
    
    @Override
    public IStrategoList tail() {
        return new ECJGenericList(doTail());
    }
    
    @Override
    public IStrategoList prepend(IStrategoTerm prefix) {
        throw new NotImplementedException(); 
    }
    
    private IStrategoTerm[] doTail() {
        IStrategoTerm[] tail = new IStrategoTerm[kids.length - 1];
        System.arraycopy(kids, 1, tail, 0, tail.length);
        return tail;
    }

    @Override
    public int getSubtermCount() {
        return kids.length;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        return kids[index];
    }

    @Override
    public IStrategoTerm[] getAllSubterms() {
        return kids;
    }

    @Override
    public String toString(int maxDepth) {
        throw new NotImplementedException();
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
    public IStrategoTerm head() {
        return kids[0];
    }

    @Override
    public boolean isEmpty() {
        return kids.length == 0;
    }
}

