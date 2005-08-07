/*
 * Created on 04.jul.2005
 *
 * Copyright (c) 2004, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

public class FatalError extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -3622131518420023392L;
    private String error;
    
    public FatalError(String s) {
        this.error = s;
    }
    
    public String toString() {
        return error;
    }
}
