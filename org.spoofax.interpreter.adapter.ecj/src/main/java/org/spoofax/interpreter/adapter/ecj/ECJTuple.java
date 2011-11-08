/*
 * Created on 4. okt.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoTuple;
import org.spoofax.terms.TermFactory;

public class ECJTuple extends StrategoTuple {

    private static final long serialVersionUID = 1L;

    ECJTuple(IStrategoTerm[] kids) {
        super(kids, TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
    }

}
