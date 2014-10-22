package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class SAlloc_2 extends NoOpNode implements I_SHeapOp
{ 
  public com.github.krukow.clj_ds.PersistentMap<Object, Object> _1;

  public String _2;

  public SAlloc_2 (INodeSource source, com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, String _2) 
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

  public salloc_Result exec_salloc(ds.manual.interpreter.SState lifted_in_1)
  { 
    this.specializeChildren(0);
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_1 = _1;
    final String lifted_2 = _2;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv = lifted_1;
    final String name = lifted_2;
    final ds.manual.interpreter.SState s = sheap_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> tmpbuild559 = s.alloc(senv, name);
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_ = tmpbuild559;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_out_1 = senv_;
    final ds.manual.interpreter.SState lifted_out_2 = s;
    final salloc_Result salloc_Result0 = new salloc_Result(lifted_out_1, lifted_out_2);
    return salloc_Result0;
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
    final SAlloc_2 other = (SAlloc_2)obj;
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