package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class bindtvars_2 extends AbstractNode implements I_Binder {
	private boolean hasSpecialized;

	public INodeList<String> _1;

	public INodeList<org.spoofax.interpreter.terms.IStrategoTerm> _2;

	public bindtvars_2(INodeSource source, INodeList<String> _1,
			INodeList<org.spoofax.interpreter.terms.IStrategoTerm> _2) {
		this.setSourceInfo(source);
		this._1 = _1;
		this._2 = _2;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final bindtvars_2 other = (bindtvars_2) obj;
		if (_1 == null) {
			if (other._1 != null) {
				return false;
			}
		} else if (!_1.equals(other._1)) {
			return false;
		}
		if (_2 == null) {
			if (other._2 != null) {
				return false;
			}
		} else if (!_2.equals(other._2)) {
			return false;
		}
		return true;
	}

	@Override
	public void specializeChildren(int depth) {
		if (!hasSpecialized) {
			hasSpecialized = true;
		}
	}

	public R_bindtvars_VEnv exec_bindtvars(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			ds.manual.interpreter.VState _2) {
		this.specializeChildren(0);
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3177 = _1;
		final ds.manual.interpreter.VState vheap_in3267 = _2;
		final INodeList<String> lifted_34117 = this._1;
		final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> lifted_34127 = this._2;
		{
			if (lifted_34117 != null
					&& lifted_34117.equals(NodeList.NIL(Object.class))) {
				if (lifted_34127 != null
						&& lifted_34127.equals(NodeList.NIL(Object.class))) {
					final ds.manual.interpreter.VState vheap_out3267 = vheap_in3267;
					final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> result_out4527 = venv_in3177;
					return new R_bindtvars_VEnv(result_out4527, vheap_out3267);
				} else {
				}
			} else {
				if (lifted_34117 != null && !lifted_34117.isEmpty()) {
					final String x3752 = lifted_34117.head();
					final INodeList<String> xxs189 = lifted_34117.tail();
					if (lifted_34127 != null && !lifted_34127.isEmpty()) {
						final org.spoofax.interpreter.terms.IStrategoTerm t10088 = lifted_34127
								.head();
						final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> txs385 = lifted_34127
								.tail();
						final IValue lifted_34157 = new S_1(null, t10088);
						final I_VHeapOp lifted_34137 = new VPushUpdate_3(null,
								venv_in3177, x3752, lifted_34157);
						final R_vinit_VEnv $tmp2639 = lifted_34137
								.exec_vinit(vheap_in3267);
						final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> e_21 = $tmp2639.value;
						final ds.manual.interpreter.VState vheap_28932 = $tmp2639
								.get_1();
						final I_Binder lifted_34147 = new bindtvars_2(null,
								xxs189, txs385);
						final R_bindtvars_VEnv $tmp2640 = lifted_34147
								.exec_bindtvars(e_21, vheap_28932);
						final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> e__10 = $tmp2640.value;
						final ds.manual.interpreter.VState vheap_34254 = $tmp2640
								.get_1();
						final ds.manual.interpreter.VState vheap_out3267 = vheap_34254;
						final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> result_out4527 = e__10;
						return new R_bindtvars_VEnv(result_out4527,
								vheap_out3267);
					} else {
					}
				} else {
				}
			}
		}
		{
			throw new InterpreterException("Rule failure");
		}
	}

	public R_bindsvars_SEnv exec_bindsvars(
			ds.manual.interpreter.AutoInterpInteropContext _1,
			org.spoofax.interpreter.terms.IStrategoTerm _2,
			org.spoofax.interpreter.terms.ITermFactory _3,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _4,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _5,
			boolean _6, org.spoofax.interpreter.core.StackTracer _7,
			ds.manual.interpreter.VState _8, ds.manual.interpreter.SState _9) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public INodeList<String> get_1() {
		return this._1;
	}

	public INodeList<org.spoofax.interpreter.terms.IStrategoTerm> get_2() {
		return this._2;
	}
}