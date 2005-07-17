/*
 * Created on 17.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

import java.util.List;

public abstract class Strategy extends NamedDecl {

    protected List<String> termParams;
    protected List<String> stratParams;

    protected List<String> getTermParams() { return termParams; }

    protected List<String> getStrategyParams() { return stratParams; }

}
