/*
 * Copyright (c) 2011-2012, Tobi Vollebregt
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.xml;

import java.util.ArrayList;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.xml.sax.SAXParseException;

/**
 * Return the errors that occurred during the last invocation of XML_parse_file,
 * XML_parse_string or XML_set_feature. Errors are returned as a list of strings.
 *
 * This primitive fails if there weren't any errors, i.e. if a list is returned,
 * it has at least one element.
 *
 * @author Tobi Vollebregt
 */
public class XML_get_parse_errors extends XMLAbstractPrimitive {

	private static final String NAME = "XML_get_parse_errors";

	public XML_get_parse_errors(XMLLibrary library) {
		super(library, NAME, 0, 0);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
			throws InterpreterException {

		final Exception fatalError = library.getLastException();
		final ArrayList<SAXParseException> nonFatalErrors = library.getLastErrors();

		// Any errors at all?
		if (fatalError == null
				&& (nonFatalErrors == null || nonFatalErrors.isEmpty())) {
			// No
			return false;
		} else {
			// Yes: convert them to Stratego terms

			final ITermFactory factory = env.getFactory();
			final ArrayList<IStrategoTerm> errors = new ArrayList<IStrategoTerm>();

			if (nonFatalErrors != null) {
				for (SAXParseException pe : nonFatalErrors) {
					errors.add(factory.makeString(toString(pe)));
				}
			}

			if (fatalError != null) {
				if (fatalError instanceof SAXParseException) {
					errors.add(factory.makeString(toString((SAXParseException) fatalError)));
				} else {
					errors.add(factory.makeString(fatalError.getMessage()));
				}
			}

			env.setCurrent(factory.makeList(errors));
			return true;
		}
	}

	private static String toString(SAXParseException pe) {
		return pe.getMessage() + " [line " + pe.getLineNumber() + ", column " + pe.getColumnNumber() + "]";
	}

}
