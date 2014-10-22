package ds.generated.interpreter;

import org.metaborg.meta.interpreter.framework.*;

public class S_1 extends AValue 
{ 
  @Override public String toString()
  { 
    return NodeUtils.toString(this);
  }

  public org.spoofax.interpreter.terms.IStrategoTerm _1;

  public S_1 (org.spoofax.interpreter.terms.IStrategoTerm _1) 
  { 
    this._1 = _1;
  }

  public org.spoofax.interpreter.terms.IStrategoTerm get_1()
  { 
    return this._1;
  }
}