package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class mkThunk_1 extends NoOpNode implements I_Thunker
{ 
  @Child public I_Strategy _1;

  public mkThunk_1 (INodeSource source, I_Strategy _1) 
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

  public thunk_Result exec_thunk(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_2, ds.manual.interpreter.SState lifted_in_3)
  { 
    this.specializeChildren(0);
    final I_Strategy lifted_1 = _1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_2;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_3;
    final I_Strategy s = lifted_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> d = senv_1;
    final CallT_3 tmpbuild498 = s.match(CallT_3.class);
    if(tmpbuild498 != null)
    { 
      final I_SVar lifted_3 = tmpbuild498.get_1();
      final INodeList<I_Strategy> lifted_4 = tmpbuild498.get_2();
      final INodeList<I_STerm> lifted_5 = tmpbuild498.get_3();
      final SVar_1 tmpbuild502 = lifted_3.match(SVar_1.class);
      if(tmpbuild502 != null)
      { 
        final String tgt = tmpbuild502.get_1();
        final INodeList<I_Strategy> ass = lifted_4;
        final INodeList<I_STerm> ats = lifted_5;
        final SLookup_2 tmpbuild501 = new SLookup_2(getSourceInfo(), d, tgt);
        final I_SHeapOp lifted_7 = tmpbuild501;
        final slook_Result tmpresult100 = lifted_7.exec_slook(sheap_1);
        final I_SLookupResult lifted_6 = tmpresult100.value;
        final ds.manual.interpreter.SState sheap_2 = tmpresult100.get_1();
        final SLookupResult_2 tmpbuild500 = lifted_6.match(SLookupResult_2.class);
        if(tmpbuild500 != null)
        { 
          final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_ = tmpbuild500.get_1();
          final I_Thunk thunk = tmpbuild500.get_2();
          final Thunk_6 tmpbuild499 = thunk.match(Thunk_6.class);
          if(tmpbuild499 != null)
          { 
            final String tgt_ = tmpbuild499.get_1();
            final INodeList<String> fss = tmpbuild499.get_2();
            final INodeList<String> fts = tmpbuild499.get_3();
            final I_Strategy sactual = tmpbuild499.get_4();
            final com.github.krukow.clj_ds.PersistentMap<Object, Object> e_tgt = tmpbuild499.get_5();
            final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_tgt = tmpbuild499.get_6();
            final ds.manual.interpreter.SState lifted_2 = sheap_2;
            final I_Thunk lifted_out_1 = thunk;
            final ds.manual.interpreter.SState lifted_out_2 = lifted_2;
            final thunk_Result thunk_Result0 = new thunk_Result(lifted_out_1, lifted_out_2);
            return thunk_Result0;
          }
        }
      }
    }
    else
    { 
      final CallT_3 tmpbuild507 = s.match(CallT_3.class);
      if(tmpbuild507 == null)
      { 
        final String tmpbuild506 = ds.manual.interpreter.Natives.createAnonymousName_1("lifted");
        final String x = tmpbuild506;
        final INodeList<String> tmpbuild504 = NodeList.NIL(String.class);
        final INodeList<String> tmpbuild505 = NodeList.NIL(String.class);
        final Thunk_6 tmpbuild503 = new Thunk_6(getSourceInfo(), x, tmpbuild504, tmpbuild505, s, e, d);
        final I_Thunk lifted_2 = tmpbuild503;
        final ds.manual.interpreter.SState lifted_3 = sheap_1;
        final I_Thunk lifted_out_1 = lifted_2;
        final ds.manual.interpreter.SState lifted_out_2 = lifted_3;
        final thunk_Result thunk_Result0 = new thunk_Result(lifted_out_1, lifted_out_2);
        return thunk_Result0;
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
    final mkThunk_1 other = (mkThunk_1)obj;
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