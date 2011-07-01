package org.spoofax.interpreter.library.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.spoofax.interpreter.core.IContext;

/**
 * This primitive takes a String as input and tries to parse this String
 * using a SAX XML parser.
 *
 * The strategy fails if the parse fails for any reason. In that case some
 * error messages may be available using XML_get_parse_errors.
 *
 * @see StrategoTermBuilder for the output format
 * 
 * @author Tobi Vollebregt
 */
public class XML_parse_string extends XML_parse_base {

    public XML_parse_string(XMLLibrary library) {
        super(library, "XML_parse_string");
    }

    @Override
    protected InputStream makeInputStream(IContext env, String input) {
        return new ByteArrayInputStream(input.getBytes());
    }
}
