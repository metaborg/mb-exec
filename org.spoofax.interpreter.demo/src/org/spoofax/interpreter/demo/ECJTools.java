/*
 * Created on 10. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.demo;

import org.spoofax.interpreter.adapters.ecj.WrappedProject;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJTools {

    public static boolean isProject(IStrategoTerm t) {
        return t instanceof WrappedProject;
    }
}
