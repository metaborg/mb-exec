package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class SLookup_2 extends NoOpNode implements I_SHeapOp
{ 
  public com.github.krukow.clj_ds.PersistentMap<Object, Object> _1;

  public String _2;

  public SLookup_2 (INodeSource source, com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, String _2) 
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

  public slook_Result exec_slook(ds.manual.interpreter.SState lifted_in_1)
  { 
    this.specializeChildren(0);
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_1 = _1;
    final String lifted_2 = _2;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv = lifted_1;
    final String name = lifted_2;
    final ds.manual.interpreter.SState s = sheap_1;
    final I_Thunk tmpbuild565 = s.lookup(senv, name);
    final I_Thunk thunk = tmpbuild565;
    final SLookupResult_2 tmpbuild564 = new SLookupResult_2(getSourceInfo(), senv, thunk);
    final I_SLookupResult lifted_3 = tmpbuild564;
    final I_SLookupResult lifted_out_1 = lifted_3;
    final ds.manual.interpreter.SState lifted_out_2 = s;
    final slook_Result slook_Result0 = new slook_Result(lifted_out_1, lifted_out_2);
    return slook_Result0;
  }

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> get_1()
  { 
    return this._1;
  }

  public String get_2()
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
    final SLookup_2 other = (SLookup_2)obj;
    if(_1 != other._1)
    { 
      return false;
    }
    if(_2 != other._2)
    { 
      return false;
    }
    return true;
  }
}