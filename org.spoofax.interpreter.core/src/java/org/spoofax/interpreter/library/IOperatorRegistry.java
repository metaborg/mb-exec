/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

public interface IOperatorRegistry {

    public AbstractPrimitive get(String name);
    public String getOperatorRegistryName();

}
