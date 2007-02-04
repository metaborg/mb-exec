/*
 * Created on 3. feb.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.spoofax.interpreter.terms.BasicStrategoReal;

public class WrappedReal extends BasicStrategoReal {

    WrappedReal(double value) {
        super(value);
    }
}
