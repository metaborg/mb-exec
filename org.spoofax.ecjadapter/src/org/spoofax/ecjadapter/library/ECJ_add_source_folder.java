/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.library;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_add_source_folder extends AbstractPrimitive {

    public ECJ_add_source_folder() {
        super("ECJ_add_source_folder", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, IConstruct[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

}
