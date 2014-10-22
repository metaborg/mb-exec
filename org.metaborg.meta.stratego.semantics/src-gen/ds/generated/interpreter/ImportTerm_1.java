package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class ImportTerm_1 extends NoOpNode implements I_Strategy
{ 
  public String _1;

  public ImportTerm_1 (INodeSource source, String _1) 
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

  public default_Result exec_default(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_2, ds.manual.interpreter.AutoInterpInteropContext lifted_in_3, org.spoofax.interpreter.terms.ITermFactory lifted_in_4, org.spoofax.interpreter.terms.IStrategoTerm lifted_in_5, org.spoofax.interpreter.core.StackTracer lifted_in_6, ds.manual.interpreter.SState lifted_in_7, boolean lifted_in_8, ds.manual.interpreter.VState lifted_in_9)
  { 
    this.specializeChildren(0);
    final String lifted_1 = _1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_2;
    final ds.manual.interpreter.AutoInterpInteropContext ic_1 = lifted_in_3;
    final org.spoofax.interpreter.terms.ITermFactory tf_1 = lifted_in_4;
    final org.spoofax.interpreter.terms.IStrategoTerm t_1 = lifted_in_5;
    final org.spoofax.interpreter.core.StackTracer trace_1 = lifted_in_6;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_7;
    final boolean bool_1 = lifted_in_8;
    final ds.manual.interpreter.VState vheap_1 = lifted_in_9;
    final String p = lifted_1;
    final ds.manual.interpreter.AutoInterpInteropContext ic = ic_1;
    final org.spoofax.interpreter.terms.IStrategoTerm tmpbuild168 = ds.manual.interpreter.Natives.importTerm_2(ic, p);
    final org.spoofax.interpreter.terms.IStrategoTerm t = tmpbuild168;
    final S_1 tmpbuild167 = new S_1(t);
    final AValue lifted_2 = tmpbuild167;
    final org.spoofax.interpreter.core.StackTracer lifted_3 = trace_1;
    final ds.manual.interpreter.SState lifted_4 = sheap_1;
    final boolean lifted_5 = bool_1;
    final ds.manual.interpreter.VState lifted_6 = vheap_1;
    final AValue lifted_out_1 = lifted_2;
    final org.spoofax.interpreter.core.StackTracer lifted_out_2 = lifted_3;
    final ds.manual.interpreter.SState lifted_out_3 = lifted_4;
    final boolean lifted_out_4 = lifted_5;
    final ds.manual.interpreter.VState lifted_out_5 = lifted_6;
    final default_Result default_Result15 = new default_Result(lifted_out_1, lifted_out_2, lifted_out_3, lifted_out_4, lifted_out_5);
    return default_Result15;
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
    final ImportTerm_1 other = (ImportTerm_1)obj;
    if(_1 != other._1)
    { 
      return false;
    }
    return true;
  }
}