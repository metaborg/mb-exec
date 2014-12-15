package org.metaborg.meta.stratego.interpreter;

import java.io.IOException;
import java.nio.file.Path;

import org.spoofax.interpreter.library.IOperatorRegistry;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public interface StrategoInterpreter {

	public void reset();

	public void load(Path p) throws IOException;

	public void load(IStrategoAppl t) throws IOException;

	public void addOperatorRegistry(IOperatorRegistry registry);

	public void setCurrentTerm(IStrategoTerm term);

	public IStrategoTerm getCurrentTerm();
	
	public ITermFactory getTermFactory();

	public boolean invoke(String name);

	public boolean evaluate(IStrategoAppl expr);

}