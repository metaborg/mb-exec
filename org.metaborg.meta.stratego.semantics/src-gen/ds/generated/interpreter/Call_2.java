package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Call_2 extends NoOpNode implements I_Strategy
{ 
  @Child public I_SVar _1;

  @Children public INodeList<I_Strategy> _2;

  public Call_2 (INodeSource source, I_SVar _1, INodeList<I_Strategy> _2) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChild(_1);
    this._2 = adoptChildren(_2);
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
      hasSpecialized = true;
    }
  }

  public I_SVar get_1()
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
    final Call_2 other = (Call_2)obj;
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