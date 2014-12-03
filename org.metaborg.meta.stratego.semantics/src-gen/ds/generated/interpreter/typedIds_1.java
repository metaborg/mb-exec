package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class typedIds_1 extends AbstractNode implements I_NameExtractor {
	private boolean hasSpecialized;

	@Children
	public INodeList<I_Typedid> _1;

	public typedIds_1(INodeSource source, INodeList<I_Typedid> _1) {
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
		final typedIds_1 other = (typedIds_1) obj;
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
			for (I_Typedid _1_elem : _1) {
				if (_1_elem instanceof IGenericNode) {
					((IGenericNode) _1_elem).specialize(depth);
				}
			}
			hasSpecialized = true;
		}
	}

	public R_exid_List_String_ exec_exid() {
		this.specializeChildren(0);
		final INodeList<I_Typedid> lifted_30877 = this._1;
		{
			if (lifted_30877 != null
					&& lifted_30877.equals(NodeList.NIL(Object.class))) {
				final INodeList<String> result_out4535 = NodeList
						.NIL(String.class);
				return new R_exid_List_String_(result_out4535);
			} else {
				if (lifted_30877 != null && !lifted_30877.isEmpty()) {
					final I_Typedid lifted_30897 = lifted_30877.head();
					final INodeList<I_Typedid> xs1001 = lifted_30877.tail();
					final VarDec_2 $tmp2695 = lifted_30897
							.match(VarDec_2.class);
					if ($tmp2695 != null) {
						final String x3755 = $tmp2695.get_1();
						final I_Type lifted_29767 = $tmp2695.get_2();
						final I_NameExtractor lifted_30907 = new typedIds_1(
								null, xs1001);
						final R_exid_List_String_ $tmp2696 = lifted_30907
								.exec_exid();
						final INodeList<String> xs_10 = $tmp2696.value;
						final INodeList<String> lifted_30887 = new NodeList<String>(
								x3755, xs_10);
						final INodeList<String> result_out4535 = lifted_30887;
						return new R_exid_List_String_(result_out4535);
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

	public INodeList<I_Typedid> get_1() {
		return this._1;
	}
}