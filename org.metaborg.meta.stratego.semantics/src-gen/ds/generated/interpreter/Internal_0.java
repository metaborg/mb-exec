package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Internal_0 extends NoOpNode implements I_Anno
{ 
  public Internal_0 (INodeSource source) 
  { 
    this.setSourceInfo(source);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      hasSpecialized = true;
    }
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
    final Internal_0 other = (Internal_0)obj;
    return true;
  }
}