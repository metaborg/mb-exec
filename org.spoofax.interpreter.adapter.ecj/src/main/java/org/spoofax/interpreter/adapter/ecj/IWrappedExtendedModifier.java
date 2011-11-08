/*
 * Created on 25. jan.. 2007
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.IExtendedModifier;

public interface IWrappedExtendedModifier {

    public IExtendedModifier getModifierWrappee();

}
