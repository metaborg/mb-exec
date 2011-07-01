package org.spoofax.interpreter.library.xml;

import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Library that adds XML parsing capabilities to Spoofax.
 *
 * The XMLLibrary instance is used to cache the parser and parser factory,
 * and to store some state, so the library is not thread safe.
 *
 * @author Tobi Vollebregt
 */
public class XMLLibrary extends AbstractStrategoOperatorRegistry {

	public static final String REGISTRY_NAME = "XML";

	private SAXParserFactory parserFactory;
	private SAXParser parser;
	private boolean allowMixedContent = false;
	private boolean allowCharacterContent = true;
	private Exception lastException;
	private ArrayList<SAXParseException> lastErrors;

	public XMLLibrary() {
		add(new XML_get_parse_errors(this));
		add(new XML_parse_file(this));
		add(new XML_parse_string(this));
		add(new XML_set_feature(this));
	}

	public String getOperatorRegistryName() {
		return REGISTRY_NAME;
	}

	public SAXParserFactory obtainParserFactory() {
		if (parserFactory == null) {
			parserFactory = SAXParserFactory.newInstance();
		}
		return parserFactory;
	}

	public SAXParser obtainParser() {
		if (parser == null) {
			try {
				parser = obtainParserFactory().newSAXParser();
			} catch (ParserConfigurationException e) {
				setLastException(e);
			} catch (SAXException e) {
				setLastException(e);
			}
		}
		return parser;
	}

	public void purgeParser() {
		parser = null;
	}

	public void clearErrors() {
		lastException = null;
		lastErrors = null;
	}

	public boolean getAllowMixedContent() {
		return allowMixedContent;
	}

	public void setAllowMixedContent(boolean allowMixedContent) {
		this.allowMixedContent = allowMixedContent;
	}

	public boolean getAllowCharacterContent() {
		return allowCharacterContent;
	}

	public void setAllowCharacterContent(boolean allowCharacterContent) {
		this.allowCharacterContent = allowCharacterContent;
	}

	public Exception getLastException() {
		return lastException;
	}

	public void setLastException(Exception lastException) {
		this.lastException = lastException;
	}

	public ArrayList<SAXParseException> getLastErrors() {
		return lastErrors;
	}

	public void setLastErrors(ArrayList<SAXParseException> lastErrors) {
		this.lastErrors = lastErrors;
	}

}
