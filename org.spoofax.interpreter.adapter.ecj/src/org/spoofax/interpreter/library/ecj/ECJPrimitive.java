/*
 * Created on 6. mars. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;

public abstract class ECJPrimitive extends AbstractPrimitive {

    protected ECJPrimitive(String name, int svars, int tvars) {
        super(name, svars, tvars);
    }

    protected static ECJLibrary getLibrary(IContext env) {
        return (ECJLibrary) env.getOperatorRegistry(ECJLibrary.REGISTRY_NAME);
    }

}
