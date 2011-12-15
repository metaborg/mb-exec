/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.core;

import org.spoofax.interpreter.stratego.StupidFormatter;

public interface IConstruct {

    public IConstruct eval(IContext e) throws InterpreterException;
    public void prettyPrint(StupidFormatter fmt);
}
