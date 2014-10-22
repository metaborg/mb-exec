package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public abstract class NoOpNode extends AbstractNode implements I_Node
{ 
  public void specializeChildren(int depth)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public ma_Result exec_ma(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, ds.manual.interpreter.AutoInterpInteropContext _2, org.spoofax.interpreter.terms.ITermFactory _3, com.github.krukow.clj_ds.PersistentMap<Object, Object> _4, org.spoofax.interpreter.terms.IStrategoTerm _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public bld_Result exec_bld(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, ds.manual.interpreter.AutoInterpInteropContext _2, org.spoofax.interpreter.terms.IStrategoTerm _3, org.spoofax.interpreter.terms.ITermFactory _4, com.github.krukow.clj_ds.PersistentMap<Object, Object> _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public blds_Result exec_blds(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.AutoInterpInteropContext _3, org.spoofax.interpreter.terms.IStrategoTerm _4, org.spoofax.interpreter.terms.ITermFactory _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public map_Result exec_map(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.AutoInterpInteropContext _3, org.spoofax.interpreter.terms.IStrategoTerm _4, org.spoofax.interpreter.terms.ITermFactory _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public first_Result exec_first(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.AutoInterpInteropContext _3, org.spoofax.interpreter.terms.IStrategoTerm _4, org.spoofax.interpreter.terms.ITermFactory _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public some_Result exec_some(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.AutoInterpInteropContext _3, org.spoofax.interpreter.terms.IStrategoTerm _4, org.spoofax.interpreter.terms.ITermFactory _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, ds.manual.interpreter.VState _8, boolean _9)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public bindtvars_Result exec_bindtvars(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, ds.manual.interpreter.VState _2)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public bindsvars_Result exec_bindsvars(ds.manual.interpreter.AutoInterpInteropContext _1, org.spoofax.interpreter.terms.IStrategoTerm _2, org.spoofax.interpreter.terms.ITermFactory _3, com.github.krukow.clj_ds.PersistentMap<Object, Object> _4, com.github.krukow.clj_ds.PersistentMap<Object, Object> _5, org.spoofax.interpreter.core.StackTracer _6, boolean _7, ds.manual.interpreter.VState _8, ds.manual.interpreter.SState _9)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public thunks_Result exec_thunks(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.SState _3)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public thunk_Result exec_thunk(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.SState _3)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public leteval_Result exec_leteval(ds.manual.interpreter.AutoInterpInteropContext _1, org.spoofax.interpreter.terms.ITermFactory _2, org.spoofax.interpreter.terms.IStrategoTerm _3, com.github.krukow.clj_ds.PersistentMap<Object, Object> _4, com.github.krukow.clj_ds.PersistentMap<Object, Object> _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public allocmodule_Result exec_allocmodule(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.SState _3)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public allocsdefs_Result exec_allocsdefs(ds.manual.interpreter.SState _1)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public storesdefs_Result exec_storesdefs(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.SState _3)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public exid_Result exec_exid()
  { 
    throw new InterpreterException("Operation not supported");
  }

  public default_Result exec_default(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.AutoInterpInteropContext _3, org.spoofax.interpreter.terms.ITermFactory _4, org.spoofax.interpreter.terms.IStrategoTerm _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public vlook_Result exec_vlook(ds.manual.interpreter.VState _1)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public vupdate_Result exec_vupdate(ds.manual.interpreter.VState _1)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public vinit_Result exec_vinit(ds.manual.interpreter.VState _1)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public slook_Result exec_slook(ds.manual.interpreter.SState _1)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public salloc_Result exec_salloc(ds.manual.interpreter.SState _1)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public trace_Result exec_trace(org.spoofax.interpreter.core.StackTracer _1)
  { 
    throw new InterpreterException("Operation not supported");
  }
}