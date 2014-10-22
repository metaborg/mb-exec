package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;
import org.spoofax.interpreter.terms.*;
import org.spoofax.jsglr.client.imploder.ImploderAttachment;
import org.spoofax.interpreter.core.Tools;

public class Generic_Strategy extends AbstractNode implements I_Strategy, IGenericNode
{ 
  public IStrategoTerm aterm;

  public Generic_Strategy (INodeSource source, IStrategoTerm term) 
  { 
    this.setSourceInfo(source);
    this.aterm = term;
  }

  @Override public <T> T match(Class<T> clazz)
  { 
    return specialize(1).match(clazz);
  }

  public void specializeChildren(int depth)
  { 
    throw new InterpreterException("Operation not supported");
  }

  public I_Node specialize(int depth)
  { 
    if(aterm instanceof IStrategoAppl)
    { 
      final IStrategoAppl term = (IStrategoAppl)aterm;
      final String name = term.getName();
      final ImploderNodeSource source = term.getAttachment(ImploderAttachment.TYPE) != null ? new ImploderNodeSource(term.getAttachment(ImploderAttachment.TYPE)) : null;
      if(name.equals("LChoice") && term.getSubtermCount() == 2)
      { 
        I_Node replacement = replace(new LChoice_2(source, new Generic_Strategy(source, term.getSubterm(0)), new Generic_Strategy(source, term.getSubterm(1))));
        if(depth > 0)
        { 
          replacement.specializeChildren(depth - 1);
        }
        return replacement;
      }
      else
      { 
        if(name.equals("Call") && term.getSubtermCount() == 2)
        { 
          I_Node replacement = replace(new Call_2(source, new Generic_SVar(source, term.getSubterm(0)), (INodeList)NodeUtils.makeList(term.getSubterm(1).getSubtermCount(), term.getSubterm(1), Generic_Strategy.class)));
          if(depth > 0)
          { 
            replacement.specializeChildren(depth - 1);
          }
          return replacement;
        }
        else
        { 
          if(name.equals("All") && term.getSubtermCount() == 1)
          { 
            I_Node replacement = replace(new All_1(source, new Generic_Strategy(source, term.getSubterm(0))));
            if(depth > 0)
            { 
              replacement.specializeChildren(depth - 1);
            }
            return replacement;
          }
          else
          { 
            if(name.equals("One") && term.getSubtermCount() == 1)
            { 
              I_Node replacement = replace(new One_1(source, new Generic_Strategy(source, term.getSubterm(0))));
              if(depth > 0)
              { 
                replacement.specializeChildren(depth - 1);
              }
              return replacement;
            }
            else
            { 
              if(name.equals("Some") && term.getSubtermCount() == 1)
              { 
                I_Node replacement = replace(new Some_1(source, new Generic_Strategy(source, term.getSubterm(0))));
                if(depth > 0)
                { 
                  replacement.specializeChildren(depth - 1);
                }
                return replacement;
              }
              else
              { 
                if(name.equals("PrimT") && term.getSubtermCount() == 3)
                { 
                  I_Node replacement = replace(new PrimT_3(source, Tools.asJavaString(term.getSubterm(0)), (INodeList)NodeUtils.makeList(term.getSubterm(1).getSubtermCount(), term.getSubterm(1), Generic_Strategy.class), (INodeList)NodeUtils.makeList(term.getSubterm(2).getSubtermCount(), term.getSubterm(2), Generic_STerm.class)));
                  if(depth > 0)
                  { 
                    replacement.specializeChildren(depth - 1);
                  }
                  return replacement;
                }
                else
                { 
                  if(name.equals("GuardedLChoice") && term.getSubtermCount() == 3)
                  { 
                    I_Node replacement = replace(new GuardedLChoice_3(source, new Generic_Strategy(source, term.getSubterm(0)), new Generic_Strategy(source, term.getSubterm(1)), new Generic_Strategy(source, term.getSubterm(2))));
                    if(depth > 0)
                    { 
                      replacement.specializeChildren(depth - 1);
                    }
                    return replacement;
                  }
                  else
                  { 
                    if(name.equals("Seq") && term.getSubtermCount() == 2)
                    { 
                      I_Node replacement = replace(new Seq_2(source, new Generic_Strategy(source, term.getSubterm(0)), new Generic_Strategy(source, term.getSubterm(1))));
                      if(depth > 0)
                      { 
                        replacement.specializeChildren(depth - 1);
                      }
                      return replacement;
                    }
                    else
                    { 
                      if(name.equals("Scope") && term.getSubtermCount() == 2)
                      { 
                        I_Node replacement = replace(new Scope_2(source, (INodeList)NodeUtils.makeList(term.getSubterm(0).getSubtermCount(), term.getSubterm(0), String.class), new Generic_Strategy(source, term.getSubterm(1))));
                        if(depth > 0)
                        { 
                          replacement.specializeChildren(depth - 1);
                        }
                        return replacement;
                      }
                      else
                      { 
                        if(name.equals("Build") && term.getSubtermCount() == 1)
                        { 
                          I_Node replacement = replace(new Build_1(source, new Generic_STerm(source, term.getSubterm(0))));
                          if(depth > 0)
                          { 
                            replacement.specializeChildren(depth - 1);
                          }
                          return replacement;
                        }
                        else
                        { 
                          if(name.equals("Match") && term.getSubtermCount() == 1)
                          { 
                            I_Node replacement = replace(new Match_1(source, new Generic_STerm(source, term.getSubterm(0))));
                            if(depth > 0)
                            { 
                              replacement.specializeChildren(depth - 1);
                            }
                            return replacement;
                          }
                          else
                          { 
                            if(name.equals("Id") && term.getSubtermCount() == 0)
                            { 
                              I_Node replacement = replace(new Id_0(source));
                              if(depth > 0)
                              { 
                                replacement.specializeChildren(depth - 1);
                              }
                              return replacement;
                            }
                            else
                            { 
                              if(name.equals("Fail") && term.getSubtermCount() == 0)
                              { 
                                I_Node replacement = replace(new Fail_0(source));
                                if(depth > 0)
                                { 
                                  replacement.specializeChildren(depth - 1);
                                }
                                return replacement;
                              }
                              else
                              { 
                                if(name.equals("CallDynamic") && term.getSubtermCount() == 3)
                                { 
                                  I_Node replacement = replace(new CallDynamic_3(source, new Generic_STerm(source, term.getSubterm(0)), (INodeList)NodeUtils.makeList(term.getSubterm(1).getSubtermCount(), term.getSubterm(1), Generic_Strategy.class), (INodeList)NodeUtils.makeList(term.getSubterm(2).getSubtermCount(), term.getSubterm(2), Generic_STerm.class)));
                                  if(depth > 0)
                                  { 
                                    replacement.specializeChildren(depth - 1);
                                  }
                                  return replacement;
                                }
                                else
                                { 
                                  if(name.equals("CallT") && term.getSubtermCount() == 3)
                                  { 
                                    I_Node replacement = replace(new CallT_3(source, new Generic_SVar(source, term.getSubterm(0)), (INodeList)NodeUtils.makeList(term.getSubterm(1).getSubtermCount(), term.getSubterm(1), Generic_Strategy.class), (INodeList)NodeUtils.makeList(term.getSubterm(2).getSubtermCount(), term.getSubterm(2), Generic_STerm.class)));
                                    if(depth > 0)
                                    { 
                                      replacement.specializeChildren(depth - 1);
                                    }
                                    return replacement;
                                  }
                                  else
                                  { 
                                    if(name.equals("Let") && term.getSubtermCount() == 2)
                                    { 
                                      I_Node replacement = replace(new Let_2(source, (INodeList)NodeUtils.makeList(term.getSubterm(0).getSubtermCount(), term.getSubterm(0), Generic_Def.class), new Generic_Strategy(source, term.getSubterm(1))));
                                      if(depth > 0)
                                      { 
                                        replacement.specializeChildren(depth - 1);
                                      }
                                      return replacement;
                                    }
                                    else
                                    { 
                                      if(name.equals("ImportTerm") && term.getSubtermCount() == 1)
                                      { 
                                        I_Node replacement = replace(new ImportTerm_1(source, Tools.asJavaString(term.getSubterm(0))));
                                        if(depth > 0)
                                        { 
                                          replacement.specializeChildren(depth - 1);
                                        }
                                        return replacement;
                                      }
                                      else
                                      { }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    IGenericNode replacement = null;
    throw new RewritingException();
  }

  @Override public ma_Result exec_ma(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, ds.manual.interpreter.AutoInterpInteropContext _2, org.spoofax.interpreter.terms.ITermFactory _3, com.github.krukow.clj_ds.PersistentMap<Object, Object> _4, org.spoofax.interpreter.terms.IStrategoTerm _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    return specialize(1).exec_ma(_1, _2, _3, _4, _5, _6, _7, _8, _9);
  }

  @Override public bld_Result exec_bld(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, ds.manual.interpreter.AutoInterpInteropContext _2, org.spoofax.interpreter.terms.IStrategoTerm _3, org.spoofax.interpreter.terms.ITermFactory _4, com.github.krukow.clj_ds.PersistentMap<Object, Object> _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    return specialize(1).exec_bld(_1, _2, _3, _4, _5, _6, _7, _8, _9);
  }

  @Override public blds_Result exec_blds(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.AutoInterpInteropContext _3, org.spoofax.interpreter.terms.IStrategoTerm _4, org.spoofax.interpreter.terms.ITermFactory _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    return specialize(1).exec_blds(_1, _2, _3, _4, _5, _6, _7, _8, _9);
  }

  @Override public map_Result exec_map(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.AutoInterpInteropContext _3, org.spoofax.interpreter.terms.IStrategoTerm _4, org.spoofax.interpreter.terms.ITermFactory _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    return specialize(1).exec_map(_1, _2, _3, _4, _5, _6, _7, _8, _9);
  }

  @Override public first_Result exec_first(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.AutoInterpInteropContext _3, org.spoofax.interpreter.terms.IStrategoTerm _4, org.spoofax.interpreter.terms.ITermFactory _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    return specialize(1).exec_first(_1, _2, _3, _4, _5, _6, _7, _8, _9);
  }

  @Override public some_Result exec_some(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.AutoInterpInteropContext _3, org.spoofax.interpreter.terms.IStrategoTerm _4, org.spoofax.interpreter.terms.ITermFactory _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, ds.manual.interpreter.VState _8, boolean _9)
  { 
    return specialize(1).exec_some(_1, _2, _3, _4, _5, _6, _7, _8, _9);
  }

  @Override public bindtvars_Result exec_bindtvars(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, ds.manual.interpreter.VState _2)
  { 
    return specialize(1).exec_bindtvars(_1, _2);
  }

  @Override public bindsvars_Result exec_bindsvars(ds.manual.interpreter.AutoInterpInteropContext _1, org.spoofax.interpreter.terms.IStrategoTerm _2, org.spoofax.interpreter.terms.ITermFactory _3, com.github.krukow.clj_ds.PersistentMap<Object, Object> _4, com.github.krukow.clj_ds.PersistentMap<Object, Object> _5, org.spoofax.interpreter.core.StackTracer _6, boolean _7, ds.manual.interpreter.VState _8, ds.manual.interpreter.SState _9)
  { 
    return specialize(1).exec_bindsvars(_1, _2, _3, _4, _5, _6, _7, _8, _9);
  }

  @Override public thunks_Result exec_thunks(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.SState _3)
  { 
    return specialize(1).exec_thunks(_1, _2, _3);
  }

  @Override public thunk_Result exec_thunk(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.SState _3)
  { 
    return specialize(1).exec_thunk(_1, _2, _3);
  }

  @Override public leteval_Result exec_leteval(ds.manual.interpreter.AutoInterpInteropContext _1, org.spoofax.interpreter.terms.ITermFactory _2, org.spoofax.interpreter.terms.IStrategoTerm _3, com.github.krukow.clj_ds.PersistentMap<Object, Object> _4, com.github.krukow.clj_ds.PersistentMap<Object, Object> _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    return specialize(1).exec_leteval(_1, _2, _3, _4, _5, _6, _7, _8, _9);
  }

  @Override public allocmodule_Result exec_allocmodule(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.SState _3)
  { 
    return specialize(1).exec_allocmodule(_1, _2, _3);
  }

  @Override public allocsdefs_Result exec_allocsdefs(ds.manual.interpreter.SState _1)
  { 
    return specialize(1).exec_allocsdefs(_1);
  }

  @Override public storesdefs_Result exec_storesdefs(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.SState _3)
  { 
    return specialize(1).exec_storesdefs(_1, _2, _3);
  }

  @Override public exid_Result exec_exid()
  { 
    return specialize(1).exec_exid();
  }

  @Override public default_Result exec_default(com.github.krukow.clj_ds.PersistentMap<Object, Object> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2, ds.manual.interpreter.AutoInterpInteropContext _3, org.spoofax.interpreter.terms.ITermFactory _4, org.spoofax.interpreter.terms.IStrategoTerm _5, org.spoofax.interpreter.core.StackTracer _6, ds.manual.interpreter.SState _7, boolean _8, ds.manual.interpreter.VState _9)
  { 
    return specialize(1).exec_default(_1, _2, _3, _4, _5, _6, _7, _8, _9);
  }

  @Override public vlook_Result exec_vlook(ds.manual.interpreter.VState _1)
  { 
    return specialize(1).exec_vlook(_1);
  }

  @Override public vupdate_Result exec_vupdate(ds.manual.interpreter.VState _1)
  { 
    return specialize(1).exec_vupdate(_1);
  }

  @Override public vinit_Result exec_vinit(ds.manual.interpreter.VState _1)
  { 
    return specialize(1).exec_vinit(_1);
  }

  @Override public slook_Result exec_slook(ds.manual.interpreter.SState _1)
  { 
    return specialize(1).exec_slook(_1);
  }

  @Override public salloc_Result exec_salloc(ds.manual.interpreter.SState _1)
  { 
    return specialize(1).exec_salloc(_1);
  }

  @Override public trace_Result exec_trace(org.spoofax.interpreter.core.StackTracer _1)
  { 
    return specialize(1).exec_trace(_1);
  }
}