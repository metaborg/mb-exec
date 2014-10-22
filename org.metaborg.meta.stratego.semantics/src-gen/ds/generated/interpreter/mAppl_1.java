package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class mAppl_1 extends NoOpNode implements I_Matcher
{ 
  @Child public I_STerm _1;

  public mAppl_1 (INodeSource source, I_STerm _1) 
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

  public ma_Result exec_ma(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, ds.manual.interpreter.AutoInterpInteropContext lifted_in_2, org.spoofax.interpreter.terms.ITermFactory lifted_in_3, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_4, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, ds.manual.interpreter.SState lifted_in_7, boolean lifted_in_8, ds.manual.interpreter.VState lifted_in_9)
  { 
    this.specializeChildren(0);
    final I_STerm lifted_1 = _1;
    { 
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
      final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_2;
      final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_3;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_4;
      final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_5;
      final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
      final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
      final boolean bool_1 = lifted_in_8;
      final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
      final Op_2 tmpbuild306 = lifted_1.match(Op_2.class);
      if(tmpbuild306 != null)
      { 
        final String c = tmpbuild306.get_1();
        final INodeList<I_STerm> pts = tmpbuild306.get_2();
        final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
        final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
        final boolean tmpbuild305 = ds.manual.interpreter.Natives.isATermAppl_1(t);
        if(tmpbuild305 == true)
        { 
          final org.spoofax.interpreter.terms.IStrategoAppl tmpbuild304 = ds.manual.interpreter.Natives.asATermAppl_1(t);
          final org.spoofax.interpreter.terms.IStrategoAppl aappl = tmpbuild304;
          final org.spoofax.interpreter.terms.IStrategoConstructor tmpbuild303 = aappl.getConstructor();
          final org.spoofax.interpreter.terms.IStrategoConstructor aconstr = tmpbuild303;
          final String tmpbuild302 = aconstr.getName();
          final String c_ = tmpbuild302;
          if(c != null && c.equals(c_))
          { 
            final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild301 = aappl.getAllSubterms();
            final org.spoofax.interpreter.terms.IStrategoTerm[] subterms = tmpbuild301;
            final org.spoofax.interpreter.terms.IStrategoList tmpbuild300 = tf.makeList(subterms);
            final org.spoofax.interpreter.terms.IStrategoList asubterms = tmpbuild300;
            final ml_1 tmpbuild299 = new ml_1(getSourceInfo(), pts);
            final I_Matcher lifted_8 = tmpbuild299;
            final ma_Result tmpresult63 = lifted_8.exec_ma(senv_1, ic_1, tf_1, venv_1, asubterms, trace_1, sheap_1, bool_1, vheap_1);
            final AValue lifted_7 = tmpresult63.value;
            final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult63.get_1();
            final ds.manual.interpreter.SState sheap_2 = tmpresult63.get_2();
            final boolean bool_2 = tmpresult63.get_3();
            final ds.manual.interpreter.VState vheap_2 = tmpresult63.get_4();
            final S_1 tmpbuild298 = lifted_7.match(S_1.class);
            if(tmpbuild298 != null)
            { 
              final org.spoofax.interpreter.terms.IStrategoTerm aats_ = tmpbuild298.get_1();
              final S_1 tmpbuild297 = new S_1(t);
              final AValue lifted_2 = tmpbuild297;
              final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_2;
              final ds.manual.interpreter.SState lifted_4 = sheap_2;
              final boolean lifted_5 = bool_2;
              final ds.manual.interpreter.VState lifted_6 = vheap_2;
              final AValue lifted_out_1 = lifted_2;
              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
              final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
              final boolean lifted_out_4 = lifted_5;
              final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
              final ma_Result ma_Result2 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
              return ma_Result2;
            }
          }
        }
      }
    }
    { 
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
      final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_2;
      final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_3;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_4;
      final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_5;
      final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
      final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
      final boolean bool_1 = lifted_in_8;
      final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
      final I_STerm dc = lifted_1;
      final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
      final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
      final F_0 tmpbuild307 = new F_0();
      final AValue lifted_2 = tmpbuild307;
      final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
      final ds.manual.interpreter.SState lifted_4 = sheap_1;
      final boolean lifted_5 = bool_1;
      final ds.manual.interpreter.VState lifted_6 = vheap_1;
      final AValue lifted_out_1 = lifted_2;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
      final boolean lifted_out_4 = lifted_5;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
      final ma_Result ma_Result2 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return ma_Result2;
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
    final mAppl_1 other = (mAppl_1)obj;
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