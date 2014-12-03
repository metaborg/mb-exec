package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class mkThunks_1 extends AbstractNode implements I_Thunker {
	private boolean hasSpecialized;

	@Children
	public INodeList<I_Strategy> _1;

	public mkThunks_1(INodeSource source, INodeList<I_Strategy> _1) {
		this.setSourceInfo(source);
		this._1 = adoptChildren(_1);
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
		final mkThunks_1 other = (mkThunks_1) obj;
		if (_1 == null) {
			if (other._1 != null) {
				return false;
			}
		} else if (!_1.equals(other._1)) {
			return false;
		}
		return true;
	}

	@Override
	public void specializeChildren(int depth) {
		if (!hasSpecialized) {
			for (I_Strategy _1_elem : _1) {
				if (_1_elem instanceof IGenericNode) {
					((IGenericNode) _1_elem).specialize(depth);
				}
			}
			hasSpecialized = true;
		}
	}

	public R_thunks_List_Thunk_ exec_thunks(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			ds.manual.interpreter.SState _3) {
		this.specializeChildren(0);
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3180 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3089 = _2;
		final ds.manual.interpreter.SState sheap_in3629 = _3;
		final INodeList<I_Strategy> lifted_31917 = this._1;
		{
			if (lifted_31917 != null
					&& lifted_31917.equals(NodeList.NIL(Object.class))) {
				final ds.manual.interpreter.SState sheap_out3629 = sheap_in3629;
				final INodeList<I_Thunk> result_out4530 = NodeList
						.NIL(I_Thunk.class);
				return new R_thunks_List_Thunk_(result_out4530, sheap_out3629);
			} else {
				if (lifted_31917 != null && !lifted_31917.isEmpty()) {
					final I_Strategy as189 = lifted_31917.head();
					final INodeList<I_Strategy> ass1009 = lifted_31917.tail();
					final I_Thunker lifted_31937 = new mkThunk_1(null, as189);
					final R_thunk_Thunk $tmp2669 = lifted_31937.exec_thunk(
							venv_in3180, senv_in3089, sheap_in3629);
					final I_Thunk as_10 = $tmp2669.value;
					final ds.manual.interpreter.SState sheap_29581 = $tmp2669
							.get_1();
					final I_Thunker lifted_31947 = new mkThunks_1(null, ass1009);
					final R_thunks_List_Thunk_ $tmp2670 = lifted_31947
							.exec_thunks(venv_in3180, senv_in3089, sheap_29581);
					final INodeList<I_Thunk> ass_10 = $tmp2670.value;
					final ds.manual.interpreter.SState sheap_34720 = $tmp2670
							.get_1();
					final INodeList<I_Thunk> lifted_31927 = new NodeList<I_Thunk>(
							as_10, ass_10);
					final ds.manual.interpreter.SState sheap_out3629 = sheap_34720;
					final INodeList<I_Thunk> result_out4530 = lifted_31927;
					return new R_thunks_List_Thunk_(result_out4530,
							sheap_out3629);
				} else {
				}
			}
		}
		{
			throw new InterpreterException("Rule failure");
		}
	}

	public R_thunk_Thunk exec_thunk(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			ds.manual.interpreter.SState _3) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public INodeList<I_Strategy> get_1() {
		return this._1;
	}
}