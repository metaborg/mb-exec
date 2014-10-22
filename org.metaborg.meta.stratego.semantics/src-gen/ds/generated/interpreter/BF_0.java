package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class BF_0 extends NoOpNode implements I_BuildRes
{ 
  public BF_0 (INodeSource source) 
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
    final BF_0 other = (BF_0)obj;
    return true;
  }
}