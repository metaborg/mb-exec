package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class mList_1 extends NoOpNode implements I_Matcher
{ 
  @Child public I_STerm _1;

  public mList_1 (INodeSource source, I_STerm _1) 
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
      final Op_2 tmpbuild322 = lifted_1.match(Op_2.class);
      if(tmpbuild322 != null)
      { 
        final String c = tmpbuild322.get_1();
        final INodeList<I_STerm> pts = tmpbuild322.get_2();
        final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
        final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
        if(pts != null && pts.isEmpty())
        { 
          final boolean tmpbuild311 = ds.manual.interpreter.Natives.isATermList_1(t);
          if(tmpbuild311 == true)
          { 
            final org.spoofax.interpreter.terms.IStrategoList tmpbuild310 = ds.manual.interpreter.Natives.asATermList_1(t);
            final org.spoofax.interpreter.terms.IStrategoList list = tmpbuild310;
            final boolean tmpbuild309 = list.isEmpty();
            if(tmpbuild309 == true)
            { 
              final S_1 tmpbuild308 = new S_1(t);
              final AValue lifted_2 = tmpbuild308;
              final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
              final ds.manual.interpreter.SState lifted_4 = sheap_1;
              final boolean lifted_5 = bool_1;
              final ds.manual.interpreter.VState lifted_6 = vheap_1;
              final AValue lifted_out_1 = lifted_2;
              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
              final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
              final boolean lifted_out_4 = lifted_5;
              final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
              final ma_Result ma_Result3 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
              return ma_Result3;
            }
          }
        }
        else
        { 
          if(pts != null && !pts.isEmpty())
          { 
            final I_STerm p1 = pts.head();
            final INodeList<I_STerm> lifted_7 = pts.tail();
            if(lifted_7 != null && !lifted_7.isEmpty())
            { 
              final I_STerm p2 = lifted_7.head();
              final INodeList<I_STerm> lifted_8 = lifted_7.tail();
              if(lifted_8 != null && lifted_8.isEmpty())
              { 
                final boolean tmpbuild321 = ds.manual.interpreter.Natives.isATermList_1(t);
                if(tmpbuild321 == true)
                { 
                  final org.spoofax.interpreter.terms.IStrategoList tmpbuild320 = ds.manual.interpreter.Natives.asATermList_1(t);
                  final org.spoofax.interpreter.terms.IStrategoList list = tmpbuild320;
                  final boolean tmpbuild319 = list.isEmpty();
                  if(tmpbuild319 == false)
                  { 
                    final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild318 = list.head();
                    final org.spoofax.interpreter.terms.IStrategoTerm head = tmpbuild318;
                    final Match_1 tmpbuild317 = new Match_1(getSourceInfo(), p1);
                    final I_Strategy lifted_10 = tmpbuild317;
                    final default_Result tmpresult65 = lifted_10.exec_default(senv_1, venv_1, ic_1, tf_1, head, trace_1, sheap_1, bool_1, vheap_1);
                    final AValue lifted_9 = tmpresult65.value;
                    final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult65.get_1();
                    final ds.manual.interpreter.SState sheap_2 = tmpresult65.get_2();
                    final boolean bool_2 = tmpresult65.get_3();
                    final ds.manual.interpreter.VState vheap_2 = tmpresult65.get_4();
                    final S_1 tmpbuild316 = lifted_9.match(S_1.class);
                    if(tmpbuild316 != null)
                    { 
                      final org.spoofax.interpreter.terms.IStrategoTerm thead = tmpbuild316.get_1();
                      final org.spoofax.interpreter.terms.IStrategoList tmpbuild315 = list.tail();
                      final org.spoofax.interpreter.terms.IStrategoList tail = tmpbuild315;
                      final Match_1 tmpbuild314 = new Match_1(getSourceInfo(), p2);
                      final I_Strategy lifted_12 = tmpbuild314;
                      final default_Result tmpresult64 = lifted_12.exec_default(senv_1, venv_1, ic_1, tf_1, tail, trace_2, sheap_2, bool_2, vheap_2);
                      final AValue lifted_11 = tmpresult64.value;
                      final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult64.get_1();
                      final ds.manual.interpreter.SState sheap_3 = tmpresult64.get_2();
                      final boolean bool_3 = tmpresult64.get_3();
                      final ds.manual.interpreter.VState vheap_3 = tmpresult64.get_4();
                      final S_1 tmpbuild313 = lifted_11.match(S_1.class);
                      if(tmpbuild313 != null)
                      { 
                        final org.spoofax.interpreter.terms.IStrategoTerm ttail = tmpbuild313.get_1();
                        final S_1 tmpbuild312 = new S_1(t);
                        final AValue lifted_2 = tmpbuild312;
                        final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
                        final ds.manual.interpreter.SState lifted_4 = sheap_3;
                        final boolean lifted_5 = bool_3;
                        final ds.manual.interpreter.VState lifted_6 = vheap_3;
                        final AValue lifted_out_1 = lifted_2;
                        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                        final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                        final boolean lifted_out_4 = lifted_5;
                        final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                        final ma_Result ma_Result3 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                        return ma_Result3;
                      }
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
      final F_0 tmpbuild323 = new F_0();
      final AValue lifted_2 = tmpbuild323;
      final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
      final ds.manual.interpreter.SState lifted_4 = sheap_1;
      final boolean lifted_5 = bool_1;
      final ds.manual.interpreter.VState lifted_6 = vheap_1;
      final AValue lifted_out_1 = lifted_2;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
      final boolean lifted_out_4 = lifted_5;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
      final ma_Result ma_Result3 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return ma_Result3;
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
    final mList_1 other = (mList_1)obj;
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