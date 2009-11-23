/*
 * Created on 30.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
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
