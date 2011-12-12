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
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;

public class JFF_make_byte_buffer_0_0 extends AbstractPrimitive {


	public JFF_make_byte_buffer_0_0() {
		super("JFI_make_byte_buffer", 0, 0);
	}

	@Override
	public boolean call(IContext context, Strategy[] svars, IStrategoTerm[] tvars) {

		IStrategoTerm current = context.current();
		
		if (!(current instanceof IStrategoTuple))
			return false;

		IStrategoTuple t = (IStrategoTuple) current;

		if (t.getSubtermCount() < 2)
			return false;
		if (t.getSubtermCount() < 2)
			return false;

		if (!(t.get(0) instanceof IStrategoInt))
			return false;

		IStrategoInt bufferSize = (IStrategoInt) t.get(0);

		if (!(t.get(1) instanceof IStrategoInt))
			return false;

		IStrategoInt direct = (IStrategoInt) t.get(1);

		ByteBuffer buf = null;
		
		if (direct.intValue() == 1)
			buf = ByteBuffer.allocateDirect(bufferSize.intValue());
		else
			buf = ByteBuffer.allocate(bufferSize.intValue());
		
		context.setCurrent(new GenericWrappedTerm("ByteBuffer", buf));
		return true;
	}

}
