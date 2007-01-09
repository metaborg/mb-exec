/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.spoofax.interpreter.library.SSL_hashtable_create.Hashtable;
import org.spoofax.interpreter.terms.IStrategoInt;

// FIXME: The function registry should probably be shared between instances
public class SSLLibrary extends AbstractStrategoOperatorRegistry {

    public final static int CONST_STDERR = 1;
    public final static int CONST_STDOUT = 2;
    public final static int CONST_STDIN = 3;
    public static final String REGISTRY_NAME = "SSL";

    // FIXME: Move these into environment

    private Map<Integer, InputStream> inputStreamMap;
    private Map<Integer, OutputStream> outputStreamMap;

    public SSLLibrary() {
        initRegistry();
        init();
    }
    
    private void initRegistry() {
        inputStreamMap = new HashMap<Integer, InputStream>();
        inputStreamMap.put(SSLLibrary.CONST_STDIN, System.in);
        
        outputStreamMap = new HashMap<Integer, OutputStream>();
        outputStreamMap.put(SSLLibrary.CONST_STDERR, System.err);
        outputStreamMap.put(SSLLibrary.CONST_STDOUT, System.out);
        
        add(new SSL_is_int());
        add(new SSL_addi());
        add(new SSL_addr());
        add(new SSL_divi());
        add(new SSL_gti());
        add(new SSL_gtr());
        add(new SSL_muli());
        add(new SSL_muli());
        add(new SSL_mod());
        add(new SSL_int_to_string());
        add(new SSL_explode_string());
        add(new SSL_string_to_int());
        add(new SSL_subti());
        add(new SSL_subtr());
        add(new SSL_new());
        add(new SSL_printnl());
        add(new SSL_is_string());
        add(new SSL_strcat());
        add(new SSL_implode_string());
        add(new SSL_strlen());
        add(new SSL_concat_strings());
        add(new SSL_rand());
        add(new SSL_getenv());
        add(new SSL_cos());
        add(new SSL_sin());
        add(new SSL_sin());
        add(new SSL_sqrt());
        add(new SSL_real_to_string());
        add(new SSL_real_to_string_precision());
        add(new SSL_string_to_real());
        add(new SSL_table_hashtable());
        add(new SSL_indexedSet_create());
        add(new SSL_indexedSet_destroy());
        add(new SSL_indexedSet_put());
        add(new SSL_indexedSet_getIndex());
        add(new SSL_indexedSet_elements());
        add(new SSL_indexedSet_remove());
        add(new SSL_indexedSet_reset());
        add(new SSL_hashtable_get());
        add(new SSL_hashtable_create());
        add(new SSL_hashtable_put());
        add(new SSL_stderr_stream());
        add(new SSL_hashtable_destroy());
        add(new SSL_hashtable_remove());
        add(new SSL_hashtable_keys());
        add(new SSL_fputs());
        add(new SSL_fputc());
        add(new SSL_write_term_to_stream_text());
        add(new SSL_access());
        add(new SSL_getcwd());
        
        add(new SSL_dynamic_rules_hashtable());
     }

    protected Map<String, AbstractPrimitive> getRegistry() {
        return registry;
    }

    public AbstractPrimitive lookup(String s) {
        return get(s);
    }

    public InputStream inputStreamFromTerm(IStrategoInt idx) {
        return inputStreamMap.get(idx.getValue());
    }

    public OutputStream outputStreamFromTerm(IStrategoInt idx) {
        return outputStreamMap.get(idx.getValue());
    }

    //@todo fix
    protected Map<Integer, Hashtable> hashtables;
    protected int counter = 0;

    /**
     * Resets the entire state of the SSL. <br>
     * Should be called once per interpreter.
     * todo: this state should be scoped inside {@link org.spoofax.interpreter.Context}
     */
    public void init() {

        if(hashtables != null) {
            for (Hashtable hashtable : hashtables.values()) {
                hashtable.clear();
            }
            hashtables.clear();
        }
        
        hashtables = new HashMap<Integer, Hashtable>();
        counter = 0;

        SSL_indexedSet_create.init();
        SSL_table_hashtable.init();
    }

    public int registerHashtable(Hashtable hashtable) {
        int ref = counter;
        hashtables.put(counter++, hashtable);
        return ref;
    }

    public boolean removeHashtable(int idx) {
        return hashtables.remove(idx) != null;
    }

    public Hashtable getHashtable(int idx) {
        return hashtables.get(idx);
    }
}
