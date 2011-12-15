/*
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.eclipse;

import java.io.IOException;

import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;

public class EFILibrary extends AbstractStrategoOperatorRegistry {

    public static final String REGISTRY_NAME = "EFI";

    private EFILibrary() {
        init();
    }

    private void init() {
        add(new EFI_ui_show_popup());
    }

    public String getOperatorRegistryName() {
        return REGISTRY_NAME;
    }

    public static void attach(Interpreter intp) throws IOException, InterpreterException {
        attach(intp, new EFILibrary(), "/shared/efi-library.ctree");
    }
}
