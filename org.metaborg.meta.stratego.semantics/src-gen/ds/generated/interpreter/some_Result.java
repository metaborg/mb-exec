package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class some_Result  
{ 
  public final org.spoofax.interpreter.terms.IStrategoList value;

  public final org.spoofax.interpreter.core.StackTracer _1;

  public final ds.manual.interpreter.SState _2;

  public final ds.manual.interpreter.VState _3;

  public final boolean _4;

  public some_Result (org.spoofax.interpreter.terms.IStrategoList value, org.spoofax.interpreter.core.StackTracer _1, ds.manual.interpreter.SState _2, ds.manual.interpreter.VState _3, boolean _4) 
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

  public ds.manual.interpreter.VState get_3()
  { 
    return this._3;
  }

  public boolean get_4()
  { 
    return this._4;
  }
}