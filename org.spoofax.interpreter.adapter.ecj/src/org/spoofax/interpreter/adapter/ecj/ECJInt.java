/*
 * Created on 29. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
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
