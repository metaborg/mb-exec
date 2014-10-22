package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class PrimT_3 extends NoOpNode implements I_Strategy
{ 
  public String _1;

  @Children public INodeList<I_Strategy> _2;

  @Children public INodeList<I_STerm> _3;

  public PrimT_3 (INodeSource source, String _1, INodeList<I_Strategy> _2, INodeList<I_STerm> _3) 
  { 
    this.setSourceInfo(source);
    this._1 = _1;
    this._2 = adoptChildren(_2);
    this._3 = adoptChildren(_3);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      for(I_Strategy _2_elem : _2)
      { 
        if(_2_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_2_elem).specialize(depth);
        }
      }
      for(I_STerm _3_elem : _3)
      { 
        if(_3_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_3_elem).specialize(depth);
        }
      }
      hasSpecialized = true;
    }
  }

  public default_Result exec_default(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_2, ds.manual.interpreter.AutoInterpInteropContext lifted_in_3, org.spoofax.interpreter.terms.ITermFactory lifted_in_4, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, ds.manual.interpreter.SState lifted_in_7, boolean lifted_in_8, ds.manual.interpreter.VState lifted_in_9)
  { 
    this.specializeChildren(0);
    final String lifted_1 = _1;
    final INodeList<I_Strategy> lifted_2 = _2;
    final INodeList<I_STerm> lifted_3 = _3;
    { 
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_2;
      final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_3;
      final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_4;
      final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_5;
      final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
      final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
      final boolean bool_1 = lifted_in_8;
      final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
      final String sname = lifted_1;
      final INodeList<I_Strategy> ass = lifted_2;
      final INodeList<I_STerm> ats = lifted_3;
      final ds.manual.interpreter.AutoInterpInteropContext ic = ic_1;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> d = senv_1;
      final bl_1 tmpbuild103 = new bl_1(getSourceInfo(), ats);
      final I_Builder lifted_9 = tmpbuild103;
      final blds_Result tmpresult13 = lifted_9.exec_blds(senv_1, e, ic_1, t_1, tf_1, trace_1, sheap_1, bool_1, vheap_1);
      final I_BuildRes lifted_8 = tmpresult13.value;
      final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult13.get_1();
      final ds.manual.interpreter.SState sheap_2 = tmpresult13.get_2();
      final boolean bool_2 = tmpresult13.get_3();
      final ds.manual.interpreter.VState vheap_2 = tmpresult13.get_4();
      final BSS_1 tmpbuild102 = lifted_8.match(BSS_1.class);
      if(tmpbuild102 != null)
      { 
        final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> ats_ = tmpbuild102.get_1();
        final mkThunks_1 tmpbuild101 = new mkThunks_1(getSourceInfo(), ass);
        final I_Thunker lifted_10 = tmpbuild101;
        final thunks_Result tmpresult12 = lifted_10.exec_thunks(venv_1, senv_1, sheap_2);
        final INodeList<I_Thunk> thunks = tmpresult12.value;
        final ds.manual.interpreter.SState sheap_3 = tmpresult12.get_1();
        final ds.manual.interpreter.primCall_3 tmpbuild100 = new ds.manual.interpreter.primCall_3(getSourceInfo(), sname, thunks, ats_);
        final I_Node lifted_11 = tmpbuild100;
        final default_Result tmpresult11 = lifted_11.exec_default(senv_1, venv_1, ic_1, tf_1, t_1, trace_2, sheap_3, bool_2, vheap_2);
        final AValue v = tmpresult11.value;
        final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult11.get_1();
        final ds.manual.interpreter.SState sheap_4 = tmpresult11.get_2();
        final boolean bool_3 = tmpresult11.get_3();
        final ds.manual.interpreter.VState vheap_3 = tmpresult11.get_4();
        final org.spoofax.interpreter.core.StackTracer lifted_4 = trace_3;
        final ds.manual.interpreter.SState lifted_5 = sheap_4;
        final boolean lifted_6 = bool_3;
        final ds.manual.interpreter.VState lifted_7 = vheap_3;
        final AValue lifted_out_1 = v;
        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_4;
        final ds.manual.interpreter.SState lifted_out_3 = lifted_5;
        final boolean lifted_out_4 = lifted_6;
        final ds.manual.interpreter.VState lifted_out_5 = lifted_7;
        final default_Result default_Result4 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
        return default_Result4;
      }
    }
    { 
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_2;
      final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_3;
      final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_4;
      final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_5;
      final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
      final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
      final boolean bool_1 = lifted_in_8;
      final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
      final String name = lifted_1;
      final INodeList<I_Strategy> ass = lifted_2;
      final INodeList<I_STerm> ats = lifted_3;
      final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
      final ds.manual.interpreter.AutoInterpInteropContext ic = ic_1;
      final F_0 tmpbuild104 = new F_0();
      final AValue lifted_4 = tmpbuild104;
      final org.spoofax.interpreter.core.StackTracer lifted_5 = trace_1;
      final ds.manual.interpreter.SState lifted_6 = sheap_1;
      final boolean lifted_7 = bool_1;
      final ds.manual.interpreter.VState lifted_8 = vheap_1;
      final AValue lifted_out_1 = lifted_4;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_5;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_6;
      final boolean lifted_out_4 = lifted_7;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_8;
      final default_Result default_Result4 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return default_Result4;
    }
  }

  public String get_1()
  { 
    return this._1;
  }

  public INodeList<I_Strategy> get_2()
  { 
    if(this._2 instanceof IGenericNode)
    { 
      ((IGenericNode)this._2).specialize(1);
    }
    return this._2;
  }

  public INodeList<I_STerm> get_3()
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
    final PrimT_3 other = (PrimT_3)obj;
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