package org.spoofax.interpreter.library.xml;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.ssl.SSLLibrary;

/**
 * This primitive takes a filename (String) as input and tries to parse this
 * file using a SAX XML parser.
 *
 * The strategy fails if the parse fails for any reason. In that case some
 * error messages may be available using XML_get_parse_errors.
 *
 * @see StrategoTermBuilder for the output format
 *
 * @author Tobi Vollebregt
 */
public class XML_parse_file extends XML_parse_base {

    public XML_parse_file(XMLLibrary library) {
        super(library, "XML_parse_file");
    }

    @Override
    protected InputStream makeInputStream(IContext env, String input) {
        InputStream in = null;

        try {
            in = SSLLibrary.instance(env).getIOAgent().openInputStream(input);
        } catch (FileNotFoundException e) {
            library.setLastException(e);
        }

        return in;
    }
}
