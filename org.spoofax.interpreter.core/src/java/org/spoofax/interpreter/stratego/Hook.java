package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.core.IConstruct;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;

public abstract class Hook {
	Hook()
	{
	}
	
	public abstract IConstruct onSuccess(IContext env) throws InterpreterException;
	public abstract IConstruct onFailure(IContext env) throws InterpreterException;
}
