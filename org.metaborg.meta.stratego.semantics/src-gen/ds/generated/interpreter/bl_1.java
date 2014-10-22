package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class bl_1 extends NoOpNode implements I_Builder
{ 
  @Children public INodeList<I_STerm> _1;

  public bl_1 (INodeSource source, INodeList<I_STerm> _1) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChildren(_1);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      for(I_STerm _1_elem : _1)
      { 
        if(_1_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_1_elem).specialize(depth);
        }
      }
      hasSpecialized = true;
    }
  }

  public blds_Result exec_blds(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_2, ds.manual.interpreter.AutoInterpInteropContext lifted_in_3, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_4, org.spoofax.interpreter.terms.ITermFactory lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, ds.manual.interpreter.SState lifted_in_7, boolean lifted_in_8, ds.manual.interpreter.VState lifted_in_9)
  { 
    this.specializeChildren(0);
    final INodeList<I_STerm> lifted_1 = _1;
    { 
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_2;
      final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_3;
      final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_4;
      final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_5;
      final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
      final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
      final boolean bool_1 = lifted_in_8;
      final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
      if(lifted_1 != null && lifted_1.isEmpty())
      { 
        final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
        final INodeList<I_Node> tmpbuild390 = NodeList.NIL(I_Node.class);
        final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> tmpbuild389 = ds.manual.interpreter.Natives.asNILofT_1(tmpbuild390);
        final BSS_1 tmpbuild388 = new BSS_1(getSourceInfo(), tmpbuild389);
        final I_BuildRes lifted_2 = tmpbuild388;
        final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
        final ds.manual.interpreter.SState lifted_4 = sheap_1;
        final boolean lifted_5 = bool_1;
        final ds.manual.interpreter.VState lifted_6 = vheap_1;
        final I_BuildRes lifted_out_1 = lifted_2;
        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
        final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
        final boolean lifted_out_4 = lifted_5;
        final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
        final blds_Result blds_Result0 = new blds_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
        return blds_Result0;
      }
      else
      { 
        if(lifted_1 != null && !lifted_1.isEmpty())
        { 
          final I_STerm texpr = lifted_1.head();
          final INodeList<I_STerm> texprs = lifted_1.tail();
          final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
          final Build_1 tmpbuild396 = new Build_1(getSourceInfo(), texpr);
          final I_Strategy lifted_8 = tmpbuild396;
          final default_Result tmpresult76 = lifted_8.exec_default(senv_1, venv_1, ic_1, tf_1, t_1, trace_1, sheap_1, bool_1, vheap_1);
          final AValue lifted_7 = tmpresult76.value;
          final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult76.get_1();
          final ds.manual.interpreter.SState sheap_2 = tmpresult76.get_2();
          final boolean bool_2 = tmpresult76.get_3();
          final ds.manual.interpreter.VState vheap_2 = tmpresult76.get_4();
          final S_1 tmpbuild395 = lifted_7.match(S_1.class);
          if(tmpbuild395 != null)
          { 
            final org.spoofax.interpreter.terms.IStrategoTerm tx = tmpbuild395.get_1();
            final bl_1 tmpbuild394 = new bl_1(getSourceInfo(), texprs);
            final I_Builder lifted_10 = tmpbuild394;
            final blds_Result tmpresult75 = lifted_10.exec_blds(senv_1, venv_1, ic_1, t_1, tf_1, trace_2, sheap_2, bool_2, vheap_2);
            final I_BuildRes lifted_9 = tmpresult75.value;
            final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult75.get_1();
            final ds.manual.interpreter.SState sheap_3 = tmpresult75.get_2();
            final boolean bool_3 = tmpresult75.get_3();
            final ds.manual.interpreter.VState vheap_3 = tmpresult75.get_4();
            final BSS_1 tmpbuild393 = lifted_9.match(BSS_1.class);
            if(tmpbuild393 != null)
            { 
              final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> txs = tmpbuild393.get_1();
              final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> tmpbuild392 = new NodeList<org.spoofax.interpreter.terms.IStrategoTerm>(tx, txs);
              final BSS_1 tmpbuild391 = new BSS_1(getSourceInfo(), tmpbuild392);
              final I_BuildRes lifted_2 = tmpbuild391;
              final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
              final ds.manual.interpreter.SState lifted_4 = sheap_3;
              final boolean lifted_5 = bool_3;
              final ds.manual.interpreter.VState lifted_6 = vheap_3;
              final I_BuildRes lifted_out_1 = lifted_2;
              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
              final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
              final boolean lifted_out_4 = lifted_5;
              final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
              final blds_Result blds_Result0 = new blds_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
              return blds_Result0;
            }
          }
        }
      }
    }
    { 
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_2;
      final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_3;
      final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_4;
      final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_5;
      final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
      final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
      final boolean bool_1 = lifted_in_8;
      final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
      final INodeList<I_STerm> x = lifted_1;
      final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
      final BF_0 tmpbuild397 = new BF_0(getSourceInfo());
      final I_BuildRes lifted_2 = tmpbuild397;
      final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
      final ds.manual.interpreter.SState lifted_4 = sheap_1;
      final boolean lifted_5 = bool_1;
      final ds.manual.interpreter.VState lifted_6 = vheap_1;
      final I_BuildRes lifted_out_1 = lifted_2;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
      final boolean lifted_out_4 = lifted_5;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
      final blds_Result blds_Result0 = new blds_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return blds_Result0;
    }
  }

  public INodeList<I_STerm> get_1()
  { 
    if(this._1 instanceof IGenericNode)
    { 
      ((IGenericNode)this._1).specialize(1);
    }
    return this._1;
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
    final bl_1 other = (bl_1)obj;
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
    return true;
  }
}