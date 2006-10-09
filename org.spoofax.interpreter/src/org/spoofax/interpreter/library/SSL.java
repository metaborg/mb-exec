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
public class SSL extends AbstractStrategoOperatorRegistry {

    public final static int CONST_STDERR = 1;
    public final static int CONST_STDOUT = 2;
    public final static int CONST_STDIN = 3;
    public static final String REGISTRY_NAME = "SSL";

    // FIXME: Move these into environment

    private Map<Integer, InputStream> inputStreamMap;
    private Map<Integer, OutputStream> outputStreamMap;

    public SSL() {
        initRegistry();
        init();
    }
    
    private void initRegistry() {
        inputStreamMap = new HashMap<Integer, InputStream>();
        inputStreamMap.put(SSL.CONST_STDIN, System.in);
        
        outputStreamMap = new HashMap<Integer, OutputStream>();
        outputStreamMap.put(SSL.CONST_STDERR, new BufferedOutputStream(System.err));
        outputStreamMap.put(SSL.CONST_STDOUT, new BufferedOutputStream(System.out));
        
        add("SSL_is_int", new SSL_is_int());
        add("SSL_addi", new SSL_addi());
        add("SSL_addr", new SSL_addr());
        add("SSL_divi", new SSL_divi());
        add("SSL_gti", new SSL_gti());
        add("SSL_gtr", new SSL_gtr());
        add("SSL_muli", new SSL_muli());
        add("SSL_mulr", new SSL_muli());
        add("SSL_mod", new SSL_mod());
        add("SSL_int_to_string", new SSL_int_to_string());
        add("SSL_explode_string", new SSL_explode_string());
        add("SSL_string_to_int", new SSL_string_to_int());
        add("SSL_subti", new SSL_subti());
        add("SSL_subtr", new SSL_subtr());
        add("SSL_new", new SSL_new());
        add("SSL_printnl", new SSL_printnl());
        add("SSL_is_string", new SSL_is_string());
        add("SSL_strcat", new SSL_strcat());
        add("SSL_implode_string", new SSL_implode_string());
        add("SSL_strlen", new SSL_strlen());
        add("SSL_concat_strings", new SSL_concat_strings());
        add("SSL_rand", new SSL_rand());
        add("SSL_getenv", new SSL_getenv());
        add("SSL_cos", new SSL_cos());
        add("SSL_sin", new SSL_sin());
        add("SSL_sqrt", new SSL_sin());
        add("SSL_real_to_string", new SSL_real_to_string());
        add("SSL_real_to_string_precision", new SSL_real_to_string_precision());
        add("SSL_string_to_real", new SSL_string_to_real());
        add("SSL_table_hashtable", new SSL_table_hashtable());
        add("SSL_indexedSet_create", new SSL_indexedSet_create());
        add("SSL_indexedSet_destroy", new SSL_indexedSet_destroy());
        add("SSL_indexedSet_put", new SSL_indexedSet_put());
        add("SSL_indexedSet_getIndex", new SSL_indexedSet_getIndex());
        add("SSL_indexedSet_elements", new SSL_indexedSet_elements());
        add("SSL_indexedSet_remove", new SSL_indexedSet_remove());
        add("SSL_indexedSet_reset", new SSL_indexedSet_reset());
        add("SSL_hashtable_get", new SSL_hashtable_get());
        add("SSL_hashtable_create", new SSL_hashtable_create());
        add("SSL_hashtable_put", new SSL_hashtable_put());
        add("SSL_stderr_stream", new SSL_stderr_stream());
        add("SSL_hashtable_destroy", new SSL_hashtable_destroy());
        add("SSL_hashtable_remove", new SSL_hashtable_remove());
        add("SSL_hashtable_keys", new SSL_hashtable_keys());
        add("SSL_fputs", new SSL_fputs());
        add("SSL_fputc", new SSL_fputc());
        add("SSL_write_term_to_stream_text", new SSL_write_term_to_stream_text());
        add("SSL_access", new SSL_access());
        add("SSL_getcwd", new SSL_getcwd());
     }

    protected Map<String, Primitive> getRegistry() {
        return registry;
    }

    public Primitive lookup(String s) {
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
