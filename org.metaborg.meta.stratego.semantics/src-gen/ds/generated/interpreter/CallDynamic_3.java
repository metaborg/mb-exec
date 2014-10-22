package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class CallDynamic_3 extends NoOpNode implements I_Strategy
{ 
  @Child public I_STerm _1;

  @Children public INodeList<I_Strategy> _2;

  @Children public INodeList<I_STerm> _3;

  public CallDynamic_3 (INodeSource source, I_STerm _1, INodeList<I_Strategy> _2, INodeList<I_STerm> _3) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChild(_1);
    this._2 = adoptChildren(_2);
    this._3 = adoptChildren(_3);
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
      for(I_Strategy _2_elem : _2)
      { 
        if(_2_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_2_elem).specialize(depth);
        }
      }
      for(I_STerm _3_elem : _3)
      { 
        if(_3_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_3_elem).specialize(depth);
        }
      }
      hasSpecialized = true;
    }
  }

  public default_Result exec_default(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_2, ds.manual.interpreter.AutoInterpInteropContext lifted_in_3, org.spoofax.interpreter.terms.ITermFactory lifted_in_4, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, ds.manual.interpreter.SState lifted_in_7, boolean lifted_in_8, ds.manual.interpreter.VState lifted_in_9)
  { 
    this.specializeChildren(0);
    final I_STerm lifted_1 = _1;
    final INodeList<I_Strategy> lifted_2 = _2;
    final INodeList<I_STerm> lifted_3 = _3;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_2;
    final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_3;
    final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_4;
    final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_5;
    final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
    final boolean bool_1 = lifted_in_8;
    final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
    final I_STerm target = lifted_1;
    final INodeList<I_Strategy> ass = lifted_2;
    final INodeList<I_STerm> ats = lifted_3;
    final Build_1 tmpbuild153 = new Build_1(getSourceInfo(), target);
    final I_Strategy lifted_9 = tmpbuild153;
    final default_Result tmpresult30 = lifted_9.exec_default(senv_1, venv_1, ic_1, tf_1, t_1, trace_1, sheap_1, bool_1, vheap_1);
    final AValue lifted_8 = tmpresult30.value;
    final org.spoofax.interpreter.core.StackTracer trace_2 = tmpresult30.get_1();
    final ds.manual.interpreter.SState sheap_2 = tmpresult30.get_2();
    final boolean bool_2 = tmpresult30.get_3();
    final ds.manual.interpreter.VState vheap_2 = tmpresult30.get_4();
    final S_1 tmpbuild152 = lifted_8.match(S_1.class);
    if(tmpbuild152 != null)
    { 
      final org.spoofax.interpreter.terms.IStrategoTerm sname_aterm = tmpbuild152.get_1();
      final boolean tmpbuild151 = ds.manual.interpreter.Natives.isATermString_1(sname_aterm);
      if(tmpbuild151 == true)
      { 
        final org.spoofax.interpreter.terms.IStrategoString tmpbuild150 = ds.manual.interpreter.Natives.asATermString_1(sname_aterm);
        final org.spoofax.interpreter.terms.IStrategoString sname_aterm_str = tmpbuild150;
        final String tmpbuild149 = sname_aterm_str.stringValue();
        final String sname = tmpbuild149;
        final SVar_1 tmpbuild148 = new SVar_1(getSourceInfo(), sname);
        final CallT_3 tmpbuild147 = new CallT_3(getSourceInfo(), tmpbuild148, ass, ats);
        final I_Strategy lifted_10 = tmpbuild147;
        final default_Result tmpresult29 = lifted_10.exec_default(senv_1, venv_1, ic_1, tf_1, t_1, trace_2, sheap_2, bool_2, vheap_2);
        final AValue v = tmpresult29.value;
        final org.spoofax.interpreter.core.StackTracer trace_3 = tmpresult29.get_1();
        final ds.manual.interpreter.SState sheap_3 = tmpresult29.get_2();
        final boolean bool_3 = tmpresult29.get_3();
        final ds.manual.interpreter.VState vheap_3 = tmpresult29.get_4();
        final org.spoofax.interpreter.core.StackTracer lifted_4 = trace_3;
        final ds.manual.interpreter.SState lifted_5 = sheap_3;
        final boolean lifted_6 = bool_3;
        final ds.manual.interpreter.VState lifted_7 = vheap_3;
        final AValue lifted_out_1 = v;
        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_4;
        final ds.manual.interpreter.SState lifted_out_3 = lifted_5;
        final boolean lifted_out_4 = lifted_6;
        final ds.manual.interpreter.VState lifted_out_5 = lifted_7;
        final default_Result default_Result12 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
        return default_Result12;
      }
    }
    throw new InterpreterException("Rule failure");
  }

  public I_STerm get_1()
  { 
    if(this._1 instanceof IGenericNode)
    { 
      ((IGenericNode)this._1).specialize(1);
    }
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

  public INodeList<I_STerm> get_3()
  { 
    if(this._3 instanceof IGenericNode)
    { 
      ((IGenericNode)this._3).specialize(1);
    }
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
    final CallDynamic_3 other = (CallDynamic_3)obj;
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
    if(_3 == null)
    { 
      if(other._3 != null)
      { 
        return false;
      }
    }
    else
      if(!_3.equals(other._3))
      { 
        return false;
      }
    return true;
  }
}