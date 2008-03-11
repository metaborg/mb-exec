/*
 * Created on 15. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.core;

public class InterpreterExit extends InterpreterException {
    
    private static final long serialVersionUID = 7198021396619788526L;
    private final int value;
    
    public InterpreterExit(int value) {
        super("Legal exit");
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

}
