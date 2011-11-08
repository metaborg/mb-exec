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
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.attachments.ITermAttachment;
import org.spoofax.terms.attachments.TermAttachmentType;

public class ECJGenericAppl implements IStrategoAppl {

    private static final long serialVersionUID = 1L;
    
    private IStrategoConstructor ctor;
    private IStrategoTerm[] children;
    

    ECJGenericAppl(IStrategoConstructor ctor, IStrategoTerm[] children) {
        this.ctor = ctor;
        this.children = children;
    }
    
    @Override
    public int getStorageType() {
        return MUTABLE;
    }
    
    @Override
    public IStrategoList getAnnotations() {
    	return TermFactory.EMPTY_LIST;
    }

    @Override
    public IStrategoConstructor getConstructor() {
        return ctor;
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        return children[index];
    }
    
    @Override
    public IStrategoTerm[] getAllSubterms() {
        return children;
    }

    @Override
    public int getSubtermCount() {
        return children.length;
    }

    @Override
    public int getTermType() {
        return IStrategoTerm.APPL;
    }

    @Override
    public boolean match(IStrategoTerm second) {
        if(!(second instanceof IStrategoAppl))
            return false;
        
        IStrategoAppl snd = (IStrategoAppl)second;
        
        if(!snd.getConstructor().equals(getConstructor()))
            return false;
        
        for(int i = 0, sz = getConstructor().getArity(); i < sz; i++) {
            if(!getSubterm(i).equals(snd.getSubterm(i)))
                return false;
        }
        
        return true;
    }

    @Override
    public void prettyPrint(ITermPrinter pp) {
        pp.print(ctor.getName());
        
        int arity = ctor.getArity();
        if(arity > 0) {
            pp.println("(", false);
            pp.indent(ctor.getName().length() + 1);
            pp.print("  ");
            pp.nextIndentOff();
            getSubterm(0).prettyPrint(pp);
            pp.println("");
            for(int i = 1; i < arity; i++) {
                pp.print(", ");
                pp.nextIndentOff();
                getSubterm(i).prettyPrint(pp);
                pp.println("");
            }
            pp.print(")");
            pp.outdent(ctor.getName().length() + 1);
            
        }
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
        throw new NotImplementedException();
    }

    @Override
    public String getName() {
        throw new NotImplementedException();
    }

}
