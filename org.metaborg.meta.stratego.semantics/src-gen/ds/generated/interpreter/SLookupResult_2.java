package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class SLookupResult_2 extends NoOpNode implements I_SLookupResult
{ 
  public com.github.krukow.clj_ds.PersistentMap<Object, Object> _1;

  @Child public I_Thunk _2;

  public SLookupResult_2 (INodeSource source, com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, I_Thunk _2) 
  { 
    this.setSourceInfo(source);
    this._1 = _1;
    this._2 = adoptChild(_2);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      if(_2 instanceof IGenericNode)
      { 
        ((IGenericNode)_2).specialize(depth);
      }
      hasSpecialized = true;
    }
  }

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> get_1()
  { 
    return this._1;
  }

  public I_Thunk get_2()
  { 
    if(this._2 instanceof IGenericNode)
    { 
      ((IGenericNode)this._2).specialize(1);
    }
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
    final SLookupResult_2 other = (SLookupResult_2)obj;
    if(_1 != other._1)
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