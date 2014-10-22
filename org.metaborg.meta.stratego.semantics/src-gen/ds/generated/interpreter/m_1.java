package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class m_1 extends NoOpNode implements I_Matcher
{ 
  @Child public I_STerm _1;

  public m_1 (INodeSource source, I_STerm _1) 
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
      final Wld_0 tmpbuild169 = lifted_1.match(Wld_0.class);
      if(tmpbuild169 != null)
      { 
        final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
        final S_1 tmpbuild170 = new S_1(t);
        final AValue v = tmpbuild170;
        final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_1;
        final ds.manual.interpreter.SState lifted_3 = sheap_1;
        final boolean lifted_4 = bool_1;
        final ds.manual.interpreter.VState lifted_5 = vheap_1;
        final AValue lifted_out_1 = v;
        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
        final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
        final boolean lifted_out_4 = lifted_4;
        final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
        final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
        return ma_Result0;
      }
      else
      { 
        final Int_1 tmpbuild171 = lifted_1.match(Int_1.class);
        if(tmpbuild171 != null)
        { 
          final String stringI = tmpbuild171.get_1();
          final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
          final boolean tmpbuild176 = ds.manual.interpreter.Natives.isATermInt_1(t);
          if(tmpbuild176 == true)
          { 
            final org.spoofax.interpreter.terms.IStrategoInt tmpbuild175 = ds.manual.interpreter.Natives.asATermInt_1(t);
            final org.spoofax.interpreter.terms.IStrategoInt aint = tmpbuild175;
            final int tmpbuild174 = aint.intValue();
            final int i_ = tmpbuild174;
            final int tmpbuild173 = ds.manual.interpreter.Natives.parseInt_1(stringI);
            final int i = tmpbuild173;
            if(i == i_)
            { 
              final S_1 tmpbuild172 = new S_1(t);
              final AValue v = tmpbuild172;
              final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_1;
              final ds.manual.interpreter.SState lifted_3 = sheap_1;
              final boolean lifted_4 = bool_1;
              final ds.manual.interpreter.VState lifted_5 = vheap_1;
              final AValue lifted_out_1 = v;
              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
              final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
              final boolean lifted_out_4 = lifted_4;
              final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
              final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
              return ma_Result0;
            }
          }
        }
        else
        { 
          final Real_1 tmpbuild177 = lifted_1.match(Real_1.class);
          if(tmpbuild177 != null)
          { 
            final String stringR = tmpbuild177.get_1();
            final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
            final boolean tmpbuild182 = ds.manual.interpreter.Natives.isATermReal_1(t);
            if(tmpbuild182 == true)
            { 
              final org.spoofax.interpreter.terms.IStrategoReal tmpbuild181 = ds.manual.interpreter.Natives.asATermReal_1(t);
              final org.spoofax.interpreter.terms.IStrategoReal areal = tmpbuild181;
              final double tmpbuild180 = areal.realValue();
              final double r_ = tmpbuild180;
              final double tmpbuild179 = ds.manual.interpreter.Natives.parseReal_1(stringR);
              final double r = tmpbuild179;
              if(r == r_)
              { 
                final S_1 tmpbuild178 = new S_1(t);
                final AValue v = tmpbuild178;
                final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_1;
                final ds.manual.interpreter.SState lifted_3 = sheap_1;
                final boolean lifted_4 = bool_1;
                final ds.manual.interpreter.VState lifted_5 = vheap_1;
                final AValue lifted_out_1 = v;
                final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                final boolean lifted_out_4 = lifted_4;
                final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                return ma_Result0;
              }
            }
          }
          else
          { 
            final Str_1 tmpbuild183 = lifted_1.match(Str_1.class);
            if(tmpbuild183 != null)
            { 
              final String s = tmpbuild183.get_1();
              final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
              final boolean tmpbuild187 = ds.manual.interpreter.Natives.isATermString_1(t);
              if(tmpbuild187 == true)
              { 
                final org.spoofax.interpreter.terms.IStrategoString tmpbuild186 = ds.manual.interpreter.Natives.asATermString_1(t);
                final org.spoofax.interpreter.terms.IStrategoString astr = tmpbuild186;
                final String tmpbuild185 = astr.stringValue();
                final String s_ = tmpbuild185;
                if(s != null && s.equals(s_))
                { 
                  final S_1 tmpbuild184 = new S_1(t);
                  final AValue v = tmpbuild184;
                  final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_1;
                  final ds.manual.interpreter.SState lifted_3 = sheap_1;
                  final boolean lifted_4 = bool_1;
                  final ds.manual.interpreter.VState lifted_5 = vheap_1;
                  final AValue lifted_out_1 = v;
                  final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                  final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                  final boolean lifted_out_4 = lifted_4;
                  final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                  final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                  return ma_Result0;
                }
              }
            }
            else
            { 
              final Var_1 tmpbuild188 = lifted_1.match(Var_1.class);
              if(tmpbuild188 != null)
              { 
                final String x = tmpbuild188.get_1();
                final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
                final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
                final VLookup_2 tmpbuild195 = new VLookup_2(getSourceInfo(), e, x);
                final I_VHeapOp lifted_7 = tmpbuild195;
                final vlook_Result tmpresult40 = lifted_7.exec_vlook(vheap_1);
                final I_VLookupResult lifted_6 = tmpresult40.value;
                final ds.manual.interpreter.VState vheap_2 = tmpresult40.get_1();
                final VLookupResult_2 tmpbuild194 = lifted_6.match(VLookupResult_2.class);
                if(tmpbuild194 != null)
                { 
                  final com.github.krukow.clj_ds.PersistentMap<Object, Object> e_ = tmpbuild194.get_1();
                  final AValue xv = tmpbuild194.get_2();
                  final F_0 tmpbuild189 = xv.match(F_0.class);
                  if(tmpbuild189 != null)
                  { 
                    final S_1 tmpbuild191 = new S_1(t);
                    final AValue v = tmpbuild191;
                    final VUpdate_3 tmpbuild190 = new VUpdate_3(getSourceInfo(), e_, x, v);
                    final I_VHeapOp lifted_8 = tmpbuild190;
                    final vupdate_Result tmpresult39 = lifted_8.exec_vupdate(vheap_2);
                    final com.github.krukow.clj_ds.PersistentMap<Object, Object> e__ = tmpresult39.value;
                    final ds.manual.interpreter.VState vheap_3 = tmpresult39.get_1();
                    final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_1;
                    final ds.manual.interpreter.SState lifted_3 = sheap_1;
                    final boolean lifted_4 = bool_1;
                    final ds.manual.interpreter.VState lifted_5 = vheap_3;
                    final AValue lifted_out_1 = v;
                    final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                    final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                    final boolean lifted_out_4 = lifted_4;
                    final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                    final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                    return ma_Result0;
                  }
                  else
                  { 
                    final S_1 tmpbuild193 = xv.match(S_1.class);
                    if(tmpbuild193 != null)
                    { 
                      final org.spoofax.interpreter.terms.IStrategoTerm t_ = tmpbuild193.get_1();
                      if(t != null && t.equals(t_))
                      { 
                        final S_1 tmpbuild192 = new S_1(t);
                        final AValue v = tmpbuild192;
                        final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_1;
                        final ds.manual.interpreter.SState lifted_3 = sheap_1;
                        final boolean lifted_4 = bool_1;
                        final ds.manual.interpreter.VState lifted_5 = vheap_2;
                        final AValue lifted_out_1 = v;
                        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                        final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                        final boolean lifted_out_4 = lifted_4;
                        final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                        final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                        return ma_Result0;
                      }
                    }
                  }
                }
              }
              else
              { 
                final As_2 tmpbuild196 = lifted_1.match(As_2.class);
                if(tmpbuild196 != null)
                { 
                  final I_Var lifted_7 = tmpbuild196.get_1();
                  final I_PreTerm lifted_8 = tmpbuild196.get_2();
                  final Var_1 tmpbuild209 = lifted_7.match(Var_1.class);
                  if(tmpbuild209 != null)
                  { 
                    final String x = tmpbuild209.get_1();
                    final I_PreTerm p = lifted_8;
                    final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
                    final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
                    final VLookup_2 tmpbuild208 = new VLookup_2(getSourceInfo(), e, x);
                    final I_VHeapOp lifted_10 = tmpbuild208;
                    final vlook_Result tmpresult44 = lifted_10.exec_vlook(vheap_1);
                    final I_VLookupResult lifted_9 = tmpresult44.value;
                    final ds.manual.interpreter.VState vheap_2 = tmpresult44.get_1();
                    final VLookupResult_2 tmpbuild207 = lifted_9.match(VLookupResult_2.class);
                    if(tmpbuild207 != null)
                    { 
                      final com.github.krukow.clj_ds.PersistentMap<Object, Object> e_ = tmpbuild207.get_1();
                      final AValue lv = tmpbuild207.get_2();
                      final F_0 tmpbuild197 = lv.match(F_0.class);
                      if(tmpbuild197 != null)
                      { 
                        final Match_1 tmpbuild202 = new Match_1(getSourceInfo(), p);
                        final I_Strategy lifted_12 = tmpbuild202;
                        final default_Result tmpresult42 = lifted_12.exec_default(senv_1, e, ic_1, tf_1, t, trace_1, sheap_1, bool_1, vheap_2);
                        final AValue lifted_11 = tmpresult42.value;
                        final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult42.get_1();
                        final ds.manual.interpreter.SState sheap_2 = tmpresult42.get_2();
                        final boolean bool_2 = tmpresult42.get_3();
                        final ds.manual.interpreter.VState vheap_3 = tmpresult42.get_4();
                        final S_1 tmpbuild201 = lifted_11.match(S_1.class);
                        if(tmpbuild201 != null)
                        { 
                          final org.spoofax.interpreter.terms.IStrategoTerm t_ = tmpbuild201.get_1();
                          final S_1 tmpbuild200 = new S_1(t);
                          final VUpdate_3 tmpbuild199 = new VUpdate_3(getSourceInfo(), e_, x, tmpbuild200);
                          final I_VHeapOp lifted_13 = tmpbuild199;
                          final vupdate_Result tmpresult41 = lifted_13.exec_vupdate(vheap_3);
                          final com.github.krukow.clj_ds.PersistentMap<Object, Object> e__ = tmpresult41.value;
                          final ds.manual.interpreter.VState vheap_4 = tmpresult41.get_1();
                          final S_1 tmpbuild198 = new S_1(t);
                          final AValue lifted_2 = tmpbuild198;
                          final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_2;
                          final ds.manual.interpreter.SState lifted_4 = sheap_2;
                          final boolean lifted_5 = bool_2;
                          final ds.manual.interpreter.VState lifted_6 = vheap_4;
                          final AValue lifted_out_1 = lifted_2;
                          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                          final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                          final boolean lifted_out_4 = lifted_5;
                          final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                          final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                          return ma_Result0;
                        }
                      }
                      else
                      { 
                        final S_1 tmpbuild206 = lv.match(S_1.class);
                        if(tmpbuild206 != null)
                        { 
                          final org.spoofax.interpreter.terms.IStrategoTerm xt = tmpbuild206.get_1();
                          if(t != null && t.equals(xt))
                          { 
                            final Match_1 tmpbuild205 = new Match_1(getSourceInfo(), p);
                            final I_Strategy lifted_12 = tmpbuild205;
                            final default_Result tmpresult43 = lifted_12.exec_default(senv_1, e, ic_1, tf_1, t, trace_1, sheap_1, bool_1, vheap_2);
                            final AValue lifted_11 = tmpresult43.value;
                            final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult43.get_1();
                            final ds.manual.interpreter.SState sheap_2 = tmpresult43.get_2();
                            final boolean bool_2 = tmpresult43.get_3();
                            final ds.manual.interpreter.VState vheap_3 = tmpresult43.get_4();
                            final S_1 tmpbuild204 = lifted_11.match(S_1.class);
                            if(tmpbuild204 != null)
                            { 
                              final org.spoofax.interpreter.terms.IStrategoTerm t__ = tmpbuild204.get_1();
                              final S_1 tmpbuild203 = new S_1(t);
                              final AValue lifted_2 = tmpbuild203;
                              final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_2;
                              final ds.manual.interpreter.SState lifted_4 = sheap_2;
                              final boolean lifted_5 = bool_2;
                              final ds.manual.interpreter.VState lifted_6 = vheap_3;
                              final AValue lifted_out_1 = lifted_2;
                              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                              final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                              final boolean lifted_out_4 = lifted_5;
                              final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                              final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                              return ma_Result0;
                            }
                          }
                        }
                      }
                    }
                  }
                }
                else
                { 
                  final Explode_2 tmpbuild210 = lifted_1.match(Explode_2.class);
                  if(tmpbuild210 != null)
                  { 
                    final I_STerm ct = tmpbuild210.get_1();
                    final I_STerm ts = tmpbuild210.get_2();
                    final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
                    final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
                    final boolean tmpbuild211 = ds.manual.interpreter.Natives.isATermAppl_1(t);
                    if(tmpbuild211 == true)
                    { 
                      final org.spoofax.interpreter.terms.IStrategoAppl tmpbuild222 = ds.manual.interpreter.Natives.asATermAppl_1(t);
                      final org.spoofax.interpreter.terms.IStrategoAppl appl = tmpbuild222;
                      final org.spoofax.interpreter.terms.IStrategoConstructor tmpbuild221 = appl.getConstructor();
                      final String tmpbuild220 = tmpbuild221.getName();
                      final org.spoofax.interpreter.terms.IStrategoString tmpbuild219 = tf.makeString(tmpbuild220);
                      final org.spoofax.interpreter.terms.IStrategoString constr = tmpbuild219;
                      final Match_1 tmpbuild218 = new Match_1(getSourceInfo(), ct);
                      final I_Strategy lifted_8 = tmpbuild218;
                      final default_Result tmpresult46 = lifted_8.exec_default(senv_1, venv_1, ic_1, tf_1, constr, trace_1, sheap_1, bool_1, vheap_1);
                      final AValue lifted_7 = tmpresult46.value;
                      final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult46.get_1();
                      final ds.manual.interpreter.SState sheap_2 = tmpresult46.get_2();
                      final boolean bool_2 = tmpresult46.get_3();
                      final ds.manual.interpreter.VState vheap_2 = tmpresult46.get_4();
                      final S_1 tmpbuild217 = lifted_7.match(S_1.class);
                      if(tmpbuild217 != null)
                      { 
                        final org.spoofax.interpreter.terms.IStrategoTerm constr_ = tmpbuild217.get_1();
                        final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild216 = appl.getAllSubterms();
                        final org.spoofax.interpreter.terms.IStrategoList tmpbuild215 = tf.makeList(tmpbuild216);
                        final org.spoofax.interpreter.terms.IStrategoList kids = tmpbuild215;
                        final Match_1 tmpbuild214 = new Match_1(getSourceInfo(), ts);
                        final I_Strategy lifted_10 = tmpbuild214;
                        final default_Result tmpresult45 = lifted_10.exec_default(senv_1, venv_1, ic_1, tf_1, kids, trace_2, sheap_2, bool_2, vheap_2);
                        final AValue lifted_9 = tmpresult45.value;
                        final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult45.get_1();
                        final ds.manual.interpreter.SState sheap_3 = tmpresult45.get_2();
                        final boolean bool_3 = tmpresult45.get_3();
                        final ds.manual.interpreter.VState vheap_3 = tmpresult45.get_4();
                        final S_1 tmpbuild213 = lifted_9.match(S_1.class);
                        if(tmpbuild213 != null)
                        { 
                          final org.spoofax.interpreter.terms.IStrategoTerm kids_ = tmpbuild213.get_1();
                          final S_1 tmpbuild212 = new S_1(t);
                          final AValue lifted_2 = tmpbuild212;
                          final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
                          final ds.manual.interpreter.SState lifted_4 = sheap_3;
                          final boolean lifted_5 = bool_3;
                          final ds.manual.interpreter.VState lifted_6 = vheap_3;
                          final AValue lifted_out_1 = lifted_2;
                          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                          final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                          final boolean lifted_out_4 = lifted_5;
                          final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                          final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                          return ma_Result0;
                        }
                      }
                    }
                    else
                    { 
                      final boolean tmpbuild223 = ds.manual.interpreter.Natives.isATermInt_1(t);
                      if(tmpbuild223 == true)
                      { 
                        final org.spoofax.interpreter.terms.IStrategoTerm constr = t;
                        final Match_1 tmpbuild229 = new Match_1(getSourceInfo(), ct);
                        final I_Strategy lifted_8 = tmpbuild229;
                        final default_Result tmpresult48 = lifted_8.exec_default(senv_1, venv_1, ic_1, tf_1, constr, trace_1, sheap_1, bool_1, vheap_1);
                        final AValue lifted_7 = tmpresult48.value;
                        final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult48.get_1();
                        final ds.manual.interpreter.SState sheap_2 = tmpresult48.get_2();
                        final boolean bool_2 = tmpresult48.get_3();
                        final ds.manual.interpreter.VState vheap_2 = tmpresult48.get_4();
                        final S_1 tmpbuild228 = lifted_7.match(S_1.class);
                        if(tmpbuild228 != null)
                        { 
                          final org.spoofax.interpreter.terms.IStrategoTerm constr_ = tmpbuild228.get_1();
                          final org.spoofax.interpreter.terms.IStrategoList tmpbuild227 = ds.manual.interpreter.Natives.makeNil_1(tf);
                          final org.spoofax.interpreter.terms.IStrategoList kids = tmpbuild227;
                          final Match_1 tmpbuild226 = new Match_1(getSourceInfo(), ts);
                          final I_Strategy lifted_10 = tmpbuild226;
                          final default_Result tmpresult47 = lifted_10.exec_default(senv_1, venv_1, ic_1, tf_1, kids, trace_2, sheap_2, bool_2, vheap_2);
                          final AValue lifted_9 = tmpresult47.value;
                          final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult47.get_1();
                          final ds.manual.interpreter.SState sheap_3 = tmpresult47.get_2();
                          final boolean bool_3 = tmpresult47.get_3();
                          final ds.manual.interpreter.VState vheap_3 = tmpresult47.get_4();
                          final S_1 tmpbuild225 = lifted_9.match(S_1.class);
                          if(tmpbuild225 != null)
                          { 
                            final org.spoofax.interpreter.terms.IStrategoTerm kids_ = tmpbuild225.get_1();
                            final S_1 tmpbuild224 = new S_1(t);
                            final AValue lifted_2 = tmpbuild224;
                            final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
                            final ds.manual.interpreter.SState lifted_4 = sheap_3;
                            final boolean lifted_5 = bool_3;
                            final ds.manual.interpreter.VState lifted_6 = vheap_3;
                            final AValue lifted_out_1 = lifted_2;
                            final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                            final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                            final boolean lifted_out_4 = lifted_5;
                            final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                            final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                            return ma_Result0;
                          }
                        }
                      }
                      else
                      { 
                        final boolean tmpbuild230 = ds.manual.interpreter.Natives.isATermString_1(t);
                        if(tmpbuild230 == true)
                        { 
                          final org.spoofax.interpreter.terms.IStrategoList tmpbuild240 = ds.manual.interpreter.Natives.makeNil_1(tf);
                          final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild239 = tf.annotateTerm(t, tmpbuild240);
                          final org.spoofax.interpreter.terms.IStrategoTerm tnoanno = tmpbuild239;
                          final String tmpbuild238 = tnoanno.toString();
                          final org.spoofax.interpreter.terms.IStrategoString tmpbuild237 = tf.makeString(tmpbuild238);
                          final org.spoofax.interpreter.terms.IStrategoString constr = tmpbuild237;
                          final Match_1 tmpbuild236 = new Match_1(getSourceInfo(), ct);
                          final I_Strategy lifted_8 = tmpbuild236;
                          final default_Result tmpresult50 = lifted_8.exec_default(senv_1, venv_1, ic_1, tf_1, constr, trace_1, sheap_1, bool_1, vheap_1);
                          final AValue lifted_7 = tmpresult50.value;
                          final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult50.get_1();
                          final ds.manual.interpreter.SState sheap_2 = tmpresult50.get_2();
                          final boolean bool_2 = tmpresult50.get_3();
                          final ds.manual.interpreter.VState vheap_2 = tmpresult50.get_4();
                          final S_1 tmpbuild235 = lifted_7.match(S_1.class);
                          if(tmpbuild235 != null)
                          { 
                            final org.spoofax.interpreter.terms.IStrategoTerm constr_ = tmpbuild235.get_1();
                            final org.spoofax.interpreter.terms.IStrategoList tmpbuild234 = ds.manual.interpreter.Natives.makeNil_1(tf);
                            final org.spoofax.interpreter.terms.IStrategoList kids = tmpbuild234;
                            final Match_1 tmpbuild233 = new Match_1(getSourceInfo(), ts);
                            final I_Strategy lifted_10 = tmpbuild233;
                            final default_Result tmpresult49 = lifted_10.exec_default(senv_1, venv_1, ic_1, tf_1, kids, trace_2, sheap_2, bool_2, vheap_2);
                            final AValue lifted_9 = tmpresult49.value;
                            final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult49.get_1();
                            final ds.manual.interpreter.SState sheap_3 = tmpresult49.get_2();
                            final boolean bool_3 = tmpresult49.get_3();
                            final ds.manual.interpreter.VState vheap_3 = tmpresult49.get_4();
                            final S_1 tmpbuild232 = lifted_9.match(S_1.class);
                            if(tmpbuild232 != null)
                            { 
                              final org.spoofax.interpreter.terms.IStrategoTerm kids_ = tmpbuild232.get_1();
                              final S_1 tmpbuild231 = new S_1(t);
                              final AValue lifted_2 = tmpbuild231;
                              final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
                              final ds.manual.interpreter.SState lifted_4 = sheap_3;
                              final boolean lifted_5 = bool_3;
                              final ds.manual.interpreter.VState lifted_6 = vheap_3;
                              final AValue lifted_out_1 = lifted_2;
                              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                              final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                              final boolean lifted_out_4 = lifted_5;
                              final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                              final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                              return ma_Result0;
                            }
                          }
                        }
                        else
                        { 
                          final boolean tmpbuild241 = ds.manual.interpreter.Natives.isATermList_1(t);
                          if(tmpbuild241 == true)
                          { 
                            final org.spoofax.interpreter.terms.IStrategoList tmpbuild247 = ds.manual.interpreter.Natives.makeNil_1(tf);
                            final org.spoofax.interpreter.terms.IStrategoList constr = tmpbuild247;
                            final Match_1 tmpbuild246 = new Match_1(getSourceInfo(), ct);
                            final I_Strategy lifted_8 = tmpbuild246;
                            final default_Result tmpresult52 = lifted_8.exec_default(senv_1, venv_1, ic_1, tf_1, constr, trace_1, sheap_1, bool_1, vheap_1);
                            final AValue lifted_7 = tmpresult52.value;
                            final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult52.get_1();
                            final ds.manual.interpreter.SState sheap_2 = tmpresult52.get_2();
                            final boolean bool_2 = tmpresult52.get_3();
                            final ds.manual.interpreter.VState vheap_2 = tmpresult52.get_4();
                            final S_1 tmpbuild245 = lifted_7.match(S_1.class);
                            if(tmpbuild245 != null)
                            { 
                              final org.spoofax.interpreter.terms.IStrategoTerm constr_ = tmpbuild245.get_1();
                              final Match_1 tmpbuild244 = new Match_1(getSourceInfo(), ts);
                              final I_Strategy lifted_10 = tmpbuild244;
                              final default_Result tmpresult51 = lifted_10.exec_default(senv_1, venv_1, ic_1, tf_1, t, trace_2, sheap_2, bool_2, vheap_2);
                              final AValue lifted_9 = tmpresult51.value;
                              final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult51.get_1();
                              final ds.manual.interpreter.SState sheap_3 = tmpresult51.get_2();
                              final boolean bool_3 = tmpresult51.get_3();
                              final ds.manual.interpreter.VState vheap_3 = tmpresult51.get_4();
                              final S_1 tmpbuild243 = lifted_9.match(S_1.class);
                              if(tmpbuild243 != null)
                              { 
                                final org.spoofax.interpreter.terms.IStrategoTerm kids_ = tmpbuild243.get_1();
                                final S_1 tmpbuild242 = new S_1(t);
                                final AValue lifted_2 = tmpbuild242;
                                final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
                                final ds.manual.interpreter.SState lifted_4 = sheap_3;
                                final boolean lifted_5 = bool_3;
                                final ds.manual.interpreter.VState lifted_6 = vheap_3;
                                final AValue lifted_out_1 = lifted_2;
                                final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                                final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                                final boolean lifted_out_4 = lifted_5;
                                final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                                final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                                return ma_Result0;
                              }
                            }
                          }
                          else
                          { 
                            final boolean tmpbuild256 = ds.manual.interpreter.Natives.isATermTuple_1(t);
                            if(tmpbuild256 == true)
                            { 
                              final org.spoofax.interpreter.terms.IStrategoString tmpbuild255 = tf.makeString("");
                              final org.spoofax.interpreter.terms.IStrategoString constr = tmpbuild255;
                              final Match_1 tmpbuild254 = new Match_1(getSourceInfo(), ct);
                              final I_Strategy lifted_8 = tmpbuild254;
                              final default_Result tmpresult54 = lifted_8.exec_default(senv_1, venv_1, ic_1, tf_1, constr, trace_1, sheap_1, bool_1, vheap_1);
                              final AValue lifted_7 = tmpresult54.value;
                              final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult54.get_1();
                              final ds.manual.interpreter.SState sheap_2 = tmpresult54.get_2();
                              final boolean bool_2 = tmpresult54.get_3();
                              final ds.manual.interpreter.VState vheap_2 = tmpresult54.get_4();
                              final S_1 tmpbuild253 = lifted_7.match(S_1.class);
                              if(tmpbuild253 != null)
                              { 
                                final org.spoofax.interpreter.terms.IStrategoTerm constr_ = tmpbuild253.get_1();
                                final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild252 = t.getAllSubterms();
                                final org.spoofax.interpreter.terms.IStrategoList tmpbuild251 = tf.makeList(tmpbuild252);
                                final org.spoofax.interpreter.terms.IStrategoList kids = tmpbuild251;
                                final Match_1 tmpbuild250 = new Match_1(getSourceInfo(), ts);
                                final I_Strategy lifted_10 = tmpbuild250;
                                final default_Result tmpresult53 = lifted_10.exec_default(senv_1, venv_1, ic_1, tf_1, kids, trace_2, sheap_2, bool_2, vheap_2);
                                final AValue lifted_9 = tmpresult53.value;
                                final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult53.get_1();
                                final ds.manual.interpreter.SState sheap_3 = tmpresult53.get_2();
                                final boolean bool_3 = tmpresult53.get_3();
                                final ds.manual.interpreter.VState vheap_3 = tmpresult53.get_4();
                                final S_1 tmpbuild249 = lifted_9.match(S_1.class);
                                if(tmpbuild249 != null)
                                { 
                                  final org.spoofax.interpreter.terms.IStrategoTerm kids_ = tmpbuild249.get_1();
                                  final S_1 tmpbuild248 = new S_1(t);
                                  final AValue lifted_2 = tmpbuild248;
                                  final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
                                  final ds.manual.interpreter.SState lifted_4 = sheap_3;
                                  final boolean lifted_5 = bool_3;
                                  final ds.manual.interpreter.VState lifted_6 = vheap_3;
                                  final AValue lifted_out_1 = lifted_2;
                                  final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                                  final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                                  final boolean lifted_out_4 = lifted_5;
                                  final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                                  final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                                  return ma_Result0;
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
                    final Op_2 tmpbuild280 = lifted_1.match(Op_2.class);
                    if(tmpbuild280 != null)
                    { 
                      final String c = tmpbuild280.get_1();
                      final INodeList<I_STerm> pts = tmpbuild280.get_2();
                      final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
                      final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
                      if(c != null && c.equals("Nil"))
                      { 
                        final int tmpbuild257 = ds.manual.interpreter.Natives.length_1(pts);
                        if(tmpbuild257 == 0)
                        { 
                          final Op_2 tmpbuild259 = new Op_2(getSourceInfo(), c, pts);
                          final mList_1 tmpbuild258 = new mList_1(getSourceInfo(), tmpbuild259);
                          final I_Matcher lifted_6 = tmpbuild258;
                          final ma_Result tmpresult55 = lifted_6.exec_ma(senv_1, ic_1, tf, venv_1, t, trace_1, sheap_1, bool_1, vheap_1);
                          final AValue mv = tmpresult55.value;
                          final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult55.get_1();
                          final ds.manual.interpreter.SState sheap_2 = tmpresult55.get_2();
                          final boolean bool_2 = tmpresult55.get_3();
                          final ds.manual.interpreter.VState vheap_2 = tmpresult55.get_4();
                          final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                          final ds.manual.interpreter.SState lifted_3 = sheap_2;
                          final boolean lifted_4 = bool_2;
                          final ds.manual.interpreter.VState lifted_5 = vheap_2;
                          final AValue lifted_out_1 = mv;
                          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                          final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                          final boolean lifted_out_4 = lifted_4;
                          final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                          final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                          return ma_Result0;
                        }
                        else
                        { 
                          final int tmpbuild262 = ds.manual.interpreter.Natives.length_1(pts);
                          if(tmpbuild262 != 0)
                          { 
                            final Op_2 tmpbuild261 = new Op_2(getSourceInfo(), c, pts);
                            final mAppl_1 tmpbuild260 = new mAppl_1(getSourceInfo(), tmpbuild261);
                            final I_Matcher lifted_6 = tmpbuild260;
                            final ma_Result tmpresult56 = lifted_6.exec_ma(senv_1, ic_1, tf, venv_1, t, trace_1, sheap_1, bool_1, vheap_1);
                            final AValue mv = tmpresult56.value;
                            final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult56.get_1();
                            final ds.manual.interpreter.SState sheap_2 = tmpresult56.get_2();
                            final boolean bool_2 = tmpresult56.get_3();
                            final ds.manual.interpreter.VState vheap_2 = tmpresult56.get_4();
                            final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                            final ds.manual.interpreter.SState lifted_3 = sheap_2;
                            final boolean lifted_4 = bool_2;
                            final ds.manual.interpreter.VState lifted_5 = vheap_2;
                            final AValue lifted_out_1 = mv;
                            final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                            final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                            final boolean lifted_out_4 = lifted_4;
                            final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                            final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                            return ma_Result0;
                          }
                        }
                      }
                      else
                      { 
                        if(c != null && c.equals("Cons"))
                        { 
                          final int tmpbuild263 = ds.manual.interpreter.Natives.length_1(pts);
                          if(tmpbuild263 == 2)
                          { 
                            final Op_2 tmpbuild265 = new Op_2(getSourceInfo(), c, pts);
                            final mList_1 tmpbuild264 = new mList_1(getSourceInfo(), tmpbuild265);
                            final I_Matcher lifted_6 = tmpbuild264;
                            final ma_Result tmpresult57 = lifted_6.exec_ma(senv_1, ic_1, tf, venv_1, t, trace_1, sheap_1, bool_1, vheap_1);
                            final AValue mv = tmpresult57.value;
                            final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult57.get_1();
                            final ds.manual.interpreter.SState sheap_2 = tmpresult57.get_2();
                            final boolean bool_2 = tmpresult57.get_3();
                            final ds.manual.interpreter.VState vheap_2 = tmpresult57.get_4();
                            final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                            final ds.manual.interpreter.SState lifted_3 = sheap_2;
                            final boolean lifted_4 = bool_2;
                            final ds.manual.interpreter.VState lifted_5 = vheap_2;
                            final AValue lifted_out_1 = mv;
                            final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                            final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                            final boolean lifted_out_4 = lifted_4;
                            final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                            final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                            return ma_Result0;
                          }
                          else
                          { 
                            final int tmpbuild268 = ds.manual.interpreter.Natives.length_1(pts);
                            if(tmpbuild268 != 2)
                            { 
                              final Op_2 tmpbuild267 = new Op_2(getSourceInfo(), c, pts);
                              final mAppl_1 tmpbuild266 = new mAppl_1(getSourceInfo(), tmpbuild267);
                              final I_Matcher lifted_6 = tmpbuild266;
                              final ma_Result tmpresult58 = lifted_6.exec_ma(senv_1, ic_1, tf, venv_1, t, trace_1, sheap_1, bool_1, vheap_1);
                              final AValue mv = tmpresult58.value;
                              final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult58.get_1();
                              final ds.manual.interpreter.SState sheap_2 = tmpresult58.get_2();
                              final boolean bool_2 = tmpresult58.get_3();
                              final ds.manual.interpreter.VState vheap_2 = tmpresult58.get_4();
                              final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                              final ds.manual.interpreter.SState lifted_3 = sheap_2;
                              final boolean lifted_4 = bool_2;
                              final ds.manual.interpreter.VState lifted_5 = vheap_2;
                              final AValue lifted_out_1 = mv;
                              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                              final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                              final boolean lifted_out_4 = lifted_4;
                              final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                              final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                              return ma_Result0;
                            }
                          }
                        }
                        else
                        { 
                          if(c != null && c.equals(""))
                          { 
                            final boolean tmpbuild275 = ds.manual.interpreter.Natives.isATermTuple_1(t);
                            if(tmpbuild275 == true)
                            { 
                              final org.spoofax.interpreter.terms.IStrategoTuple tmpbuild274 = ds.manual.interpreter.Natives.asATermTuple_1(t);
                              final org.spoofax.interpreter.terms.IStrategoTuple tupl = tmpbuild274;
                              final org.spoofax.interpreter.terms.IStrategoTerm[] tmpbuild273 = tupl.getAllSubterms();
                              final org.spoofax.interpreter.terms.IStrategoTerm[] elems = tmpbuild273;
                              final org.spoofax.interpreter.terms.IStrategoList tmpbuild272 = tf.makeList(elems);
                              final org.spoofax.interpreter.terms.IStrategoList elems_ = tmpbuild272;
                              final ml_1 tmpbuild271 = new ml_1(getSourceInfo(), pts);
                              final I_Matcher lifted_8 = tmpbuild271;
                              final ma_Result tmpresult59 = lifted_8.exec_ma(senv_1, ic_1, tf_1, venv_1, elems_, trace_1, sheap_1, bool_1, vheap_1);
                              final AValue lifted_7 = tmpresult59.value;
                              final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult59.get_1();
                              final ds.manual.interpreter.SState sheap_2 = tmpresult59.get_2();
                              final boolean bool_2 = tmpresult59.get_3();
                              final ds.manual.interpreter.VState vheap_2 = tmpresult59.get_4();
                              final S_1 tmpbuild270 = lifted_7.match(S_1.class);
                              if(tmpbuild270 != null)
                              { 
                                final org.spoofax.interpreter.terms.IStrategoTerm tupl_ = tmpbuild270.get_1();
                                final S_1 tmpbuild269 = new S_1(t);
                                final AValue lifted_2 = tmpbuild269;
                                final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_2;
                                final ds.manual.interpreter.SState lifted_4 = sheap_2;
                                final boolean lifted_5 = bool_2;
                                final ds.manual.interpreter.VState lifted_6 = vheap_2;
                                final AValue lifted_out_1 = lifted_2;
                                final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                                final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                                final boolean lifted_out_4 = lifted_5;
                                final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                                final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                                return ma_Result0;
                              }
                            }
                          }
                          else
                          { 
                            if(c == null || !c.equals("Nil"))
                            { 
                              if(c == null || !c.equals("Cons"))
                              { 
                                if(c == null || !c.equals(""))
                                { 
                                  final Op_2 tmpbuild279 = new Op_2(getSourceInfo(), c, pts);
                                  final mAppl_1 tmpbuild278 = new mAppl_1(getSourceInfo(), tmpbuild279);
                                  final I_Matcher lifted_8 = tmpbuild278;
                                  final ma_Result tmpresult60 = lifted_8.exec_ma(senv_1, ic_1, tf, venv_1, t, trace_1, sheap_1, bool_1, vheap_1);
                                  final AValue lifted_7 = tmpresult60.value;
                                  final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult60.get_1();
                                  final ds.manual.interpreter.SState sheap_2 = tmpresult60.get_2();
                                  final boolean bool_2 = tmpresult60.get_3();
                                  final ds.manual.interpreter.VState vheap_2 = tmpresult60.get_4();
                                  final S_1 tmpbuild277 = lifted_7.match(S_1.class);
                                  if(tmpbuild277 != null)
                                  { 
                                    final org.spoofax.interpreter.terms.IStrategoTerm t_ = tmpbuild277.get_1();
                                    final S_1 tmpbuild276 = new S_1(t);
                                    final AValue lifted_2 = tmpbuild276;
                                    final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_2;
                                    final ds.manual.interpreter.SState lifted_4 = sheap_2;
                                    final boolean lifted_5 = bool_2;
                                    final ds.manual.interpreter.VState lifted_6 = vheap_2;
                                    final AValue lifted_out_1 = lifted_2;
                                    final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                                    final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                                    final boolean lifted_out_4 = lifted_5;
                                    final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                                    final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                                    return ma_Result0;
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
      final F_0 tmpbuild281 = new F_0();
      final AValue lifted_2 = tmpbuild281;
      final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
      final ds.manual.interpreter.SState lifted_4 = sheap_1;
      final boolean lifted_5 = bool_1;
      final ds.manual.interpreter.VState lifted_6 = vheap_1;
      final AValue lifted_out_1 = lifted_2;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
      final boolean lifted_out_4 = lifted_5;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
      final ma_Result ma_Result0 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return ma_Result0;
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
    final m_1 other = (m_1)obj;
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