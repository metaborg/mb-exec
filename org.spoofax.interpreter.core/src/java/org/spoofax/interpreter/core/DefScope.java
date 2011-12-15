/*
 * Created on 30.jul.2005
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2
 */
package org.spoofax.interpreter.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spoofax.interpreter.stratego.SDefT;

public class DefScope {

    private DefScope parent;

    private Map<String, SDefT> sdefs;

    public DefScope(DefScope parent) {
        this.parent = parent;

        sdefs = new HashMap<String, SDefT>();
    }

    public SDefT lookupSDefT(String name) {

        SDefT sdef = sdefs.get(name);

        if(sdef == null && parent != null)
            return parent.lookupSDefT(name);

        return sdef;
    }

    public void add(String name, SDefT sdef) {
        sdefs.put(name, sdef);
    }

    public DefScope getParent() {
        return parent;
    }

    public void add(List<SDefT> defs) {
        for(SDefT def : defs)
            add(def.getName(), def);
    }
}
