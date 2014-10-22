package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class Id_0 extends NoOpNode implements I_Strategy
{ 
  public Id_0 (INodeSource source) 
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

  public default_Result exec_default(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_2, ds.manual.interpreter.AutoInterpInteropContext lifted_in_3, org.spoofax.interpreter.terms.ITermFactory lifted_in_4, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, ds.manual.interpreter.SState lifted_in_7, boolean lifted_in_8, ds.manual.interpreter.VState lifted_in_9)
  { 
    this.specializeChildren(0);
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_2;
    final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_3;
    final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_4;
    final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_5;
    final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
    final boolean bool_1 = lifted_in_8;
    final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
    final org.spoofax.interpreter.terms.IStrategoTerm t = t_1;
    final S_1 tmpbuild145 = new S_1(t);
    final AValue lifted_1 = tmpbuild145;
    final org.spoofax.interpreter.core.StackTracer lifted_2 = trace_1;
    final ds.manual.interpreter.SState lifted_3 = sheap_1;
    final boolean lifted_4 = bool_1;
    final ds.manual.interpreter.VState lifted_5 = vheap_1;
    final AValue lifted_out_1 = lifted_1;
    final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_2;
    final ds.manual.interpreter.SState lifted_out_3 = lifted_3;
    final boolean lifted_out_4 = lifted_4;
    final ds.manual.interpreter.VState lifted_out_5 = lifted_5;
    final default_Result default_Result10 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
    return default_Result10;
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
    final Id_0 other = (Id_0)obj;
    return true;
  }
}