package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class letEval_2 extends NoOpNode implements I_LetHelper
{ 
  @Children public INodeList<I_Def> _1;

  @Child public I_Strategy _2;

  public letEval_2 (INodeSource source, INodeList<I_Def> _1, I_Strategy _2) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChildren(_1);
    this._2 = adoptChild(_2);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      for(I_Def _1_elem : _1)
      { 
        if(_1_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_1_elem).specialize(depth);
        }
      }
      if(_2 instanceof IGenericNode)
      { 
        ((IGenericNode)_2).specialize(depth);
      }
      hasSpecialized = true;
    }
  }

  public leteval_Result exec_leteval(ds.manual.interpreter.AutoInterpInteropContext lifted_in_1, org.spoofax.interpreter.terms.ITermFactory lifted_in_2, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_3, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_4, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, ds.manual.interpreter.SState lifted_in_7, boolean lifted_in_8, ds.manual.interpreter.VState lifted_in_9)
  { 
    this.specializeChildren(0);
    final INodeList<I_Def> lifted_1 = _1;
    final I_Strategy lifted_2 = _2;
    final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_1;
    final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_2;
    final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_3;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_4;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_5;
    final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
    final boolean bool_1 = lifted_in_8;
    final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
    if(lifted_1 != null && lifted_1.isEmpty())
    { 
      final I_Strategy in_s = lifted_2;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> d = senv_1;
      final default_Result tmpresult103 = in_s.exec_default(d, e, ic_1, tf_1, t_1, trace_1, sheap_1, bool_1, vheap_1);
      final AValue v = tmpresult103.value;
      final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult103.get_1();
      final ds.manual.interpreter.SState sheap_2 = tmpresult103.get_2();
      final boolean bool_2 = tmpresult103.get_3();
      final ds.manual.interpreter.VState vheap_2 = tmpresult103.get_4();
      final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_2;
      final ds.manual.interpreter.SState lifted_4 = sheap_2;
      final boolean lifted_5 = bool_2;
      final ds.manual.interpreter.VState lifted_6 = vheap_2;
      final AValue lifted_out_1 = v;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
      final boolean lifted_out_4 = lifted_5;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
      final leteval_Result leteval_Result0 = new leteval_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return leteval_Result0;
    }
    else
    { 
      if(lifted_1 != null && !lifted_1.isEmpty())
      { 
        final I_Def sdef = lifted_1.head();
        final INodeList<I_Def> sdefs = lifted_1.tail();
        final I_Strategy in_s = lifted_2;
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> d = senv_1;
        final SDefT_4 tmpbuild518 = sdef.match(SDefT_4.class);
        if(tmpbuild518 != null)
        { 
          final String sname = tmpbuild518.get_1();
          final INodeList<I_Typedid> ss = tmpbuild518.get_2();
          final INodeList<I_Typedid> ts = tmpbuild518.get_3();
          final I_Strategy s = tmpbuild518.get_4();
          final typedIds_1 tmpbuild517 = new typedIds_1(getSourceInfo(), ss);
          final I_NameExtractor lifted_7 = tmpbuild517;
          final exid_Result tmpresult107 = lifted_7.exec_exid();
          final INodeList<String> ss_ = tmpresult107.value;
          final typedIds_1 tmpbuild516 = new typedIds_1(getSourceInfo(), ts);
          final I_NameExtractor lifted_8 = tmpbuild516;
          final exid_Result tmpresult106 = lifted_8.exec_exid();
          final INodeList<String> ts_ = tmpresult106.value;
          final Thunk_6 tmpbuild515 = new Thunk_6(getSourceInfo(), sname, ss_, ts_, s, e, d);
          final SUpdate_3 tmpbuild514 = new SUpdate_3(getSourceInfo(), d, sname, tmpbuild515);
          final I_SHeapOp lifted_9 = tmpbuild514;
          final salloc_Result tmpresult105 = lifted_9.exec_salloc(sheap_1);
          final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_ = tmpresult105.value;
          final ds.manual.interpreter.SState sheap_2 = tmpresult105.get_1();
          final letEval_2 tmpbuild513 = new letEval_2(getSourceInfo(), sdefs, in_s);
          final I_LetHelper lifted_10 = tmpbuild513;
          final leteval_Result tmpresult104 = lifted_10.exec_leteval(ic_1, tf_1, t_1, e, d, trace_1, sheap_2, bool_1, vheap_1);
          final AValue v = tmpresult104.value;
          final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult104.get_1();
          final ds.manual.interpreter.SState sheap_3 = tmpresult104.get_2();
          final boolean bool_2 = tmpresult104.get_3();
          final ds.manual.interpreter.VState vheap_2 = tmpresult104.get_4();
          final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_2;
          final ds.manual.interpreter.SState lifted_4 = sheap_3;
          final boolean lifted_5 = bool_2;
          final ds.manual.interpreter.VState lifted_6 = vheap_2;
          final AValue lifted_out_1 = v;
          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
          final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
          final boolean lifted_out_4 = lifted_5;
          final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
          final leteval_Result leteval_Result0 = new leteval_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
          return leteval_Result0;
        }
      }
    }
    throw new InterpreterException("Rule failure");
  }

  public INodeList<I_Def> get_1()
  { 
    if(this._1 instanceof IGenericNode)
    { 
      ((IGenericNode)this._1).specialize(1);
    }
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
    final letEval_2 other = (letEval_2)obj;
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