package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class SParAlloc_2 extends AbstractNode implements I_SHeapOp {
	private boolean hasSpecialized;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _1;

	public INodeList<String> _2;

	public SParAlloc_2(
			INodeSource source,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _1,
			INodeList<String> _2) {
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
		final SParAlloc_2 other = (SParAlloc_2) obj;
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

	public R_slook_SLookupResult exec_slook(ds.manual.interpreter.SState _1) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public R_salloc_SEnv exec_salloc(ds.manual.interpreter.SState _1) {
		this.specializeChildren(0);
		final ds.manual.interpreter.SState sheap_in3635 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv3000 = this._1;
		final INodeList<String> lifted_31037 = this._2;
		{
			if (lifted_31037 != null
					&& lifted_31037.equals(NodeList.NIL(Object.class))) {
				final ds.manual.interpreter.SState sheap_out3635 = sheap_in3635;
				final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> result_out4543 = senv3000;
				return new R_salloc_SEnv(result_out4543, sheap_out3635);
			} else {
				if (lifted_31037 != null && !lifted_31037.isEmpty()) {
					final String name2461 = lifted_31037.head();
					final INodeList<String> names453 = lifted_31037.tail();
					final I_SHeapOp lifted_31047 = new SAlloc_2(null, senv3000,
							name2461);
					final R_salloc_SEnv $tmp2699 = lifted_31047
							.exec_salloc(sheap_in3635);
					final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_52 = $tmp2699.value;
					final ds.manual.interpreter.SState s_21 = $tmp2699.get_1();
					final I_SHeapOp lifted_31057 = new SParAlloc_2(null,
							senv_52, names453);
					final R_salloc_SEnv $tmp2700 = lifted_31057
							.exec_salloc(s_21);
					final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv__21 = $tmp2700.value;
					final ds.manual.interpreter.SState s__10 = $tmp2700.get_1();
					final ds.manual.interpreter.SState sheap_out3635 = s__10;
					final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> result_out4543 = senv__21;
					return new R_salloc_SEnv(result_out4543, sheap_out3635);
				} else {
				}
			}
		}
		{
			throw new InterpreterException("Rule failure");
		}
	}

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> get_1() {
		return this._1;
	}

	public INodeList<String> get_2() {
		return this._2;
	}
}