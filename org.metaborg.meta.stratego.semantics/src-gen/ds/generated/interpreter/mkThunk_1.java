package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class mkThunk_1 extends AbstractNode implements I_Thunker {
	private boolean hasSpecialized;

	@Child
	public I_Strategy _1;

	public mkThunk_1(INodeSource source, I_Strategy _1) {
		this.setSourceInfo(source);
		this._1 = adoptChild(_1);
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
		final mkThunk_1 other = (mkThunk_1) obj;
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
			if (_1 instanceof IGenericNode) {
				((IGenericNode) _1).specialize(depth);
			}
			hasSpecialized = true;
		}
	}

	public R_thunks_List_Thunk_ exec_thunks(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			ds.manual.interpreter.SState _3) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public R_thunk_Thunk exec_thunk(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			ds.manual.interpreter.SState _3) {
		this.specializeChildren(0);
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3179 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3088 = _2;
		final ds.manual.interpreter.SState sheap_in3628 = _3;
		final I_Strategy s11441 = this._1;
		{
			final CallT_3 $tmp2662 = s11441.match(CallT_3.class);
			if ($tmp2662 != null) {
				final I_SVar lifted_31967 = $tmp2662.get_1();
				final INodeList<I_Strategy> lifted_30107 = $tmp2662.get_2();
				final INodeList<I_STerm> lifted_30117 = $tmp2662.get_3();
				final SVar_1 $tmp2663 = lifted_31967.match(SVar_1.class);
				if ($tmp2663 != null) {
					final String tgt478 = $tmp2663.get_1();
					final I_SHeapOp lifted_31977 = new SLookup_2(null,
							senv_in3088, tgt478);
					final R_slook_SLookupResult $tmp2664 = lifted_31977
							.exec_slook(sheap_in3628);
					final I_SLookupResult lifted_31987 = $tmp2664.value;
					final ds.manual.interpreter.SState sheap_29580 = $tmp2664
							.get_1();
					final SLookupResult_2 $tmp2665 = lifted_31987
							.match(SLookupResult_2.class);
					if ($tmp2665 != null) {
						final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> lifted_30037 = $tmp2665
								.get_1();
						final I_Thunk thunk1738 = $tmp2665.get_2();
						final Thunk_6 $tmp2666 = thunk1738.match(Thunk_6.class);
						if ($tmp2666 != null) {
							final String lifted_30047 = $tmp2666.get_1();
							final INodeList<String> lifted_30057 = $tmp2666
									.get_2();
							final INodeList<String> lifted_30067 = $tmp2666
									.get_3();
							final I_Strategy lifted_30077 = $tmp2666.get_4();
							final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> lifted_30087 = $tmp2666
									.get_5();
							final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> lifted_30097 = $tmp2666
									.get_6();
							final ds.manual.interpreter.SState sheap_out3628 = sheap_29580;
							final I_Thunk result_out4529 = thunk1738;
							return new R_thunk_Thunk(result_out4529,
									sheap_out3628);
						} else {
						}
					} else {
					}
				} else {
				}
			} else {
				if ($antimatch21(s11441)) {
					final String x3754 = ds.manual.interpreter.Natives
							.createAnonymousName_1("lifted");
					final I_Thunk lifted_32007 = new Thunk_6(null, x3754,
							NodeList.NIL(String.class),
							NodeList.NIL(String.class), s11441, venv_in3179,
							senv_in3088);
					final ds.manual.interpreter.SState sheap_out3628 = sheap_in3628;
					final I_Thunk result_out4529 = lifted_32007;
					return new R_thunk_Thunk(result_out4529, sheap_out3628);
				} else {
				}
			}
		}
		{
			throw new InterpreterException("Rule failure");
		}
	}

	private boolean $antimatch21(I_Strategy $tmp2667) {
		final CallT_3 $tmp2668 = $tmp2667.match(CallT_3.class);
		if ($tmp2668 != null) {
			final I_SVar lifted_3565 = $tmp2668.get_1();
			final INodeList<I_Strategy> lifted_3566 = $tmp2668.get_2();
			final INodeList<I_STerm> lifted_3567 = $tmp2668.get_3();
			final I_SVar lifted_30127 = lifted_3565;
			final INodeList<I_Strategy> lifted_30137 = lifted_3566;
			final INodeList<I_STerm> lifted_30147 = lifted_3567;
			return false;
		} else {
		}
		return true;
	}

	public I_Strategy get_1() {
		return this._1;
	}
}