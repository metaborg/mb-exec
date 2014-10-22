package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class allocModule_1 extends NoOpNode implements I_Allocator
{ 
  @Child public I_Module _1;

  public allocModule_1 (INodeSource source, I_Module _1) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChild(_1);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      if(_1 instanceof IGenericNode)
      { 
        ((IGenericNode)_1).specialize(depth);
      }
      hasSpecialized = true;
    }
  }

  public allocmodule_Result exec_allocmodule(com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_1, com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_in_2, ds.manual.interpreter.SState lifted_in_3)
  { 
    this.specializeChildren(0);
    final I_Module lifted_1 = _1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> venv_1 = lifted_in_1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_1 = lifted_in_2;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_3;
    final Specification_1 tmpbuild523 = lifted_1.match(Specification_1.class);
    if(tmpbuild523 != null)
    { 
      final INodeList<I_Decl> lifted_3 = tmpbuild523.get_1();
      if(lifted_3 != null && !lifted_3.isEmpty())
      { 
        final I_Decl sigs = lifted_3.head();
        final INodeList<I_Decl> lifted_4 = lifted_3.tail();
        if(lifted_4 != null && !lifted_4.isEmpty())
        { 
          final I_Decl lifted_5 = lifted_4.head();
          final INodeList<I_Decl> lifted_6 = lifted_4.tail();
          if(lifted_6 != null && lifted_6.isEmpty())
          { 
            final Strategies_1 tmpbuild522 = lifted_5.match(Strategies_1.class);
            if(tmpbuild522 != null)
            { 
              final INodeList<I_Def> ss = tmpbuild522.get_1();
              final com.github.krukow.clj_ds.PersistentMap<Object, Object> e = venv_1;
              final com.github.krukow.clj_ds.PersistentMap<Object, Object> d = senv_1;
              final allocDefs_2 tmpbuild521 = new allocDefs_2(getSourceInfo(), ss, d);
              final I_Allocator lifted_8 = tmpbuild521;
              final allocsdefs_Result tmpresult109 = lifted_8.exec_allocsdefs(sheap_1);
              final I_AllocatorResult lifted_7 = tmpresult109.value;
              final ds.manual.interpreter.SState sheap_2 = tmpresult109.get_1();
              final allocdDefs_2 tmpbuild520 = lifted_7.match(allocdDefs_2.class);
              if(tmpbuild520 != null)
              { 
                final INodeList<I_Def> ss_ = tmpbuild520.get_1();
                final com.github.krukow.clj_ds.PersistentMap<Object, Object> d_ = tmpbuild520.get_2();
                final storeDefs_1 tmpbuild519 = new storeDefs_1(getSourceInfo(), ss_);
                final I_Allocator lifted_9 = tmpbuild519;
                final storesdefs_Result tmpresult108 = lifted_9.exec_storesdefs(e, d_, sheap_2);
                final boolean b = tmpresult108.value;
                final ds.manual.interpreter.SState sheap_3 = tmpresult108.get_1();
                final ds.manual.interpreter.SState lifted_2 = sheap_3;
                final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_out_1 = d_;
                final ds.manual.interpreter.SState lifted_out_2 = lifted_2;
                final allocmodule_Result allocmodule_Result0 = new allocmodule_Result(lifted_out_1, lifted_out_2);
                return allocmodule_Result0;
              }
            }
          }
        }
      }
    }
    throw new InterpreterException("Rule failure");
  }

  public I_Module get_1()
  { 
    if(this._1 instanceof IGenericNode)
    { 
      ((IGenericNode)this._1).specialize(1);
    }
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
    final allocModule_1 other = (allocModule_1)obj;
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