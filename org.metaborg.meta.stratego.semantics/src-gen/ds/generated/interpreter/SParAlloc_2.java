package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class SParAlloc_2 extends NoOpNode implements I_SHeapOp
{ 
  public com.github.krukow.clj_ds.PersistentMap<Object, Object> _1;

  @Children public INodeList<String> _2;

  public SParAlloc_2 (INodeSource source, com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, INodeList<String> _2) 
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
    final INodeList<String> lifted_2 = _2;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv = lifted_1;
    if(lifted_2 != null && lifted_2.isEmpty())
    { 
      final ds.manual.interpreter.SState s = sheap_1;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_out_1 = senv;
      final ds.manual.interpreter.SState lifted_out_2 = s;
      final salloc_Result salloc_Result1 = new salloc_Result(lifted_out_1, lifted_out_2);
      return salloc_Result1;
    }
    else
    { 
      if(lifted_2 != null && !lifted_2.isEmpty())
      { 
        final String name = lifted_2.head();
        final INodeList<String> names = lifted_2.tail();
        final ds.manual.interpreter.SState s = sheap_1;
        final SAlloc_2 tmpbuild561 = new SAlloc_2(getSourceInfo(), senv, name);
        final I_SHeapOp lifted_3 = tmpbuild561;
        final salloc_Result tmpresult121 = lifted_3.exec_salloc(s);
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_ = tmpresult121.value;
        final ds.manual.interpreter.SState sheap_2 = tmpresult121.get_1();
        final ds.manual.interpreter.SState s_ = sheap_2;
        final SParAlloc_2 tmpbuild560 = new SParAlloc_2(getSourceInfo(), senv_, names);
        final I_SHeapOp lifted_4 = tmpbuild560;
        final salloc_Result tmpresult120 = lifted_4.exec_salloc(s_);
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv__ = tmpresult120.value;
        final ds.manual.interpreter.SState sheap_3 = tmpresult120.get_1();
        final ds.manual.interpreter.SState s__ = sheap_3;
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_out_1 = senv__;
        final ds.manual.interpreter.SState lifted_out_2 = s__;
        final salloc_Result salloc_Result1 = new salloc_Result(lifted_out_1, lifted_out_2);
        return salloc_Result1;
      }
    }
    throw new InterpreterException("Rule failure");
  }

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> get_1()
  { 
    return this._1;
  }

  public INodeList<String> get_2()
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
    final SParAlloc_2 other = (SParAlloc_2)obj;
    if(_1 != other._1)
    { 
      return false;
    }
    if(_2 == null)
    { 
      if(other._2 != null)
      { 
        return false;
      }
    }
    else
      if(!_2.equals(other._2))
      { 
        return false;
      }
    return true;
  }
}