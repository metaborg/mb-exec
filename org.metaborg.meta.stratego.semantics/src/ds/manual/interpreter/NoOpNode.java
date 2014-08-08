package ds.manual.interpreter;

import org.metaborg.meta.interpreter.framework.AbstractNode;
import org.metaborg.meta.interpreter.framework.InterpreterException;
import org.spoofax.interpreter.core.StackTracer;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import com.github.krukow.clj_ds.PersistentMap;

import ds.generated.interpreter.I_Node;
import ds.generated.interpreter.allocmodule_Result;
import ds.generated.interpreter.allocsdefs_Result;
import ds.generated.interpreter.bindsvars_Result;
import ds.generated.interpreter.bindtvars_Result;
import ds.generated.interpreter.bld_Result;
import ds.generated.interpreter.blds_Result;
import ds.generated.interpreter.default_Result;
import ds.generated.interpreter.exid_Result;
import ds.generated.interpreter.first_Result;
import ds.generated.interpreter.leteval_Result;
import ds.generated.interpreter.ma_Result;
import ds.generated.interpreter.map_Result;
import ds.generated.interpreter.salloc_Result;
import ds.generated.interpreter.slook_Result;
import ds.generated.interpreter.some_Result;
import ds.generated.interpreter.storesdefs_Result;
import ds.generated.interpreter.thunk_Result;
import ds.generated.interpreter.thunks_Result;
import ds.generated.interpreter.trace_Result;
import ds.generated.interpreter.vinit_Result;
import ds.generated.interpreter.vlook_Result;
import ds.generated.interpreter.vupdate_Result;

public abstract class NoOpNode extends AbstractNode implements I_Node {

	@Override
	public ma_Result exec_ma(PersistentMap<Object, Object> _1,
			AutoInterpInteropContext _2, ITermFactory _3,
			PersistentMap<Object, Object> _4, IStrategoTerm _5, StackTracer _6,
			SState _7, boolean _8, VState _9) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public bld_Result exec_bld(PersistentMap<Object, Object> _1,
			AutoInterpInteropContext _2, IStrategoTerm _3, ITermFactory _4,
			PersistentMap<Object, Object> _5, StackTracer _6, SState _7,
			boolean _8, VState _9) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public blds_Result exec_blds(PersistentMap<Object, Object> _1,
			PersistentMap<Object, Object> _2, AutoInterpInteropContext _3,
			IStrategoTerm _4, ITermFactory _5, StackTracer _6, SState _7,
			boolean _8, VState _9) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public map_Result exec_map(PersistentMap<Object, Object> _1,
			PersistentMap<Object, Object> _2, AutoInterpInteropContext _3,
			IStrategoTerm _4, ITermFactory _5, StackTracer _6, SState _7,
			boolean _8, VState _9) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public first_Result exec_first(PersistentMap<Object, Object> _1,
			PersistentMap<Object, Object> _2, AutoInterpInteropContext _3,
			IStrategoTerm _4, ITermFactory _5, StackTracer _6, SState _7,
			boolean _8, VState _9) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public some_Result exec_some(PersistentMap<Object, Object> _1,
			PersistentMap<Object, Object> _2, AutoInterpInteropContext _3,
			IStrategoTerm _4, ITermFactory _5, StackTracer _6, SState _7,
			VState _8, boolean _9) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public bindtvars_Result exec_bindtvars(PersistentMap<Object, Object> _1,
			VState _2) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public bindsvars_Result exec_bindsvars(AutoInterpInteropContext _1,
			IStrategoTerm _2, ITermFactory _3,
			PersistentMap<Object, Object> _4, PersistentMap<Object, Object> _5,
			StackTracer _6, boolean _7, VState _8, SState _9) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public thunks_Result exec_thunks(PersistentMap<Object, Object> _1,
			PersistentMap<Object, Object> _2, SState _3) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public thunk_Result exec_thunk(PersistentMap<Object, Object> _1,
			PersistentMap<Object, Object> _2, SState _3) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public leteval_Result exec_leteval(AutoInterpInteropContext _1,
			ITermFactory _2, IStrategoTerm _3,
			PersistentMap<Object, Object> _4, PersistentMap<Object, Object> _5,
			StackTracer _6, SState _7, boolean _8, VState _9) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public allocmodule_Result exec_allocmodule(
			PersistentMap<Object, Object> _1, PersistentMap<Object, Object> _2,
			SState _3) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public allocsdefs_Result exec_allocsdefs(SState _1) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public storesdefs_Result exec_storesdefs(PersistentMap<Object, Object> _1,
			PersistentMap<Object, Object> _2, SState _3) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public exid_Result exec_exid() {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public default_Result exec_default(PersistentMap<Object, Object> _1,
			PersistentMap<Object, Object> _2, AutoInterpInteropContext _3,
			ITermFactory _4, IStrategoTerm _5, StackTracer _6, SState _7,
			boolean _8, VState _9) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public vlook_Result exec_vlook(VState _1) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public vupdate_Result exec_vupdate(VState _1) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public vinit_Result exec_vinit(VState _1) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public slook_Result exec_slook(SState _1) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public salloc_Result exec_salloc(SState _1) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

	@Override
	public trace_Result exec_trace(StackTracer _1) {
		// TODO Auto-generated method stub
		throw new InterpreterException("Operation not supported");
	}

}
