package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class allocDefs_2 extends AbstractNode implements I_Allocator {
	private boolean hasSpecialized;

	@Children
	public INodeList<I_Def> _1;

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2;

	public allocDefs_2(
			INodeSource source,
			INodeList<I_Def> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2) {
		this.setSourceInfo(source);
		this._1 = adoptChildren(_1);
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
		final allocDefs_2 other = (allocDefs_2) obj;
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
		final ds.manual.interpreter.SState sheap_in3632 = _1;
		final INodeList<I_Def> lifted_35387 = this._1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv2998 = this._2;
		{
			if (lifted_35387 != null
					&& lifted_35387.equals(NodeList.NIL(Object.class))) {
				final I_AllocatorResult lifted_35307 = new allocdDefs_2(null,
						NodeList.NIL(I_Def.class), senv2998);
				final ds.manual.interpreter.SState sheap_out3632 = sheap_in3632;
				final I_AllocatorResult result_out4533 = lifted_35307;
				return new R_allocsdefs_AllocatorResult(result_out4533,
						sheap_out3632);
			} else {
				if (lifted_35387 != null && !lifted_35387.isEmpty()) {
					final I_Def sdef391 = lifted_35387.head();
					final INodeList<I_Def> sdefs942 = lifted_35387.tail();
					final AnnoDef_2 $tmp2682 = sdef391.match(AnnoDef_2.class);
					if ($tmp2682 != null) {
						final INodeList<I_Anno> lifted_30737 = $tmp2682.get_1();
						final I_StrategyDef sdef_10 = $tmp2682.get_2();
						final INodeList<I_Def> lifted_35357 = new NodeList<I_Def>(
								sdef_10, sdefs942);
						final I_Allocator lifted_35347 = new allocDefs_2(null,
								lifted_35357, senv2998);
						final R_allocsdefs_AllocatorResult $tmp2683 = lifted_35347
								.exec_allocsdefs(sheap_in3632);
						final I_AllocatorResult ad320 = $tmp2683.value;
						final ds.manual.interpreter.SState sheap_29585 = $tmp2683
								.get_1();
						final ds.manual.interpreter.SState sheap_out3632 = sheap_29585;
						final I_AllocatorResult result_out4533 = ad320;
						return new R_allocsdefs_AllocatorResult(result_out4533,
								sheap_out3632);
					} else {
						final ExtSDef_3 $tmp2684 = sdef391
								.match(ExtSDef_3.class);
						if ($tmp2684 != null) {
							final String lifted_30747 = $tmp2684.get_1();
							final INodeList<I_Typedid> lifted_30757 = $tmp2684
									.get_2();
							final INodeList<I_Typedid> lifted_30767 = $tmp2684
									.get_3();
							final I_Allocator lifted_35377 = new allocDefs_2(
									null, sdefs942, senv2998);
							final R_allocsdefs_AllocatorResult $tmp2685 = lifted_35377
									.exec_allocsdefs(sheap_in3632);
							final I_AllocatorResult ad321 = $tmp2685.value;
							final ds.manual.interpreter.SState sheap_29586 = $tmp2685
									.get_1();
							final ds.manual.interpreter.SState sheap_out3632 = sheap_29586;
							final I_AllocatorResult result_out4533 = ad321;
							return new R_allocsdefs_AllocatorResult(
									result_out4533, sheap_out3632);
						} else {
							final SDefT_4 $tmp2686 = sdef391
									.match(SDefT_4.class);
							if ($tmp2686 != null) {
								final String sname1211 = $tmp2686.get_1();
								final INodeList<I_Typedid> lifted_30777 = $tmp2686
										.get_2();
								final INodeList<I_Typedid> lifted_30787 = $tmp2686
										.get_3();
								final I_Strategy lifted_30797 = $tmp2686
										.get_4();
								final I_SHeapOp lifted_35417 = new SAlloc_2(
										null, senv2998, sname1211);
								final R_salloc_SEnv $tmp2687 = lifted_35417
										.exec_salloc(sheap_in3632);
								final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_50 = $tmp2687.value;
								final ds.manual.interpreter.SState sheap_29587 = $tmp2687
										.get_1();
								final I_Allocator lifted_35427 = new allocDefs_2(
										null, sdefs942, senv_50);
								final R_allocsdefs_AllocatorResult $tmp2688 = lifted_35427
										.exec_allocsdefs(sheap_29587);
								final I_AllocatorResult lifted_35437 = $tmp2688.value;
								final ds.manual.interpreter.SState sheap_34723 = $tmp2688
										.get_1();
								final allocdDefs_2 $tmp2689 = lifted_35437
										.match(allocdDefs_2.class);
								if ($tmp2689 != null) {
									final INodeList<I_Def> sdefs_10 = $tmp2689
											.get_1();
									final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv__20 = $tmp2689
											.get_2();
									final INodeList<I_Def> lifted_35447 = new NodeList<I_Def>(
											sdef391, sdefs_10);
									final I_AllocatorResult lifted_35397 = new allocdDefs_2(
											null, lifted_35447, senv__20);
									final ds.manual.interpreter.SState sheap_out3632 = sheap_34723;
									final I_AllocatorResult result_out4533 = lifted_35397;
									return new R_allocsdefs_AllocatorResult(
											result_out4533, sheap_out3632);
								} else {
								}
							} else {
							}
						}
					}
				} else {
				}
			}
		}
		{
			throw new InterpreterException("Rule failure");
		}
	}

	public R_storesdefs_Bool exec_storesdefs(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			ds.manual.interpreter.SState _3) {
		this.specializeChildren(0);
		throw new InterpreterException("Rule failure");
	}

	public INodeList<I_Def> get_1() {
		return this._1;
	}

	public com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> get_2() {
		return this._2;
	}
}