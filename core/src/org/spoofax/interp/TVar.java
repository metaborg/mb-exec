/*
 * Created on 18.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

public class TVar {
    private String name;
    public TVar(String name) {
        this.name = name;
    }
    
    public String getName() { return name; }
}
