package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class bAppl_1 extends NoOpNode implements I_Builder
{ 
  @Child public I_PreTerm _1;

  public bAppl_1 (INodeSource source, I_PreTerm _1) 
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

  public bld_Result exec_bld(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, ds.manual.interpreter.AutoInterpInteropContext lifted_in_2, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_3, org.spoofax.interpreter.terms.ITermFactory lifted_in_4, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, ds.manual.interpreter.SState lifted_in_7, boolean lifted_in_8, ds.manual.interpreter.VState lifted_in_9)
  { 
    this.specializeChildren(0);
    final I_PreTerm lifted_1 = _1;
    { 
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
      final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_2;
      final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_3;
      final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_4;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_5;
      final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
      final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
      final boolean bool_1 = lifted_in_8;
      final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
      final Op_2 tmpbuild405 = lifted_1.match(Op_2.class);
      if(tmpbuild405 != null)
      { 
        final String c = tmpbuild405.get_1();
        final INodeList<I_STerm> ts = tmpbuild405.get_2();
        final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
        final int tmpbuild404 = ds.manual.interpreter.Natives.length_1(ts);
        final int numts = tmpbuild404;
        final org.spoofax.interpreter.terms.IStrategoConstructor tmpbuild403 = tf.makeConstructor(c, numts);
        final org.spoofax.interpreter.terms.IStrategoConstructor constr = tmpbuild403;
        final bl_1 tmpbuild402 = new bl_1(getSourceInfo(), ts);
        final I_Builder lifted_8 = tmpbuild402;
        final blds_Result tmpresult77 = lifted_8.exec_blds(senv_1, venv_1, ic_1, t_1, tf_1, trace_1, sheap_1, bool_1, vheap_1);
        final I_BuildRes lifted_7 = tmpresult77.value;
        final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult77.get_1();
        final ds.manual.interpreter.SState sheap_2 = tmpresult77.get_2();
        final boolean bool_2 = tmpresult77.get_3();
        final ds.manual.interpreter.VState vheap_2 = tmpresult77.get_4();
        final BSS_1 tmpbuild401 = lifted_7.match(BSS_1.class);
        if(tmpbuild401 != null)
        { 
          final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> ts_ = tmpbuild401.get_1();
          final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild400 = ds.manual.interpreter.Natives.List2TARRAY_1(ts_);
          final org.spoofax.interpreter.terms.IStrategoTerm[] ts__ = tmpbuild400;
          final org.spoofax.interpreter.terms.IStrategoAppl tmpbuild399 = tf.makeAppl(constr, ts__);
          final org.spoofax.interpreter.terms.IStrategoAppl appl = tmpbuild399;
          final BS_1 tmpbuild398 = new BS_1(getSourceInfo(), appl);
          final I_BuildRes lifted_2 = tmpbuild398;
          final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_2;
          final ds.manual.interpreter.SState lifted_4 = sheap_2;
          final boolean lifted_5 = bool_2;
          final ds.manual.interpreter.VState lifted_6 = vheap_2;
          final I_BuildRes lifted_out_1 = lifted_2;
          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
          final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
          final boolean lifted_out_4 = lifted_5;
          final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
          final bld_Result bld_Result1 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
          return bld_Result1;
        }
      }
    }
    { 
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
      final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_2;
      final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_3;
      final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_4;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_5;
      final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
      final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
      final boolean bool_1 = lifted_in_8;
      final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
      final I_PreTerm t = lifted_1;
      final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
      final BF_0 tmpbuild406 = new BF_0(getSourceInfo());
      final I_BuildRes lifted_2 = tmpbuild406;
      final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
      final ds.manual.interpreter.SState lifted_4 = sheap_1;
      final boolean lifted_5 = bool_1;
      final ds.manual.interpreter.VState lifted_6 = vheap_1;
      final I_BuildRes lifted_out_1 = lifted_2;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
      final boolean lifted_out_4 = lifted_5;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
      final bld_Result bld_Result1 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return bld_Result1;
    }
  }

  public I_PreTerm get_1()
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
    final bAppl_1 other = (bAppl_1)obj;
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