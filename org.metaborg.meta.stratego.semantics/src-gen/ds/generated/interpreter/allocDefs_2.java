package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class allocDefs_2 extends NoOpNode implements I_Allocator
{ 
  @Children public INodeList<I_Def> _1;

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> _2;

  public allocDefs_2 (INodeSource source, INodeList<I_Def> _1, com.github.krukow.clj_ds.PersistentMap<Object, Object> _2) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChildren(_1);
    this._2 = _2;
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      for(I_Def _1_elem : _1)
      { 
        if(_1_elem instanceof IGenericNode)
        { 
          ((IGenericNode)_1_elem).specialize(depth);
        }
      }
      hasSpecialized = true;
    }
  }

  public allocsdefs_Result exec_allocsdefs(ds.manual.interpreter.SState lifted_in_1)
  { 
    this.specializeChildren(0);
    final INodeList<I_Def> lifted_1 = _1;
    final com.github.krukow.clj_ds.PersistentMap<Object, Object> lifted_2 = _2;
    final ds.manual.interpreter.SState sheap_1 = lifted_in_1;
    if(lifted_1 != null && lifted_1.isEmpty())
    { 
      final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv = lifted_2;
      final INodeList<I_Def> tmpbuild525 = NodeList.NIL(I_Def.class);
      final allocdDefs_2 tmpbuild524 = new allocdDefs_2(getSourceInfo(), tmpbuild525, senv);
      final I_AllocatorResult lifted_3 = tmpbuild524;
      final ds.manual.interpreter.SState lifted_4 = sheap_1;
      final I_AllocatorResult lifted_out_1 = lifted_3;
      final ds.manual.interpreter.SState lifted_out_2 = lifted_4;
      final allocsdefs_Result allocsdefs_Result0 = new allocsdefs_Result(lifted_out_1, lifted_out_2);
      return allocsdefs_Result0;
    }
    else
    { 
      if(lifted_1 != null && !lifted_1.isEmpty())
      { 
        final I_Def sdef = lifted_1.head();
        final INodeList<I_Def> sdefs = lifted_1.tail();
        final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv = lifted_2;
        final AnnoDef_2 tmpbuild526 = sdef.match(AnnoDef_2.class);
        if(tmpbuild526 != null)
        { 
          final INodeList<I_Anno> annos = tmpbuild526.get_1();
          final I_StrategyDef sdef_ = tmpbuild526.get_2();
          final INodeList<I_Def> tmpbuild528 = new NodeList<I_Def>(sdef_, sdefs);
          final allocDefs_2 tmpbuild527 = new allocDefs_2(getSourceInfo(), tmpbuild528, senv);
          final I_Allocator lifted_4 = tmpbuild527;
          final allocsdefs_Result tmpresult110 = lifted_4.exec_allocsdefs(sheap_1);
          final I_AllocatorResult ad = tmpresult110.value;
          final ds.manual.interpreter.SState sheap_2 = tmpresult110.get_1();
          final ds.manual.interpreter.SState lifted_3 = sheap_2;
          final I_AllocatorResult lifted_out_1 = ad;
          final ds.manual.interpreter.SState lifted_out_2 = lifted_3;
          final allocsdefs_Result allocsdefs_Result0 = new allocsdefs_Result(lifted_out_1, lifted_out_2);
          return allocsdefs_Result0;
        }
        else
        { 
          final ExtSDef_3 tmpbuild529 = sdef.match(ExtSDef_3.class);
          if(tmpbuild529 != null)
          { 
            final String a = tmpbuild529.get_1();
            final INodeList<I_Typedid> b = tmpbuild529.get_2();
            final INodeList<I_Typedid> c = tmpbuild529.get_3();
            final allocDefs_2 tmpbuild530 = new allocDefs_2(getSourceInfo(), sdefs, senv);
            final I_Allocator lifted_4 = tmpbuild530;
            final allocsdefs_Result tmpresult111 = lifted_4.exec_allocsdefs(sheap_1);
            final I_AllocatorResult ad = tmpresult111.value;
            final ds.manual.interpreter.SState sheap_2 = tmpresult111.get_1();
            final ds.manual.interpreter.SState lifted_3 = sheap_2;
            final I_AllocatorResult lifted_out_1 = ad;
            final ds.manual.interpreter.SState lifted_out_2 = lifted_3;
            final allocsdefs_Result allocsdefs_Result0 = new allocsdefs_Result(lifted_out_1, lifted_out_2);
            return allocsdefs_Result0;
          }
          else
          { 
            final SDefT_4 tmpbuild536 = sdef.match(SDefT_4.class);
            if(tmpbuild536 != null)
            { 
              final String sname = tmpbuild536.get_1();
              final INodeList<I_Typedid> ss = tmpbuild536.get_2();
              final INodeList<I_Typedid> ts = tmpbuild536.get_3();
              final I_Strategy s = tmpbuild536.get_4();
              final SAlloc_2 tmpbuild535 = new SAlloc_2(getSourceInfo(), senv, sname);
              final I_SHeapOp lifted_5 = tmpbuild535;
              final salloc_Result tmpresult113 = lifted_5.exec_salloc(sheap_1);
              final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv_ = tmpresult113.value;
              final ds.manual.interpreter.SState sheap_2 = tmpresult113.get_1();
              final allocDefs_2 tmpbuild534 = new allocDefs_2(getSourceInfo(), sdefs, senv_);
              final I_Allocator lifted_7 = tmpbuild534;
              final allocsdefs_Result tmpresult112 = lifted_7.exec_allocsdefs(sheap_2);
              final I_AllocatorResult lifted_6 = tmpresult112.value;
              final ds.manual.interpreter.SState sheap_3 = tmpresult112.get_1();
              final allocdDefs_2 tmpbuild533 = lifted_6.match(allocdDefs_2.class);
              if(tmpbuild533 != null)
              { 
                final INodeList<I_Def> sdefs_ = tmpbuild533.get_1();
                final com.github.krukow.clj_ds.PersistentMap<Object, Object> senv__ = tmpbuild533.get_2();
                final INodeList<I_Def> tmpbuild532 = new NodeList<I_Def>(sdef, sdefs_);
                final allocdDefs_2 tmpbuild531 = new allocdDefs_2(getSourceInfo(), tmpbuild532, senv__);
                final I_AllocatorResult lifted_3 = tmpbuild531;
                final ds.manual.interpreter.SState lifted_4 = sheap_3;
                final I_AllocatorResult lifted_out_1 = lifted_3;
                final ds.manual.interpreter.SState lifted_out_2 = lifted_4;
                final allocsdefs_Result allocsdefs_Result0 = new allocsdefs_Result(lifted_out_1, lifted_out_2);
                return allocsdefs_Result0;
              }
            }
          }
        }
      }
    }
    throw new InterpreterException("Rule failure");
  }

  public INodeList<I_Def> get_1()
  { 
    if(this._1 instanceof IGenericNode)
    { 
      ((IGenericNode)this._1).specialize(1);
    }
    return this._1;
  }

  public com.github.krukow.clj_ds.PersistentMap<Object, Object> get_2()
  { 
    return this._2;
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
    final allocDefs_2 other = (allocDefs_2)obj;
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
    if(_2 != other._2)
    { 
      return false;
    }
    return true;
  }
}