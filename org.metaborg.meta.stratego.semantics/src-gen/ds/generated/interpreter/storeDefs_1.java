package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class storeDefs_1 extends NoOpNode implements I_Allocator
{ 
  @Children public INodeList<I_Def> _1;

  public storeDefs_1 (INodeSource source, INodeList<I_Def> _1) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChildren(_1);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      for(I_Def _1_elem : _1)
      { 
        if(_1_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_1_elem).specialize(depth);
        }
      }
      hasSpecialized = true;
    }
  }

  public storesdefs_Result exec_storesdefs(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_2, ds.manual.interpreter.SState lifted_in_3)
  { 
    this.specializeChildren(0);
    final INodeList<I_Def> lifted_1 = _1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_2;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_3;
    if(lifted_1 != null && lifted_1.isEmpty())
    { 
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> d = senv_1;
      final boolean v = true;
      final ds.manual.interpreter.SState lifted_2 = sheap_1;
      final boolean lifted_out_1 = v;
      final ds.manual.interpreter.SState lifted_out_2 = lifted_2;
      final storesdefs_Result storesdefs_Result0 = new storesdefs_Result(lifted_out_1, lifted_out_2);
      return storesdefs_Result0;
    }
    else
    { 
      if(lifted_1 != null && !lifted_1.isEmpty())
      { 
        final I_Def sdef = lifted_1.head();
        final INodeList<I_Def> sdefs = lifted_1.tail();
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> d = senv_1;
        final SDefT_4 tmpbuild542 = sdef.match(SDefT_4.class);
        if(tmpbuild542 != null)
        { 
          final String sname = tmpbuild542.get_1();
          final INodeList<I_Typedid> ss = tmpbuild542.get_2();
          final INodeList<I_Typedid> ts = tmpbuild542.get_3();
          final I_Strategy s = tmpbuild542.get_4();
          final typedIds_1 tmpbuild541 = new typedIds_1(getSourceInfo(), ss);
          final I_NameExtractor lifted_3 = tmpbuild541;
          final exid_Result tmpresult117 = lifted_3.exec_exid();
          final INodeList<String> ss_ = tmpresult117.value;
          final typedIds_1 tmpbuild540 = new typedIds_1(getSourceInfo(), ts);
          final I_NameExtractor lifted_4 = tmpbuild540;
          final exid_Result tmpresult116 = lifted_4.exec_exid();
          final INodeList<String> ts_ = tmpresult116.value;
          final Thunk_6 tmpbuild539 = new Thunk_6(getSourceInfo(), sname, ss_, ts_, s, e, d);
          final SUpdate_3 tmpbuild538 = new SUpdate_3(getSourceInfo(), d, sname, tmpbuild539);
          final I_SHeapOp lifted_5 = tmpbuild538;
          final salloc_Result tmpresult115 = lifted_5.exec_salloc(sheap_1);
          final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_ = tmpresult115.value;
          final ds.manual.interpreter.SState sheap_2 = tmpresult115.get_1();
          final storeDefs_1 tmpbuild537 = new storeDefs_1(getSourceInfo(), sdefs);
          final I_Allocator lifted_6 = tmpbuild537;
          final storesdefs_Result tmpresult114 = lifted_6.exec_storesdefs(e, d, sheap_2);
          final boolean b = tmpresult114.value;
          final ds.manual.interpreter.SState sheap_3 = tmpresult114.get_1();
          final ds.manual.interpreter.SState lifted_2 = sheap_3;
          final boolean lifted_out_1 = b;
          final ds.manual.interpreter.SState lifted_out_2 = lifted_2;
          final storesdefs_Result storesdefs_Result0 = new storesdefs_Result(lifted_out_1, lifted_out_2);
          return storesdefs_Result0;
        }
      }
    }
    throw new InterpreterException("Rule failure");
  }

  public INodeList<I_Def> get_1()
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
    final storeDefs_1 other = (storeDefs_1)obj;
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