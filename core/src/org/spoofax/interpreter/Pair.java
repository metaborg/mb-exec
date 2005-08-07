/*
 * Created on 17.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interpreter;

public class Pair<F, S> {
    public F first;
    public S second;
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
    
    @Override
    public String toString() {
        return "<" + first.toString() + "," + second.toString() + ">"; 
    }
}
