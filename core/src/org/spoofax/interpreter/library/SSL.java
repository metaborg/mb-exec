/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.util.HashMap;
import java.util.Map;

public class SSL {

    private static Map<String, Primitive> registry; 
    
    private static void initRegistry() {
        registry = new HashMap<String, Primitive>();
        registry.put("SSL_is_int", new SSL_is_int());
        registry.put("SSL_addi", new SSL_addi());
        registry.put("SSL_addr", new SSL_addr());
    }
    
    protected static Map<String, Primitive> getRegistry() {
        if(registry == null)
            initRegistry();
        return registry;
    }

    public static Primitive lookup(String s) {
        return getRegistry().get(s);
    }
}
