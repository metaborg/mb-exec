package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class VPush_2 extends NoOpNode implements I_VHeapOp
{ 
  public com.github.krukow.clj_ds.PersistentMap<Object, Object> _1;

  public String _2;

  public VPush_2 (INodeSource source, com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, String _2) 
  { 
    this.setSourceInfo(source);
    this._1 = _1;
    this._2 = _2;
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      hasSpecialized = true;
    }
  }

  public vinit_Result exec_vinit(ds.manual.interpreter.VState lifted_in_1)
  { 
    this.specializeChildren(0);
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_1 = _1;
    final String lifted_2 = _2;
    final ds.manual.interpreter.VState vheap_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv = lifted_1;
    final String x = lifted_2;
    final ds.manual.interpreter.VState s = vheap_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> tmpbuild556 = s.alloc(venv, x);
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_ = tmpbuild556;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_out_1 = venv_;
    final ds.manual.interpreter.VState lifted_out_2 = s;
    final vinit_Result vinit_Result0 = new vinit_Result(lifted_out_1, lifted_out_2);
    return vinit_Result0;
  }

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> get_1()
  { 
    return this._1;
  }

  public String get_2()
  { 
    return this._2;
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
    final VPush_2 other = (VPush_2)obj;
    if(_1 != other._1)
    { 
      return false;
    }
    if(_2 != other._2)
    { 
      return false;
    }
    return true;
  }
}