package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class sdefNames_1 extends NoOpNode implements I_NameExtractor
{ 
  @Children public INodeList<I_Def> _1;

  public sdefNames_1 (INodeSource source, INodeList<I_Def> _1) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChildren(_1);
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

  public exid_Result exec_exid()
  { 
    this.specializeChildren(0);
    final INodeList<I_Def> lifted_1 = _1;
    if(lifted_1 != null && lifted_1.isEmpty())
    { 
      final INodeList<I_Node> tmpbuild549 = NodeList.NIL(I_Node.class);
      final INodeList<String> tmpbuild548 = ds.manual.interpreter.Natives.asNILofString_1(tmpbuild549);
      final INodeList<String> lifted_2 = tmpbuild548;
      final INodeList<String> lifted_out_1 = lifted_2;
      final exid_Result exid_Result1 = new exid_Result(lifted_out_1);
      return exid_Result1;
    }
    else
    { 
      if(lifted_1 != null && !lifted_1.isEmpty())
      { 
        final I_Def lifted_3 = lifted_1.head();
        final INodeList<I_Def> sdefs = lifted_1.tail();
        final SDefT_4 tmpbuild552 = lifted_3.match(SDefT_4.class);
        if(tmpbuild552 != null)
        { 
          final String sname = tmpbuild552.get_1();
          final INodeList<I_Typedid> ss = tmpbuild552.get_2();
          final INodeList<I_Typedid> ts = tmpbuild552.get_3();
          final I_Strategy s = tmpbuild552.get_4();
          final sdefNames_1 tmpbuild551 = new sdefNames_1(getSourceInfo(), sdefs);
          final I_NameExtractor lifted_4 = tmpbuild551;
          final exid_Result tmpresult119 = lifted_4.exec_exid();
          final INodeList<String> snames = tmpresult119.value;
          final INodeList<String> tmpbuild550 = new NodeList<String>(sname, snames);
          final INodeList<String> lifted_2 = tmpbuild550;
          final INodeList<String> lifted_out_1 = lifted_2;
          final exid_Result exid_Result1 = new exid_Result(lifted_out_1);
          return exid_Result1;
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
    final sdefNames_1 other = (sdefNames_1)obj;
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