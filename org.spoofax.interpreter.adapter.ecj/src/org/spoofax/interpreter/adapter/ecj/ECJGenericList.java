/*
 * Created on 4. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import java.io.IOException;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.attachments.ITermAttachment;
import org.spoofax.terms.attachments.TermAttachmentType;

public class ECJGenericList implements IStrategoList {

    private static final long serialVersionUID = 1L;
    
    private IStrategoTerm[] kids;

    ECJGenericList(IStrategoTerm[] kids) {
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
    public int getTermType() {
        return IStrategoTerm.LIST;
    }

    @Override
    public int getStorageType() {
        return IStrategoTerm.IMMUTABLE;
    }

    @Override
    public IStrategoList getAnnotations() {
        return TermFactory.EMPTY_LIST;
    }

    @Override
    public boolean match(IStrategoTerm second) {
        throw new NotImplementedException();
    }

    @Override
    public void prettyPrint(ITermPrinter pp) {
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
        return true;
    }

    @Override
    public IStrategoTerm get(int index) {
        return kids[index];
    }

    @Override
    public int size() {
        return kids.length;
    }

    @Override
    public IStrategoTerm head() {
        return kids[0];
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}

