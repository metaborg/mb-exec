/*
 * Created on 25. feb.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.eclipse;

import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;

public class EFILibrary extends AbstractStrategoOperatorRegistry {

    public static final String REGISTRY_NAME = "EFI";

    public EFILibrary() {
        init();
    }
    
    private void init() {
        add(new EFI_ui_show_popup());
    }
    
}
