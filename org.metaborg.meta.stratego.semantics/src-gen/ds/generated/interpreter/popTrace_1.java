package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class popTrace_1 extends NoOpNode implements I_TraceOp
{ 
  public AValue _1;

  public popTrace_1 (INodeSource source, AValue _1) 
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
    final AValue lifted_1 = _1;
    final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_1;
    final AValue v = lifted_1;
    final org.spoofax.interpreter.core.StackTracer t = trace_1;
    final F_0 tmpbuild568 = v.match(F_0.class);
    if(tmpbuild568 != null)
    { 
      final boolean tmpbuild569 = ds.manual.interpreter.Natives.popOnFailure_1(t);
      final boolean bpop = tmpbuild569;
      final boolean b = true;
      final boolean lifted_out_1 = b;
      final org.spoofax.interpreter.core.StackTracer lifted_out_2 = t;
      final trace_Result trace_Result1 = new trace_Result(lifted_out_1, lifted_out_2);
      return trace_Result1;
    }
    else
    { 
      final S_1 tmpbuild571 = v.match(S_1.class);
      if(tmpbuild571 != null)
      { 
        final org.spoofax.interpreter.terms.IStrategoTerm aterm = tmpbuild571.get_1();
        final boolean tmpbuild570 = ds.manual.interpreter.Natives.popOnSuccess_1(t);
        final boolean bpop = tmpbuild570;
        final boolean b = true;
        final boolean lifted_out_1 = b;
        final org.spoofax.interpreter.core.StackTracer lifted_out_2 = t;
        final trace_Result trace_Result1 = new trace_Result(lifted_out_1, lifted_out_2);
        return trace_Result1;
      }
    }
    throw new InterpreterException("Rule failure");
  }

  public AValue get_1()
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
    final popTrace_1 other = (popTrace_1)obj;
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