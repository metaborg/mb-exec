package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class All_1 extends NoOpNode implements I_Strategy
{ 
  @Child public I_Strategy _1;

  public All_1 (INodeSource source, I_Strategy _1) 
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
      final boolean tmpbuild2 = ds.manual.interpreter.Natives.isATermInt_1(t);
      if(tmpbuild2 == true)
      { 
        final S_1 tmpbuild3 = new S_1(t);
        final AValue lifted_2 = tmpbuild3;
        final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
        final ds.manual.interpreter.SState lifted_4 = sheap_1;
        final boolean lifted_5 = bool_1;
        final ds.manual.interpreter.VState lifted_6 = vheap_1;
        final AValue lifted_out_1 = lifted_2;
        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
        final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
        final boolean lifted_out_4 = lifted_5;
        final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
        final default_Result default_Result1 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
        return default_Result1;
      }
      else
      { 
        final boolean tmpbuild4 = ds.manual.interpreter.Natives.isATermReal_1(t);
        if(tmpbuild4 == true)
        { 
          final S_1 tmpbuild5 = new S_1(t);
          final AValue lifted_2 = tmpbuild5;
          final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
          final ds.manual.interpreter.SState lifted_4 = sheap_1;
          final boolean lifted_5 = bool_1;
          final ds.manual.interpreter.VState lifted_6 = vheap_1;
          final AValue lifted_out_1 = lifted_2;
          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
          final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
          final boolean lifted_out_4 = lifted_5;
          final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
          final default_Result default_Result1 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
          return default_Result1;
        }
        else
        { 
          final boolean tmpbuild6 = ds.manual.interpreter.Natives.isATermString_1(t);
          if(tmpbuild6 == true)
          { 
            final S_1 tmpbuild7 = new S_1(t);
            final AValue lifted_2 = tmpbuild7;
            final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
            final ds.manual.interpreter.SState lifted_4 = sheap_1;
            final boolean lifted_5 = bool_1;
            final ds.manual.interpreter.VState lifted_6 = vheap_1;
            final AValue lifted_out_1 = lifted_2;
            final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
            final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
            final boolean lifted_out_4 = lifted_5;
            final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
            final default_Result default_Result1 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
            return default_Result1;
          }
          else
          { 
            final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
            final boolean tmpbuild8 = ds.manual.interpreter.Natives.isATermTuple_1(t);
            if(tmpbuild8 == true)
            { 
              final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild19 = t.getAllSubterms();
              final org.spoofax.interpreter.terms.IStrategoTerm[] ts = tmpbuild19;
              final org.spoofax.interpreter.terms.IStrategoList tmpbuild18 = tf.makeList(ts);
              final org.spoofax.interpreter.terms.IStrategoList ats = tmpbuild18;
              final map_1 tmpbuild17 = new map_1(getSourceInfo(), s);
              final I_Mapper lifted_7 = tmpbuild17;
              final map_Result tmpresult2 = lifted_7.exec_map(senv_1, venv_1, ic_1, ats, tf_1, trace_1, sheap_1, bool_1, vheap_1);
              final AValue lifted_6 = tmpresult2.value;
              final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult2.get_1();
              final ds.manual.interpreter.SState sheap_2 = tmpresult2.get_2();
              final boolean bool_2 = tmpresult2.get_3();
              final ds.manual.interpreter.VState vheap_2 = tmpresult2.get_4();
              final S_1 tmpbuild16 = lifted_6.match(S_1.class);
              if(tmpbuild16 != null)
              { 
                final org.spoofax.interpreter.terms.IStrategoTerm ats_ = tmpbuild16.get_1();
                final boolean tmpbuild15 = ds.manual.interpreter.Natives.isATermList_1(ats_);
                if(tmpbuild15 == true)
                { 
                  final org.spoofax.interpreter.terms.IStrategoList tmpbuild14 = ds.manual.interpreter.Natives.asATermList_1(ats_);
                  final org.spoofax.interpreter.terms.IStrategoList ats__ = tmpbuild14;
                  final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild13 = ats_.getAllSubterms();
                  final org.spoofax.interpreter.terms.IStrategoTerm[] ts_ = tmpbuild13;
                  final org.spoofax.interpreter.terms.IStrategoTuple tmpbuild12 = tf.makeTuple(ts_);
                  final org.spoofax.interpreter.terms.IStrategoTuple t_ = tmpbuild12;
                  final org.spoofax.interpreter.terms.IStrategoList tmpbuild11 = t.getAnnotations();
                  final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild10 = tf.annotateTerm(t_, tmpbuild11);
                  final org.spoofax.interpreter.terms.IStrategoTerm t__ = tmpbuild10;
                  final S_1 tmpbuild9 = new S_1(t__);
                  final AValue v = tmpbuild9;
                  final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                  final ds.manual.interpreter.SState lifted_3 = sheap_2;
                  final boolean lifted_4 = bool_2;
                  final ds.manual.interpreter.VState lifted_5 = vheap_2;
                  final AValue lifted_out_1 = v;
                  final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                  final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                  final boolean lifted_out_4 = lifted_4;
                  final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                  final default_Result default_Result1 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                  return default_Result1;
                }
              }
            }
            else
            { 
              final boolean tmpbuild20 = ds.manual.interpreter.Natives.isATermList_1(t);
              if(tmpbuild20 == true)
              { 
                final org.spoofax.interpreter.terms.IStrategoList tmpbuild26 = ds.manual.interpreter.Natives.asATermList_1(t);
                final org.spoofax.interpreter.terms.IStrategoList tlist = tmpbuild26;
                final map_1 tmpbuild25 = new map_1(getSourceInfo(), s);
                final I_Mapper lifted_7 = tmpbuild25;
                final map_Result tmpresult3 = lifted_7.exec_map(senv_1, venv_1, ic_1, tlist, tf_1, trace_1, sheap_1, bool_1, vheap_1);
                final AValue lifted_6 = tmpresult3.value;
                final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult3.get_1();
                final ds.manual.interpreter.SState sheap_2 = tmpresult3.get_2();
                final boolean bool_2 = tmpresult3.get_3();
                final ds.manual.interpreter.VState vheap_2 = tmpresult3.get_4();
                final S_1 tmpbuild24 = lifted_6.match(S_1.class);
                if(tmpbuild24 != null)
                { 
                  final org.spoofax.interpreter.terms.IStrategoTerm ats_ = tmpbuild24.get_1();
                  final org.spoofax.interpreter.terms.IStrategoList tmpbuild23 = t.getAnnotations();
                  final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild22 = tf.annotateTerm(ats_, tmpbuild23);
                  final org.spoofax.interpreter.terms.IStrategoTerm ats__ = tmpbuild22;
                  final S_1 tmpbuild21 = new S_1(ats__);
                  final AValue v = tmpbuild21;
                  final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                  final ds.manual.interpreter.SState lifted_3 = sheap_2;
                  final boolean lifted_4 = bool_2;
                  final ds.manual.interpreter.VState lifted_5 = vheap_2;
                  final AValue lifted_out_1 = v;
                  final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                  final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                  final boolean lifted_out_4 = lifted_4;
                  final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                  final default_Result default_Result1 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                  return default_Result1;
                }
              }
              else
              { 
                final boolean tmpbuild40 = ds.manual.interpreter.Natives.isATermAppl_1(t);
                if(tmpbuild40 == true)
                { 
                  final org.spoofax.interpreter.terms.IStrategoAppl tmpbuild39 = ds.manual.interpreter.Natives.asATermAppl_1(t);
                  final org.spoofax.interpreter.terms.IStrategoAppl at = tmpbuild39;
                  final org.spoofax.interpreter.terms.IStrategoConstructor tmpbuild38 = at.getConstructor();
                  final org.spoofax.interpreter.terms.IStrategoConstructor ac = tmpbuild38;
                  final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild37 = at.getAllSubterms();
                  final org.spoofax.interpreter.terms.IStrategoTerm[] ts = tmpbuild37;
                  final org.spoofax.interpreter.terms.IStrategoList tmpbuild36 = tf.makeList(ts);
                  final org.spoofax.interpreter.terms.IStrategoList ats = tmpbuild36;
                  final map_1 tmpbuild35 = new map_1(getSourceInfo(), s);
                  final I_Mapper lifted_7 = tmpbuild35;
                  final map_Result tmpresult4 = lifted_7.exec_map(senv_1, venv_1, ic_1, ats, tf_1, trace_1, sheap_1, bool_1, vheap_1);
                  final AValue lifted_6 = tmpresult4.value;
                  final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult4.get_1();
                  final ds.manual.interpreter.SState sheap_2 = tmpresult4.get_2();
                  final boolean bool_2 = tmpresult4.get_3();
                  final ds.manual.interpreter.VState vheap_2 = tmpresult4.get_4();
                  final S_1 tmpbuild34 = lifted_6.match(S_1.class);
                  if(tmpbuild34 != null)
                  { 
                    final org.spoofax.interpreter.terms.IStrategoTerm ats_ = tmpbuild34.get_1();
                    final boolean tmpbuild33 = ds.manual.interpreter.Natives.isATermList_1(ats_);
                    if(tmpbuild33 == true)
                    { 
                      final org.spoofax.interpreter.terms.IStrategoList tmpbuild32 = ds.manual.interpreter.Natives.asATermList_1(ats_);
                      final org.spoofax.interpreter.terms.IStrategoList ats__ = tmpbuild32;
                      final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild31 = ats__.getAllSubterms();
                      final org.spoofax.interpreter.terms.IStrategoTerm[] ts_ = tmpbuild31;
                      final org.spoofax.interpreter.terms.IStrategoAppl tmpbuild30 = tf.makeAppl(ac, ts_);
                      final org.spoofax.interpreter.terms.IStrategoAppl t_ = tmpbuild30;
                      final org.spoofax.interpreter.terms.IStrategoList tmpbuild29 = t.getAnnotations();
                      final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild28 = tf.annotateTerm(t_, tmpbuild29);
                      final org.spoofax.interpreter.terms.IStrategoTerm t__ = tmpbuild28;
                      final S_1 tmpbuild27 = new S_1(t__);
                      final AValue v = tmpbuild27;
                      final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                      final ds.manual.interpreter.SState lifted_3 = sheap_2;
                      final boolean lifted_4 = bool_2;
                      final ds.manual.interpreter.VState lifted_5 = vheap_2;
                      final AValue lifted_out_1 = v;
                      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                      final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                      final boolean lifted_out_4 = lifted_4;
                      final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                      final default_Result default_Result1 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                      return default_Result1;
                    }
                  }
                }
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
      final F_0 tmpbuild41 = new F_0();
      final AValue lifted_2 = tmpbuild41;
      final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
      final ds.manual.interpreter.SState lifted_4 = sheap_1;
      final boolean lifted_5 = bool_1;
      final ds.manual.interpreter.VState lifted_6 = vheap_1;
      final AValue lifted_out_1 = lifted_2;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
      final boolean lifted_out_4 = lifted_5;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
      final default_Result default_Result1 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return default_Result1;
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
    final All_1 other = (All_1)obj;
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