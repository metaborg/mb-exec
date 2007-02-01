/*
 * Created on 27. jan.. 2007
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.terms;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.StringBufferInputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.spoofax.NotImplementedException;

public class BasicTermFactory implements ITermFactory {

    public static final IStrategoTerm[] EMPTY = new IStrategoTerm[0];

    public IStrategoTerm parseFromFile(String path) throws IOException {
        return parseFromStream(new FileInputStream(path));
    }

    public IStrategoTerm parseFromStream(InputStream inputStream) throws IOException {
        PushbackInputStream bis = new PushbackInputStream(inputStream);
        
        return parse(bis);
    }

    private IStrategoTerm parse(PushbackInputStream bis) throws IOException {
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
        StringBuffer sb = new StringBuffer();
        int ch = bis.read();
        if(ch == '"')
            return new BasicStrategoString("");
        int prev = ch;
        do {
            if(ch == '\\') {
            } else if(prev != '\\') {
                sb.append((char)ch);
            } else {
                switch(ch) {
                case '"': 
                    sb.append((char)ch);
                    break;
                default:
                    sb.append(new String(new char[] { (char)prev, (char)ch }));
                }
            }
            prev = ch;
            ch = bis.read();
        } while(!(ch == '\"' && prev != '\\'));
        return new BasicStrategoString(sb.toString());
    }

    private IStrategoTerm parseAppl(PushbackInputStream bis) throws IOException {
        System.err.println("appl");
        StringBuilder sb = new StringBuilder();
        int ch;
        
        ch = bis.read();
        do {
            sb.append((char)ch);
            ch = bis.read();
        } while(Character.isLetter(ch));
        
        System.err.println(" - " + sb.toString());

        if(ch == '(') {
            List<IStrategoTerm> l = parseTermSequence(bis, ')');
            IStrategoConstructor c = makeConstructor(sb.toString(), l.size(), false);
            return makeAppl(c, l.toArray(new IStrategoTerm[0]));
        } else {
            bis.unread(ch);
            IStrategoConstructor c = makeConstructor(sb.toString(), 0, false);
            return makeAppl(c, new IStrategoTerm[0]);
        }
    }

    private IStrategoTerm parseTuple(PushbackInputStream bis) throws IOException {
        System.err.println("tuple");
        return makeTuple(parseTermSequence(bis, ')').toArray(new IStrategoTerm[0]));
    }

    private List<IStrategoTerm> parseTermSequence(PushbackInputStream bis, char endChar) throws IOException {
        System.err.println("sequence");
        List<IStrategoTerm> els = new LinkedList<IStrategoTerm>();
        int ch = bis.read();
        if(ch == endChar)
            return els;
        bis.unread(ch);
        do {
            els.add(parse(bis));
            ch = bis.read();
        } while(ch == ',');
        
        if(ch != endChar)
            throw new ParseError("Sequence must end with '" + endChar + "',saw '" + (char)ch + "'");
        
        return els;
    }

    private IStrategoTerm parseList(PushbackInputStream bis) throws IOException {
        System.err.println("list");
        return makeList(parseTermSequence(bis, ']'));
    }

    private IStrategoTerm parseNumber(PushbackInputStream bis) throws IOException {
        System.err.println("number");
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
            return parseFromStream(new StringBufferInputStream(text));
        } catch(IOException e) {
            return null;
        }
    }

    public IStrategoTerm replaceAppl(IStrategoConstructor constructor, IStrategoTerm[] kids,
            IStrategoTerm old) {
        return makeAppl(constructor, kids);
    }

    public void unparseToFile(IStrategoTerm t, OutputStream ous) throws IOException {
        ITermPrinter tp = new InlinePrinter();
        t.prettyPrint(tp);
        ous.write(tp.getString().getBytes());
    }

    public boolean hasConstructor(String s, int i) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoList kids) {
        return new BasicStrategoAppl(ctr, kids.getAllSubterms());
        
    }

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoTerm... terms) {
        return new BasicStrategoAppl(ctr, terms);
    }

    public IStrategoConstructor makeConstructor(String name, int arity, boolean quoted) {
        return new BasicStrategoConstructor(name, arity, quoted);
    }

    public IStrategoInt makeInt(int i) {
        return new BasicStrategoInt(i);
    }

    public IStrategoList makeList(IStrategoTerm... terms) {
        return new BasicStrategoList(terms);
    }

    public IStrategoList makeList(Collection<IStrategoTerm> terms) {
        return new BasicStrategoList(terms.toArray(new IStrategoTerm[0]));
    }

    public IStrategoReal makeReal(double d) {
        return new BasicStrategoReal(d);
    }

    public IStrategoString makeString(String s) {
        return new BasicStrategoString(s);
    }

    public IStrategoTuple makeTuple(IStrategoTerm... terms) {
        return new BasicStrategoTuple(terms);
    }

}
