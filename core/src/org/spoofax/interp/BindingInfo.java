/*
 * Created on 30.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

import java.util.ArrayList;
import java.util.List;

public class BindingInfo {
    List<Pair<VarScope, String>> bindings;
    
    public BindingInfo() {
        bindings = new ArrayList<Pair<VarScope,String>>();
    }
    
    void add(VarScope scope, String varName) {
        bindings.add(new Pair<VarScope, String>(scope, varName));
    }

    public List<Pair<VarScope, String>> getBindings() {
        return bindings;
    }
    
}
