package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class mkThunks_1 extends NoOpNode implements I_Thunker
{ 
  @Children public INodeList<I_Strategy> _1;

  public mkThunks_1 (INodeSource source, INodeList<I_Strategy> _1) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChildren(_1);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      for(I_Strategy _1_elem : _1)
      { 
        if(_1_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_1_elem).specialize(depth);
        }
      }
      hasSpecialized = true;
    }
  }

  public thunks_Result exec_thunks(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_2, ds.manual.interpreter.SState lifted_in_3)
  { 
    this.specializeChildren(0);
    final INodeList<I_Strategy> lifted_1 = _1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_2;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_3;
    if(lifted_1 != null && lifted_1.isEmpty())
    { 
      final INodeList<I_Node> tmpbuild509 = NodeList.NIL(I_Node.class);
      final INodeList<I_Thunk> tmpbuild508 = ds.manual.interpreter.Natives.asListOfThunks_1(tmpbuild509);
      final INodeList<I_Thunk> lifted_2 = tmpbuild508;
      final ds.manual.interpreter.SState lifted_3 = sheap_1;
      final INodeList<I_Thunk> lifted_out_1 = lifted_2;
      final ds.manual.interpreter.SState lifted_out_2 = lifted_3;
      final thunks_Result thunks_Result0 = new thunks_Result(lifted_out_1, lifted_out_2);
      return thunks_Result0;
    }
    else
    { 
      if(lifted_1 != null && !lifted_1.isEmpty())
      { 
        final I_Strategy as = lifted_1.head();
        final INodeList<I_Strategy> ass = lifted_1.tail();
        final mkThunk_1 tmpbuild512 = new mkThunk_1(getSourceInfo(), as);
        final I_Thunker lifted_4 = tmpbuild512;
        final thunk_Result tmpresult102 = lifted_4.exec_thunk(venv_1, senv_1, sheap_1);
        final I_Thunk as_ = tmpresult102.value;
        final ds.manual.interpreter.SState sheap_2 = tmpresult102.get_1();
        final mkThunks_1 tmpbuild511 = new mkThunks_1(getSourceInfo(), ass);
        final I_Thunker lifted_5 = tmpbuild511;
        final thunks_Result tmpresult101 = lifted_5.exec_thunks(venv_1, senv_1, sheap_2);
        final INodeList<I_Thunk> ass_ = tmpresult101.value;
        final ds.manual.interpreter.SState sheap_3 = tmpresult101.get_1();
        final INodeList<I_Thunk> tmpbuild510 = new NodeList<I_Thunk>(as_, ass_);
        final INodeList<I_Thunk> lifted_2 = tmpbuild510;
        final ds.manual.interpreter.SState lifted_3 = sheap_3;
        final INodeList<I_Thunk> lifted_out_1 = lifted_2;
        final ds.manual.interpreter.SState lifted_out_2 = lifted_3;
        final thunks_Result thunks_Result0 = new thunks_Result(lifted_out_1, lifted_out_2);
        return thunks_Result0;
      }
    }
    throw new InterpreterException("Rule failure");
  }

  public INodeList<I_Strategy> get_1()
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
    final mkThunks_1 other = (mkThunks_1)obj;
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