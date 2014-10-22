package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Wld_0 extends NoOpNode implements I_Wld
{ 
  public Wld_0 (INodeSource source) 
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
    final Wld_0 other = (Wld_0)obj;
    return true;
  }
}