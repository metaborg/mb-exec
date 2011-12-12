/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.java;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;

public class JFF_open_file_channel_0_0 extends AbstractPrimitive {
	
	public JFF_open_file_channel_0_0() {
		super("JFI_open_file_channel", 0, 0);
	}

	@Override
	public boolean call(IContext context, Strategy[] svars, IStrategoTerm[] tvars) {
		
		IStrategoTerm current = context.current();
		
		if(!(current instanceof IStrategoTuple))
			return false;
		
		IStrategoTuple t = (IStrategoTuple) current;

		if(t.getSubtermCount() < 2)
			return false;
		
		if(!(t.get(0) instanceof IStrategoString))
			return false;
		
		IStrategoString fileName = (IStrategoString) t.get(0);

		if(!(t.get(1) instanceof IStrategoString))
			return false;

		IStrategoString mode = (IStrategoString) t.get(1);

		try {
			RandomAccessFile raf = new RandomAccessFile(new File(fileName.stringValue()), mode.stringValue());
			context.setCurrent(new GenericWrappedTerm("FileChannel", raf.getChannel()));
			return true;
		} catch(IOException e) {
			return JFFLibrary.invokeExceptionHandler(context, e);
		}
	}


}
