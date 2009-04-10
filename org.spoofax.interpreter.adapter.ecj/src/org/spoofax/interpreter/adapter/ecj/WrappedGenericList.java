/*
 * Created on 4. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.BasicStrategoArrayList;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedGenericList extends BasicStrategoArrayList {

    WrappedGenericList(IStrategoTerm[] kids) {
        super(kids);
    }
    
    @Override
    public IStrategoList tail() {
        return new WrappedGenericList(doTail());
    }
    
    @Override
    public IStrategoList prepend(IStrategoTerm prefix) {
        return new WrappedGenericList(doPrepend(prefix)); 
    }
}
