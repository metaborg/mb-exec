package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class thunks_Result  
{ 
  public final INodeList<I_Thunk> value;

  public final ds.manual.interpreter.SState _1;

  public thunks_Result (INodeList<I_Thunk> value, ds.manual.interpreter.SState _1) 
  { 
    this.value = value;
    this._1 = _1;
  }

  public ds.manual.interpreter.SState get_1()
  { 
    return this._1;
  }
}