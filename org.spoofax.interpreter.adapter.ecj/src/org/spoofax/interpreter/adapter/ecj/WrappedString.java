/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.BasicStrategoString;

public class WrappedString extends BasicStrategoString {

    WrappedString(String wrappee) {
        super(wrappee);
    }

    WrappedString(char[] wrappee) {
        super(String.valueOf(wrappee));
    }

}
