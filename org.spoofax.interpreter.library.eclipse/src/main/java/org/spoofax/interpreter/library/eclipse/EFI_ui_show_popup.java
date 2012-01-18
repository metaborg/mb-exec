/*
 * Created on 21. feb. 2007
 *
 * Copyright (c) 2007-2012, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.eclipse;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class EFI_ui_show_popup extends AbstractPrimitive {

    public EFI_ui_show_popup() {
        super("EFI_ui_show_popup", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {
    	
        if(!Tools.isTermString(tvars[0]))
            return false;
        if(!Tools.isTermString(tvars[1]))
            return false;
        
        final String title = Tools.asJavaString(tvars[0]); 
        final String body = Tools.asJavaString(tvars[1]);
        
        Shell shell = new Shell();
        MessageDialog.openInformation(
            shell,
            title,
            body);
        
        return true;
    }

    void test() {
    	
    	/*
    	 * ObjWrap(id)
    	 * prims
    	eclipse-find-class = Class.forName
    	eclipse-new-instance = Constructor.newInstance()
    	eclipse-get-constructor = Class.getConstructor(typesig)
    	eclipse-invoke-method = Method.invoke()
    	*/
    }
}
