/*
 * Copyright (c) 2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.java;

import static org.spoofax.terms.Term.isTermInt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;
import org.spoofax.interpreter.terms.ITermFactory;

public class JFFLibrary extends AbstractStrategoOperatorRegistry {

	public static final String REGISTRY_NAME = "JFI";

	private final ITermFactory termFactory;
	private final Map<Integer, Object> objectWrappers = new HashMap<Integer, Object>();
	private int objectCounter;
	private IStrategoConstructor objWrapCtor;

	public JFFLibrary(ITermFactory termFactory) {
		this.termFactory = termFactory;

		add(new JFF_close_file_channel_0_0());
		add(new JFF_flip_byte_buffer_0_0());
		add(new JFF_make_byte_buffer_0_0());
		add(new JFF_open_file_channel_0_0());
		add(new JFF_read_from_file_channel_0_0());
		add(new JFF_write_to_file_channel_0_0());
		add(new JFF_find_class());
		add(new JFF_find_primitive_type());
		add(new JFF_get_constructor());
		add(new JFF_get_method());
		add(new JFF_invoke_method());
		add(new JFF_new_instance());
		add(new JFF_chmod_0_0());

		init();
	}

	@Override
	public String getOperatorRegistryName() {
		return REGISTRY_NAME;
	}

	public static boolean invokeExceptionHandler(IContext ctx, Exception e) {
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

	public void init() {

		objectCounter = 0;
		objectWrappers.clear();

		objWrapCtor = termFactory.makeConstructor("ObjWrap", 1);
	}

    public static JFFLibrary instance(IContext env) {
        return (JFFLibrary)env.getOperatorRegistry(REGISTRY_NAME);
    }

	public IStrategoTerm wrapObject(Object obj) {
		int idx = objectCounter++;
		objectWrappers.put(idx, obj);
		return termFactory.makeAppl(objWrapCtor, termFactory.makeInt(idx));
	}

	public Object unwrapObject(IStrategoAppl term) {
		IStrategoTerm t = term.getSubterm(0);
		if(!(isTermInt(t)))
			return null;
		return objectWrappers.get(((IStrategoInt)t).intValue());
	}


	public Object unpackTerm(IStrategoTerm term) {
		switch(term.getTermType()) {
		case IStrategoTerm.INT:
			return ((IStrategoInt)term).intValue();
		case IStrategoTerm.REAL:
			return ((IStrategoReal)term).realValue();
		case IStrategoTerm.STRING:
			return ((IStrategoString)term).stringValue();
		case IStrategoTerm.APPL:
			return unwrapObject((IStrategoAppl)term);
		default:
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T unwrap(IStrategoTerm[] tvars, int index,
			Class<T> clazz) {
		if(index >= tvars.length)
			return null;
		if(!(tvars[index] instanceof GenericWrappedTerm))
			return null;
		GenericWrappedTerm t = (GenericWrappedTerm) tvars[index];
		if(!t.getWrappee().getClass().equals(clazz))
			return null;
		return (T) t.getWrappee();
	}


	public static void attach(Interpreter intp) throws IOException, InterpreterException {
		attach(intp, new JFFLibrary(intp.getFactory()), "/share/jff-library.ctree");
    }
}
