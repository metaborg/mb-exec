package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class CallT_3 extends NoOpNode implements I_Strategy
{ 
  @Child public I_SVar _1;

  @Children public INodeList<I_Strategy> _2;

  @Children public INodeList<I_STerm> _3;

  public CallT_3 (INodeSource source, I_SVar _1, INodeList<I_Strategy> _2, INodeList<I_STerm> _3) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChild(_1);
    this._2 = adoptChildren(_2);
    this._3 = adoptChildren(_3);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      if(_1 instanceof IGenericNode)
      { 
        ((IGenericNode)_1).specialize(depth);
      }
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
    final I_SVar lifted_1 = _1;
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
      final SVar_1 tmpbuild162 = lifted_1.match(SVar_1.class);
      if(tmpbuild162 != null)
      { 
        final String sname = tmpbuild162.get_1();
        final INodeList<I_Strategy> ass = lifted_2;
        final INodeList<I_STerm> ats = lifted_3;
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> d = senv_1;
        final SLookup_2 tmpbuild161 = new SLookup_2(getSourceInfo(), d, sname);
        final I_SHeapOp lifted_9 = tmpbuild161;
        final slook_Result tmpresult35 = lifted_9.exec_slook(sheap_1);
        final I_SLookupResult lifted_8 = tmpresult35.value;
        final ds.manual.interpreter.SState sheap_2 = tmpresult35.get_1();
        final SLookupResult_2 tmpbuild160 = lifted_8.match(SLookupResult_2.class);
        if(tmpbuild160 != null)
        { 
          final com.github.krukow.clj_ds.PersistentMap<Object, Object> e_ = tmpbuild160.get_1();
          final I_Thunk thunk = tmpbuild160.get_2();
          final Thunk_6 tmpbuild159 = thunk.match(Thunk_6.class);
          if(tmpbuild159 != null)
          { 
            final String sname__ = tmpbuild159.get_1();
            final INodeList<String> fss = tmpbuild159.get_2();
            final INodeList<String> fts = tmpbuild159.get_3();
            final I_Strategy s = tmpbuild159.get_4();
            final com.github.krukow.clj_ds.PersistentMap<Object, Object> e_def = tmpbuild159.get_5();
            final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_def = tmpbuild159.get_6();
            final bl_1 tmpbuild158 = new bl_1(getSourceInfo(), ats);
            final I_Builder lifted_11 = tmpbuild158;
            final blds_Result tmpresult34 = lifted_11.exec_blds(senv_1, e, ic_1, t_1, tf_1, trace_1, sheap_2, bool_1, vheap_1);
            final I_BuildRes lifted_10 = tmpresult34.value;
            final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult34.get_1();
            final ds.manual.interpreter.SState sheap_3 = tmpresult34.get_2();
            final boolean bool_2 = tmpresult34.get_3();
            final ds.manual.interpreter.VState vheap_2 = tmpresult34.get_4();
            final BSS_1 tmpbuild157 = lifted_10.match(BSS_1.class);
            if(tmpbuild157 != null)
            { 
              final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> ats_ = tmpbuild157.get_1();
              final bindtvars_2 tmpbuild156 = new bindtvars_2(getSourceInfo(), fts, ats_);
              final I_Binder lifted_12 = tmpbuild156;
              final bindtvars_Result tmpresult33 = lifted_12.exec_bindtvars(e_def, vheap_2);
              final com.github.krukow.clj_ds.PersistentMap<Object, Object> e_call = tmpresult33.value;
              final ds.manual.interpreter.VState vheap_3 = tmpresult33.get_1();
              final bindsvars_3 tmpbuild155 = new bindsvars_3(getSourceInfo(), fss, ass, d_def);
              final I_Binder lifted_13 = tmpbuild155;
              final bindsvars_Result tmpresult32 = lifted_13.exec_bindsvars(ic_1, t_1, tf_1, e, d, trace_2, bool_2, vheap_3, sheap_3);
              final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_call = tmpresult32.value;
              final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult32.get_1();
              final boolean bool_3 = tmpresult32.get_2();
              final ds.manual.interpreter.VState vheap_4 = tmpresult32.get_3();
              final ds.manual.interpreter.SState sheap_4 = tmpresult32.get_4();
              final tracing_2 tmpbuild154 = new tracing_2(getSourceInfo(), sname, s);
              final I_Node lifted_14 = tmpbuild154;
              final default_Result tmpresult31 = lifted_14.exec_default(d_call, e_call, ic_1, tf_1, t_1, trace_3, sheap_4, bool_3, vheap_4);
              final AValue v = tmpresult31.value;
              final org.spoofax.interpreter.core.StackTracer trace_4 = tmpresult31.get_1();
              final ds.manual.interpreter.SState sheap_5 = tmpresult31.get_2();
              final boolean bool_4 = tmpresult31.get_3();
              final ds.manual.interpreter.VState vheap_5 = tmpresult31.get_4();
              final org.spoofax.interpreter.core.StackTracer lifted_4 = trace_4;
              final ds.manual.interpreter.SState lifted_5 = sheap_5;
              final boolean lifted_6 = bool_4;
              final ds.manual.interpreter.VState lifted_7 = vheap_5;
              final AValue lifted_out_1 = v;
              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_4;
              final ds.manual.interpreter.SState lifted_out_3 = lifted_5;
              final boolean lifted_out_4 = lifted_6;
              final ds.manual.interpreter.VState lifted_out_5 = lifted_7;
              final default_Result default_Result13 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
              return default_Result13;
            }
          }
        }
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
      final I_SVar dc1 = lifted_1;
      final INodeList<I_Strategy> dc2 = lifted_2;
      final INodeList<I_STerm> dc3 = lifted_3;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> d = senv_1;
      final F_0 tmpbuild163 = new F_0();
      final AValue lifted_4 = tmpbuild163;
      final org.spoofax.interpreter.core.StackTracer lifted_5 = trace_1;
      final ds.manual.interpreter.SState lifted_6 = sheap_1;
      final boolean lifted_7 = bool_1;
      final ds.manual.interpreter.VState lifted_8 = vheap_1;
      final AValue lifted_out_1 = lifted_4;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_5;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_6;
      final boolean lifted_out_4 = lifted_7;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_8;
      final default_Result default_Result13 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return default_Result13;
    }
  }

  public I_SVar get_1()
  { 
    if(this._1 instanceof IGenericNode)
    { 
      ((IGenericNode)this._1).specialize(1);
    }
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
    final CallT_3 other = (CallT_3)obj;
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