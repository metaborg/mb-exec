/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import org.spoofax.interpreter.terms.BasicStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class WrappedString extends BasicStrategoString {

    WrappedString(String wrappee) {
        super(wrappee);
    }

    WrappedString(char[] wrappee) {
        super(String.valueOf(wrappee));
    }

    public boolean match(IStrategoTerm second) {
        return doSlowMatch(second);
    }
}
