/*
 * Created on 4. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.spoofax.interpreter.terms.BasicStrategoTuple;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedTuple extends BasicStrategoTuple {

    WrappedTuple(IStrategoTerm[] kids) {
        super(kids);
    }

    public boolean match(IStrategoTerm second) {
        return doSlowMatch(second);
    }

}
