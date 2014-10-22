package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class bindsvars_Result  
{ 
  public final com.github.krukow.clj_ds.PersistentMap<Object, Object> value;

  public final org.spoofax.interpreter.core.StackTracer _1;

  public final boolean _2;

  public final ds.manual.interpreter.VState _3;

  public final ds.manual.interpreter.SState _4;

  public bindsvars_Result (com.github.krukow.clj_ds.PersistentMap<Object, Object> value, org.spoofax.interpreter.core.StackTracer _1, boolean _2, ds.manual.interpreter.VState _3, ds.manual.interpreter.SState _4) 
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

  public boolean get_2()
  { 
    return this._2;
  }

  public ds.manual.interpreter.VState get_3()
  { 
    return this._3;
  }

  public ds.manual.interpreter.SState get_4()
  { 
    return this._4;
  }
}