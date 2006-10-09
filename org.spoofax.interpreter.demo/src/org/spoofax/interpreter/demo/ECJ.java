/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.demo;

import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;

public class ECJ extends AbstractStrategoOperatorRegistry {

    public static final String REGISTRY_NAME = "ECJ";

    ECJ() {
        init();
    }
    
    private void init() {
        new ECJ_parse_java();
    }
}
