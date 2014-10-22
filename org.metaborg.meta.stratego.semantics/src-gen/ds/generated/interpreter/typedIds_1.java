package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class typedIds_1 extends NoOpNode implements I_NameExtractor
{ 
  @Children public INodeList<I_Typedid> _1;

  public typedIds_1 (INodeSource source, INodeList<I_Typedid> _1) 
  { 
    this.setSourceInfo(source);
    this._1 = adoptChildren(_1);
  }

  private boolean hasSpecialized;

  public void specializeChildren(int depth)
  { 
    if(!hasSpecialized)
    { 
      for(I_Typedid _1_elem : _1)
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
    final INodeList<I_Typedid> lifted_1 = _1;
    if(lifted_1 != null && lifted_1.isEmpty())
    { 
      final INodeList<I_Node> tmpbuild544 = NodeList.NIL(I_Node.class);
      final INodeList<String> tmpbuild543 = ds.manual.interpreter.Natives.asNILofString_1(tmpbuild544);
      final INodeList<String> lifted_2 = tmpbuild543;
      final INodeList<String> lifted_out_1 = lifted_2;
      final exid_Result exid_Result0 = new exid_Result(lifted_out_1);
      return exid_Result0;
    }
    else
    { 
      if(lifted_1 != null && !lifted_1.isEmpty())
      { 
        final I_Typedid lifted_3 = lifted_1.head();
        final INodeList<I_Typedid> xs = lifted_1.tail();
        final VarDec_2 tmpbuild547 = lifted_3.match(VarDec_2.class);
        if(tmpbuild547 != null)
        { 
          final String x = tmpbuild547.get_1();
          final I_Type dc = tmpbuild547.get_2();
          final typedIds_1 tmpbuild546 = new typedIds_1(getSourceInfo(), xs);
          final I_NameExtractor lifted_4 = tmpbuild546;
          final exid_Result tmpresult118 = lifted_4.exec_exid();
          final INodeList<String> xs_ = tmpresult118.value;
          final INodeList<String> tmpbuild545 = new NodeList<String>(x, xs_);
          final INodeList<String> lifted_2 = tmpbuild545;
          final INodeList<String> lifted_out_1 = lifted_2;
          final exid_Result exid_Result0 = new exid_Result(lifted_out_1);
          return exid_Result0;
        }
      }
    }
    throw new InterpreterException("Rule failure");
  }

  public INodeList<I_Typedid> get_1()
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
    final typedIds_1 other = (typedIds_1)obj;
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