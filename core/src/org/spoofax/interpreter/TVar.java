/*
 * Created on 18.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

public class TVar {
    private String name;
    public TVar(String name) {
        this.name = name;
    }
    
    public String getName() { return name; }
}
