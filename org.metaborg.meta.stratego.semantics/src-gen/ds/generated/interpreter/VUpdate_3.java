package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class VUpdate_3 extends NoOpNode implements I_VHeapOp
{ 
  public com.github.krukow.clj_ds.PersistentMap<Object, Object> _1;

  public String _2;

  public AValue _3;

  public VUpdate_3 (INodeSource source, com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, String _2, AValue _3) 
  { 
    this.setSourceInfo(source);
    this._1 = _1;
    this._2 = _2;
    this._3 = _3;
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      hasSpecialized = true;
    }
  }

  public vupdate_Result exec_vupdate(ds.manual.interpreter.VState lifted_in_1)
  { 
    this.specializeChildren(0);
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_1 = _1;
    final String lifted_2 = _2;
    final AValue lifted_3 = _3;
    final ds.manual.interpreter.VState vheap_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv = lifted_1;
    final String x = lifted_2;
    final AValue v = lifted_3;
    final ds.manual.interpreter.VState s = vheap_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> tmpbuild555 = s.update(venv, x, v);
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_ = tmpbuild555;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_out_1 = venv;
    final ds.manual.interpreter.VState lifted_out_2 = s;
    final vupdate_Result vupdate_Result0 = new vupdate_Result(lifted_out_1, lifted_out_2);
    return vupdate_Result0;
  }

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> get_1()
  { 
    return this._1;
  }

  public String get_2()
  { 
    return this._2;
  }

  public AValue get_3()
  { 
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
    final VUpdate_3 other = (VUpdate_3)obj;
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