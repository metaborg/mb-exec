/*
 * Created on 4. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.attachments.ITermAttachment;
import org.spoofax.terms.attachments.TermAttachmentType;
import org.spoofax.terms.skeleton.SkeletonStrategoAppl;

public class ECJGenericAppl extends SkeletonStrategoAppl {

    private static final long serialVersionUID = 1L;
    
    private IStrategoConstructor ctor;
    private IStrategoTerm[] kids;
    

    ECJGenericAppl(IStrategoConstructor ctor, IStrategoTerm[] kids) {
        super(TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
        this.ctor = ctor;
        this.kids = kids;
    }
    
    @Override
    public IStrategoConstructor getConstructor() {
        return ctor;
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
}
