/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import java.io.IOException;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.terms.attachments.ITermAttachment;
import org.spoofax.terms.attachments.TermAttachmentType;

public class ASTCtor implements IStrategoConstructor {

    private static final long serialVersionUID = 1L;
    
    private final String name;
    private final int arity;

    ASTCtor(String name, int arity) {
        this.name = name;
        this.arity = arity;
    }
    
    public int getStorageType() {
        return IMMUTABLE;
    }
    
    public int getArity() {
        return arity;
    }

    public String getName() {
        return name;
    }

    public IStrategoAppl instantiate(ITermFactory factory, IStrategoTerm... kids) {
        return new ECJGenericAppl(this, kids);
    }

    public IStrategoAppl instantiate(ITermFactory factory, IStrategoList kids) {
        IStrategoTerm[] children = new IStrategoTerm[kids.size()];
        for(int i = 0; i < children.length; i++) 
            children[i] = kids.getSubterm(i); 
        return new ECJGenericAppl(this, children); 
        //throw new NotImplementedException();
    }

    public IStrategoTerm getSubterm(int index) {
        throw new NotImplementedException();
    }

    public int getSubtermCount() {
        throw new NotImplementedException();
    }

    public int getTermType() {
        return IStrategoTerm.CTOR;
    }

    public boolean match(IStrategoTerm second) {
        throw new NotImplementedException();
    }

    public void prettyPrint(ITermPrinter pp) {
        pp.print(name + "##" + arity);
    }

    public IStrategoTerm[] getAllSubterms() {
        throw new NotImplementedException();
    }

    public IStrategoList getAnnotations() {
    	throw new UnsupportedOperationException();
    }

    @Override
    public String toString(int maxDepth) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void writeAsString(Appendable output, int maxDepth)
            throws IOException {
        // TODO Auto-generated method stub
        
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
}
