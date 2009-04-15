/*
 * Created on 08.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk near strategoxt.org>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ssl;

import java.util.HashMap;
import java.util.Map;

import org.spoofax.interpreter.core.Context;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;
import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.library.ssl.SSL_hashtable_create.Hashtable;
import org.spoofax.interpreter.library.ssl.SSL_indexedSet_create.IndexedSet;

// FIXME: The function registry should probably be shared between instances
public class SSLLibrary extends AbstractStrategoOperatorRegistry {

    public static final String REGISTRY_NAME = "SSL";

    // FIXME: Move these into environment
    
    private IOAgent ioAgent = new IOAgent();
    
    private Map<Integer, IndexedSet> indexedSetMap;
    private int indexedSetCounter;

    //@todo fix
    protected Map<Integer, Hashtable> hashtableMap;
    private int hashtableCounter;
    private int dynruleHashtableRef;
    private int tableTableRef;

    public SSLLibrary() {
        initRegistry();
        init();
    }
    
    public String getOperatorRegistryName() {
        return REGISTRY_NAME;
    }
    
    private void initRegistry() {
        add(new SSL_is_int());
        add(new SSL_is_real());
        add(new SSL_addi());
        add(new SSL_addr());
        add(new SSL_chdir());
        add(new SSL_mkdir());
        add(new SSL_P_tmpdir());
        add(new SSL_mkstemp());
        add(new SSL_S_ISDIR());
        add(new SSL_divi());
        add(new SSL_divr());
        add(new SSL_gti());
        add(new SSL_gtr());
        add(new SSL_muli());
        add(new SSL_mulr());
        add(new SSL_modi());
        add(new SSL_modr());
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
        add(new SSL_real());
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
        add(new SSL_readdir());
        add(new SSL_modification_time());
        add(new SSL_write_term_to_string());
        add(new SSL_stdin_stream());
        add(new SSL_fileno());
        add(new SSL_get_list_length());
        add(new SSL_get_constructor());
        add(new SSL_get_arguments());
        add(new SSL_explode_term());
        add(new SSL_get_appl_arguments_map());
        add(new SSL_STDIN_FILENO());
        add(new SSL_STDOUT_FILENO());
        add(new SSL_stdout_stream());
        add(new SSL_STDERR_FILENO());
        add(new SSL_hashtable_reset());
        add(new SSL_EXDEV());
        add(new SSL_close());
        add(new SSL_perror());
        add(new SSL_fopen());
        add(new SSL_write_term_to_stream_baf());
        add(new SSL_write_term_to_stream_taf());
        add(new SSL_fclose());
        add(new SSL_read_term_from_string());
        add(new SSL_fgetc());
        add(new SSL_table_fold());
        add(new SSL_table_keys_fold());
        add(new SSL_table_values_fold());
        add(new SSL_list_loop());
        add(new SSL_list_fold());
        add(new SSL_read_term_from_stream());
        add(new SSL_remove());
        add(new SSL_exit());
        add(new SSL_copy());
        add(new SSL_checksum());
        add(new SSL_newname());
        add(new SSL_stacktrace_get_all_frame_names());
     }

    protected Map<String, AbstractPrimitive> getRegistry() {
        return registry;
    }

    public AbstractPrimitive lookup(String s) {
        return get(s);
    }

    /**
     * Resets the entire state of the SSL. <br>
     * Should be called once per interpreter.
     * todo: this state should be scoped inside {@link Context}
     */
    public void init() {

        if(hashtableMap != null) {
            for (Hashtable hashtable : hashtableMap.values()) {
                hashtable.clear();
            }
            hashtableMap.clear();
        }
        
        hashtableMap = new HashMap<Integer, Hashtable>();
        hashtableCounter = 0;

        indexedSetMap = new HashMap<Integer, IndexedSet>();
        indexedSetCounter = 0;
        
        // FIXME initialize on-demand
        dynruleHashtableRef = registerHashtable(new Hashtable(128, 75));
        tableTableRef = registerHashtable(new Hashtable(128, 75));
    }
    

    public int registerHashtable(Hashtable hashtable) {
        int ref = hashtableCounter;
        hashtableMap.put(hashtableCounter++, hashtable);
        return ref;
    }

    public boolean removeHashtable(int idx) {
        return hashtableMap.remove(idx) != null;
    }

    public Hashtable getHashtable(int idx) {
        return hashtableMap.get(idx);
    }

    public int getDynamicRuleHashtableRef() {
        return dynruleHashtableRef;
    }

    public int getTableTableRef() {
        return tableTableRef;
    }
    
    public IOAgent getIOAgent() {
        return ioAgent;
    }
    
    public void setIOAgent(IOAgent ioAgent) {
        this.ioAgent = ioAgent;
    }

    public static SSLLibrary instance(IContext env) {
        return (SSLLibrary)env.getOperatorRegistry(REGISTRY_NAME);
    }

    public IndexedSet getIndexedSet(int i) {
        return indexedSetMap.get(i);
    }

    public int registerIndexedSet(IndexedSet set) {
        int ref = indexedSetCounter;
        indexedSetMap.put(indexedSetCounter++, set);
        return ref;
    }

    public boolean removeIndexedSet(int idx) {
        return indexedSetMap.remove(idx) != null;
    }

    
}
