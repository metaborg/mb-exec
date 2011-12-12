/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.java;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class JFF_read_from_file_channel_0_0 extends AbstractPrimitive {
	
	public JFF_read_from_file_channel_0_0() {
		super("JFI_read_from_file_channel", 0, 0);
	}

	@Override
	public boolean call(IContext context, Strategy[] svars, IStrategoTerm[] tvars) {

		IStrategoTerm current = context.current();
		
		FileChannel c = JFFLibrary.fromTupleWrapped(current, 0, FileChannel.class);
		ByteBuffer buf = JFFLibrary.fromTupleWrapped(current, 1, ByteBuffer.class);
		
		if(c == null || buf == null)
			return false;
		
		try {
			c.read(buf);
		} catch(IOException e) {
			return JFFLibrary.invokeExceptionHandler(context, e);
		}
		
		return true;
	}


}
