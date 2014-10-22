package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Match_1 extends NoOpNode implements I_Strategy
{ 
  @Child public I_STerm _1;

  public Match_1 (INodeSource source, I_STerm _1) 
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
      final Wld_0 tmpbuild125 = lifted_1.match(Wld_0.class);
      if(tmpbuild125 != null)
      { 
        final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
        final S_1 tmpbuild126 = new S_1(t);
        final AValue lifted_2 = tmpbuild126;
        final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
        final ds.manual.interpreter.SState lifted_4 = sheap_1;
        final boolean lifted_5 = bool_1;
        final ds.manual.interpreter.VState lifted_6 = vheap_1;
        final AValue lifted_out_1 = lifted_2;
        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
        final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
        final boolean lifted_out_4 = lifted_5;
        final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
        final default_Result default_Result9 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
        return default_Result9;
      }
      else
      { 
        final NoAnnoList_1 tmpbuild127 = lifted_1.match(NoAnnoList_1.class);
        if(tmpbuild127 != null)
        { 
          final I_PreTerm mp = tmpbuild127.get_1();
          final m_1 tmpbuild128 = new m_1(getSourceInfo(), mp);
          final I_Matcher lifted_6 = tmpbuild128;
          final ma_Result tmpresult24 = lifted_6.exec_ma(senv_1, ic_1, tf_1, venv_1, t_1, trace_1, sheap_1, bool_1, vheap_1);
          final AValue v = tmpresult24.value;
          final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult24.get_1();
          final ds.manual.interpreter.SState sheap_2 = tmpresult24.get_2();
          final boolean bool_2 = tmpresult24.get_3();
          final ds.manual.interpreter.VState vheap_2 = tmpresult24.get_4();
          final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
          final ds.manual.interpreter.SState lifted_3 = sheap_2;
          final boolean lifted_4 = bool_2;
          final ds.manual.interpreter.VState lifted_5 = vheap_2;
          final AValue lifted_out_1 = v;
          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
          final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
          final boolean lifted_out_4 = lifted_4;
          final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
          final default_Result default_Result9 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
          return default_Result9;
        }
        else
        { 
          final Anno_2 tmpbuild129 = lifted_1.match(Anno_2.class);
          if(tmpbuild129 != null)
          { 
            final I_PreTerm mp = tmpbuild129.get_1();
            final I_PreTerm ma = tmpbuild129.get_2();
            final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
            final m_1 tmpbuild135 = new m_1(getSourceInfo(), mp);
            final I_Matcher lifted_7 = tmpbuild135;
            final ma_Result tmpresult26 = lifted_7.exec_ma(senv_1, ic_1, tf_1, venv_1, t, trace_1, sheap_1, bool_1, vheap_1);
            final AValue lifted_6 = tmpresult26.value;
            final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult26.get_1();
            final ds.manual.interpreter.SState sheap_2 = tmpresult26.get_2();
            final boolean bool_2 = tmpresult26.get_3();
            final ds.manual.interpreter.VState vheap_2 = tmpresult26.get_4();
            final S_1 tmpbuild134 = lifted_6.match(S_1.class);
            if(tmpbuild134 != null)
            { 
              final org.spoofax.interpreter.terms.IStrategoTerm t_ = tmpbuild134.get_1();
              final org.spoofax.interpreter.terms.IStrategoList tmpbuild133 = t.getAnnotations();
              final org.spoofax.interpreter.terms.IStrategoList aa = tmpbuild133;
              final m_1 tmpbuild132 = new m_1(getSourceInfo(), ma);
              final I_Matcher lifted_9 = tmpbuild132;
              final ma_Result tmpresult25 = lifted_9.exec_ma(senv_1, ic_1, tf_1, venv_1, aa, trace_2, sheap_2, bool_2, vheap_2);
              final AValue lifted_8 = tmpresult25.value;
              final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult25.get_1();
              final ds.manual.interpreter.SState sheap_3 = tmpresult25.get_2();
              final boolean bool_3 = tmpresult25.get_3();
              final ds.manual.interpreter.VState vheap_3 = tmpresult25.get_4();
              final S_1 tmpbuild131 = lifted_8.match(S_1.class);
              if(tmpbuild131 != null)
              { 
                final org.spoofax.interpreter.terms.IStrategoTerm t__ = tmpbuild131.get_1();
                final S_1 tmpbuild130 = new S_1(t);
                final AValue v = tmpbuild130;
                final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_3;
                final ds.manual.interpreter.SState lifted_3 = sheap_3;
                final boolean lifted_4 = bool_3;
                final ds.manual.interpreter.VState lifted_5 = vheap_3;
                final AValue lifted_out_1 = v;
                final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                final boolean lifted_out_4 = lifted_4;
                final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                final default_Result default_Result9 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                return default_Result9;
              }
            }
          }
          else
          { 
            final Var_1 tmpbuild136 = lifted_1.match(Var_1.class);
            if(tmpbuild136 != null)
            { 
              final String x = tmpbuild136.get_1();
              final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
              final Var_1 tmpbuild140 = new Var_1(getSourceInfo(), x);
              final m_1 tmpbuild139 = new m_1(getSourceInfo(), tmpbuild140);
              final I_Matcher lifted_7 = tmpbuild139;
              final ma_Result tmpresult27 = lifted_7.exec_ma(senv_1, ic_1, tf_1, venv_1, t, trace_1, sheap_1, bool_1, vheap_1);
              final AValue lifted_6 = tmpresult27.value;
              final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult27.get_1();
              final ds.manual.interpreter.SState sheap_2 = tmpresult27.get_2();
              final boolean bool_2 = tmpresult27.get_3();
              final ds.manual.interpreter.VState vheap_2 = tmpresult27.get_4();
              final S_1 tmpbuild138 = lifted_6.match(S_1.class);
              if(tmpbuild138 != null)
              { 
                final org.spoofax.interpreter.terms.IStrategoTerm t_ = tmpbuild138.get_1();
                final S_1 tmpbuild137 = new S_1(t);
                final AValue v = tmpbuild137;
                final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                final ds.manual.interpreter.SState lifted_3 = sheap_2;
                final boolean lifted_4 = bool_2;
                final ds.manual.interpreter.VState lifted_5 = vheap_2;
                final AValue lifted_out_1 = v;
                final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                final boolean lifted_out_4 = lifted_4;
                final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                final default_Result default_Result9 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                return default_Result9;
              }
            }
            else
            { 
              final As_2 tmpbuild143 = lifted_1.match(As_2.class);
              if(tmpbuild143 != null)
              { 
                final I_Var var = tmpbuild143.get_1();
                final I_PreTerm p = tmpbuild143.get_2();
                final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
                final As_2 tmpbuild142 = new As_2(getSourceInfo(), var, p);
                final m_1 tmpbuild141 = new m_1(getSourceInfo(), tmpbuild142);
                final I_Matcher lifted_6 = tmpbuild141;
                final ma_Result tmpresult28 = lifted_6.exec_ma(senv_1, ic_1, tf_1, venv_1, t, trace_1, sheap_1, bool_1, vheap_1);
                final AValue v = tmpresult28.value;
                final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult28.get_1();
                final ds.manual.interpreter.SState sheap_2 = tmpresult28.get_2();
                final boolean bool_2 = tmpresult28.get_3();
                final ds.manual.interpreter.VState vheap_2 = tmpresult28.get_4();
                final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_2;
                final ds.manual.interpreter.SState lifted_3 = sheap_2;
                final boolean lifted_4 = bool_2;
                final ds.manual.interpreter.VState lifted_5 = vheap_2;
                final AValue lifted_out_1 = v;
                final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                final boolean lifted_out_4 = lifted_4;
                final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                final default_Result default_Result9 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                return default_Result9;
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
      final I_STerm x = lifted_1;
      final F_0 tmpbuild144 = new F_0();
      final AValue v = tmpbuild144;
      final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_1;
      final ds.manual.interpreter.SState lifted_3 = sheap_1;
      final boolean lifted_4 = bool_1;
      final ds.manual.interpreter.VState lifted_5 = vheap_1;
      final AValue lifted_out_1 = v;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
      final boolean lifted_out_4 = lifted_4;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
      final default_Result default_Result9 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return default_Result9;
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
    final Match_1 other = (Match_1)obj;
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