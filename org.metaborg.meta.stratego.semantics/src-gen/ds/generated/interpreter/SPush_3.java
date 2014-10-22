package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class SPush_3 extends NoOpNode implements I_SHeapOp
{ 
  public com.github.krukow.clj_ds.PersistentMap<Object, Object> _1;

  public String _2;

  @Child public I_Thunk _3;

  public SPush_3 (INodeSource source, com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, String _2, I_Thunk _3) 
  { 
    this.setSourceInfo(source);
    this._1 = _1;
    this._2 = _2;
    this._3 = adoptChild(_3);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      if(_3 instanceof IGenericNode)
      { 
        ((IGenericNode)_3).specialize(depth);
      }
      hasSpecialized = true;
    }
  }

  public salloc_Result exec_salloc(ds.manual.interpreter.SState lifted_in_1)
  { 
    this.specializeChildren(0);
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_1 = _1;
    final String lifted_2 = _2;
    final I_Thunk lifted_3 = _3;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv = lifted_1;
    final String name = lifted_2;
    final I_Thunk thunk = lifted_3;
    final ds.manual.interpreter.SState s = sheap_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> tmpbuild563 = s.allocupdate(senv, name, thunk);
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_ = tmpbuild563;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_out_1 = senv_;
    final ds.manual.interpreter.SState lifted_out_2 = s;
    final salloc_Result salloc_Result3 = new salloc_Result(lifted_out_1, lifted_out_2);
    return salloc_Result3;
  }

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> get_1()
  { 
    return this._1;
  }

  public String get_2()
  { 
    return this._2;
  }

  public I_Thunk get_3()
  { 
    if(this._3 instanceof IGenericNode)
    { 
      ((IGenericNode)this._3).specialize(1);
    }
    return this._3;
  }

  @Override public boolean equals(Object obj)
  { 
    if(this == obj)
    { 
      return true;
    }
    if(obj == null)
    { 
      return false;
    }
    if(getClass() != obj.getClass())
    { 
      return false;
    }
    final SPush_3 other = (SPush_3)obj;
    if(_1 != other._1)
    { 
      return false;
    }
    if(_2 != other._2)
    { 
      return false;
    }
    if(_3 == null)
    { 
      if(other._3 != null)
      { 
        return false;
      }
    }
    else
      if(!_3.equals(other._3))
      { 
        return false;
      }
    return true;
  }
}