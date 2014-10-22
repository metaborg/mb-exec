package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class bindsvars_3 extends NoOpNode implements I_Binder
{ 
  @Children public INodeList<String> _1;

  @Children public INodeList<I_Strategy> _2;

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> _3;

  public bindsvars_3 (INodeSource source, INodeList<String> _1, INodeList<I_Strategy> _2, com.github.krukow.clj_ds.PersistentMap<Object, Object> _3) 
  { 
    this.setSourceInfo(source);
    this._1 = _1;
    this._2 = adoptChildren(_2);
    this._3 = _3;
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      for(I_Strategy _2_elem : _2)
      { 
        if(_2_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_2_elem).specialize(depth);
        }
      }
      hasSpecialized = true;
    }
  }

  public bindsvars_Result exec_bindsvars(ds.manual.interpreter.AutoInterpInteropContext lifted_in_1, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_2, org.spoofax.interpreter.terms.ITermFactory lifted_in_3, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_4, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, boolean lifted_in_7, ds.manual.interpreter.VState lifted_in_8, ds.manual.interpreter.SState lifted_in_9)
  { 
    this.specializeChildren(0);
    final INodeList<String> lifted_1 = _1;
    final INodeList<I_Strategy> lifted_2 = _2;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_3 = _3;
    final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_1;
    final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_2;
    final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_3;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_4;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_5;
    final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
    final boolean bool_1 = lifted_in_7;
    final ds.manual.interpreter.VState vheap_1 = lifted_in_8;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_9;
    if(lifted_1 != null && lifted_1.isEmpty())
    { 
      if(lifted_2 != null && lifted_2.isEmpty())
      { 
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_into = lifted_3;
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> d = senv_1;
        final org.spoofax.interpreter.core.StackTracer lifted_4 = trace_1;
        final boolean lifted_5 = bool_1;
        final ds.manual.interpreter.VState lifted_6 = vheap_1;
        final ds.manual.interpreter.SState lifted_7 = sheap_1;
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_out_1 = d_into;
        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_4;
        final boolean lifted_out_3 = lifted_5;
        final ds.manual.interpreter.VState lifted_out_4 = lifted_6;
        final ds.manual.interpreter.SState lifted_out_5 = lifted_7;
        final bindsvars_Result bindsvars_Result0 = new bindsvars_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
        return bindsvars_Result0;
      }
    }
    else
    { 
      if(lifted_1 != null && !lifted_1.isEmpty())
      { 
        final String x = lifted_1.head();
        final INodeList<String> xs = lifted_1.tail();
        if(lifted_2 != null && !lifted_2.isEmpty())
        { 
          final I_Strategy s = lifted_2.head();
          final INodeList<I_Strategy> ss = lifted_2.tail();
          final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_into = lifted_3;
          final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
          final com.github.krukow.clj_ds.PersistentMap<Object, Object> d = senv_1;
          final CallT_3 tmpbuild468 = s.match(CallT_3.class);
          if(tmpbuild468 != null)
          { 
            final I_SVar lifted_8 = tmpbuild468.get_1();
            final INodeList<I_Strategy> lifted_9 = tmpbuild468.get_2();
            final INodeList<I_STerm> lifted_10 = tmpbuild468.get_3();
            final SVar_1 tmpbuild491 = lifted_8.match(SVar_1.class);
            if(tmpbuild491 != null)
            { 
              final String tgt = tmpbuild491.get_1();
              final INodeList<I_Strategy> ass = lifted_9;
              final INodeList<I_STerm> ats = lifted_10;
              final boolean tmpbuild490 = ds.manual.interpreter.Natives.isEmpty_1(ass);
              final boolean empty_ass = tmpbuild490;
              final boolean tmpbuild489 = ds.manual.interpreter.Natives.isEmpty_1(ats);
              final boolean empty_ats = tmpbuild489;
              final boolean tmpbuild469 = ds.manual.interpreter.Natives.booleanAnd_2(empty_ass, empty_ats);
              if(tmpbuild469 == true)
              { 
                final SLookup_2 tmpbuild475 = new SLookup_2(getSourceInfo(), d, tgt);
                final I_SHeapOp lifted_12 = tmpbuild475;
                final slook_Result tmpresult91 = lifted_12.exec_slook(sheap_1);
                final I_SLookupResult lifted_11 = tmpresult91.value;
                final ds.manual.interpreter.SState sheap_2 = tmpresult91.get_1();
                final SLookupResult_2 tmpbuild474 = lifted_11.match(SLookupResult_2.class);
                if(tmpbuild474 != null)
                { 
                  final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_13 = tmpbuild474.get_1();
                  final I_Thunk lifted_14 = tmpbuild474.get_2();
                  final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_ = lifted_13;
                  final Thunk_6 tmpbuild473 = lifted_14.match(Thunk_6.class);
                  if(tmpbuild473 != null)
                  { 
                    final String tgt__ = tmpbuild473.get_1();
                    final INodeList<String> fss = tmpbuild473.get_2();
                    final INodeList<String> fts = tmpbuild473.get_3();
                    final I_Strategy sactual = tmpbuild473.get_4();
                    final com.github.krukow.clj_ds.PersistentMap<Object, Object> e_tgt = tmpbuild473.get_5();
                    final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_tgt = tmpbuild473.get_6();
                    final Thunk_6 tmpbuild472 = new Thunk_6(getSourceInfo(), x, fss, fts, sactual, e_tgt, d_tgt);
                    final SPush_3 tmpbuild471 = new SPush_3(getSourceInfo(), d_into, x, tmpbuild472);
                    final I_SHeapOp lifted_15 = tmpbuild471;
                    final salloc_Result tmpresult90 = lifted_15.exec_salloc(sheap_2);
                    final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_into_ = tmpresult90.value;
                    final ds.manual.interpreter.SState sheap_3 = tmpresult90.get_1();
                    final bindsvars_3 tmpbuild470 = new bindsvars_3(getSourceInfo(), xs, ss, d_into_);
                    final I_Binder lifted_16 = tmpbuild470;
                    final bindsvars_Result tmpresult89 = lifted_16.exec_bindsvars(ic_1, t_1, tf_1, e, d, trace_1, bool_1, vheap_1, sheap_3);
                    final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_into__ = tmpresult89.value;
                    final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult89.get_1();
                    final boolean bool_2 = tmpresult89.get_2();
                    final ds.manual.interpreter.VState vheap_2 = tmpresult89.get_3();
                    final ds.manual.interpreter.SState sheap_4 = tmpresult89.get_4();
                    final org.spoofax.interpreter.core.StackTracer lifted_4 = trace_2;
                    final boolean lifted_5 = bool_2;
                    final ds.manual.interpreter.VState lifted_6 = vheap_2;
                    final ds.manual.interpreter.SState lifted_7 = sheap_4;
                    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_out_1 = d_into__;
                    final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_4;
                    final boolean lifted_out_3 = lifted_5;
                    final ds.manual.interpreter.VState lifted_out_4 = lifted_6;
                    final ds.manual.interpreter.SState lifted_out_5 = lifted_7;
                    final bindsvars_Result bindsvars_Result0 = new bindsvars_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                    return bindsvars_Result0;
                  }
                }
              }
              else
              { 
                final boolean tmpbuild488 = ds.manual.interpreter.Natives.booleanAnd_2(empty_ass, empty_ats);
                if(tmpbuild488 == false)
                { 
                  final SLookup_2 tmpbuild487 = new SLookup_2(getSourceInfo(), d, tgt);
                  final I_SHeapOp lifted_12 = tmpbuild487;
                  final slook_Result tmpresult97 = lifted_12.exec_slook(sheap_1);
                  final I_SLookupResult lifted_11 = tmpresult97.value;
                  final ds.manual.interpreter.SState sheap_2 = tmpresult97.get_1();
                  final SLookupResult_2 tmpbuild486 = lifted_11.match(SLookupResult_2.class);
                  if(tmpbuild486 != null)
                  { 
                    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_13 = tmpbuild486.get_1();
                    final I_Thunk lifted_14 = tmpbuild486.get_2();
                    final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_ = lifted_13;
                    final Thunk_6 tmpbuild485 = lifted_14.match(Thunk_6.class);
                    if(tmpbuild485 != null)
                    { 
                      final String tgt__ = tmpbuild485.get_1();
                      final INodeList<String> fss = tmpbuild485.get_2();
                      final INodeList<String> fts = tmpbuild485.get_3();
                      final I_Strategy sactual = tmpbuild485.get_4();
                      final com.github.krukow.clj_ds.PersistentMap<Object, Object> e_tgt = tmpbuild485.get_5();
                      final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_tgt = tmpbuild485.get_6();
                      final bl_1 tmpbuild484 = new bl_1(getSourceInfo(), ats);
                      final I_Builder lifted_16 = tmpbuild484;
                      final blds_Result tmpresult96 = lifted_16.exec_blds(d, e, ic_1, t_1, tf_1, trace_1, sheap_2, bool_1, vheap_1);
                      final I_BuildRes lifted_15 = tmpresult96.value;
                      final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult96.get_1();
                      final ds.manual.interpreter.SState sheap_3 = tmpresult96.get_2();
                      final boolean bool_2 = tmpresult96.get_3();
                      final ds.manual.interpreter.VState vheap_2 = tmpresult96.get_4();
                      final BSS_1 tmpbuild483 = lifted_15.match(BSS_1.class);
                      if(tmpbuild483 != null)
                      { 
                        final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> ats_ = tmpbuild483.get_1();
                        final bindtvars_2 tmpbuild482 = new bindtvars_2(getSourceInfo(), fts, ats_);
                        final I_Binder lifted_17 = tmpbuild482;
                        final bindtvars_Result tmpresult95 = lifted_17.exec_bindtvars(e_tgt, vheap_2);
                        final com.github.krukow.clj_ds.PersistentMap<Object, Object> e_tgt_ = tmpresult95.value;
                        final ds.manual.interpreter.VState vheap_3 = tmpresult95.get_1();
                        final bindsvars_3 tmpbuild481 = new bindsvars_3(getSourceInfo(), fss, ass, d_tgt);
                        final I_Binder lifted_18 = tmpbuild481;
                        final bindsvars_Result tmpresult94 = lifted_18.exec_bindsvars(ic_1, t_1, tf_1, e, d, trace_2, bool_2, vheap_3, sheap_3);
                        final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_tgt_ = tmpresult94.value;
                        final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult94.get_1();
                        final boolean bool_3 = tmpresult94.get_2();
                        final ds.manual.interpreter.VState vheap_4 = tmpresult94.get_3();
                        final ds.manual.interpreter.SState sheap_4 = tmpresult94.get_4();
                        final INodeList<String> tmpbuild479 = NodeList.NIL(String.class);
                        final INodeList<String> tmpbuild480 = NodeList.NIL(String.class);
                        final Thunk_6 tmpbuild478 = new Thunk_6(getSourceInfo(), x, tmpbuild479, tmpbuild480, sactual, e_tgt_, d_tgt_);
                        final SPush_3 tmpbuild477 = new SPush_3(getSourceInfo(), d_into, x, tmpbuild478);
                        final I_SHeapOp lifted_19 = tmpbuild477;
                        final salloc_Result tmpresult93 = lifted_19.exec_salloc(sheap_4);
                        final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_into_ = tmpresult93.value;
                        final ds.manual.interpreter.SState sheap_5 = tmpresult93.get_1();
                        final bindsvars_3 tmpbuild476 = new bindsvars_3(getSourceInfo(), xs, ss, d_into_);
                        final I_Binder lifted_20 = tmpbuild476;
                        final bindsvars_Result tmpresult92 = lifted_20.exec_bindsvars(ic_1, t_1, tf_1, e, d, trace_3, bool_3, vheap_4, sheap_5);
                        final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_into__ = tmpresult92.value;
                        final org.spoofax.interpreter.core.StackTracer trace_4 = tmpresult92.get_1();
                        final boolean bool_4 = tmpresult92.get_2();
                        final ds.manual.interpreter.VState vheap_5 = tmpresult92.get_3();
                        final ds.manual.interpreter.SState sheap_6 = tmpresult92.get_4();
                        final org.spoofax.interpreter.core.StackTracer lifted_4 = trace_4;
                        final boolean lifted_5 = bool_4;
                        final ds.manual.interpreter.VState lifted_6 = vheap_5;
                        final ds.manual.interpreter.SState lifted_7 = sheap_6;
                        final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_out_1 = d_into__;
                        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_4;
                        final boolean lifted_out_3 = lifted_5;
                        final ds.manual.interpreter.VState lifted_out_4 = lifted_6;
                        final ds.manual.interpreter.SState lifted_out_5 = lifted_7;
                        final bindsvars_Result bindsvars_Result0 = new bindsvars_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                        return bindsvars_Result0;
                      }
                    }
                  }
                }
              }
            }
          }
          else
          { 
            final CallT_3 tmpbuild497 = s.match(CallT_3.class);
            if(tmpbuild497 == null)
            { 
              final INodeList<String> tmpbuild495 = NodeList.NIL(String.class);
              final INodeList<String> tmpbuild496 = NodeList.NIL(String.class);
              final Thunk_6 tmpbuild494 = new Thunk_6(getSourceInfo(), x, tmpbuild495, tmpbuild496, s, e, d);
              final SPush_3 tmpbuild493 = new SPush_3(getSourceInfo(), d_into, x, tmpbuild494);
              final I_SHeapOp lifted_8 = tmpbuild493;
              final salloc_Result tmpresult99 = lifted_8.exec_salloc(sheap_1);
              final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_into_ = tmpresult99.value;
              final ds.manual.interpreter.SState sheap_2 = tmpresult99.get_1();
              final bindsvars_3 tmpbuild492 = new bindsvars_3(getSourceInfo(), xs, ss, d_into_);
              final I_Binder lifted_9 = tmpbuild492;
              final bindsvars_Result tmpresult98 = lifted_9.exec_bindsvars(ic_1, t_1, tf_1, e, d, trace_1, bool_1, vheap_1, sheap_2);
              final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_into__ = tmpresult98.value;
              final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult98.get_1();
              final boolean bool_2 = tmpresult98.get_2();
              final ds.manual.interpreter.VState vheap_2 = tmpresult98.get_3();
              final ds.manual.interpreter.SState sheap_3 = tmpresult98.get_4();
              final org.spoofax.interpreter.core.StackTracer lifted_4 = trace_2;
              final boolean lifted_5 = bool_2;
              final ds.manual.interpreter.VState lifted_6 = vheap_2;
              final ds.manual.interpreter.SState lifted_7 = sheap_3;
              final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_out_1 = d_into__;
              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_4;
              final boolean lifted_out_3 = lifted_5;
              final ds.manual.interpreter.VState lifted_out_4 = lifted_6;
              final ds.manual.interpreter.SState lifted_out_5 = lifted_7;
              final bindsvars_Result bindsvars_Result0 = new bindsvars_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
              return bindsvars_Result0;
            }
          }
        }
      }
    }
    throw new InterpreterException("Rule failure");
  }

  public INodeList<String> get_1()
  { 
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

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> get_3()
  { 
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
    final bindsvars_3 other = (bindsvars_3)obj;
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
    if(_3 != other._3)
    { 
      return false;
    }
    return true;
  }
}