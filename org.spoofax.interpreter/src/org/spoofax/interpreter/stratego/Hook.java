package org.spoofax.interpreter.stratego;

import org.spoofax.interpreter.IConstruct;
import org.spoofax.interpreter.IContext;
import org.spoofax.interpreter.InterpreterException;

public abstract class Hook {
	Hook()
	{
	}
	
	abstract IConstruct onSuccess(IContext env) throws InterpreterException;
	abstract IConstruct onFailure() throws InterpreterException;
}
