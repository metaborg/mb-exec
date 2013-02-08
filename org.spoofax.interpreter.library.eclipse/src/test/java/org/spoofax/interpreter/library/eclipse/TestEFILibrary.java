/*
 * Copyright (c) 2012, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.eclipse;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestEFILibrary {

    @Test
    public void testThatCtreeExists() {
    	assertNotNull(EFILibrary.class.getClassLoader().getResourceAsStream("share/efi-library.ctree"));
    }

    
}
