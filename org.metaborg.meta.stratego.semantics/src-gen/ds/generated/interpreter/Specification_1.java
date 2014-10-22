package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Specification_1 extends NoOpNode implements I_Module
{ 
  @Children public INodeList<I_Decl> _1;

  public Specification_1 (INodeSource source, INodeList<I_Decl> _1) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChildren(_1);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      for(I_Decl _1_elem : _1)
      { 
        if(_1_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_1_elem).specialize(depth);
        }
      }
      hasSpecialized = true;
    }
  }

  public INodeList<I_Decl> get_1()
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
    final Specification_1 other = (Specification_1)obj;
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