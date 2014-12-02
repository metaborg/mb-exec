/**
 * 
 */
package org.metaborg.meta.stratego.semantics;

import java.io.IOException;
import java.io.InputStream;

import org.metaborg.meta.interpreter.framework.INodeSource;
import org.metaborg.meta.interpreter.framework.IValue;
import org.metaborg.meta.interpreter.framework.ImploderNodeSource;
import org.metaborg.meta.interpreter.framework.InterpreterExitException;
import org.metaborg.meta.interpreter.framework.NodeList;
import org.spoofax.interpreter.library.IOperatorRegistry;
import org.spoofax.interpreter.library.ssl.SSLLibrary;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.io.binary.TermReader;
import org.strategoxt.lang.StrategoErrorExit;

import com.github.krukow.clj_ds.PersistentMap;
import com.github.krukow.clj_lang.PersistentTreeMap;

import ds.generated.interpreter.CallT_3;
import ds.generated.interpreter.F_0;
import ds.generated.interpreter.Generic_I_Module;
import ds.generated.interpreter.I_Module;
import ds.generated.interpreter.I_STerm;
import ds.generated.interpreter.I_Strategy;
import ds.generated.interpreter.R_allocmodule_SEnv;
import ds.generated.interpreter.SVar_1;
import ds.generated.interpreter.S_1;
import ds.generated.interpreter.allocModule_1;
import ds.manual.interpreter.AutoInterpInteropContext;
import ds.manual.interpreter.SBox;
import ds.manual.interpreter.SState;
import ds.manual.interpreter.VState;

/**
 * @author vladvergu
 *
 */
public class StrategoCoreInterpreter {

	private SState sheap;
	private PersistentMap<String, SBox> senv;

	private IStrategoTerm currentTerm;
	private TermFactory programTermFactory, termFactory;
	private AutoInterpInteropContext context;

	public StrategoCoreInterpreter() {

	}

	public void reset() {
		sheap = new SState();
		senv = PersistentTreeMap.EMPTY;
		currentTerm = null;
		programTermFactory = new TermFactory();
		termFactory = new TermFactory();
		context = new AutoInterpInteropContext();
		context.setFactory(termFactory);
		context.addOperatorRegistry(new SSLLibrary());
	}

	public void setCurrentTerm(IStrategoTerm term) {
		currentTerm = term;
	}

	public IStrategoTerm getCurrentTerm() {
		return currentTerm;
	}

	public TermFactory getProgramTermFactory() {
		return programTermFactory;
	}

	public void loadCTree(InputStream is) throws IOException {
		loadCTree(new TermReader(getProgramTermFactory()).parseFromStream(is));
	}

	public void loadCTree(IStrategoTerm ctree) {
		INodeSource ctreeSource = ctree.getAttachment(ImploderAttachment.TYPE) != null ? new ImploderNodeSource(
				ctree.getAttachment(ImploderAttachment.TYPE)) : null;

		I_Module ctreeNode = (I_Module) new Generic_I_Module(ctreeSource, ctree)
				.specialize(1);

		R_allocmodule_SEnv sdefs_result = new allocModule_1(ctreeSource,
				ctreeNode).exec_allocmodule(PersistentTreeMap.EMPTY, senv,
				sheap);
		sheap = sdefs_result._1;
		senv = sdefs_result.value;
	}

	public void addOperatorRegistry(IOperatorRegistry registry) {
		context.addOperatorRegistry(registry);
	}

	public AutoInterpInteropContext getInteropContext() {
		return context;
	}

	public void invoke(String sname) throws StrategoErrorExit {
		CallT_3 mainCall = new CallT_3(null, new SVar_1(null, sname),
				NodeList.NIL(I_Strategy.class), NodeList.NIL(I_STerm.class));
		IValue result = null;
		try {
			result = mainCall.exec_default(context, senv,
					PersistentTreeMap.EMPTY, currentTerm, termFactory, sheap,
					new VState(), false, context.getStackTracer()).value;
		} catch (InterpreterExitException e) {
			System.out.println("Exit ....");
			context.getStackTracer().printStackTrace(System.err, false);
			return;
		}
		if (result instanceof F_0) {
			throw new StrategoErrorExit("Strategy failed");
		} else {
			setCurrentTerm(((S_1) result).get_1());
		}
	}

}
