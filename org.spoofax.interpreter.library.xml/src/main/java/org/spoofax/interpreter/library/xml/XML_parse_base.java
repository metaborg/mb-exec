/*
 * Copyright (c) 2011-2012, Tobi Vollebregt
 *
 * Licensed under the GNU Lesser General Public License, v2.1
 */
package org.spoofax.interpreter.library.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.xml.sax.SAXException;

/**
 * Base class for XML_parse_string and XML_parse_file
 *
 * @author Tobi Vollebregt
 */
public abstract class XML_parse_base extends XMLAbstractPrimitive {

    public XML_parse_base(XMLLibrary library, String name) {
        super(library, name, 0, 1);
    }

    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars) {
        if (tvars[0].getTermType() != IStrategoTerm.STRING) {
            return false;
        }

        library.clearErrors();

        final String input = ((IStrategoString) tvars[0]).stringValue();
        final InputStream in = makeInputStream(env, input);

        if (in == null) {
            return false;
        }

        final IStrategoTerm result = parse(env, in);

        if (result == null) {
            return false;
        }

        env.setCurrent(result);
        return true;
    }

    protected abstract InputStream makeInputStream(IContext env, String input);

    protected IStrategoTerm parse(IContext env, InputStream in) {
        final SAXParser parser = library.obtainParser();

        if (parser == null) {
            return null;
        }

        final StrategoTermBuilder builder = new StrategoTermBuilder(env.getFactory(), library);
        IStrategoTerm result = null;

        try {
        	parser.getXMLReader().setErrorHandler(builder);
            parser.parse(in, builder);
            result = builder.getRootElement();
        } catch (SAXException e) {
        	library.setLastException(e);
        } catch (IOException e) {
        	library.setLastException(e);
        }

      	library.setLastErrors(builder.getErrors());
        return result;
    }

}
