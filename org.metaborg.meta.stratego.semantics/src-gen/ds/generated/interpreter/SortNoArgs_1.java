package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class SortNoArgs_1 extends NoOpNode implements I_Sort
{ 
  public String _1;

  public SortNoArgs_1 (INodeSource source, String _1) 
  { 
    this.setSourceInfo(source);
    this._1 = _1;
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      hasSpecialized = true;
    }
  }

  public String get_1()
  { 
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
    final SortNoArgs_1 other = (SortNoArgs_1)obj;
    if(_1 != other._1)
    { 
      return false;
    }
    return true;
  }
}