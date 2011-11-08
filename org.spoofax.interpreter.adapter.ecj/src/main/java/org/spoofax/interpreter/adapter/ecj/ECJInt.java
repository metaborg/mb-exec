/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.TermFactory;

public class ECJInt extends StrategoInt {

    private static final long serialVersionUID = 1L;

    ECJInt(int value) {
        super(value, TermFactory.EMPTY_LIST, IStrategoTerm.IMMUTABLE);
    }
}
