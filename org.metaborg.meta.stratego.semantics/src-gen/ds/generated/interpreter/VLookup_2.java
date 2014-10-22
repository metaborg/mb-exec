package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class VLookup_2 extends NoOpNode implements I_VHeapOp
{ 
  public com.github.krukow.clj_ds.PersistentMap<Object, Object> _1;

  public String _2;

  public VLookup_2 (INodeSource source, com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, String _2) 
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

  public vlook_Result exec_vlook(ds.manual.interpreter.VState lifted_in_1)
  { 
    this.specializeChildren(0);
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_1 = _1;
    final String lifted_2 = _2;
    final ds.manual.interpreter.VState vheap_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv = lifted_1;
    final String x = lifted_2;
    final ds.manual.interpreter.VState s = vheap_1;
    final AValue tmpbuild554 = s.lookup(venv, x);
    final AValue v = tmpbuild554;
    final VLookupResult_2 tmpbuild553 = new VLookupResult_2(getSourceInfo(), venv, v);
    final I_VLookupResult lifted_3 = tmpbuild553;
    final I_VLookupResult lifted_out_1 = lifted_3;
    final ds.manual.interpreter.VState lifted_out_2 = s;
    final vlook_Result vlook_Result0 = new vlook_Result(lifted_out_1, lifted_out_2);
    return vlook_Result0;
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
    final VLookup_2 other = (VLookup_2)obj;
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