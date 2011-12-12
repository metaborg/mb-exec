/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 * 
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.java;

import java.io.IOException;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;

public class JFILibrary extends AbstractStrategoOperatorRegistry {
	
	public static final String REGISTRY_NAME = "JFI";
	
	public JFILibrary() {
		add(new JFI_close_file_channel_0_0());
		add(new JFI_flip_byte_buffer_0_0());
		add(new JFI_make_byte_buffer_0_0());
		add(new JFI_open_file_channel_0_0());
		add(new JFI_read_from_file_channel_0_0());
		add(new JFI_write_to_file_channel_0_0());
	}
	
	@Override
	public String getOperatorRegistryName() {
		return REGISTRY_NAME;
	}

	public static boolean invokeExceptionHandler(IContext ctx, IOException e) {
		throw new NotImplementedException();
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromTupleWrapped(IStrategoTerm current, int index, Class<T> clazz) {

		if(!(current instanceof IStrategoTuple))
			return null;
		
		IStrategoTuple tuple = (IStrategoTuple) current;
		
		if(index >= tuple.getSubtermCount())
			return null;
		
		if(!(tuple.get(index) instanceof GenericWrappedTerm))
			return null;
		
		GenericWrappedTerm wrapper = (GenericWrappedTerm) tuple.get(index);
		
		if(wrapper.getWrappee().getClass().equals(clazz))
			return null;
		
		return (T) wrapper.getWrappee();
		
	}
}
