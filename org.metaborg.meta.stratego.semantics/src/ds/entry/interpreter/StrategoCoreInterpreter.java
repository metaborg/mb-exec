/**
 * 
 */
package ds.entry.interpreter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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
import ds.generated.interpreter.I_STerm;
import ds.generated.interpreter.I_Strategy;
import ds.generated.interpreter.SVar_1;
import ds.generated.interpreter.S_1;
import ds.generated.interpreter.topdefs_1;
import ds.manual.interpreter.AutoInterpInteropContext;

/**
 * @author vladvergu
 *
 */
public class StrategoCoreInterpreter {

	private PersistentMap<Object, Object> sdefs;
	private IStrategoTerm currentTerm;
	private TermFactory programTermFactory, termFactory;
	private AutoInterpInteropContext context;
	public StrategoCoreInterpreter() {

	}

	public void reset() {
		sdefs = new PersistentTreeMap<>();
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
		sdefs = new topdefs_1(ctreeSource, ctreeNode).exec_sdefs(sdefs).value;
	}

	public void addOperatorRegistry(IOperatorRegistry registry) {
		context.addOperatorRegistry(registry);
	}

	public AutoInterpInteropContext getInteropContext() {
		return context;
	}

	public void invoke(String sname) throws StrategoErrorExit {
		PersistentMap<Object, Object> env = new PersistentTreeMap<>();

		CallT_3 mainCall = new CallT_3(null, new SVar_1(null, sname), NodeList.NIL(I_Strategy.class),
				NodeList.NIL(I_STerm.class));

		AValue result = mainCall.exec_default(context, sdefs, termFactory, currentTerm, false, env).value;
		if (result instanceof F_0) {
			throw new StrategoErrorExit("Strategy failed");
		} else {
			setCurrentTerm(((S_1) result).get_1());
		}
	}

}
