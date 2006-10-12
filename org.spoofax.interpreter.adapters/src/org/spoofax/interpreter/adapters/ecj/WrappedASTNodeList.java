/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.PrettyPrinter;

public class WrappedASTNodeList implements IStrategoList {

    private List wrappee;
    
    public WrappedASTNodeList(List wrappee) {
        this.wrappee = wrappee;
    }
    
    public IStrategoTerm get(int i) {
        return getSubterm(i);
    }

    public IStrategoTerm head() {
        return ECJFactory.genericWrap((ASTNode)wrappee.get(0));
    }

    @SuppressWarnings("unchecked")
    public IStrategoList prepend(IStrategoTerm prefix) {
        
        // Trying to build a hybrid list. Do on-the-fly conversion. 
        if(!(prefix instanceof WrappedASTNode)) {
            final int sz = wrappee.size();
            IStrategoTerm[] r = new IStrategoTerm[sz + 1];
            r[0] = prefix;
            for(int i = 0; i < sz; i++) {
                r[i + 1] = (IStrategoTerm) wrappee.get(i);
            }
            return new WrappedGenericList(r);
        }
        
        List r = new ArrayList();
        r.add(prefix);
        r.addAll(wrappee);
        return new WrappedASTNodeList(r);
    }

    public int size() {
        return wrappee.size();
    }

    @SuppressWarnings("unchecked")
    public IStrategoList tail() {
        List r = new ArrayList();
        for(int i = 1; i < wrappee.size(); i++) {
            r.add(wrappee.get(i));
        }
        return new WrappedASTNodeList(r);
    }

    public IStrategoTerm getSubterm(int index) {
        Object o = wrappee.get(index);
        if(o instanceof IStrategoTerm)
            return (IStrategoTerm)o;
        if(o instanceof ASTNode)
            return ECJFactory.genericWrap((ASTNode)o);
        
        throw new NotImplementedException("Unsupported type : " + o.getClass());
    }

    public int getSubtermCount() {
        return wrappee.size();
    }

    public int getTermType() {
        return IStrategoTerm.LIST;
    }

    public boolean match(IStrategoTerm second) {
        if(second instanceof IStrategoList) {
            IStrategoList snd = (IStrategoList) second;
            if(size() != snd.size()) 
                return false;
            for(int i = 0; i < size(); i++) 
                if(!get(i).match(snd.get(i)))
                    return false;
            return true;
        } 
        return false;
    }

    public void prettyPrint(PrettyPrinter pp) {
        int sz = size();
        if(sz > 0) {
            pp.println("[");
            pp.indent(2);
            get(0).prettyPrint(pp);
            for(int i = 1; i < sz; i++) {
                pp.print(", ");
                pp.nextIndentOff();
                get(i).prettyPrint(pp);
                pp.println("");
            }
            pp.println("");
            pp.print("]");
            pp.outdent(2);

        } else {
            pp.print("[]");
        }
    }

    public List getWrappee() {
        return wrappee;
    }
}
