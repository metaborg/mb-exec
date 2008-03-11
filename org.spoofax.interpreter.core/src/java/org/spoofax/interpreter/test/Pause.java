/*
 * Created on 1. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.test;

import java.io.IOException;

import junit.framework.TestCase;

public class Pause extends TestCase {
    
    public void testPause() throws IOException {
        System.in.read();
    }
}
