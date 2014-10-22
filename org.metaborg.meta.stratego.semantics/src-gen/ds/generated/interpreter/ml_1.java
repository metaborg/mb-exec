package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class ml_1 extends NoOpNode implements I_Matcher
{ 
  @Children public INodeList<I_STerm> _1;

  public ml_1 (INodeSource source, INodeList<I_STerm> _1) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChildren(_1);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      for(I_STerm _1_elem : _1)
      { 
        if(_1_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_1_elem).specialize(depth);
        }
      }
      hasSpecialized = true;
    }
  }

  public ma_Result exec_ma(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, ds.manual.interpreter.AutoInterpInteropContext lifted_in_2, org.spoofax.interpreter.terms.ITermFactory lifted_in_3, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_4, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, ds.manual.interpreter.SState lifted_in_7, boolean lifted_in_8, ds.manual.interpreter.VState lifted_in_9)
  { 
    this.specializeChildren(0);
    final INodeList<I_STerm> lifted_1 = _1;
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
      if(lifted_1 != null && lifted_1.isEmpty())
      { 
        final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
        final boolean tmpbuild285 = ds.manual.interpreter.Natives.isATermList_1(t);
        if(tmpbuild285 == true)
        { 
          final org.spoofax.interpreter.terms.IStrategoList tmpbuild284 = ds.manual.interpreter.Natives.asATermList_1(t);
          final org.spoofax.interpreter.terms.IStrategoList alist = tmpbuild284;
          final boolean tmpbuild283 = alist.isEmpty();
          if(tmpbuild283 == true)
          { 
            final S_1 tmpbuild282 = new S_1(t);
            final AValue lifted_2 = tmpbuild282;
            final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
            final ds.manual.interpreter.SState lifted_4 = sheap_1;
            final boolean lifted_5 = bool_1;
            final ds.manual.interpreter.VState lifted_6 = vheap_1;
            final AValue lifted_out_1 = lifted_2;
            final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
            final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
            final boolean lifted_out_4 = lifted_5;
            final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
            final ma_Result ma_Result1 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
            return ma_Result1;
          }
        }
      }
      else
      { 
        if(lifted_1 != null && !lifted_1.isEmpty())
        { 
          final I_STerm p = lifted_1.head();
          final INodeList<I_STerm> ps = lifted_1.tail();
          final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
          final boolean tmpbuild295 = ds.manual.interpreter.Natives.isATermList_1(t);
          if(tmpbuild295 == true)
          { 
            final org.spoofax.interpreter.terms.IStrategoList tmpbuild294 = ds.manual.interpreter.Natives.asATermList_1(t);
            final org.spoofax.interpreter.terms.IStrategoList alist = tmpbuild294;
            final boolean tmpbuild293 = alist.isEmpty();
            if(tmpbuild293 == false)
            { 
              final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild292 = alist.head();
              final org.spoofax.interpreter.terms.IStrategoTerm th = tmpbuild292;
              final Match_1 tmpbuild291 = new Match_1(getSourceInfo(), p);
              final I_Strategy lifted_8 = tmpbuild291;
              final default_Result tmpresult62 = lifted_8.exec_default(senv_1, venv_1, ic_1, tf_1, th, trace_1, sheap_1, bool_1, vheap_1);
              final AValue lifted_7 = tmpresult62.value;
              final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult62.get_1();
              final ds.manual.interpreter.SState sheap_2 = tmpresult62.get_2();
              final boolean bool_2 = tmpresult62.get_3();
              final ds.manual.interpreter.VState vheap_2 = tmpresult62.get_4();
              final S_1 tmpbuild290 = lifted_7.match(S_1.class);
              if(tmpbuild290 != null)
              { 
                final org.spoofax.interpreter.terms.IStrategoTerm t_ = tmpbuild290.get_1();
                final org.spoofax.interpreter.terms.IStrategoList tmpbuild289 = alist.tail();
                final org.spoofax.interpreter.terms.IStrategoList tl = tmpbuild289;
                final ml_1 tmpbuild288 = new ml_1(getSourceInfo(), ps);
                final I_Matcher lifted_10 = tmpbuild288;
                final ma_Result tmpresult61 = lifted_10.exec_ma(senv_1, ic_1, tf_1, venv_1, tl, trace_2, sheap_2, bool_2, vheap_2);
                final AValue lifted_9 = tmpresult61.value;
                final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult61.get_1();
                final ds.manual.interpreter.SState sheap_3 = tmpresult61.get_2();
                final boolean bool_3 = tmpresult61.get_3();
                final ds.manual.interpreter.VState vheap_3 = tmpresult61.get_4();
                final S_1 tmpbuild287 = lifted_9.match(S_1.class);
                if(tmpbuild287 != null)
                { 
                  final org.spoofax.interpreter.terms.IStrategoTerm t__ = tmpbuild287.get_1();
                  final S_1 tmpbuild286 = new S_1(t);
                  final AValue lifted_2 = tmpbuild286;
                  final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
                  final ds.manual.interpreter.SState lifted_4 = sheap_3;
                  final boolean lifted_5 = bool_3;
                  final ds.manual.interpreter.VState lifted_6 = vheap_3;
                  final AValue lifted_out_1 = lifted_2;
                  final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                  final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                  final boolean lifted_out_4 = lifted_5;
                  final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                  final ma_Result ma_Result1 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                  return ma_Result1;
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
      final INodeList<I_STerm> l = lifted_1;
      final F_0 tmpbuild296 = new F_0();
      final AValue lifted_2 = tmpbuild296;
      final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
      final ds.manual.interpreter.SState lifted_4 = sheap_1;
      final boolean lifted_5 = bool_1;
      final ds.manual.interpreter.VState lifted_6 = vheap_1;
      final AValue lifted_out_1 = lifted_2;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
      final boolean lifted_out_4 = lifted_5;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
      final ma_Result ma_Result1 = new ma_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return ma_Result1;
    }
  }

  public INodeList<I_STerm> get_1()
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
    final ml_1 other = (ml_1)obj;
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