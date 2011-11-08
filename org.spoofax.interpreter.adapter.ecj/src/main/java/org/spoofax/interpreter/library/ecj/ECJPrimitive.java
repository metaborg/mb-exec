/*
 * Created on 6. mars. 2007
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
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
