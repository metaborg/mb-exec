/*
 * Created on 17.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter;

public class SVar {

    private String name;
    
    public SVar(String name) {
        this.name = name;
    }
    
    String getName() { return name; }
}
