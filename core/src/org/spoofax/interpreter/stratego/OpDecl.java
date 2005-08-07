/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter.stratego;

public class OpDecl {

    protected String name;
    
    public OpDecl(String name) {
        this.name = name;
    }
    
    public String getName() { return name; }
}
