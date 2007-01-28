/*
 * Created on 25. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapter.ecj;

import org.eclipse.jdt.core.dom.IExtendedModifier;

public interface IWrappedExtendedModifier {

    public IExtendedModifier getWrappee();

}
