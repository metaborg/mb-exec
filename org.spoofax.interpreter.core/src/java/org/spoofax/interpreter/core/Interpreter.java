/*
 * Created on 07.aug.2005
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 *
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.core;

import java.io.IOException;
import java.io.InputStream;

import org.spoofax.interpreter.library.IOperatorRegistry;
import org.spoofax.interpreter.stratego.SDefT;
import org.spoofax.interpreter.stratego.StupidFormatter;
import org.spoofax.interpreter.terms.BasicTermFactory;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

public class Interpreter {

    private Context context;
    private StrategoCoreLoader loader;

    public Interpreter() {
        ITermFactory f = new BasicTermFactory();
        doInit(f,f);
    }

    public Interpreter(ITermFactory factory) {
        doInit(factory, factory);
    }

    public Interpreter(ITermFactory termFactory, ITermFactory programFactory) {
        doInit(termFactory, programFactory);
    }

    private void doInit(ITermFactory termFactory, ITermFactory programFactory) {
        
        Context.indentation = 0;
        context = new Context(termFactory, programFactory);
        
        loader = new StrategoCoreLoader(context);
    }
    

    public boolean invoke(String name) throws InterpreterException {
        
        SDefT def = context.lookupSVar(name);

        if (def == null) {
            throw new InterpreterException("Definition '" + name + "' not found");
        }

        return def.getBody().evaluate(context);
    }

    public IContext getContext() {
        return context;
    }

    public void setCurrent(IStrategoTerm inp) {
        context.setCurrent(inp);
    }

    public IStrategoTerm current() {
        return context.current();
    }

    public String prettyPrint() throws InterpreterException {
        StupidFormatter sf = new StupidFormatter();
        SDefT s = context.lookupSVar("main_0_0");
        s.prettyPrint(sf);
        return sf.toString();
    }

    public void shutdown() {
        //todo perf: this takes 2 secs overall
        //if(DebugUtil.cleanupInShutdown) {
        //    context.cleanup();
        //}
    }

    public ITermFactory getFactory() {
        return context.getFactory();
    }

    public void load(InputStream stream) throws IOException, InterpreterException {
        loader.load(stream);
    }
    
    public void load(String file) throws IOException, InterpreterException {
        loader.load(file);
    }

	public void load(IStrategoTerm term) throws InterpreterException {
		loader.load(term);
	}

    public void addOperatorRegistry(String domainName, IOperatorRegistry or) {
        context.addOperatorRegistry(domainName, or);
    }
}
