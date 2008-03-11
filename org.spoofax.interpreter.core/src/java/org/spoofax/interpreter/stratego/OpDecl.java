/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.stratego;

public class OpDecl {

    protected String name;
    
    public OpDecl(String name) {
        this.name = name;
    }
    
    public String getName() { return name; }
}
