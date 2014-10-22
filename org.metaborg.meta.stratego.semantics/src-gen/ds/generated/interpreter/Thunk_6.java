package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Thunk_6 extends NoOpNode implements I_Thunk
{ 
  public String _1;

  @Children public INodeList<String> _2;

  @Children public INodeList<String> _3;

  @Child public I_Strategy _4;

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> _5;

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> _6;

  public Thunk_6 (INodeSource source, String _1, INodeList<String> _2, INodeList<String> _3, I_Strategy _4, com.github.krukow.clj_ds.PersistentMap<Object, Object> _5, com.github.krukow.clj_ds.PersistentMap<Object, Object> _6) 
  { 
    this.setSourceInfo(source);
    this._1 = _1;
    this._2 = _2;
    this._3 = _3;
    this._4 = adoptChild(_4);
    this._5 = _5;
    this._6 = _6;
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      if(_4 instanceof IGenericNode)
      { 
        ((IGenericNode)_4).specialize(depth);
      }
      hasSpecialized = true;
    }
  }

  public String get_1()
  { 
    return this._1;
  }

  public INodeList<String> get_2()
  { 
    return this._2;
  }

  public INodeList<String> get_3()
  { 
    return this._3;
  }

  public I_Strategy get_4()
  { 
    if(this._4 instanceof IGenericNode)
    { 
      ((IGenericNode)this._4).specialize(1);
    }
    return this._4;
  }

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> get_5()
  { 
    return this._5;
  }

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> get_6()
  { 
    return this._6;
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
    final Thunk_6 other = (Thunk_6)obj;
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
    if(_4 == null)
    { 
      if(other._4 != null)
      { 
        return false;
      }
    }
    else
      if(!_4.equals(other._4))
      { 
        return false;
      }
    if(_5 != other._5)
    { 
      return false;
    }
    if(_6 != other._6)
    { 
      return false;
    }
    return true;
  }
}