/**
 * 
 */
package ds.manual.interpreter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.StackTracer;
import org.spoofax.interpreter.core.StrategoSignature;
import org.spoofax.interpreter.core.VarScope;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.library.IOperatorRegistry;
import org.spoofax.interpreter.library.ssl.SSLLibrary;
import org.spoofax.interpreter.stratego.Match.Results;
import org.spoofax.interpreter.stratego.OpDecl;
import org.spoofax.interpreter.stratego.SDefT;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

/**
 * @author vladvergu
 *
 */
public class AutoInterpInteropContext implements IContext {
	private ITermFactory termFactory;
	private Map<String, IOperatorRegistry> registries;
	private StackTracer stacktracer;
	private IStrategoTerm ct;

	private StrategoSignature strategoSignature;

	public AutoInterpInteropContext() {
		registries = new HashMap<>();
		stacktracer = new StackTracer();
		addOperatorRegistry(new SSLLibrary());
	}

	@Override
	public void asyncCancel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void asyncCancelReset() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addOperatorRegistry(IOperatorRegistry reg) {
		this.registries.put(reg.getOperatorRegistryName(), reg);
	}

	@Override
	public boolean bindVars(Results arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IStrategoTerm current() {
		return ct;
	}

	@Override
	public ITermFactory getFactory() {
		return termFactory;
	}

	@Override
	public Collection<OpDecl> getOperatorDeclarations() {
		throw new UnsupportedOperationException();
	}

	@Override
	public IOperatorRegistry getOperatorRegistry(String reg) {
		return registries.get(reg);
	}

	@Override
	public StackTracer getStackTracer() {
		return stacktracer;
	}

	@Override
	public StrategoSignature getStrategoSignature() {
		if (strategoSignature == null) {
			strategoSignature = new StrategoSignature(termFactory);
		}
		return strategoSignature;
	}

	@Override
	public Collection<String> getStrategyNames() {
		throw new UnsupportedOperationException();
	}

	@Override
	public VarScope getVarScope() {
		throw new UnsupportedOperationException();
	}

	@Override
	public AbstractPrimitive lookupOperator(String primName) {
		for (IOperatorRegistry reg : registries.values()) {
			AbstractPrimitive t = reg.get(primName);
			if (t != null)
				return t;
		}
		throw new org.metaborg.meta.interpreter.framework.InterpreterException(
				"Operator " + primName + " not found");
	}

	@Override
	public SDefT lookupSVar(String arg0) throws InterpreterException {
		throw new UnsupportedOperationException();
	}

	@Override
	public IStrategoTerm lookupVar(String arg0) throws InterpreterException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void popVarScope() {
		throw new UnsupportedOperationException();

	}

	@Override
	public void restoreVarScope(VarScope arg0) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void setCurrent(IStrategoTerm ct) {
		this.ct = ct;
	}

	@Override
	public void setFactory(ITermFactory tf) {
		this.termFactory = tf;
	}

	@Override
	public void setVarScope(VarScope arg0) {
		throw new UnsupportedOperationException();

	}

}
