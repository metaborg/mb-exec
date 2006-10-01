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

public class WrappedList implements IStrategoList {

    private List wrappee;
    
    public WrappedList(List wrappee) {
        this.wrappee = wrappee;
    }
    
    public IStrategoList append(IStrategoTerm postfix) {
        ASTNode n = ((WrappedASTNode)postfix).getWrappee();
        
        List r = new ArrayList();
        r.addAll(wrappee);
        r.add(n);
        return new WrappedList(r);
    }

    public IStrategoTerm get(int i) {
        return getSubterm(i);
    }

    public IStrategoTerm head() {
        return WrappedECJFactory.genericWrap((ASTNode)wrappee.get(0));
    }

    public IStrategoList insert(IStrategoTerm prefix) {
        throw new NotImplementedException();
    }

    public int size() {
        return wrappee.size();
    }

    public IStrategoList tail() {
        throw new NotImplementedException();
    }

    public IStrategoTerm getSubterm(int index) {
        return WrappedECJFactory.genericWrap((ASTNode)wrappee.get(index));
    }

    public int getSubtermCount() {
        return wrappee.size();
    }

    public int getTermType() {
        return IStrategoTerm.LIST;
    }

    public boolean match(IStrategoTerm second) {
        throw new NotImplementedException();
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
}
