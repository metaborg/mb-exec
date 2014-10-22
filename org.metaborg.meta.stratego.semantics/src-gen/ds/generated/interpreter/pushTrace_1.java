package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class pushTrace_1 extends NoOpNode implements I_TraceOp
{ 
  public String _1;

  public pushTrace_1 (INodeSource source, String _1) 
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

  public trace_Result exec_trace(org.spoofax.interpreter.core.StackTracer lifted_in_1)
  { 
    this.specializeChildren(0);
    final String lifted_1 = _1;
    final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_1;
    final String name = lifted_1;
    final org.spoofax.interpreter.core.StackTracer t = trace_1;
    final boolean b = true;
    final boolean lifted_out_1 = b;
    final org.spoofax.interpreter.core.StackTracer lifted_out_2 = t;
    final trace_Result trace_Result0 = new trace_Result(lifted_out_1, lifted_out_2);
    return trace_Result0;
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
    final pushTrace_1 other = (pushTrace_1)obj;
    if(_1 != other._1)
    { 
      return false;
    }
    return true;
  }
}