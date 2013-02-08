/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2006-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library;

public interface IOperatorRegistry {

    public AbstractPrimitive get(String name);
    public String getOperatorRegistryName();

}
