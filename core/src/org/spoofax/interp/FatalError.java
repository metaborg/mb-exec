/*
 * Created on 04.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the IBM Common Public License, v1.0
 */
package org.spoofax.interp;

public class FatalError extends Exception {

    private String error;
    
    public FatalError(String s) {
        this.error = s;
    }
    
    public String toString() {
        return error;
    }
}
