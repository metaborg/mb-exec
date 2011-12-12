/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.java;

import java.nio.ByteBuffer;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class JFI_flip_byte_buffer_0_0 extends AbstractPrimitive {

	public JFI_flip_byte_buffer_0_0() {
		super("JFI_flip_byte_buffer", 0, 0);
	}

	@Override
	public boolean call(IContext context, Strategy[] svars, IStrategoTerm[] tvars) {

		IStrategoTerm current = context.current();
		
		if (!(current instanceof GenericWrappedTerm))
			return false;

		GenericWrappedTerm wrapper = (GenericWrappedTerm) current;
		
		if(!(wrapper.getWrappee() instanceof ByteBuffer))
			return true;
		
		ByteBuffer buf = (ByteBuffer) wrapper.getWrappee();
		
		buf.flip();
		
		return false;
	}

}
