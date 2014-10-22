package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class map_1 extends NoOpNode implements I_Mapper
{ 
  @Child public I_Strategy _1;

  public map_1 (INodeSource source, I_Strategy _1) 
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

  public map_Result exec_map(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_2, ds.manual.interpreter.AutoInterpInteropContext lifted_in_3, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_4, org.spoofax.interpreter.terms.ITermFactory lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, ds.manual.interpreter.SState lifted_in_7, boolean lifted_in_8, ds.manual.interpreter.VState lifted_in_9)
  { 
    this.specializeChildren(0);
    final I_Strategy lifted_1 = _1;
    { 
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_2;
      final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_3;
      final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_4;
      final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_5;
      final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
      final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
      final boolean bool_1 = lifted_in_8;
      final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
      final I_Strategy s = lifted_1;
      final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
      final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
      final boolean tmpbuild431 = ds.manual.interpreter.Natives.isATermList_1(t);
      if(tmpbuild431 == true)
      { 
        final org.spoofax.interpreter.terms.IStrategoList tmpbuild430 = ds.manual.interpreter.Natives.asATermList_1(t);
        final org.spoofax.interpreter.terms.IStrategoList lt = tmpbuild430;
        final boolean tmpbuild418 = lt.isEmpty();
        if(tmpbuild418 == true)
        { 
          final S_1 tmpbuild419 = new S_1(t);
          final AValue v = tmpbuild419;
          final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_1;
          final ds.manual.interpreter.SState lifted_3 = sheap_1;
          final boolean lifted_4 = bool_1;
          final ds.manual.interpreter.VState lifted_5 = vheap_1;
          final AValue lifted_out_1 = v;
          final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
          final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
          final boolean lifted_out_4 = lifted_4;
          final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
          final map_Result map_Result0 = new map_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
          return map_Result0;
        }
        else
        { 
          final boolean tmpbuild429 = lt.isEmpty();
          if(tmpbuild429 == false)
          { 
            final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild428 = lt.head();
            final org.spoofax.interpreter.terms.IStrategoTerm th = tmpbuild428;
            final org.spoofax.interpreter.terms.IStrategoList tmpbuild427 = lt.tail();
            final org.spoofax.interpreter.terms.IStrategoList ts = tmpbuild427;
            final default_Result tmpresult81 = s.exec_default(senv_1, venv_1, ic_1, tf_1, th, trace_1, sheap_1, bool_1, vheap_1);
            final AValue lifted_6 = tmpresult81.value;
            final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult81.get_1();
            final ds.manual.interpreter.SState sheap_2 = tmpresult81.get_2();
            final boolean bool_2 = tmpresult81.get_3();
            final ds.manual.interpreter.VState vheap_2 = tmpresult81.get_4();
            final S_1 tmpbuild426 = lifted_6.match(S_1.class);
            if(tmpbuild426 != null)
            { 
              final org.spoofax.interpreter.terms.IStrategoTerm th_ = tmpbuild426.get_1();
              final map_1 tmpbuild425 = new map_1(getSourceInfo(), s);
              final I_Mapper lifted_8 = tmpbuild425;
              final map_Result tmpresult80 = lifted_8.exec_map(senv_1, venv_1, ic_1, ts, tf_1, trace_2, sheap_2, bool_2, vheap_2);
              final AValue lifted_7 = tmpresult80.value;
              final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult80.get_1();
              final ds.manual.interpreter.SState sheap_3 = tmpresult80.get_2();
              final boolean bool_3 = tmpresult80.get_3();
              final ds.manual.interpreter.VState vheap_3 = tmpresult80.get_4();
              final S_1 tmpbuild424 = lifted_7.match(S_1.class);
              if(tmpbuild424 != null)
              { 
                final org.spoofax.interpreter.terms.IStrategoTerm ts_ = tmpbuild424.get_1();
                final boolean tmpbuild423 = ds.manual.interpreter.Natives.isATermList_1(ts_);
                if(tmpbuild423 == true)
                { 
                  final org.spoofax.interpreter.terms.IStrategoList tmpbuild422 = ds.manual.interpreter.Natives.asATermList_1(ts_);
                  final org.spoofax.interpreter.terms.IStrategoList ats = tmpbuild422;
                  final org.spoofax.interpreter.terms.IStrategoList tmpbuild421 = tf.makeListCons(th_, ats);
                  final org.spoofax.interpreter.terms.IStrategoList ts__ = tmpbuild421;
                  final S_1 tmpbuild420 = new S_1(ts__);
                  final AValue v = tmpbuild420;
                  final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_3;
                  final ds.manual.interpreter.SState lifted_3 = sheap_3;
                  final boolean lifted_4 = bool_3;
                  final ds.manual.interpreter.VState lifted_5 = vheap_3;
                  final AValue lifted_out_1 = v;
                  final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
                  final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
                  final boolean lifted_out_4 = lifted_4;
                  final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
                  final map_Result map_Result0 = new map_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                  return map_Result0;
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
      final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_4;
      final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_5;
      final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
      final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
      final boolean bool_1 = lifted_in_8;
      final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
      final I_Strategy s = lifted_1;
      final F_0 tmpbuild432 = new F_0();
      final AValue lifted_2 = tmpbuild432;
      final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
      final ds.manual.interpreter.SState lifted_4 = sheap_1;
      final boolean lifted_5 = bool_1;
      final ds.manual.interpreter.VState lifted_6 = vheap_1;
      final AValue lifted_out_1 = lifted_2;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
      final boolean lifted_out_4 = lifted_5;
      final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
      final map_Result map_Result0 = new map_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
      return map_Result0;
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
    final map_1 other = (map_1)obj;
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