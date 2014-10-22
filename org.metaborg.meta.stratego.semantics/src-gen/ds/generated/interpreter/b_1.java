package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class b_1 extends NoOpNode implements I_Builder
{ 
  @Child public I_PreTerm _1;

  public b_1 (INodeSource source, I_PreTerm _1) 
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
      final Var_1 tmpbuild324 = lifted_1.match(Var_1.class);
      if(tmpbuild324 != null)
      { 
        final String x = tmpbuild324.get_1();
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
        final VLookup_2 tmpbuild328 = new VLookup_2(getSourceInfo(), e, x);
        final I_VHeapOp lifted_8 = tmpbuild328;
        final vlook_Result tmpresult66 = lifted_8.exec_vlook(vheap_1);
        final I_VLookupResult lifted_7 = tmpresult66.value;
        final ds.manual.interpreter.VState vheap_2 = tmpresult66.get_1();
        final VLookupResult_2 tmpbuild327 = lifted_7.match(VLookupResult_2.class);
        if(tmpbuild327 != null)
        { 
          final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_9 = tmpbuild327.get_1();
          final AValue lifted_10 = tmpbuild327.get_2();
          final com.github.krukow.clj_ds.PersistentMap<Object, Object> e_ = lifted_9;
          final S_1 tmpbuild326 = lifted_10.match(S_1.class);
          if(tmpbuild326 != null)
          { 
            final org.spoofax.interpreter.terms.IStrategoTerm t = tmpbuild326.get_1();
            final BS_1 tmpbuild325 = new BS_1(getSourceInfo(), t);
            final I_BuildRes lifted_2 = tmpbuild325;
            final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
            final ds.manual.interpreter.SState lifted_4 = sheap_1;
            final boolean lifted_5 = bool_1;
            final ds.manual.interpreter.VState lifted_6 = vheap_2;
            final I_BuildRes lifted_out_1 = lifted_2;
            final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
            final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
            final boolean lifted_out_4 = lifted_5;
            final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
            final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
            return bld_Result0;
          }
        }
      }
      else
      { 
        final Int_1 tmpbuild329 = lifted_1.match(Int_1.class);
        if(tmpbuild329 != null)
        { 
          final String stringI = tmpbuild329.get_1();
          final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
          final int tmpbuild332 = ds.manual.interpreter.Natives.parseInt_1(stringI);
          final int i_ = tmpbuild332;
          final org.spoofax.interpreter.terms.IStrategoInt tmpbuild331 = tf.makeInt(i_);
          final org.spoofax.interpreter.terms.IStrategoInt aint = tmpbuild331;
          final BS_1 tmpbuild330 = new BS_1(getSourceInfo(), aint);
          final I_BuildRes lifted_2 = tmpbuild330;
          final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
          final ds.manual.interpreter.SState lifted_4 = sheap_1;
          final boolean lifted_5 = bool_1;
          final ds.manual.interpreter.VState lifted_6 = vheap_1;
          final I_BuildRes lifted_out_1 = lifted_2;
          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
          final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
          final boolean lifted_out_4 = lifted_5;
          final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
          final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
          return bld_Result0;
        }
        else
        { 
          final Real_1 tmpbuild333 = lifted_1.match(Real_1.class);
          if(tmpbuild333 != null)
          { 
            final String stringR = tmpbuild333.get_1();
            final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
            final double tmpbuild336 = ds.manual.interpreter.Natives.parseReal_1(stringR);
            final double r_ = tmpbuild336;
            final org.spoofax.interpreter.terms.IStrategoReal tmpbuild335 = tf.makeReal(r_);
            final org.spoofax.interpreter.terms.IStrategoReal areal = tmpbuild335;
            final BS_1 tmpbuild334 = new BS_1(getSourceInfo(), areal);
            final I_BuildRes lifted_2 = tmpbuild334;
            final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
            final ds.manual.interpreter.SState lifted_4 = sheap_1;
            final boolean lifted_5 = bool_1;
            final ds.manual.interpreter.VState lifted_6 = vheap_1;
            final I_BuildRes lifted_out_1 = lifted_2;
            final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
            final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
            final boolean lifted_out_4 = lifted_5;
            final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
            final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
            return bld_Result0;
          }
          else
          { 
            final Str_1 tmpbuild337 = lifted_1.match(Str_1.class);
            if(tmpbuild337 != null)
            { 
              final String s = tmpbuild337.get_1();
              final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
              final org.spoofax.interpreter.terms.IStrategoString tmpbuild339 = tf.makeString(s);
              final org.spoofax.interpreter.terms.IStrategoString astr = tmpbuild339;
              final BS_1 tmpbuild338 = new BS_1(getSourceInfo(), astr);
              final I_BuildRes lifted_2 = tmpbuild338;
              final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
              final ds.manual.interpreter.SState lifted_4 = sheap_1;
              final boolean lifted_5 = bool_1;
              final ds.manual.interpreter.VState lifted_6 = vheap_1;
              final I_BuildRes lifted_out_1 = lifted_2;
              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
              final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
              final boolean lifted_out_4 = lifted_5;
              final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
              final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
              return bld_Result0;
            }
            else
            { 
              final Explode_2 tmpbuild340 = lifted_1.match(Explode_2.class);
              if(tmpbuild340 != null)
              { 
                final I_STerm cname_expr = tmpbuild340.get_1();
                final I_STerm ts_expr = tmpbuild340.get_2();
                final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
                final Build_1 tmpbuild366 = new Build_1(getSourceInfo(), cname_expr);
                final I_Strategy lifted_8 = tmpbuild366;
                final default_Result tmpresult68 = lifted_8.exec_default(senv_1, venv_1, ic_1, tf_1, t_1, trace_1, sheap_1, bool_1, vheap_1);
                final AValue lifted_7 = tmpresult68.value;
                final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult68.get_1();
                final ds.manual.interpreter.SState sheap_2 = tmpresult68.get_2();
                final boolean bool_2 = tmpresult68.get_3();
                final ds.manual.interpreter.VState vheap_2 = tmpresult68.get_4();
                final S_1 tmpbuild365 = lifted_7.match(S_1.class);
                if(tmpbuild365 != null)
                { 
                  final org.spoofax.interpreter.terms.IStrategoTerm c = tmpbuild365.get_1();
                  final Build_1 tmpbuild364 = new Build_1(getSourceInfo(), ts_expr);
                  final I_Strategy lifted_10 = tmpbuild364;
                  final default_Result tmpresult67 = lifted_10.exec_default(senv_1, venv_1, ic_1, tf_1, t_1, trace_2, sheap_2, bool_2, vheap_2);
                  final AValue lifted_9 = tmpresult67.value;
                  final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult67.get_1();
                  final ds.manual.interpreter.SState sheap_3 = tmpresult67.get_2();
                  final boolean bool_3 = tmpresult67.get_3();
                  final ds.manual.interpreter.VState vheap_3 = tmpresult67.get_4();
                  final S_1 tmpbuild363 = lifted_9.match(S_1.class);
                  if(tmpbuild363 != null)
                  { 
                    final org.spoofax.interpreter.terms.IStrategoTerm ts = tmpbuild363.get_1();
                    final boolean tmpbuild362 = ds.manual.interpreter.Natives.isATermList_1(ts);
                    if(tmpbuild362 == true)
                    { 
                      final boolean tmpbuild341 = ds.manual.interpreter.Natives.isATermInt_1(c);
                      if(tmpbuild341 == true)
                      { 
                        final BS_1 tmpbuild342 = new BS_1(getSourceInfo(), c);
                        final I_BuildRes lifted_2 = tmpbuild342;
                        final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
                        final ds.manual.interpreter.SState lifted_4 = sheap_3;
                        final boolean lifted_5 = bool_3;
                        final ds.manual.interpreter.VState lifted_6 = vheap_3;
                        final I_BuildRes lifted_out_1 = lifted_2;
                        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                        final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                        final boolean lifted_out_4 = lifted_5;
                        final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                        final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                        return bld_Result0;
                      }
                      else
                      { 
                        final boolean tmpbuild343 = ds.manual.interpreter.Natives.isATermList_1(c);
                        if(tmpbuild343 == true)
                        { 
                          final BS_1 tmpbuild344 = new BS_1(getSourceInfo(), ts);
                          final I_BuildRes lifted_2 = tmpbuild344;
                          final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
                          final ds.manual.interpreter.SState lifted_4 = sheap_3;
                          final boolean lifted_5 = bool_3;
                          final ds.manual.interpreter.VState lifted_6 = vheap_3;
                          final I_BuildRes lifted_out_1 = lifted_2;
                          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                          final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                          final boolean lifted_out_4 = lifted_5;
                          final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                          final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                          return bld_Result0;
                        }
                        else
                        { 
                          final boolean tmpbuild361 = ds.manual.interpreter.Natives.isATermString_1(c);
                          if(tmpbuild361 == true)
                          { 
                            final org.spoofax.interpreter.terms.IStrategoString tmpbuild360 = ds.manual.interpreter.Natives.asATermString_1(c);
                            final org.spoofax.interpreter.terms.IStrategoString tc = tmpbuild360;
                            final String tmpbuild359 = tc.stringValue();
                            final String cname = tmpbuild359;
                            if(cname != null && cname.equals(""))
                            { 
                              final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild347 = ts.getAllSubterms();
                              final org.spoofax.interpreter.terms.IStrategoTuple tmpbuild346 = tf.makeTuple(tmpbuild347);
                              final org.spoofax.interpreter.terms.IStrategoTuple tupl = tmpbuild346;
                              final BS_1 tmpbuild345 = new BS_1(getSourceInfo(), tupl);
                              final I_BuildRes lifted_2 = tmpbuild345;
                              final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
                              final ds.manual.interpreter.SState lifted_4 = sheap_3;
                              final boolean lifted_5 = bool_3;
                              final ds.manual.interpreter.VState lifted_6 = vheap_3;
                              final I_BuildRes lifted_out_1 = lifted_2;
                              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                              final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                              final boolean lifted_out_4 = lifted_5;
                              final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                              final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                              return bld_Result0;
                            }
                            else
                            { 
                              final boolean tmpbuild348 = ds.manual.interpreter.Natives.isQuotedString_1(cname);
                              if(tmpbuild348 == true)
                              { 
                                final String tmpbuild351 = ds.manual.interpreter.Natives.unquoteString_1(cname);
                                final String svalue = tmpbuild351;
                                final org.spoofax.interpreter.terms.IStrategoString tmpbuild350 = tf.makeString(svalue);
                                final org.spoofax.interpreter.terms.IStrategoString s = tmpbuild350;
                                final BS_1 tmpbuild349 = new BS_1(getSourceInfo(), s);
                                final I_BuildRes lifted_2 = tmpbuild349;
                                final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
                                final ds.manual.interpreter.SState lifted_4 = sheap_3;
                                final boolean lifted_5 = bool_3;
                                final ds.manual.interpreter.VState lifted_6 = vheap_3;
                                final I_BuildRes lifted_out_1 = lifted_2;
                                final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                                final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                                final boolean lifted_out_4 = lifted_5;
                                final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                                final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                                return bld_Result0;
                              }
                              else
                              { 
                                final boolean tmpbuild358 = ds.manual.interpreter.Natives.isQuotedString_1(cname);
                                if(tmpbuild358 == false)
                                { 
                                  final org.spoofax.interpreter.terms.IStrategoList tmpbuild357 = ds.manual.interpreter.Natives.asATermList_1(ts);
                                  final org.spoofax.interpreter.terms.IStrategoList tslist = tmpbuild357;
                                  final int tmpbuild356 = tslist.size();
                                  final org.spoofax.interpreter.terms.IStrategoConstructor tmpbuild355 = tf.makeConstructor(cname, tmpbuild356);
                                  final org.spoofax.interpreter.terms.IStrategoConstructor constr = tmpbuild355;
                                  final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild354 = ts.getAllSubterms();
                                  final org.spoofax.interpreter.terms.IStrategoTerm[] kids = tmpbuild354;
                                  final org.spoofax.interpreter.terms.IStrategoAppl tmpbuild353 = tf.makeAppl(constr, kids);
                                  final org.spoofax.interpreter.terms.IStrategoAppl appl = tmpbuild353;
                                  final BS_1 tmpbuild352 = new BS_1(getSourceInfo(), appl);
                                  final I_BuildRes lifted_2 = tmpbuild352;
                                  final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
                                  final ds.manual.interpreter.SState lifted_4 = sheap_3;
                                  final boolean lifted_5 = bool_3;
                                  final ds.manual.interpreter.VState lifted_6 = vheap_3;
                                  final I_BuildRes lifted_out_1 = lifted_2;
                                  final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                                  final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                                  final boolean lifted_out_4 = lifted_5;
                                  final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                                  final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                                  return bld_Result0;
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
              else
              { 
                final Op_2 tmpbuild386 = lifted_1.match(Op_2.class);
                if(tmpbuild386 != null)
                { 
                  final String c = tmpbuild386.get_1();
                  final INodeList<I_STerm> ts = tmpbuild386.get_2();
                  final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
                  if(c != null && c.equals("Nil"))
                  { 
                    final int tmpbuild367 = ds.manual.interpreter.Natives.length_1(ts);
                    if(tmpbuild367 == 0)
                    { 
                      final Op_2 tmpbuild369 = new Op_2(getSourceInfo(), c, ts);
                      final bList_1 tmpbuild368 = new bList_1(getSourceInfo(), tmpbuild369);
                      final I_Builder lifted_6 = tmpbuild368;
                      final bld_Result tmpresult69 = lifted_6.exec_bld(senv_1, ic_1, t_1, tf, venv_1, trace_1, sheap_1, bool_1, vheap_1);
                      final I_BuildRes bv = tmpresult69.value;
                      final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult69.get_1();
                      final ds.manual.interpreter.SState sheap_2 = tmpresult69.get_2();
                      final boolean bool_2 = tmpresult69.get_3();
                      final ds.manual.interpreter.VState vheap_2 = tmpresult69.get_4();
                      final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                      final ds.manual.interpreter.SState lifted_3 = sheap_2;
                      final boolean lifted_4 = bool_2;
                      final ds.manual.interpreter.VState lifted_5 = vheap_2;
                      final I_BuildRes lifted_out_1 = bv;
                      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                      final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                      final boolean lifted_out_4 = lifted_4;
                      final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                      final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                      return bld_Result0;
                    }
                    else
                    { 
                      final int tmpbuild372 = ds.manual.interpreter.Natives.length_1(ts);
                      if(tmpbuild372 != 0)
                      { 
                        final Op_2 tmpbuild371 = new Op_2(getSourceInfo(), c, ts);
                        final bAppl_1 tmpbuild370 = new bAppl_1(getSourceInfo(), tmpbuild371);
                        final I_Builder lifted_6 = tmpbuild370;
                        final bld_Result tmpresult70 = lifted_6.exec_bld(senv_1, ic_1, t_1, tf_1, venv_1, trace_1, sheap_1, bool_1, vheap_1);
                        final I_BuildRes bv = tmpresult70.value;
                        final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult70.get_1();
                        final ds.manual.interpreter.SState sheap_2 = tmpresult70.get_2();
                        final boolean bool_2 = tmpresult70.get_3();
                        final ds.manual.interpreter.VState vheap_2 = tmpresult70.get_4();
                        final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                        final ds.manual.interpreter.SState lifted_3 = sheap_2;
                        final boolean lifted_4 = bool_2;
                        final ds.manual.interpreter.VState lifted_5 = vheap_2;
                        final I_BuildRes lifted_out_1 = bv;
                        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                        final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                        final boolean lifted_out_4 = lifted_4;
                        final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                        final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                        return bld_Result0;
                      }
                    }
                  }
                  else
                  { 
                    if(c != null && c.equals("Cons"))
                    { 
                      final int tmpbuild373 = ds.manual.interpreter.Natives.length_1(ts);
                      if(tmpbuild373 == 2)
                      { 
                        final Op_2 tmpbuild375 = new Op_2(getSourceInfo(), c, ts);
                        final bList_1 tmpbuild374 = new bList_1(getSourceInfo(), tmpbuild375);
                        final I_Builder lifted_6 = tmpbuild374;
                        final bld_Result tmpresult71 = lifted_6.exec_bld(senv_1, ic_1, t_1, tf_1, venv_1, trace_1, sheap_1, bool_1, vheap_1);
                        final I_BuildRes bv = tmpresult71.value;
                        final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult71.get_1();
                        final ds.manual.interpreter.SState sheap_2 = tmpresult71.get_2();
                        final boolean bool_2 = tmpresult71.get_3();
                        final ds.manual.interpreter.VState vheap_2 = tmpresult71.get_4();
                        final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                        final ds.manual.interpreter.SState lifted_3 = sheap_2;
                        final boolean lifted_4 = bool_2;
                        final ds.manual.interpreter.VState lifted_5 = vheap_2;
                        final I_BuildRes lifted_out_1 = bv;
                        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                        final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                        final boolean lifted_out_4 = lifted_4;
                        final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                        final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                        return bld_Result0;
                      }
                      else
                      { 
                        final int tmpbuild378 = ds.manual.interpreter.Natives.length_1(ts);
                        if(tmpbuild378 != 2)
                        { 
                          final Op_2 tmpbuild377 = new Op_2(getSourceInfo(), c, ts);
                          final bAppl_1 tmpbuild376 = new bAppl_1(getSourceInfo(), tmpbuild377);
                          final I_Builder lifted_6 = tmpbuild376;
                          final bld_Result tmpresult72 = lifted_6.exec_bld(senv_1, ic_1, t_1, tf_1, venv_1, trace_1, sheap_1, bool_1, vheap_1);
                          final I_BuildRes bv = tmpresult72.value;
                          final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult72.get_1();
                          final ds.manual.interpreter.SState sheap_2 = tmpresult72.get_2();
                          final boolean bool_2 = tmpresult72.get_3();
                          final ds.manual.interpreter.VState vheap_2 = tmpresult72.get_4();
                          final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                          final ds.manual.interpreter.SState lifted_3 = sheap_2;
                          final boolean lifted_4 = bool_2;
                          final ds.manual.interpreter.VState lifted_5 = vheap_2;
                          final I_BuildRes lifted_out_1 = bv;
                          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                          final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                          final boolean lifted_out_4 = lifted_4;
                          final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                          final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                          return bld_Result0;
                        }
                      }
                    }
                    else
                    { 
                      if(c != null && c.equals(""))
                      { 
                        final bl_1 tmpbuild383 = new bl_1(getSourceInfo(), ts);
                        final I_Builder lifted_8 = tmpbuild383;
                        final blds_Result tmpresult73 = lifted_8.exec_blds(senv_1, venv_1, ic_1, t_1, tf, trace_1, sheap_1, bool_1, vheap_1);
                        final I_BuildRes lifted_7 = tmpresult73.value;
                        final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult73.get_1();
                        final ds.manual.interpreter.SState sheap_2 = tmpresult73.get_2();
                        final boolean bool_2 = tmpresult73.get_3();
                        final ds.manual.interpreter.VState vheap_2 = tmpresult73.get_4();
                        final BSS_1 tmpbuild382 = lifted_7.match(BSS_1.class);
                        if(tmpbuild382 != null)
                        { 
                          final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> ts_ = tmpbuild382.get_1();
                          final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild381 = ds.manual.interpreter.Natives.List2TARRAY_1(ts_);
                          final org.spoofax.interpreter.terms.IStrategoTerm[] ts__ = tmpbuild381;
                          final org.spoofax.interpreter.terms.IStrategoTuple tmpbuild380 = tf.makeTuple(ts__);
                          final org.spoofax.interpreter.terms.IStrategoTuple tup = tmpbuild380;
                          final BS_1 tmpbuild379 = new BS_1(getSourceInfo(), tup);
                          final I_BuildRes lifted_2 = tmpbuild379;
                          final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_2;
                          final ds.manual.interpreter.SState lifted_4 = sheap_2;
                          final boolean lifted_5 = bool_2;
                          final ds.manual.interpreter.VState lifted_6 = vheap_2;
                          final I_BuildRes lifted_out_1 = lifted_2;
                          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                          final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                          final boolean lifted_out_4 = lifted_5;
                          final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                          final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                          return bld_Result0;
                        }
                      }
                      else
                      { 
                        if(c == null || !c.equals("Cons"))
                        { 
                          if(c == null || !c.equals("Nil"))
                          { 
                            if(c == null || !c.equals(""))
                            { 
                              final Op_2 tmpbuild385 = new Op_2(getSourceInfo(), c, ts);
                              final bAppl_1 tmpbuild384 = new bAppl_1(getSourceInfo(), tmpbuild385);
                              final I_Builder lifted_6 = tmpbuild384;
                              final bld_Result tmpresult74 = lifted_6.exec_bld(senv_1, ic_1, t_1, tf_1, venv_1, trace_1, sheap_1, bool_1, vheap_1);
                              final I_BuildRes bv = tmpresult74.value;
                              final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult74.get_1();
                              final ds.manual.interpreter.SState sheap_2 = tmpresult74.get_2();
                              final boolean bool_2 = tmpresult74.get_3();
                              final ds.manual.interpreter.VState vheap_2 = tmpresult74.get_4();
                              final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                              final ds.manual.interpreter.SState lifted_3 = sheap_2;
                              final boolean lifted_4 = bool_2;
                              final ds.manual.interpreter.VState lifted_5 = vheap_2;
                              final I_BuildRes lifted_out_1 = bv;
                              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                              final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                              final boolean lifted_out_4 = lifted_4;
                              final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                              final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                              return bld_Result0;
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
      final I_PreTerm x = lifted_1;
      final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
      final BF_0 tmpbuild387 = new BF_0(getSourceInfo());
      final I_BuildRes lifted_2 = tmpbuild387;
      final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
      final ds.manual.interpreter.SState lifted_4 = sheap_1;
      final boolean lifted_5 = bool_1;
      final ds.manual.interpreter.VState lifted_6 = vheap_1;
      final I_BuildRes lifted_out_1 = lifted_2;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
      final boolean lifted_out_4 = lifted_5;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
      final bld_Result bld_Result0 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return bld_Result0;
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
    final b_1 other = (b_1)obj;
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