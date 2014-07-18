/**
 * 
 */
package ds.entry.interpreter;

import java.io.IOException;
import java.io.InputStream;

import org.metaborg.meta.interpreter.framework.AValue;
import org.metaborg.meta.interpreter.framework.INodeSource;
import org.metaborg.meta.interpreter.framework.ImploderNodeSource;
import org.metaborg.meta.interpreter.framework.NodeList;
import org.metaborg.meta.interpreter.framework.NodeUtils;
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
import ds.generated.interpreter.Generic_Module;
import ds.generated.interpreter.I_Module;
import ds.generated.interpreter.I_SEnv;
import ds.generated.interpreter.I_STerm;
import ds.generated.interpreter.I_Strategy;
import ds.generated.interpreter.SInit_0;
import ds.generated.interpreter.SVar_1;
import ds.generated.interpreter.S_1;
import ds.generated.interpreter.salloc_Result;
import ds.generated.interpreter.sdefs_Result;
import ds.generated.interpreter.topdefs_1;
import ds.manual.interpreter.AutoInterpInteropContext;

/**
 * @author vladvergu
 *
 */
public class StrategoCoreInterpreter {

	private PersistentMap<Object, Object> sheap;
	private I_SEnv senv;

	private IStrategoTerm currentTerm;
	private TermFactory programTermFactory, termFactory;
	private AutoInterpInteropContext context;

	public StrategoCoreInterpreter() {

	}

	public void reset() {
		sheap = new PersistentTreeMap<>();
		salloc_Result sheap_init_result = new SInit_0(null).exec_salloc(sheap);
		sheap = sheap_init_result._1;
		senv = sheap_init_result.value;
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
		I_Module ctreeNode = new Generic_Module(ctreeSource, ctree);
		ctreeNode = (I_Module) NodeUtils.eagerReplacement(ctreeNode);
		sdefs_Result sdefs_result = new topdefs_1(ctreeSource, ctreeNode).exec_sdefs(PersistentTreeMap.EMPTY, senv, sheap);
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
		PersistentMap<Object, Object> vheap = new PersistentTreeMap<>();

		CallT_3 mainCall = new CallT_3(null, new SVar_1(null, sname), NodeList.NIL(I_Strategy.class),
				NodeList.NIL(I_STerm.class));
		AValue result = mainCall.exec_default(senv, PersistentTreeMap.EMPTY, context, termFactory, currentTerm, sheap,
				false, vheap).value;
		if (result instanceof F_0) {
			throw new StrategoErrorExit("Strategy failed");
		} else {
			setCurrentTerm(((S_1) result).get_1());
		}
	}

}
