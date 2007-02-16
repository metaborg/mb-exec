/*
 * Created on 16. feb.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.library.xlet;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;
import org.spoofax.interpreter.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class SPX_ui_popup extends AbstractPrimitive {

    SPX_ui_popup() {
        super("SPX_ui_popup", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, IConstruct[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
        
        if(!Tools.isTermString(tvars[0]))
            return false;
        if(!Tools.isTermString(tvars[1]))
            return false;
        
        
        
        return true;
    }

}
