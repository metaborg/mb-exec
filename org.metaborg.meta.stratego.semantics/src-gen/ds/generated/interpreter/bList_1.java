package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class bList_1 extends NoOpNode implements I_Builder
{ 
  @Child public I_PreTerm _1;

  public bList_1 (INodeSource source, I_PreTerm _1) 
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
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
    final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_2;
    final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_3;
    final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_4;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_5;
    final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
    final boolean bool_1 = lifted_in_8;
    final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
    final Op_2 tmpbuild417 = lifted_1.match(Op_2.class);
    if(tmpbuild417 != null)
    { 
      final String c = tmpbuild417.get_1();
      final INodeList<I_STerm> ts = tmpbuild417.get_2();
      final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
      if(ts != null && ts.isEmpty())
      { 
        final org.spoofax.interpreter.terms.IStrategoList tmpbuild408 = ds.manual.interpreter.Natives.makeNil_1(tf);
        final org.spoofax.interpreter.terms.IStrategoList nil = tmpbuild408;
        final BS_1 tmpbuild407 = new BS_1(getSourceInfo(), nil);
        final I_BuildRes lifted_2 = tmpbuild407;
        final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
        final ds.manual.interpreter.SState lifted_4 = sheap_1;
        final boolean lifted_5 = bool_1;
        final ds.manual.interpreter.VState lifted_6 = vheap_1;
        final I_BuildRes lifted_out_1 = lifted_2;
        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
        final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
        final boolean lifted_out_4 = lifted_5;
        final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
        final bld_Result bld_Result2 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
        return bld_Result2;
      }
      else
      { 
        if(ts != null && !ts.isEmpty())
        { 
          final I_STerm t1 = ts.head();
          final INodeList<I_STerm> lifted_7 = ts.tail();
          if(lifted_7 != null && !lifted_7.isEmpty())
          { 
            final I_STerm t2 = lifted_7.head();
            final INodeList<I_STerm> lifted_8 = lifted_7.tail();
            if(lifted_8 != null && lifted_8.isEmpty())
            { 
              final Build_1 tmpbuild416 = new Build_1(getSourceInfo(), t1);
              final I_Strategy lifted_10 = tmpbuild416;
              final default_Result tmpresult79 = lifted_10.exec_default(senv_1, venv_1, ic_1, tf, t_1, trace_1, sheap_1, bool_1, vheap_1);
              final AValue lifted_9 = tmpresult79.value;
              final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult79.get_1();
              final ds.manual.interpreter.SState sheap_2 = tmpresult79.get_2();
              final boolean bool_2 = tmpresult79.get_3();
              final ds.manual.interpreter.VState vheap_2 = tmpresult79.get_4();
              final S_1 tmpbuild415 = lifted_9.match(S_1.class);
              if(tmpbuild415 != null)
              { 
                final org.spoofax.interpreter.terms.IStrategoTerm head = tmpbuild415.get_1();
                final Build_1 tmpbuild414 = new Build_1(getSourceInfo(), t2);
                final I_Strategy lifted_12 = tmpbuild414;
                final default_Result tmpresult78 = lifted_12.exec_default(senv_1, venv_1, ic_1, tf, t_1, trace_2, sheap_2, bool_2, vheap_2);
                final AValue lifted_11 = tmpresult78.value;
                final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult78.get_1();
                final ds.manual.interpreter.SState sheap_3 = tmpresult78.get_2();
                final boolean bool_3 = tmpresult78.get_3();
                final ds.manual.interpreter.VState vheap_3 = tmpresult78.get_4();
                final S_1 tmpbuild413 = lifted_11.match(S_1.class);
                if(tmpbuild413 != null)
                { 
                  final org.spoofax.interpreter.terms.IStrategoTerm tail = tmpbuild413.get_1();
                  final boolean tmpbuild412 = ds.manual.interpreter.Natives.isATermList_1(tail);
                  if(tmpbuild412 == true)
                  { 
                    final org.spoofax.interpreter.terms.IStrategoList tmpbuild411 = ds.manual.interpreter.Natives.asATermList_1(tail);
                    final org.spoofax.interpreter.terms.IStrategoList tail_ = tmpbuild411;
                    final org.spoofax.interpreter.terms.IStrategoList tmpbuild410 = tf.makeListCons(head, tail_);
                    final org.spoofax.interpreter.terms.IStrategoList list = tmpbuild410;
                    final BS_1 tmpbuild409 = new BS_1(getSourceInfo(), list);
                    final I_BuildRes lifted_2 = tmpbuild409;
                    final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_3;
                    final ds.manual.interpreter.SState lifted_4 = sheap_3;
                    final boolean lifted_5 = bool_3;
                    final ds.manual.interpreter.VState lifted_6 = vheap_3;
                    final I_BuildRes lifted_out_1 = lifted_2;
                    final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
                    final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
                    final boolean lifted_out_4 = lifted_5;
                    final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
                    final bld_Result bld_Result2 = new bld_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
                    return bld_Result2;
                  }
                }
              }
            }
          }
        }
      }
    }
    throw new InterpreterException("Rule failure");
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
    final bList_1 other = (bList_1)obj;
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