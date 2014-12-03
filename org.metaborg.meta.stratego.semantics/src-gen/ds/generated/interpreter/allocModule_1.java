package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class allocModule_1 extends AbstractNode implements I_Allocator {
	private boolean hasSpecialized;

	@Child
	public I_Module _1;

	public allocModule_1(INodeSource source, I_Module _1) {
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
		final allocModule_1 other = (allocModule_1) obj;
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

	public R_allocmodule_SEnv exec_allocmodule(
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> _1,
			com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> _2,
			ds.manual.interpreter.SState _3) {
		this.specializeChildren(0);
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.VBox> venv_in3182 = _1;
		final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> senv_in3091 = _2;
		final ds.manual.interpreter.SState sheap_in3631 = _3;
		final I_Module lifted_35217 = this._1;
		{
			final Specification_1 $tmp2677 = lifted_35217
					.match(Specification_1.class);
			if ($tmp2677 != null) {
				final INodeList<I_Decl> lifted_35227 = $tmp2677.get_1();
				if (lifted_35227 != null && !lifted_35227.isEmpty()) {
					final I_Decl lifted_30727 = lifted_35227.head();
					final INodeList<I_Decl> lifted_35267 = lifted_35227.tail();
					if (lifted_35267 != null && !lifted_35267.isEmpty()) {
						final I_Decl lifted_35277 = lifted_35267.head();
						final INodeList<I_Decl> lifted_35287 = lifted_35267
								.tail();
						final Strategies_1 $tmp2678 = lifted_35277
								.match(Strategies_1.class);
						if ($tmp2678 != null) {
							final INodeList<I_Def> ss869 = $tmp2678.get_1();
							if (lifted_35287 != null
									&& lifted_35287.equals(NodeList
											.NIL(Object.class))) {
								final I_Allocator lifted_35237 = new allocDefs_2(
										null, ss869, senv_in3091);
								final R_allocsdefs_AllocatorResult $tmp2679 = lifted_35237
										.exec_allocsdefs(sheap_in3631);
								final I_AllocatorResult lifted_35247 = $tmp2679.value;
								final ds.manual.interpreter.SState sheap_29584 = $tmp2679
										.get_1();
								final allocdDefs_2 $tmp2680 = lifted_35247
										.match(allocdDefs_2.class);
								if ($tmp2680 != null) {
									final INodeList<I_Def> ss_31 = $tmp2680
											.get_1();
									final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> d_21 = $tmp2680
											.get_2();
									final I_Allocator lifted_35257 = new storeDefs_1(
											null, ss_31);
									final R_storesdefs_Bool $tmp2681 = lifted_35257
											.exec_storesdefs(venv_in3182, d_21,
													sheap_29584);
									final boolean lifted_30717 = $tmp2681.value;
									final ds.manual.interpreter.SState sheap_34722 = $tmp2681
											.get_1();
									final ds.manual.interpreter.SState sheap_out3631 = sheap_34722;
									final com.github.krukow.clj_ds.PersistentMap<String, ds.manual.interpreter.SBox> result_out4532 = d_21;
									return new R_allocmodule_SEnv(
											result_out4532, sheap_out3631);
								} else {
								}
							} else {
							}
						} else {
						}
					} else {
					}
				} else {
				}
			} else {
			}
		}
		{
			throw new InterpreterException("Rule failure");
		}
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
		throw new InterpreterException("Rule failure");
	}

	public I_Module get_1() {
		return this._1;
	}
}