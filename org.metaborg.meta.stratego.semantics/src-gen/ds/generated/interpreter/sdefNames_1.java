package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class sdefNames_1 extends AbstractNode implements I_NameExtractor {
	private boolean hasSpecialized;

	@Children
	public INodeList<I_Def> _1;

	public sdefNames_1(INodeSource source, INodeList<I_Def> _1) {
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
		final sdefNames_1 other = (sdefNames_1) obj;
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

	public R_exid_List_String_ exec_exid() {
		this.specializeChildren(0);
		final INodeList<I_Def> lifted_31267 = this._1;
		{
			if (lifted_31267 != null
					&& lifted_31267.equals(NodeList.NIL(Object.class))) {
				final INodeList<String> result_out4536 = NodeList
						.NIL(String.class);
				return new R_exid_List_String_(result_out4536);
			} else {
				if (lifted_31267 != null && !lifted_31267.isEmpty()) {
					final I_Def lifted_31287 = lifted_31267.head();
					final INodeList<I_Def> sdefs944 = lifted_31267.tail();
					final SDefT_4 $tmp2697 = lifted_31287.match(SDefT_4.class);
					if ($tmp2697 != null) {
						final String sname1213 = $tmp2697.get_1();
						final INodeList<I_Typedid> lifted_29827 = $tmp2697
								.get_2();
						final INodeList<I_Typedid> lifted_29837 = $tmp2697
								.get_3();
						final I_Strategy lifted_29847 = $tmp2697.get_4();
						final I_NameExtractor lifted_31297 = new sdefNames_1(
								null, sdefs944);
						final R_exid_List_String_ $tmp2698 = lifted_31297
								.exec_exid();
						final INodeList<String> snames321 = $tmp2698.value;
						final INodeList<String> lifted_31277 = new NodeList<String>(
								sname1213, snames321);
						final INodeList<String> result_out4536 = lifted_31277;
						return new R_exid_List_String_(result_out4536);
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