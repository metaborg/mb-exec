package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public interface I_VHeapOp extends IMatchableNode {
	public R_vlook_VLookupResult exec_vlook(ds.manual.interpreter.VState _1);

	public R_vupdate_VEnv exec_vupdate(ds.manual.interpreter.VState _1);

	public R_vinit_VEnv exec_vinit(ds.manual.interpreter.VState _1);
}