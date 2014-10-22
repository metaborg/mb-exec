package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class leteval_Result  
{ 
  public final AValue value;

  public final org.spoofax.interpreter.core.StackTracer _1;

  public final ds.manual.interpreter.SState _2;

  public final boolean _3;

  public final ds.manual.interpreter.VState _4;

  public leteval_Result (AValue value, org.spoofax.interpreter.core.StackTracer _1, ds.manual.interpreter.SState _2, boolean _3, ds.manual.interpreter.VState _4) 
  { 
    this.value = value;
    this._1 = _1;
    this._2 = _2;
    this._3 = _3;
    this._4 = _4;
  }

  public org.spoofax.interpreter.core.StackTracer get_1()
  { 
    return this._1;
  }

  public ds.manual.interpreter.SState get_2()
  { 
    return this._2;
  }

  public boolean get_3()
  { 
    return this._3;
  }

  public ds.manual.interpreter.VState get_4()
  { 
    return this._4;
  }
}