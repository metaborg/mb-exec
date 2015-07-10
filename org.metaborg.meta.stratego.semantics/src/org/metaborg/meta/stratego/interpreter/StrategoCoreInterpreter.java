/**
 * 
 */
package org.metaborg.meta.stratego.interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import org.metaborg.meta.interpreter.framework.NodeSource;
import org.spoofax.interpreter.core.StackTracer;
import org.spoofax.interpreter.library.IOperatorRegistry;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.TermFactory;
import org.spoofax.terms.io.binary.TermReader;

import com.github.krukow.clj_ds.PersistentMap;
import com.github.krukow.clj_lang.PersistentTreeMap;

import ds.generated.interpreter.A_Module;
import ds.generated.interpreter.A_Strategy;
import ds.generated.interpreter.A_Value;
import ds.generated.interpreter.CallT_3;
import ds.generated.interpreter.F_0;
import ds.generated.interpreter.Generic_A_Module;
import ds.generated.interpreter.Generic_A_StrategyDef;
import ds.generated.interpreter.L_A_STerm;
import ds.generated.interpreter.L_A_Strategy;
import ds.generated.interpreter.R_allocmodule_Unit;
import ds.generated.interpreter.R_default_Value;
import ds.generated.interpreter.SDefT_4;
import ds.generated.interpreter.SVar_1;
import ds.generated.interpreter.S_1;
import ds.manual.interpreter.AutoInterpInteropContext;
import ds.manual.interpreter.SBox;
import ds.manual.interpreter.SState;
import ds.manual.interpreter.VState;

/**
 * @author vladvergu
 *
 */
public class StrategoCoreInterpreter implements StrategoInterpreter {

	private ITermFactory termFactory;

	private SState strategyHeap;
	private PersistentMap<String, SBox> strategyEnv;

	private AutoInterpInteropContext interopContext;
	private IStrategoTerm currentTerm;

	public StrategoCoreInterpreter() {
		this(new TermFactory());
	}

	public StrategoCoreInterpreter(ITermFactory factory) {
		termFactory = factory;
		reset();
	}

	@Override
	public void reset() {
		interopContext = new AutoInterpInteropContext();
		interopContext.setFactory(termFactory);
		strategyHeap = new SState();
		strategyEnv = PersistentTreeMap.EMPTY;
		currentTerm = null;
	}

	@Override
	public void load(Path p) throws IOException {
		load((IStrategoAppl) new TermReader(termFactory)
				.parseFromStream(new FileInputStream(p.toFile())));
	}

	@Override
	public void load(IStrategoAppl ctree) throws IOException {
		A_Module ctreeNode = (A_Module) new Generic_A_Module(
				NodeSource.fromStrategoTerm(ctree), ctree).specialize(1);
		R_allocmodule_Unit sdefs_result = ctreeNode
				.exec_allocmodule(new VState(), getTermFactory(),
						getCurrentTerm(), new SState(), interopContext,
						PersistentTreeMap.EMPTY,
						interopContext.getStackTracer(), false,
						strategyEnv);
		strategyEnv = sdefs_result._3;
	}

	@Override
	public void addOperatorRegistry(IOperatorRegistry registry) {
		interopContext.addOperatorRegistry(registry);
	}

	@Override
	public void setCurrentTerm(IStrategoTerm term) {
		currentTerm = term;
	}

	@Override
	public IStrategoTerm getCurrentTerm() {
		return currentTerm;
	}

	@Override
	public ITermFactory getTermFactory() {
		return termFactory;
	}

	@Override
	public boolean invoke(String sname) {
		CallT_3 call = new CallT_3(null, new SVar_1(null, sname),
				new L_A_Strategy(null), new L_A_STerm(null));
		return evaluate(call);
	}

	@Override
	public boolean evaluate(IStrategoAppl sExpr) {
		final ITermFactory factory = getTermFactory();
		final IStrategoConstructor sdefT = factory.makeConstructor("SDefT", 4);
		final IStrategoConstructor exprConstr = sExpr.getConstructor();

		// wrap in SDefT if not already an SDefT
		if (!exprConstr.getName().equals(sdefT.getName())
				|| exprConstr.getArity() != sdefT.getArity()) {
			sExpr = factory.makeAppl(sdefT, factory.makeString("dummy_0_0"),
					factory.makeList(), factory.makeList(), sExpr);
		}

		SDefT_4 sdef = (SDefT_4) new Generic_A_StrategyDef(null, sExpr)
				.specialize(2);

		return evaluate(sdef.get_4());
	}

	private boolean evaluate(A_Strategy sexpr) {
		try {
			R_default_Value result = sexpr.exec_default(new VState(),
					getTermFactory(), getCurrentTerm(), strategyHeap,
					strategyEnv, interopContext, PersistentTreeMap.EMPTY,

					interopContext.getStackTracer(), false);
			A_Value value = result.value;
			StackTracer stacktracer = interopContext.getStackTracer();
			if (value.match(F_0.class) != null) {
				stacktracer.popOnFailure();
				return false;
			} else {
				stacktracer.popOnSuccess();
				setCurrentTerm(value.match(S_1.class).get_1());
				return true;
			}
		} catch (RuntimeException ex) {
			interopContext.getStackTracer().popOnExit(false);
			throw ex;
		}
	}
}
