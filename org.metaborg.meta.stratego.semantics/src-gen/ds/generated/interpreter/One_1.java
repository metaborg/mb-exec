package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class One_1 extends NoOpNode implements I_Strategy
{ 
  @Child public I_Strategy _1;

  public One_1 (INodeSource source, I_Strategy _1) 
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
    final I_Strategy lifted_1 = _1;
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
      final I_Strategy s = lifted_1;
      final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
      final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
      final boolean tmpbuild42 = ds.manual.interpreter.Natives.isATermTuple_1(t);
      if(tmpbuild42 == true)
      { 
        final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild49 = t.getAllSubterms();
        final org.spoofax.interpreter.terms.IStrategoTerm[] ts = tmpbuild49;
        final org.spoofax.interpreter.terms.IStrategoList tmpbuild48 = tf.makeList(ts);
        final org.spoofax.interpreter.terms.IStrategoList ats = tmpbuild48;
        final first_1 tmpbuild47 = new first_1(getSourceInfo(), s);
        final I_Firster lifted_7 = tmpbuild47;
        final first_Result tmpresult5 = lifted_7.exec_first(senv_1, venv_1, ic_1, ats, tf_1, trace_1, sheap_1, bool_1, vheap_1);
        final AValue lifted_6 = tmpresult5.value;
        final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult5.get_1();
        final ds.manual.interpreter.SState sheap_2 = tmpresult5.get_2();
        final boolean bool_2 = tmpresult5.get_3();
        final ds.manual.interpreter.VState vheap_2 = tmpresult5.get_4();
        final S_1 tmpbuild46 = lifted_6.match(S_1.class);
        if(tmpbuild46 != null)
        { 
          final org.spoofax.interpreter.terms.IStrategoTerm t_ = tmpbuild46.get_1();
          final org.spoofax.interpreter.terms.IStrategoList tmpbuild45 = t.getAnnotations();
          final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild44 = tf.annotateTerm(t_, tmpbuild45);
          final org.spoofax.interpreter.terms.IStrategoTerm t__ = tmpbuild44;
          final S_1 tmpbuild43 = new S_1(t__);
          final AValue v = tmpbuild43;
          final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
          final ds.manual.interpreter.SState lifted_3 = sheap_2;
          final boolean lifted_4 = bool_2;
          final ds.manual.interpreter.VState lifted_5 = vheap_2;
          final AValue lifted_out_1 = v;
          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
          final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
          final boolean lifted_out_4 = lifted_4;
          final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
          final default_Result default_Result2 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
          return default_Result2;
        }
      }
      else
      { 
        final boolean tmpbuild50 = ds.manual.interpreter.Natives.isATermList_1(t);
        if(tmpbuild50 == true)
        { 
          final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild57 = t.getAllSubterms();
          final org.spoofax.interpreter.terms.IStrategoTerm[] ts = tmpbuild57;
          final org.spoofax.interpreter.terms.IStrategoList tmpbuild56 = tf.makeList(ts);
          final org.spoofax.interpreter.terms.IStrategoList ats = tmpbuild56;
          final first_1 tmpbuild55 = new first_1(getSourceInfo(), s);
          final I_Firster lifted_7 = tmpbuild55;
          final first_Result tmpresult6 = lifted_7.exec_first(senv_1, venv_1, ic_1, ats, tf_1, trace_1, sheap_1, bool_1, vheap_1);
          final AValue lifted_6 = tmpresult6.value;
          final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult6.get_1();
          final ds.manual.interpreter.SState sheap_2 = tmpresult6.get_2();
          final boolean bool_2 = tmpresult6.get_3();
          final ds.manual.interpreter.VState vheap_2 = tmpresult6.get_4();
          final S_1 tmpbuild54 = lifted_6.match(S_1.class);
          if(tmpbuild54 != null)
          { 
            final org.spoofax.interpreter.terms.IStrategoTerm ats_ = tmpbuild54.get_1();
            final org.spoofax.interpreter.terms.IStrategoList tmpbuild53 = t.getAnnotations();
            final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild52 = tf.annotateTerm(ats_, tmpbuild53);
            final org.spoofax.interpreter.terms.IStrategoTerm ats__ = tmpbuild52;
            final S_1 tmpbuild51 = new S_1(ats__);
            final AValue v = tmpbuild51;
            final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
            final ds.manual.interpreter.SState lifted_3 = sheap_2;
            final boolean lifted_4 = bool_2;
            final ds.manual.interpreter.VState lifted_5 = vheap_2;
            final AValue lifted_out_1 = v;
            final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
            final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
            final boolean lifted_out_4 = lifted_4;
            final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
            final default_Result default_Result2 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
            return default_Result2;
          }
        }
        else
        { 
          final boolean tmpbuild70 = ds.manual.interpreter.Natives.isATermAppl_1(t);
          if(tmpbuild70 == true)
          { 
            final org.spoofax.interpreter.terms.IStrategoAppl tmpbuild69 = ds.manual.interpreter.Natives.asATermAppl_1(t);
            final org.spoofax.interpreter.terms.IStrategoAppl at = tmpbuild69;
            final org.spoofax.interpreter.terms.IStrategoConstructor tmpbuild68 = at.getConstructor();
            final org.spoofax.interpreter.terms.IStrategoConstructor ac = tmpbuild68;
            final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild67 = at.getAllSubterms();
            final org.spoofax.interpreter.terms.IStrategoTerm[] ts = tmpbuild67;
            final org.spoofax.interpreter.terms.IStrategoList tmpbuild66 = tf.makeList(ts);
            final org.spoofax.interpreter.terms.IStrategoList ats = tmpbuild66;
            final first_1 tmpbuild65 = new first_1(getSourceInfo(), s);
            final I_Firster lifted_7 = tmpbuild65;
            final first_Result tmpresult7 = lifted_7.exec_first(senv_1, venv_1, ic_1, ats, tf_1, trace_1, sheap_1, bool_1, vheap_1);
            final AValue lifted_6 = tmpresult7.value;
            final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult7.get_1();
            final ds.manual.interpreter.SState sheap_2 = tmpresult7.get_2();
            final boolean bool_2 = tmpresult7.get_3();
            final ds.manual.interpreter.VState vheap_2 = tmpresult7.get_4();
            final S_1 tmpbuild64 = lifted_6.match(S_1.class);
            if(tmpbuild64 != null)
            { 
              final org.spoofax.interpreter.terms.IStrategoTerm ats_ = tmpbuild64.get_1();
              final boolean tmpbuild63 = ds.manual.interpreter.Natives.isATermList_1(ats_);
              if(tmpbuild63 == true)
              { 
                final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild62 = ats_.getAllSubterms();
                final org.spoofax.interpreter.terms.IStrategoTerm[] ts_ = tmpbuild62;
                final org.spoofax.interpreter.terms.IStrategoAppl tmpbuild61 = tf.makeAppl(ac, ts_);
                final org.spoofax.interpreter.terms.IStrategoAppl t_ = tmpbuild61;
                final org.spoofax.interpreter.terms.IStrategoList tmpbuild60 = t.getAnnotations();
                final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild59 = tf.annotateTerm(t_, tmpbuild60);
                final org.spoofax.interpreter.terms.IStrategoTerm t__ = tmpbuild59;
                final S_1 tmpbuild58 = new S_1(t__);
                final AValue v = tmpbuild58;
                final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                final ds.manual.interpreter.SState lifted_3 = sheap_2;
                final boolean lifted_4 = bool_2;
                final ds.manual.interpreter.VState lifted_5 = vheap_2;
                final AValue lifted_out_1 = v;
                final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                final boolean lifted_out_4 = lifted_4;
                final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                final default_Result default_Result2 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                return default_Result2;
              }
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
      final I_Strategy s = lifted_1;
      final F_0 tmpbuild71 = new F_0();
      final AValue lifted_2 = tmpbuild71;
      final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
      final ds.manual.interpreter.SState lifted_4 = sheap_1;
      final boolean lifted_5 = bool_1;
      final ds.manual.interpreter.VState lifted_6 = vheap_1;
      final AValue lifted_out_1 = lifted_2;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
      final boolean lifted_out_4 = lifted_5;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
      final default_Result default_Result2 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return default_Result2;
    }
  }

  public I_Strategy get_1()
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
    final One_1 other = (One_1)obj;
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