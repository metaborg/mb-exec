/*
 * Created on 13.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import aterm.AFun;
import aterm.ATerm;
import aterm.ATermList;
import aterm.pure.PureFactory;

public class BAFReader {

    private static final int BAF_MAGIC = 0xBAF;

    private static final int BAF_VERSION = 0x300;

    private static final int MAX_INLINE_ARITY = 6;

    private static final int HEADER_BITS = 32;

    private BitStream reader;

    private int nrUniqueSymbols = -1;

    private SymEntry[] symbols;

    private PureFactory factory;

    class SymEntry {

        public AFun fun;

        public int arity;

        public int nrTerms;

        public int termWidth;

        public ATerm[] terms;

        public int[] nrTopSyms;

        public int[] symWidth;

        public int[][] topSyms;

    }

    public BAFReader(PureFactory factory, InputStream inputStream) {
        this.factory = factory;
        reader = new BitStream(inputStream);
    }

    ATerm readBAF() throws FatalError, IOException {

        int val = reader.readInt();

        if (val == 0)
            val = reader.readInt();

        if (val != BAF_MAGIC)
            throw new FatalError("Input is not a BAF file");

        val = reader.readInt();

        if (val != BAF_VERSION)
            throw new FatalError("Wrong BAF version, giving up");

        nrUniqueSymbols = reader.readInt();
        int nrUniqueTerms = reader.readInt();

        debug("" + nrUniqueSymbols + " unique symbols");
        debug("" + nrUniqueTerms + " unique terms");

        symbols = new SymEntry[nrUniqueSymbols];

        readAllSymbols();

        int i = reader.readInt();

        return readTerm(symbols[i]);
    }

    private void debug(int val) {
        System.out.printf("%x\n", val);
    }

    private void debug(String s) {
        System.out.println(s);
    }

    private ATerm readTerm(SymEntry e) throws FatalError, IOException {
        int arity = e.arity;
        ATerm[] args = new ATerm[arity];
        ATerm result;

        for (int i = 0; i < arity; i++) {
            int val = reader.readBits(e.symWidth[i]);
            debug(val);
            debug("" + e.topSyms[i].length);
            SymEntry argSym = symbols[e.topSyms[i][val]];

            val = reader.readBits(argSym.termWidth);
            if (argSym.terms[val] == null) {
                argSym.terms[val] = readTerm(argSym);
            }

            if (argSym.terms[val] == null)
                argSym.terms[val] = readTerm(e);

            if (argSym.terms[val] == null)
                throw new FatalError("Cannot be null");
            
            args[i] = argSym.terms[val];
        }

        switch (e.fun.getType()) {
        case ATerm.INT:
            int val = reader.readBits(HEADER_BITS);
            result = factory.makeInt(val);
            break;
        case ATerm.REAL:
            reader.flushBitsFromReader();
            String s = reader.readString();
            result = factory.makeReal(new Double(s));
            break;
        case ATerm.BLOB:
            reader.flushBitsFromReader();
            String t = reader.readString();
            result = factory.makeBlob(t.getBytes());
            break;
        case ATerm.PLACEHOLDER:
            result = factory.makePlaceholder(args[0]);
            break;
        case ATerm.LIST:
            result = ((ATermList) args[1]).append(args[0]);
            break;
        // EMPTY_LIST
        // ANNOTATION
        default:
            debug(e.fun + "/ " + args);
            for (int i = 0; i < args.length; i++)
                debug("" + args[i]);
            result = factory.makeAppl(e.fun, args);
        }

        return result;
    }

    private void readAllSymbols() throws IOException {

        for (int i = 0; i < nrUniqueSymbols; i++) {
            SymEntry e = new SymEntry();
            symbols[i] = e;

            AFun fun = readSymbol();
            e.fun = fun;
            int arity = e.arity = fun.getArity();

            int v = reader.readInt();
            e.nrTerms = v;
            e.termWidth = bitWidth(v);
            // FIXME: original code is inconsistent at this point!
            e.terms = (v == 0) ? null : new ATerm[v];

            if (arity == 0) {
                e.nrTopSyms = null;
                e.symWidth = null;
                e.topSyms = null;
            } else {

                e.nrTopSyms = new int[arity];
                e.symWidth = new int[arity];
                e.topSyms = new int[arity][];
            }
            for (int j = 0; j < arity; j++) {
                v = reader.readInt();
                e.nrTopSyms[j] = v;
                e.symWidth[j] = bitWidth(v);
                e.topSyms[j] = new int[v];

                for (int k = 0; k < e.nrTopSyms[j]; k++) {
                    v = reader.readInt();
                    e.topSyms[j][k] = v;
                }
            }
        }
    }

    private int bitWidth(int v) {
        int nrBits = 0;

        if (v <= 1)
            return 0;

        while (v != 0) {
            v >>= 1;
            nrBits++;
        }

        return nrBits;
    }

    private AFun readSymbol() throws IOException {
        String s = reader.readString();
        int arity = reader.readInt();
        int quoted = reader.readInt();

        debug(s + " / " + arity + " / " + quoted);

        return factory.makeAFun(s, arity, quoted != 0);
    }

    public static void main(String[] args) throws FatalError, IOException {

        PureFactory factory = new PureFactory();
        FileInputStream file = new FileInputStream(args[0]);
        BAFReader reader = new BAFReader(factory, file);

        ATerm t = reader.readBAF();
        System.out.println("---\n" + t);

    }
}
