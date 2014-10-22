package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class bindtvars_2 extends NoOpNode implements I_Binder
{ 
  @Children public INodeList<String> _1;

  @Children public INodeList<org.spoofax.interpreter.terms.IStrategoTerm> _2;

  public bindtvars_2 (INodeSource source, INodeList<String> _1, INodeList<org.spoofax.interpreter.terms.IStrategoTerm> _2) 
  { 
    this.setSourceInfo(source);
    this._1 = _1;
    this._2 = _2;
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      hasSpecialized = true;
    }
  }

  public bindtvars_Result exec_bindtvars(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, ds.manual.interpreter.VState lifted_in_2)
  { 
    this.specializeChildren(0);
    final INodeList<String> lifted_1 = _1;
    final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> lifted_2 = _2;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_1;
    final ds.manual.interpreter.VState vheap_1 = lifted_in_2;
    if(lifted_1 != null && lifted_1.isEmpty())
    { 
      if(lifted_2 != null && lifted_2.isEmpty())
      { 
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
        final ds.manual.interpreter.VState lifted_3 = vheap_1;
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_out_1 = e;
        final ds.manual.interpreter.VState lifted_out_2 = lifted_3;
        final bindtvars_Result bindtvars_Result0 = new bindtvars_Result(lifted_out_1, lifted_out_2);
        return bindtvars_Result0;
      }
    }
    else
    { 
      if(lifted_1 != null && !lifted_1.isEmpty())
      { 
        final String x = lifted_1.head();
        final INodeList<String> xxs = lifted_1.tail();
        if(lifted_2 != null && !lifted_2.isEmpty())
        { 
          final org.spoofax.interpreter.terms.IStrategoTerm t = lifted_2.head();
          final INodeList<org.spoofax.interpreter.terms.IStrategoTerm> txs = lifted_2.tail();
          final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
          final S_1 tmpbuild467 = new S_1(t);
          final VPushUpdate_3 tmpbuild466 = new VPushUpdate_3(getSourceInfo(), e, x, tmpbuild467);
          final I_VHeapOp lifted_4 = tmpbuild466;
          final vinit_Result tmpresult88 = lifted_4.exec_vinit(vheap_1);
          final com.github.krukow.clj_ds.PersistentMap<Object, Object> e_ = tmpresult88.value;
          final ds.manual.interpreter.VState vheap_2 = tmpresult88.get_1();
          final bindtvars_2 tmpbuild465 = new bindtvars_2(getSourceInfo(), xxs, txs);
          final I_Binder lifted_5 = tmpbuild465;
          final bindtvars_Result tmpresult87 = lifted_5.exec_bindtvars(e_, vheap_2);
          final com.github.krukow.clj_ds.PersistentMap<Object, Object> e__ = tmpresult87.value;
          final ds.manual.interpreter.VState vheap_3 = tmpresult87.get_1();
          final ds.manual.interpreter.VState lifted_3 = vheap_3;
          final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_out_1 = e__;
          final ds.manual.interpreter.VState lifted_out_2 = lifted_3;
          final bindtvars_Result bindtvars_Result0 = new bindtvars_Result(lifted_out_1, lifted_out_2);
          return bindtvars_Result0;
        }
      }
    }
    throw new InterpreterException("Rule failure");
  }

  public INodeList<String> get_1()
  { 
    return this._1;
  }

  public INodeList<org.spoofax.interpreter.terms.IStrategoTerm> get_2()
  { 
    return this._2;
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
    final bindtvars_2 other = (bindtvars_2)obj;
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
    return true;
  }
}