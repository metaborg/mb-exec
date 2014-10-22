package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Override_0 extends NoOpNode implements I_Anno
{ 
  public Override_0 (INodeSource source) 
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
    final Override_0 other = (Override_0)obj;
    return true;
  }
}