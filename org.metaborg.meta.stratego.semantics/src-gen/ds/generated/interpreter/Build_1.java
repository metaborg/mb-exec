package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Build_1 extends NoOpNode implements I_Strategy
{ 
  @Child public I_STerm _1;

  public Build_1 (INodeSource source, I_STerm _1) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChild(_1);
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
      hasSpecialized = true;
    }
  }

  public default_Result exec_default(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_2, ds.manual.interpreter.AutoInterpInteropContext lifted_in_3, org.spoofax.interpreter.terms.ITermFactory lifted_in_4, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, ds.manual.interpreter.SState lifted_in_7, boolean lifted_in_8, ds.manual.interpreter.VState lifted_in_9)
  { 
    this.specializeChildren(0);
    final I_STerm lifted_1 = _1;
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
      final Anno_2 tmpbuild110 = lifted_1.match(Anno_2.class);
      if(tmpbuild110 != null)
      { 
        final I_PreTerm t = tmpbuild110.get_1();
        final I_PreTerm annos = tmpbuild110.get_2();
        final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
        final b_1 tmpbuild118 = new b_1(getSourceInfo(), t);
        final I_Builder lifted_8 = tmpbuild118;
        final bld_Result tmpresult22 = lifted_8.exec_bld(senv_1, ic_1, t_1, tf_1, venv_1, trace_1, sheap_1, bool_1, vheap_1);
        final I_BuildRes lifted_7 = tmpresult22.value;
        final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult22.get_1();
        final ds.manual.interpreter.SState sheap_2 = tmpresult22.get_2();
        final boolean bool_2 = tmpresult22.get_3();
        final ds.manual.interpreter.VState vheap_2 = tmpresult22.get_4();
        final BS_1 tmpbuild117 = lifted_7.match(BS_1.class);
        if(tmpbuild117 != null)
        { 
          final org.spoofax.interpreter.terms.IStrategoTerm at = tmpbuild117.get_1();
          final b_1 tmpbuild116 = new b_1(getSourceInfo(), annos);
          final I_Builder lifted_10 = tmpbuild116;
          final bld_Result tmpresult21 = lifted_10.exec_bld(senv_1, ic_1, t_1, tf_1, venv_1, trace_2, sheap_2, bool_2, vheap_2);
          final I_BuildRes lifted_9 = tmpresult21.value;
          final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult21.get_1();
          final ds.manual.interpreter.SState sheap_3 = tmpresult21.get_2();
          final boolean bool_3 = tmpresult21.get_3();
          final ds.manual.interpreter.VState vheap_3 = tmpresult21.get_4();
          final BS_1 tmpbuild115 = lifted_9.match(BS_1.class);
          if(tmpbuild115 != null)
          { 
            final org.spoofax.interpreter.terms.IStrategoTerm annos_aterm = tmpbuild115.get_1();
            final boolean tmpbuild114 = ds.manual.interpreter.Natives.isATermList_1(annos_aterm);
            if(tmpbuild114 == true)
            { 
              final org.spoofax.interpreter.terms.IStrategoList tmpbuild113 = ds.manual.interpreter.Natives.asATermList_1(annos_aterm);
              final org.spoofax.interpreter.terms.IStrategoList annos_aterm_list = tmpbuild113;
              final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild112 = tf.annotateTerm(at, annos_aterm_list);
              final org.spoofax.interpreter.terms.IStrategoTerm aterm = tmpbuild112;
              final S_1 tmpbuild111 = new S_1(aterm);
              final AValue lifted_2 = tmpbuild111;
              final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
              final ds.manual.interpreter.SState lifted_4 = sheap_3;
              final boolean lifted_5 = bool_3;
              final ds.manual.interpreter.VState lifted_6 = vheap_3;
              final AValue lifted_out_1 = lifted_2;
              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
              final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
              final boolean lifted_out_4 = lifted_5;
              final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
              final default_Result default_Result8 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
              return default_Result8;
            }
          }
        }
      }
      else
      { 
        final Var_1 tmpbuild123 = lifted_1.match(Var_1.class);
        if(tmpbuild123 != null)
        { 
          final String x = tmpbuild123.get_1();
          final Var_1 tmpbuild122 = new Var_1(getSourceInfo(), x);
          final b_1 tmpbuild121 = new b_1(getSourceInfo(), tmpbuild122);
          final I_Builder lifted_8 = tmpbuild121;
          final bld_Result tmpresult23 = lifted_8.exec_bld(senv_1, ic_1, t_1, tf_1, venv_1, trace_1, sheap_1, bool_1, vheap_1);
          final I_BuildRes lifted_7 = tmpresult23.value;
          final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult23.get_1();
          final ds.manual.interpreter.SState sheap_2 = tmpresult23.get_2();
          final boolean bool_2 = tmpresult23.get_3();
          final ds.manual.interpreter.VState vheap_2 = tmpresult23.get_4();
          final BS_1 tmpbuild120 = lifted_7.match(BS_1.class);
          if(tmpbuild120 != null)
          { 
            final org.spoofax.interpreter.terms.IStrategoTerm t = tmpbuild120.get_1();
            final S_1 tmpbuild119 = new S_1(t);
            final AValue lifted_2 = tmpbuild119;
            final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_2;
            final ds.manual.interpreter.SState lifted_4 = sheap_2;
            final boolean lifted_5 = bool_2;
            final ds.manual.interpreter.VState lifted_6 = vheap_2;
            final AValue lifted_out_1 = lifted_2;
            final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
            final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
            final boolean lifted_out_4 = lifted_5;
            final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
            final default_Result default_Result8 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
            return default_Result8;
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
      final I_STerm e = lifted_1;
      final F_0 tmpbuild124 = new F_0();
      final AValue lifted_2 = tmpbuild124;
      final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
      final ds.manual.interpreter.SState lifted_4 = sheap_1;
      final boolean lifted_5 = bool_1;
      final ds.manual.interpreter.VState lifted_6 = vheap_1;
      final AValue lifted_out_1 = lifted_2;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
      final boolean lifted_out_4 = lifted_5;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
      final default_Result default_Result8 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return default_Result8;
    }
  }

  public I_STerm get_1()
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
    final Build_1 other = (Build_1)obj;
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