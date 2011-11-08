/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.adapter.ecj.skeleton.SkeletonStrategoList;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermPrinter;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.attachments.ITermAttachment;
import org.spoofax.terms.attachments.TermAttachmentType;

public class WrappedASTNodeList extends SkeletonStrategoList {

    private static final long serialVersionUID = 1L;
    
    private List<ASTNode> wrappee;
    
    public WrappedASTNodeList(List<ASTNode> wrappee) {
        super(TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
        
        for(Object n : wrappee) {
            if(!(n instanceof ASTNode) && n != null)
                throw new ClassCastException("Cannot convert " + n.getClass() + " to ASTNode");
        }
        
        this.wrappee = (List<ASTNode>)wrappee;
    }

    @Override
    public IStrategoTerm head() {
        return ECJFactory.genericWrap(wrappee.get(0));
    }

    @Override
    public IStrategoList prepend(IStrategoTerm prefix) {
        
        // Trying to build a hybrid list. Do on-the-fly conversion.
        if(prefix instanceof WrappedASTNode) {
            List<ASTNode> r = new ArrayList<ASTNode>();
            ASTNode n = ((WrappedASTNode)prefix).getWrappee();
            r.add(n);
            r.addAll(wrappee);
            return new WrappedASTNodeList(r);
        } else { 
            final int sz = wrappee.size();
            IStrategoTerm[] r = new IStrategoTerm[sz + 1];
            r[0] = prefix;
            for(int i = 0; i < sz; i++) {
                r[i + 1] = ECJFactory.genericWrap(wrappee.get(i));
            }
            return new ECJGenericList(r);
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public IStrategoList tail() {
        List r = new ArrayList();
        for(int i = 1; i < wrappee.size(); i++) {
            r.add(wrappee.get(i));
        }
        return new WrappedASTNodeList(r);
    }

    @Override
    public IStrategoTerm getSubterm(int index) {
        Object o = wrappee.get(index);
        if(o instanceof IStrategoTerm)
            return (IStrategoTerm)o;
        if(o instanceof ASTNode)
            return ECJFactory.genericWrap((ASTNode)o);
        if(o == null)
            return None.INSTANCE;
        
        throw new NotImplementedException("Unsupported type : " + o.getClass());
    }

    @Override
    public IStrategoTerm[] getAllSubterms() {
        IStrategoTerm[] r = new IStrategoTerm[wrappee.size()];
        ASTNode[] s = wrappee.toArray(new ASTNode[0]);
        for(int i = 0; i< r.length; i++) {
            r[i] = ECJFactory.genericWrap(s[i]);
        }
        return r;
    }
    
    @Override
    public int getSubtermCount() {
        return wrappee.size();
    }

    @Override
    public boolean doSlowMatch(IStrategoTerm second, int commonStorageType) {
        if(second instanceof IStrategoList) {
            IStrategoList snd = (IStrategoList) second;
            if(size() != snd.size()) 
                return false;
            for(int i = 0; i < size(); i++) 
                if(!getSubterm(i).match(snd.getSubterm(i)))
                    return false;
            return true;
        } else {
            return super.doSlowMatch(second, commonStorageType);
        }
    }

    public List<ASTNode> getWrappee() {
        return wrappee;
    }

    @Override
    public boolean isEmpty() {
        return wrappee.isEmpty();
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
