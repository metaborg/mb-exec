/**
 * 
 */
package org.metaborg.meta.stratego.interpreter;

import java.io.IOException;
import java.nio.file.Path;

import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.jsglr.client.ParseException;
import org.spoofax.jsglr.shared.BadTokenException;
import org.spoofax.jsglr.shared.SGLRException;
import org.spoofax.jsglr.shared.TokenExpectedException;
import org.spoofax.terms.TermFactory;

/**
 * @author vladvergu
 *
 */
public class StrategoSugarInterpreter extends StrategoCoreInterpreter {

	private final StrategoFrontend frontend;

	public StrategoSugarInterpreter(Path libraryLocation, Path frontendLocation) {
		this(new TermFactory(), libraryLocation, frontendLocation);
	}

	public StrategoSugarInterpreter(ITermFactory factory, Path libraryLocation,
			Path frontendLocation) {
		super(factory);
		this.frontend = new StrategoFrontend(libraryLocation, frontendLocation);
	}

	@Override
	public void load(Path p) throws IOException {
		if (p.toString().toLowerCase().endsWith(".str")) {
			try {
				super.load(frontend.parseAndCompile(p));
			} catch (SGLRException | InterruptedException e) {
				throw new RuntimeException(e);
			}
		} else {
			super.load(p);
		}
	}

	public boolean evaluate(String code) throws TokenExpectedException,
			BadTokenException, ParseException, SGLRException,
			InterruptedException {
		IStrategoAppl program = frontend.parseAndCompile(code);

		// TODO: if program is an SDefT then we should record the definition of
		// a new Strategy

		return evaluate(program);

	}
}
