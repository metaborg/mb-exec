package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Scope_2 extends NoOpNode implements I_Strategy
{ 
  @Children public INodeList<String> _1;

  @Child public I_Strategy _2;

  public Scope_2 (INodeSource source, INodeList<String> _1, I_Strategy _2) 
  { 
    this.setSourceInfo(source);
    this._1 = _1;
    this._2 = adoptChild(_2);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      if(_2 instanceof IGenericNode)
      { 
        ((IGenericNode)_2).specialize(depth);
      }
      hasSpecialized = true;
    }
  }

  public default_Result exec_default(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_2, ds.manual.interpreter.AutoInterpInteropContext lifted_in_3, org.spoofax.interpreter.terms.ITermFactory lifted_in_4, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, ds.manual.interpreter.SState lifted_in_7, boolean lifted_in_8, ds.manual.interpreter.VState lifted_in_9)
  { 
    this.specializeChildren(0);
    final INodeList<String> lifted_1 = _1;
    final I_Strategy lifted_2 = _2;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_2;
    final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_3;
    final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_4;
    final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_5;
    final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
    final boolean bool_1 = lifted_in_8;
    final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
    final INodeList<String> vs = lifted_1;
    final I_Strategy s = lifted_2;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
    final VPushBatch_2 tmpbuild109 = new VPushBatch_2(getSourceInfo(), e, vs);
    final I_VHeapOp lifted_7 = tmpbuild109;
    final vinit_Result tmpresult20 = lifted_7.exec_vinit(vheap_1);
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> e_ = tmpresult20.value;
    final ds.manual.interpreter.VState vheap_2 = tmpresult20.get_1();
    final default_Result tmpresult19 = s.exec_default(senv_1, e_, ic_1, tf_1, t_1, trace_1, sheap_1, bool_1, vheap_2);
    final AValue v = tmpresult19.value;
    final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult19.get_1();
    final ds.manual.interpreter.SState sheap_2 = tmpresult19.get_2();
    final boolean bool_2 = tmpresult19.get_3();
    final ds.manual.interpreter.VState vheap_3 = tmpresult19.get_4();
    final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_2;
    final ds.manual.interpreter.SState lifted_4 = sheap_2;
    final boolean lifted_5 = bool_2;
    final ds.manual.interpreter.VState lifted_6 = vheap_3;
    final AValue lifted_out_1 = v;
    final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
    final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
    final boolean lifted_out_4 = lifted_5;
    final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
    final default_Result default_Result7 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
    return default_Result7;
  }

  public INodeList<String> get_1()
  { 
    return this._1;
  }

  public I_Strategy get_2()
  { 
    if(this._2 instanceof IGenericNode)
    { 
      ((IGenericNode)this._2).specialize(1);
    }
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
    final Scope_2 other = (Scope_2)obj;
    if(_1 == null)
    { 
      if(other._1 != null)
      { 
        return false;
      }
    }
    else
      if(!_1.equals(other._1))
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