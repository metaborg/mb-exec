package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Op_2 extends NoOpNode implements I_PreTerm
{ 
  public String _1;

  @Children public INodeList<I_STerm> _2;

  public Op_2 (INodeSource source, String _1, INodeList<I_STerm> _2) 
  { 
    this.setSourceInfo(source);
    this._1 = _1;
    this._2 = adoptChildren(_2);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      for(I_STerm _2_elem : _2)
      { 
        if(_2_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_2_elem).specialize(depth);
        }
      }
      hasSpecialized = true;
    }
  }

  public String get_1()
  { 
    return this._1;
  }

  public INodeList<I_STerm> get_2()
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
    final Op_2 other = (Op_2)obj;
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