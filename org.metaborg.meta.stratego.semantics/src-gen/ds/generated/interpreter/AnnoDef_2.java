package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class AnnoDef_2 extends NoOpNode implements I_Def
{ 
  @Children public INodeList<I_Anno> _1;

  @Child public I_StrategyDef _2;

  public AnnoDef_2 (INodeSource source, INodeList<I_Anno> _1, I_StrategyDef _2) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChildren(_1);
    this._2 = adoptChild(_2);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      for(I_Anno _1_elem : _1)
      { 
        if(_1_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_1_elem).specialize(depth);
        }
      }
      if(_2 instanceof IGenericNode)
      { 
        ((IGenericNode)_2).specialize(depth);
      }
      hasSpecialized = true;
    }
  }

  public INodeList<I_Anno> get_1()
  { 
    if(this._1 instanceof IGenericNode)
    { 
      ((IGenericNode)this._1).specialize(1);
    }
    return this._1;
  }

  public I_StrategyDef get_2()
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
    final AnnoDef_2 other = (AnnoDef_2)obj;
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