/*
 * Created on 27. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.spoofax.NotImplementedException;

public class BasicTermFactory implements ITermFactory {

    public static final IStrategoTerm[] EMPTY = new IStrategoTerm[0];

    public static final BasicStrategoList EMPTY_LIST = new BasicStrategoList(null, null, null); 

    private static final Map<BasicStrategoConstructor, BasicStrategoConstructor> ctorCache =
        Collections.synchronizedMap(new WeakHashMap<BasicStrategoConstructor,BasicStrategoConstructor>());
    
    private static final Set<String> stringPool =
        Collections.synchronizedSet(new HashSet<String>());
    
    public IStrategoTerm parseFromFile(String path) throws IOException {
        return parseFromStream(new FileInputStream(path));
    }

    public IStrategoTerm parseFromStream(InputStream inputStream) throws IOException {
        PushbackInputStream bis = new PushbackInputStream(inputStream);
        
        return parseFromStream(bis);
    }

    protected IStrategoTerm parseFromStream(PushbackInputStream bis) throws IOException {
        parseSkip(bis);
        final int ch = bis.read();
        switch(ch) {
        case '[': return parseList(bis);
        case '(': return parseTuple(bis);
        case '"': return parseString(bis);
        default:
            bis.unread(ch);
            if(Character.isLetter(ch)) {
                return parseAppl(bis);
            }
            else if(Character.isDigit(ch))
                return parseNumber(bis);
        }
        throw new ParseError("Invalid term : '" + (char)ch + "'");
    }

    private IStrategoTerm parseString(PushbackInputStream bis) throws IOException {
        StringBuilder sb = new StringBuilder();
        int ch = bis.read();
        if(ch == '"')
            return makeString("");
        boolean escaped = false;
        do {
            escaped = false;
            if(ch == '\\') {
                escaped = true;
                ch = bis.read();
            }
            if(escaped) {
                switch(ch) {
                case 'n':
                    sb.append('\n');
                    break;
                case 't':
                    sb.append('\t');
                    break;
                case 'b':
                    sb.append('\b');
                    break;
                case 'f':
                    sb.append('\f');
                    break;
                case 'r':
                    sb.append('\r');
                    break;
                case '\\':
                    sb.append('\\');
                    break;
                case '\'':
                    sb.append('\'');
                    break;
                case '\"':
                    sb.append('\"');
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    throw new NotImplementedException();
                default:
                    sb.append("\\" + (char)ch); 
                }
                ch = bis.read();
            } else if(ch != '\"') {
                if (ch == -1)
                    throw new ParseError("Unterminated string: " + sb);
                sb.append((char)ch);
                ch = bis.read();
            }
        } while(escaped || ch != '\"');
        return makeString(sb.toString());
    }

    private IStrategoTerm parseAppl(PushbackInputStream bis) throws IOException {
        //System.err.println("appl");
        StringBuilder sb = new StringBuilder();
        int ch;
        
        ch = bis.read();
        do {
            sb.append((char)ch);
            ch = bis.read();
        } while(Character.isLetter(ch) || ch == '-');
        
        //System.err.println(" - " + sb.toString());
        
        bis.unread(ch);
        parseSkip(bis);
        ch = bis.read();

        if(ch == '(') {
            List<IStrategoTerm> l = parseTermSequence(bis, ')');
            IStrategoConstructor c = makeConstructor(sb.toString(), l.size());
            return makeAppl(c, l.toArray(EMPTY));
        } else {
            bis.unread(ch);
            IStrategoConstructor c = makeConstructor(sb.toString(), 0);
            return makeAppl(c, new IStrategoTerm[0]);
        }
    }

    private IStrategoTerm parseTuple(PushbackInputStream bis) throws IOException {
        //System.err.println("tuple");
        return makeTuple(parseTermSequence(bis, ')').toArray(EMPTY));
    }

    private List<IStrategoTerm> parseTermSequence(PushbackInputStream bis, char endChar) throws IOException {
        //System.err.println("sequence");
        List<IStrategoTerm> els = new LinkedList<IStrategoTerm>();
        parseSkip(bis);
        int ch = bis.read();
        if(ch == endChar)
            return els;
        bis.unread(ch);
        do {
            els.add(parseFromStream(bis));
            parseSkip(bis);
            ch = bis.read();
        } while(ch == ',');
        
        if (ch != endChar) {
            bis.unread(ch);
            parseSkip(bis);
            ch = bis.read();
        }

        if(ch != endChar)
            throw new ParseError("Sequence must end with '" + endChar + "',saw '" + (char)ch + "'");
        
        return els;
    }

    private IStrategoTerm parseList(PushbackInputStream bis) throws IOException {
        //System.err.println("list");
        return makeList(parseTermSequence(bis, ']'));
    }

    private IStrategoTerm parseNumber(PushbackInputStream bis) throws IOException {
        //System.err.println("number");
        String whole = parseDigitSequence(bis);
        
        int ch = bis.read();
        if(ch == '.') {
            String frac = parseDigitSequence(bis);
            ch = bis.read();
            if(ch == 'e' || ch == 'E') {
                String exp = parseDigitSequence(bis);
                double d = Double.parseDouble(whole + "." + frac + "e" + exp);
                return makeReal(d);
            }
            bis.unread(ch);
            double d = Double.parseDouble(whole + "." + frac);
            return makeReal(d);
        }
        bis.unread(ch);
        return makeInt(Integer.parseInt(whole));
    }

    private String parseDigitSequence(PushbackInputStream bis) throws IOException {
        StringBuilder sb = new StringBuilder();
        int ch = bis.read();
        do {
            sb.append((char)ch);
            ch = bis.read();
        } while(Character.isDigit(ch));
        bis.unread(ch);
        return sb.toString(); 
    }

    public IStrategoTerm parseFromString(String text) {
        try {
            return parseFromStream(new ByteArrayInputStream(text.getBytes()));
        } catch(IOException e) {
            return null;
        }
    }
    
    private void parseSkip(PushbackInputStream input) throws IOException {
        int b = input.read();
        switch (b) {
            case ' ': case '\t': case '\n':
                parseSkip(input);
                return;
            default:
                input.unread(b);
        }
    }

    public IStrategoAppl replaceAppl(IStrategoConstructor constructor, IStrategoTerm[] kids,
            IStrategoAppl old) {
        return makeAppl(constructor, kids, old.getAnnotations());
    }

    public IStrategoTuple replaceTuple(IStrategoTerm[] kids, IStrategoTuple old) {
        IStrategoTuple result = makeTuple(kids);
        IStrategoList annos = old.getAnnotations();
        return annos.isEmpty()
            ? result
            : (IStrategoTuple) annotateTerm(result, annos);
    }
    
    public IStrategoList replaceList(IStrategoTerm[] kids, IStrategoList old) {
        IStrategoList result = makeList(kids);
        IStrategoList annos = old.getAnnotations();
        return annos.isEmpty()
            ? result
            : (IStrategoList) annotateTerm(result, annos);
    }

    public void unparseToFile(IStrategoTerm t, OutputStream ous) throws IOException {
        ITermPrinter tp = new InlinePrinter();
        t.prettyPrint(tp);
        ous.write(tp.getString().getBytes());
    }

    public boolean hasConstructor(String name, int arity) {
        return arity == 0
            ? stringPool.contains(name)
            : ctorCache.get(new BasicStrategoConstructor(name, arity)) != null;
    }

    public final IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoList kids,
            IStrategoList annotations) {
        return makeAppl(ctr, kids.getAllSubterms(), annotations);
    }

    public final IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoList kids) {
        return makeAppl(ctr, kids, null);
    }

    public IStrategoAppl makeAppl(IStrategoConstructor ctr,
            IStrategoTerm[] terms, IStrategoList annotations) {
        return new BasicStrategoAppl(ctr, terms, annotations);
    }

    public final IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoTerm... terms) {
        return makeAppl(ctr, terms, null);
    }

    public IStrategoConstructor makeConstructor(String name, int arity) {
        BasicStrategoConstructor result = new BasicStrategoConstructor(name, arity);
        BasicStrategoConstructor cached = ctorCache.get(result);
        if (cached == null) {
            ctorCache.put(result, result);
            if (arity == 0) stringPool.add(name);
        } else {
            result = cached;
        }
        return result;
    }

    public IStrategoInt makeInt(int i) {
        return new BasicStrategoInt(i, null);
    }

    public IStrategoList makeList(IStrategoTerm... terms) {
        BasicStrategoList result = EMPTY_LIST;
        for (int i = terms.length - 1; i >= 0; i--) {
            result = new BasicStrategoList(terms[i], result, null);
        }
        return result;
    }

    public IStrategoList makeList(Collection<IStrategoTerm> terms) {
        return makeList(terms.toArray(EMPTY));
    }
    
    public IStrategoList makeList(IStrategoTerm head, IStrategoList tail) {
        return new BasicStrategoList(head, tail, null);
    }

    public IStrategoReal makeReal(double d) {
        return new BasicStrategoReal(d, null);
    }

    public IStrategoString makeString(String s) {
        stringPool.add(s);
        return new BasicStrategoString(s, null);
    }

    public IStrategoTuple makeTuple(IStrategoTerm... terms) {
        return new BasicStrategoTuple(terms, null);
    }
    
    public IStrategoTerm annotateTerm(IStrategoTerm term, IStrategoList annotations) {
        if (term instanceof BasicStrategoTerm) {
            if (term.getAnnotations() == annotations) { // cheap check
                return term;
            } else {
                BasicStrategoTerm result = ((BasicStrategoTerm) term).clone();
                result.internalSetAnnotations(annotations);
                return result;
            }
        } else if (term == null) {
            throw new IllegalArgumentException("Term to annotate cannot be null");
        } else {
            // TODO: Use a generic annotation wrapper class?
            throw new NotImplementedException("Annotating term of type " + term.getClass().getName() + " in " + getClass().getSimpleName());
        }
    }

}
