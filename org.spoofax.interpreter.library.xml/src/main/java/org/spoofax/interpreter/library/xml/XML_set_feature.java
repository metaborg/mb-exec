/*
 * Copyright (c) 2011-2012, Tobi Vollebregt
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.xml;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.xml.sax.SAXException;

/**
 * Sets a feature to be enabled/disabled for any subsequent parses.
 *
 * A list of standard features can be found here:
 * http://www.saxproject.org/apidoc/org/xml/sax/package-summary.html#package_description
 *
 * Additionally the following two Spoofax-specific features are recognized:
 *
 *  - http://spoofax.org/sax/features/mixed-content, default: false
 *    If set, include text nodes between elements in the result.
 *
 *  - http://spoofax.org/sax/features/character-data, default: true
 *    If set, include character data as a text node in elements that do not
 *    have any child elements. Has no effect if mixed-content is already set.
 *
 * @author Tobi Vollebregt
 */
public class XML_set_feature extends XMLAbstractPrimitive {

	private static final String PREFIX = "http://spoofax.org/sax/features/";
	private static final String MIXED_CONTENT = PREFIX + "mixed-content";
	private static final String CHARACTER_DATA = PREFIX + "character-data";

	public XML_set_feature(XMLLibrary library) {
		super(library, "XML_set_feature", 0, 2);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) {

		if (tvars[0].getTermType() != IStrategoTerm.STRING
				|| tvars[1].getTermType() != IStrategoTerm.INT) {
			return false;
		}

		library.clearErrors();

		final String feature = ((IStrategoString) tvars[0]).stringValue();
		final boolean enable = ((IStrategoInt) tvars[1]).intValue() != 0;

		if (feature.startsWith(PREFIX)) {
			if (feature.equals(MIXED_CONTENT)) {
				library.setAllowMixedContent(enable);
				return true;
			}
			else if (feature.equals(CHARACTER_DATA)) {
				library.setAllowCharacterContent(enable);
				return true;
			}
			else {
				library.setLastException(new Exception("Feature \"" + feature + "\" not recognized"));
				return false;
			}
		}
		else {
			final SAXParserFactory parserFactory = library.obtainParserFactory();

			if (parserFactory == null) {
				return false;
			}

			try {
				parserFactory.setFeature(feature, enable);
			    library.purgeParser();
				return true;
			} catch (SAXException e) {
				library.setLastException(e);
			} catch (ParserConfigurationException e) {
				library.setLastException(e);
			} 

			return false;
		}
	}

}
