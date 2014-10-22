package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class oneOrMore_1 extends NoOpNode implements I_Somer
{ 
  @Child public I_Strategy _1;

  public oneOrMore_1 (INodeSource source, I_Strategy _1) 
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

  public some_Result exec_some(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_2, ds.manual.interpreter.AutoInterpInteropContext lifted_in_3, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_4, org.spoofax.interpreter.terms.ITermFactory lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, ds.manual.interpreter.SState lifted_in_7, ds.manual.interpreter.VState lifted_in_8, boolean lifted_in_9)
  { 
    this.specializeChildren(0);
    final I_Strategy lifted_1 = _1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_2;
    final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_3;
    final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_4;
    final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_5;
    final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
    final ds.manual.interpreter.VState vheap_1 = lifted_in_8;
    final boolean bool_1 = lifted_in_9;
    final I_Strategy s = lifted_1;
    final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
    final org.spoofax.interpreter.terms.ITermFactory tf = tf_1;
    final boolean f = bool_1;
    final boolean tmpbuild464 = ds.manual.interpreter.Natives.isATermList_1(t);
    if(tmpbuild464 == true)
    { 
      final org.spoofax.interpreter.terms.IStrategoList tmpbuild463 = ds.manual.interpreter.Natives.asATermList_1(t);
      final org.spoofax.interpreter.terms.IStrategoList list = tmpbuild463;
      final boolean tmpbuild452 = list.isEmpty();
      if(tmpbuild452 == true)
      { 
        final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_1;
        final ds.manual.interpreter.SState lifted_3 = sheap_1;
        final ds.manual.interpreter.VState lifted_4 = vheap_1;
        final boolean lifted_5 = false;
        final org.spoofax.interpreter.terms.IStrategoList lifted_out_1 = list;
        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
        final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
        final ds.manual.interpreter.VState lifted_out_4 = lifted_4;
        final boolean lifted_out_5 = lifted_5;
        final some_Result some_Result0 = new some_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
        return some_Result0;
      }
      else
      { 
        final boolean tmpbuild462 = list.isEmpty();
        if(tmpbuild462 == false)
        { 
          final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild461 = list.head();
          final org.spoofax.interpreter.terms.IStrategoTerm head = tmpbuild461;
          final org.spoofax.interpreter.terms.IStrategoList tmpbuild460 = list.tail();
          final org.spoofax.interpreter.terms.IStrategoList tail = tmpbuild460;
          final default_Result tmpresult86 = s.exec_default(senv_1, venv_1, ic_1, tf_1, head, trace_1, sheap_1, bool_1, vheap_1);
          final AValue vhead = tmpresult86.value;
          final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult86.get_1();
          final ds.manual.interpreter.SState sheap_2 = tmpresult86.get_2();
          final boolean bool_2 = tmpresult86.get_3();
          final ds.manual.interpreter.VState vheap_2 = tmpresult86.get_4();
          final F_0 tmpbuild453 = vhead.match(F_0.class);
          if(tmpbuild453 != null)
          { 
            final oneOrMore_1 tmpbuild456 = new oneOrMore_1(getSourceInfo(), s);
            final I_Somer lifted_5 = tmpbuild456;
            final some_Result tmpresult84 = lifted_5.exec_some(senv_1, venv_1, ic_1, tail, tf_1, trace_2, sheap_2, vheap_2, f);
            final org.spoofax.interpreter.terms.IStrategoList tail_ = tmpresult84.value;
            final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult84.get_1();
            final ds.manual.interpreter.SState sheap_3 = tmpresult84.get_2();
            final ds.manual.interpreter.VState vheap_3 = tmpresult84.get_3();
            final boolean bool_3 = tmpresult84.get_4();
            final boolean f_ = bool_3;
            final org.spoofax.interpreter.terms.IStrategoList tmpbuild455 = tf.makeListCons(head, tail_);
            final org.spoofax.interpreter.terms.IStrategoList list_ = tmpbuild455;
            final boolean tmpbuild454 = ds.manual.interpreter.Natives.booleanOr_2(f, f_);
            final boolean f__ = tmpbuild454;
            final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_3;
            final ds.manual.interpreter.SState lifted_3 = sheap_3;
            final ds.manual.interpreter.VState lifted_4 = vheap_3;
            final org.spoofax.interpreter.terms.IStrategoList lifted_out_1 = list_;
            final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
            final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
            final ds.manual.interpreter.VState lifted_out_4 = lifted_4;
            final boolean lifted_out_5 = f__;
            final some_Result some_Result0 = new some_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
            return some_Result0;
          }
          else
          { 
            final S_1 tmpbuild459 = vhead.match(S_1.class);
            if(tmpbuild459 != null)
            { 
              final org.spoofax.interpreter.terms.IStrategoTerm head_ = tmpbuild459.get_1();
              final boolean lifted_6 = true;
              final oneOrMore_1 tmpbuild458 = new oneOrMore_1(getSourceInfo(), s);
              final I_Somer lifted_7 = tmpbuild458;
              final some_Result tmpresult85 = lifted_7.exec_some(senv_1, venv_1, ic_1, tail, tf_1, trace_2, sheap_2, vheap_2, lifted_6);
              final org.spoofax.interpreter.terms.IStrategoList tail_ = tmpresult85.value;
              final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult85.get_1();
              final ds.manual.interpreter.SState sheap_3 = tmpresult85.get_2();
              final ds.manual.interpreter.VState vheap_3 = tmpresult85.get_3();
              final boolean bool_3 = tmpresult85.get_4();
              final boolean f_ = bool_3;
              final org.spoofax.interpreter.terms.IStrategoList tmpbuild457 = tf.makeListCons(head_, tail_);
              final org.spoofax.interpreter.terms.IStrategoList list_ = tmpbuild457;
              final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_3;
              final ds.manual.interpreter.SState lifted_3 = sheap_3;
              final ds.manual.interpreter.VState lifted_4 = vheap_3;
              final boolean lifted_5 = true;
              final org.spoofax.interpreter.terms.IStrategoList lifted_out_1 = list_;
              final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
              final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
              final ds.manual.interpreter.VState lifted_out_4 = lifted_4;
              final boolean lifted_out_5 = lifted_5;
              final some_Result some_Result0 = new some_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
              return some_Result0;
            }
          }
        }
      }
    }
    throw new InterpreterException("Rule failure");
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
    final oneOrMore_1 other = (oneOrMore_1)obj;
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