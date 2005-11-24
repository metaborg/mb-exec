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
        registry.put("SSL_divi", new SSL_divi());
        registry.put("SSL_gti", new SSL_gti());
        registry.put("SSL_gtr", new SSL_gtr());
        registry.put("SSL_muli", new SSL_muli());
        registry.put("SSL_mod", new SSL_mod());
        registry.put("SSL_int_to_string", new SSL_int_to_string());
        registry.put("SSL_explode_string", new SSL_explode_string());
        registry.put("SSL_string_to_int", new SSL_string_to_int());
        registry.put("SSL_subti", new SSL_subti());
        registry.put("SSL_subtr", new SSL_subtr());
        registry.put("SSL_new", new SSL_new());
        registry.put("SSL_printnl", new SSL_printnl());
        registry.put("SSL_is_string", new SSL_is_string());
        registry.put("SSL_strcat", new SSL_strcat());
        registry.put("SSL_implode_string", new SSL_implode_string());
        registry.put("SSL_strlen", new SSL_strlen());
        registry.put("SSL_concat_strings", new SSL_concat_strings());
        registry.put("SSL_rand", new SSL_rand());
        registry.put("SSL_getenv", new SSL_getenv());
        registry.put("SSL_cos", new SSL_cos());
        registry.put("SSL_sin", new SSL_sin());
        registry.put("SSL_sqrt", new SSL_sin());
        registry.put("SSL_real_to_string", new SSL_real_to_string());
        registry.put("SSL_real_to_string_precision", new SSL_real_to_string_precision());
        registry.put("SSL_string_to_real", new SSL_string_to_real());
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
