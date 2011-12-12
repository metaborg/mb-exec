/*
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.core;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class BindingInfo {
    
    final VarScope scope;
    
    final String name;
    
    IStrategoTerm value;
    
    BindingInfo(VarScope scope, String name) {
        this.scope = scope;
        this.name = name;
    }
    
}
