package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class storeDefs_1 extends AbstractNode implements I_Allocator {
	private boolean hasSpecialized;

	@Children
	public INodeList<I_Def> _1;

	public storeDefs_1(INodeSource source, INodeList<I_Def> _1) {
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
		final storeDefs_1 other = (storeDefs_1) obj;
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
			for (I_Def _1_elem : _1) {
				if (_1_elem instanceof IGenericNode) {
					((IGenericNode) _1_elem).specialize(depth);
				}
			}
			hasSpecialized = true;
		}
	}

	public R_allocmodule_SEnv exec_allocmodule(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			ds.manual.interpreter.SState _3) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public R_allocsdefs_AllocatorResult exec_allocsdefs(
			ds.manual.interpreter.SState _1) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public R_storesdefs_Bool exec_storesdefs(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			ds.manual.interpreter.SState _3) {
		this.specializeChildren(0);
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3183 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3092 = _2;
		final ds.manual.interpreter.SState sheap_in3633 = _3;
		final INodeList<I_Def> lifted_30957 = this._1;
		{
			if (lifted_30957 != null
					&& lifted_30957.equals(NodeList.NIL(Object.class))) {
				final ds.manual.interpreter.SState sheap_out3633 = sheap_in3633;
				final boolean result_out4534 = true;
				return new R_storesdefs_Bool(result_out4534, sheap_out3633);
			} else {
				if (lifted_30957 != null && !lifted_30957.isEmpty()) {
					final I_Def lifted_30967 = lifted_30957.head();
					final INodeList<I_Def> sdefs943 = lifted_30957.tail();
					final SDefT_4 $tmp2690 = lifted_30967.match(SDefT_4.class);
					if ($tmp2690 != null) {
						final String sname1212 = $tmp2690.get_1();
						final INodeList<I_Typedid> ss870 = $tmp2690.get_2();
						final INodeList<I_Typedid> ts5871 = $tmp2690.get_3();
						final I_Strategy s11443 = $tmp2690.get_4();
						final I_NameExtractor lifted_30977 = new typedIds_1(
								null, ss870);
						final R_exid_List_String_ $tmp2691 = lifted_30977
								.exec_exid();
						final INodeList<String> ss_32 = $tmp2691.value;
						final I_NameExtractor lifted_30987 = new typedIds_1(
								null, ts5871);
						final R_exid_List_String_ $tmp2692 = lifted_30987
								.exec_exid();
						final INodeList<String> ts_107 = $tmp2692.value;
						final I_Thunk lifted_31017 = new Thunk_6(null,
								sname1212, ss_32, ts_107, s11443, venv_in3183,
								senv_in3092);
						final I_SHeapOp lifted_30997 = new SUpdate_3(null,
								senv_in3092, sname1212, lifted_31017);
						final R_salloc_SEnv $tmp2693 = lifted_30997
								.exec_salloc(sheap_in3633);
						final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> lifted_29797 = $tmp2693.value;
						final ds.manual.interpreter.SState sheap_29588 = $tmp2693
								.get_1();
						final I_Allocator lifted_31007 = new storeDefs_1(null,
								sdefs943);
						final R_storesdefs_Bool $tmp2694 = lifted_31007
								.exec_storesdefs(venv_in3183, senv_in3092,
										sheap_29588);
						final boolean b160 = $tmp2694.value;
						final ds.manual.interpreter.SState sheap_34724 = $tmp2694
								.get_1();
						final ds.manual.interpreter.SState sheap_out3633 = sheap_34724;
						final boolean result_out4534 = b160;
						return new R_storesdefs_Bool(result_out4534,
								sheap_out3633);
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

	public INodeList<I_Def> get_1() {
		return this._1;
	}
}