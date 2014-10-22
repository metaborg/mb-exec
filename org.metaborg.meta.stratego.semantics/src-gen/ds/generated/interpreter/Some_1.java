package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Some_1 extends NoOpNode implements I_Strategy
{ 
  @Child public I_Strategy _1;

  public Some_1 (INodeSource source, I_Strategy _1) 
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
      final boolean tmpbuild72 = ds.manual.interpreter.Natives.isATermTuple_1(t);
      if(tmpbuild72 == true)
      { 
        final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild80 = t.getAllSubterms();
        final org.spoofax.interpreter.terms.IStrategoTerm[] ts = tmpbuild80;
        final org.spoofax.interpreter.terms.IStrategoList tmpbuild79 = tf.makeList(ts);
        final org.spoofax.interpreter.terms.IStrategoList ats = tmpbuild79;
        final boolean lifted_6 = false;
        final oneOrMore_1 tmpbuild78 = new oneOrMore_1(getSourceInfo(), s);
        final I_Somer lifted_7 = tmpbuild78;
        final some_Result tmpresult8 = lifted_7.exec_some(senv_1, venv_1, ic_1, ats, tf_1, trace_1, sheap_1, vheap_1, lifted_6);
        final org.spoofax.interpreter.terms.IStrategoList ats_ = tmpresult8.value;
        final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult8.get_1();
        final ds.manual.interpreter.SState sheap_2 = tmpresult8.get_2();
        final ds.manual.interpreter.VState vheap_2 = tmpresult8.get_3();
        final boolean bool_2 = tmpresult8.get_4();
        final boolean f = bool_2;
        if(f == true)
        { 
          final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild77 = ats_.getAllSubterms();
          final org.spoofax.interpreter.terms.IStrategoTerm[] ts_ = tmpbuild77;
          final org.spoofax.interpreter.terms.IStrategoTuple tmpbuild76 = tf.makeTuple(ts_);
          final org.spoofax.interpreter.terms.IStrategoTuple t_ = tmpbuild76;
          final org.spoofax.interpreter.terms.IStrategoList tmpbuild75 = t.getAnnotations();
          final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild74 = tf.annotateTerm(t_, tmpbuild75);
          final org.spoofax.interpreter.terms.IStrategoTerm t__ = tmpbuild74;
          final S_1 tmpbuild73 = new S_1(t__);
          final AValue v = tmpbuild73;
          final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
          final ds.manual.interpreter.SState lifted_3 = sheap_2;
          final boolean lifted_4 = bool_2;
          final ds.manual.interpreter.VState lifted_5 = vheap_2;
          final AValue lifted_out_1 = v;
          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
          final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
          final boolean lifted_out_4 = lifted_4;
          final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
          final default_Result default_Result3 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
          return default_Result3;
        }
      }
      else
      { 
        final boolean tmpbuild81 = ds.manual.interpreter.Natives.isATermList_1(t);
        if(tmpbuild81 == true)
        { 
          final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild87 = t.getAllSubterms();
          final org.spoofax.interpreter.terms.IStrategoTerm[] ts = tmpbuild87;
          final org.spoofax.interpreter.terms.IStrategoList tmpbuild86 = tf.makeList(ts);
          final org.spoofax.interpreter.terms.IStrategoList ats = tmpbuild86;
          final boolean lifted_6 = false;
          final oneOrMore_1 tmpbuild85 = new oneOrMore_1(getSourceInfo(), s);
          final I_Somer lifted_7 = tmpbuild85;
          final some_Result tmpresult9 = lifted_7.exec_some(senv_1, venv_1, ic_1, ats, tf_1, trace_1, sheap_1, vheap_1, lifted_6);
          final org.spoofax.interpreter.terms.IStrategoList ats_ = tmpresult9.value;
          final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult9.get_1();
          final ds.manual.interpreter.SState sheap_2 = tmpresult9.get_2();
          final ds.manual.interpreter.VState vheap_2 = tmpresult9.get_3();
          final boolean bool_2 = tmpresult9.get_4();
          final boolean f = bool_2;
          if(f == true)
          { 
            final org.spoofax.interpreter.terms.IStrategoList tmpbuild84 = t.getAnnotations();
            final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild83 = tf.annotateTerm(ats_, tmpbuild84);
            final org.spoofax.interpreter.terms.IStrategoTerm ats__ = tmpbuild83;
            final S_1 tmpbuild82 = new S_1(ats__);
            final AValue v = tmpbuild82;
            final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
            final ds.manual.interpreter.SState lifted_3 = sheap_2;
            final boolean lifted_4 = bool_2;
            final ds.manual.interpreter.VState lifted_5 = vheap_2;
            final AValue lifted_out_1 = v;
            final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
            final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
            final boolean lifted_out_4 = lifted_4;
            final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
            final default_Result default_Result3 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
            return default_Result3;
          }
        }
        else
        { 
          final boolean tmpbuild98 = ds.manual.interpreter.Natives.isATermAppl_1(t);
          if(tmpbuild98 == true)
          { 
            final org.spoofax.interpreter.terms.IStrategoAppl tmpbuild97 = ds.manual.interpreter.Natives.asATermAppl_1(t);
            final org.spoofax.interpreter.terms.IStrategoAppl at = tmpbuild97;
            final org.spoofax.interpreter.terms.IStrategoConstructor tmpbuild96 = at.getConstructor();
            final org.spoofax.interpreter.terms.IStrategoConstructor ac = tmpbuild96;
            final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild95 = at.getAllSubterms();
            final org.spoofax.interpreter.terms.IStrategoTerm[] ts = tmpbuild95;
            final org.spoofax.interpreter.terms.IStrategoList tmpbuild94 = tf.makeList(ts);
            final org.spoofax.interpreter.terms.IStrategoList ats = tmpbuild94;
            final boolean lifted_6 = false;
            final oneOrMore_1 tmpbuild93 = new oneOrMore_1(getSourceInfo(), s);
            final I_Somer lifted_7 = tmpbuild93;
            final some_Result tmpresult10 = lifted_7.exec_some(senv_1, venv_1, ic_1, ats, tf_1, trace_1, sheap_1, vheap_1, lifted_6);
            final org.spoofax.interpreter.terms.IStrategoList ats_ = tmpresult10.value;
            final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult10.get_1();
            final ds.manual.interpreter.SState sheap_2 = tmpresult10.get_2();
            final ds.manual.interpreter.VState vheap_2 = tmpresult10.get_3();
            final boolean bool_2 = tmpresult10.get_4();
            final boolean f = bool_2;
            if(f == true)
            { 
              final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild92 = ats_.getAllSubterms();
              final org.spoofax.interpreter.terms.IStrategoTerm[] ts_ = tmpbuild92;
              final org.spoofax.interpreter.terms.IStrategoAppl tmpbuild91 = tf.makeAppl(ac, ts_);
              final org.spoofax.interpreter.terms.IStrategoAppl t_ = tmpbuild91;
              final org.spoofax.interpreter.terms.IStrategoList tmpbuild90 = t.getAnnotations();
              final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild89 = tf.annotateTerm(t_, tmpbuild90);
              final org.spoofax.interpreter.terms.IStrategoTerm t__ = tmpbuild89;
              final S_1 tmpbuild88 = new S_1(t__);
              final AValue v = tmpbuild88;
              final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
              final ds.manual.interpreter.SState lifted_3 = sheap_2;
              final boolean lifted_4 = bool_2;
              final ds.manual.interpreter.VState lifted_5 = vheap_2;
              final AValue lifted_out_1 = v;
              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
              final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
              final boolean lifted_out_4 = lifted_4;
              final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
              final default_Result default_Result3 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
              return default_Result3;
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
      final F_0 tmpbuild99 = new F_0();
      final AValue lifted_2 = tmpbuild99;
      final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
      final ds.manual.interpreter.SState lifted_4 = sheap_1;
      final boolean lifted_5 = bool_1;
      final ds.manual.interpreter.VState lifted_6 = vheap_1;
      final AValue lifted_out_1 = lifted_2;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
      final boolean lifted_out_4 = lifted_5;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
      final default_Result default_Result3 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return default_Result3;
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
    final Some_1 other = (Some_1)obj;
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