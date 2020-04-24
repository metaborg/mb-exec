/*
 * Created on 3. okt.. 2006
 *
 * Copyright (c) 2006-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser Lesser General Public License, v2.1.1
 */
package org.spoofax.interpreter.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.spoofax.interpreter.stratego.All;
import org.spoofax.interpreter.stratego.Build;
import org.spoofax.interpreter.stratego.CallDynamic;
import org.spoofax.interpreter.stratego.CallT;
import org.spoofax.interpreter.stratego.ExtSDef;
import org.spoofax.interpreter.stratego.Fail;
import org.spoofax.interpreter.stratego.GuardedLChoice;
import org.spoofax.interpreter.stratego.Id;
import org.spoofax.interpreter.stratego.ImportTerm;
import org.spoofax.interpreter.stratego.Let;
import org.spoofax.interpreter.stratego.Match;
import org.spoofax.interpreter.stratego.One;
import org.spoofax.interpreter.stratego.OpDecl;
import org.spoofax.interpreter.stratego.PrimT;
import org.spoofax.interpreter.stratego.SDefT;
import org.spoofax.interpreter.stratego.SDefT.ArgType;
import org.spoofax.interpreter.stratego.SDefT.ConstType;
import org.spoofax.interpreter.stratego.SDefT.FunType;
import org.spoofax.interpreter.stratego.SDefT.SVar;
import org.spoofax.interpreter.stratego.Scope;
import org.spoofax.interpreter.stratego.Seq;
import org.spoofax.interpreter.stratego.Some;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.util.DebugUtil;
import org.spoofax.terms.io.binary.TermReader;
import org.spoofax.terms.util.TermUtils;

public class StrategoCoreLoader {

    private Context context;

    StrategoCoreLoader(Context context) {
        this.context = context;
    }

    private ExtSDef parseExtSDef(IStrategoAppl t) {

        String name = TermUtils.toJavaStringAt(t, 0);
        IStrategoList svars = TermUtils.toListAt(t, 1);
        IStrategoList tvars = TermUtils.toListAt(t, 2);

        DebugUtil.debug("name  : ", name);

        SVar[] realsvars = makeSVars(svars);
        String[] realtvars = makeVars(tvars);

        VarScope oldScope = context.getVarScope();
        VarScope newScope = new VarScope(oldScope);

        return new ExtSDef(name, realsvars, realtvars, newScope);
    }

    private Strategy parseStrategy(IStrategoAppl appl) throws InterpreterException {

        IStrategoConstructor ctor = appl.getConstructor();
        StrategoSignature sign = context.getStrategoSignature();

        if (ctor.equals(sign.getBuild())) {
            return parseBuild(appl);
        } else if (ctor.equals(sign.getScope())) {
            return parseScope(appl);
        } else if (ctor.equals(sign.getSeq())) {
            return parseSeq(appl);
        } else if (ctor.equals(sign.getGuardedLChoice())) {
            return parseGuardedLChoice(appl);
        } else if (ctor.equals(sign.getMatch())) {
            return parseMatch(appl);
        } else if (ctor.equals(sign.getId())) {
            return parseId(appl);
        } else if (ctor.equals(sign.getCallT())) {
            return parseCallT(appl);
        } else if (ctor.equals(sign.getPrimT())) {
            return parsePrimT(appl);
        } else if (ctor.equals(sign.getLet())) {
            return parseLet(appl);
        } else if (ctor.equals(sign.getFail())) {
            return makeFail(appl);
        } else if (ctor.equals(sign.getId())) {
            return makeId(appl);
        } else if (ctor.equals(sign.getAll())) {
            return makeAll(appl);
        } else if (ctor.equals(sign.getOne())) {
            return makeOne(appl);
        } else if (ctor.equals(sign.getSome())) {
            return makeSome(appl);
        } else if (ctor.equals(sign.getImportTerm())) {
        	return makeImportTerm(appl);
        } else if (ctor.equals(sign.getCallDynamic())){
        	return parseCallDynamic(appl);
        }

        throw new InterpreterException("Unknown op '" + ctor + "'");
    }

    private Strategy makeImportTerm(IStrategoAppl appl) {
    	IStrategoString str = TermUtils.toStringAt(appl, 0);
    	return new ImportTerm(str.stringValue());
	}

	private Strategy makeId(IStrategoAppl appl) {
        return new Id();
    }

    private Some makeSome(IStrategoAppl t) throws InterpreterException {
        Strategy body = parseStrategy(TermUtils.toApplAt(t, 0));
        return new Some(body);
    }

    private One makeOne(IStrategoAppl t) throws InterpreterException {
        Strategy body = parseStrategy(TermUtils.toApplAt(t, 0));
        return new One(body);
    }

    private All makeAll(IStrategoAppl t) throws InterpreterException {
        Strategy body = parseStrategy(TermUtils.toApplAt(t, 0));
        return new All(body);
    }

    private Strategy makeFail(IStrategoAppl appl) {
        return new Fail();
    }

    private Let parseLet(IStrategoAppl t) throws InterpreterException {

        IStrategoTerm[] l = TermUtils.toListAt(t, 0).getAllSubterms();
        SDefT[] defs = new SDefT[l.length];

        for (int i = 0; i < l.length; i++) {
            defs[i] = parseSDefT(TermUtils.toAppl(l[i]));
        }

        Strategy body = parseStrategy(TermUtils.toApplAt(t, 1));

        return new Let(defs, body);
    }

    public SDefT parseSDefT(IStrategoAppl t) throws InterpreterException {
        DebugUtil.debug("parseSDefT()");

        String name = TermUtils.toJavaStringAt(t, 0);
        IStrategoList svars = TermUtils.toListAt(t, 1);
        IStrategoList tvars = TermUtils.toListAt(t, 2);

        DebugUtil.debug(" name  : ", name);
        DebugUtil.debug(" svars : ", svars);
        SVar[] realsvars = makeSVars(svars);
        DebugUtil.debug(" svars : ", realsvars);
        DebugUtil.debug(" tvars : ", tvars);

        String[] realtvars = makeVars(tvars);
        DebugUtil.debug(" tvars : ", realtvars);

        VarScope newScope = new VarScope(context.getVarScope());

        context.setVarScope(newScope);
        Strategy body = parseStrategy(TermUtils.toApplAt(t, 3));

        context.popVarScope();

        DebugUtil.debug(" +name: ", name);

        return new SDefT(name, realsvars, realtvars, body, newScope);
    }

    private String[] makeVars(IStrategoList svars) {

        IStrategoTerm[] sv = svars.getAllSubterms();
        String[] realsvars = new String[sv.length];

        DebugUtil.debug(" vars  : ", svars);

        for (int j = 0; j < svars.size(); j++) {
            realsvars[j]  = TermUtils.toJavaStringAt(sv[j], 0);
        }

        return realsvars;
    }

    private SVar[] makeSVars(IStrategoList svars) {
        DebugUtil.debug("makeSVars()");

        IStrategoTerm[] sv = svars.getAllSubterms();
        SVar[] realsvars = new SVar[sv.length];

        DebugUtil.debug(" vars  : ", svars);

        for (int j = 0; j < sv.length; j++) {
            IStrategoAppl t = (IStrategoAppl) sv[j];
            ArgType type = parseArgType(TermUtils.toApplAt(t, 1));
            String name = TermUtils.toJavaStringAt(t, 0);
            realsvars[j] = new SVar(name, type);
        }

        DebugUtil.debug("       : ", realsvars);
        return realsvars;
    }

    private ArgType parseArgType(IStrategoAppl t) {
        if(Tools.isFunType(t, context)) {
            IStrategoList l = TermUtils.toListAt(t, 0);
            List<ArgType> ch = new ArrayList<ArgType>();
            for (int i = 0; i < l.size(); i++) {
                ch.add(parseArgType(TermUtils.toApplAt(l, i)));
            }
            return new FunType(ch);
        } else if (Tools.isConstType(t, context)) {
            return ConstType.INSTANCE;
        }
        return null;
    }

    private PrimT parsePrimT(IStrategoAppl t) throws InterpreterException {
        String name = TermUtils.toJavaStringAt(t, 0);
        Strategy[] svars = parseStrategyList(TermUtils.toListAt(t, 1));
        IStrategoTerm[] tvars = parseTermList(TermUtils.toListAt(t, 2));

        return new PrimT(name, svars, tvars);
    }

    private Strategy parseCallT(IStrategoAppl t) throws InterpreterException {

        DebugUtil.debug("parseCallT()");
        String name = TermUtils.toJavaStringAt(TermUtils.toApplAt(t, 0), 0);

        DebugUtil.debug(" name  : ", name);

        IStrategoList svars = TermUtils.toListAt(t, 1);
        Strategy[] realsvars = parseStrategyList(svars);

        IStrategoTerm[] realtvars = parseTermList(TermUtils.toListAt(t, 2));

        DebugUtil.debug(" -svars : ", realsvars);
        DebugUtil.debug(" -tvars : ", realtvars);
        return new CallT(name, realsvars, realtvars);
    }

    private Strategy parseCallDynamic(IStrategoAppl t) throws InterpreterException {
    	
        DebugUtil.debug("parseDynamicCall()");
    	IStrategoTerm sref = t.getSubterm(0);
    	
        DebugUtil.debug(" name  : ", sref);

    	IStrategoList svars = TermUtils.toListAt(t, 1);
    	Strategy[] realsvars = parseStrategyList(svars);
    	
    	IStrategoTerm[] realtvars = parseTermList(TermUtils.toListAt(t, 2));
    	
        DebugUtil.debug(" -svars : ", realsvars);
        DebugUtil.debug(" -tvars : ", realtvars);
    	return new CallDynamic(sref, realsvars, realtvars);
    }

    private IStrategoTerm[] parseTermList(IStrategoList tvars) {
        return tvars.getAllSubterms();
    }
/*
        IStrategoTerm[] tv = tvars.getAllSubterms()
        List<IStrategoTerm> v = new IStrategoTerm[.size());
        for (int i = 0; i < tvars.size(); i++) {
            v.add(Tools.termAt(tvars, i));
        }
        return v;
    }
*/
    private Strategy[] parseStrategyList(IStrategoList svars) throws InterpreterException {
        IStrategoTerm[] sv = svars.getAllSubterms();
        Strategy[] v = new Strategy[sv.length];
        for (int i = 0; i < sv.length; i++) {
            v[i] = parseStrategy((IStrategoAppl)sv[i]);
        }
        return v;
    }

    private Id parseId(IStrategoAppl t) {
        return new Id();
    }

    private Match parseMatch(IStrategoAppl t) {
        IStrategoAppl u = TermUtils.toApplAt(t, 0);
        return new Match(u);
    }

    @SuppressWarnings("unchecked")
    private GuardedLChoice parseGuardedLChoice(IStrategoAppl t) throws InterpreterException {
    	LinkedList<Pair<Strategy,Strategy>> s = new LinkedList<Pair<Strategy,Strategy>>();
        IStrategoConstructor ctor = context.getStrategoSignature().getGuardedLChoice();

    	while (t.getConstructor().equals(ctor)) {
          s.add(new Pair<Strategy,Strategy>(parseStrategy(TermUtils.toApplAt(t, 0)), parseStrategy(TermUtils.toApplAt(t, 1))));
          t = TermUtils.toApplAt(t, 2);
    	}

    	s.add(new Pair<Strategy,Strategy>(parseStrategy(t), null));

        return new GuardedLChoice(s.toArray(new Pair[0]));
    }

    private Seq parseSeq(IStrategoAppl t) throws InterpreterException {
    	LinkedList<Strategy> s = new LinkedList<Strategy>();
        StrategoSignature sign = context.getStrategoSignature();

    	while (t.getConstructor().equals(sign.getSeq())) {
          s.add(parseStrategy(TermUtils.toApplAt(t, 0)));
          t = TermUtils.toApplAt(t, 1);
    	}

    	s.add(parseStrategy(t));

        return new Seq(s.toArray(new Strategy[0]));
    }

    private Scope parseScope(IStrategoAppl t) throws InterpreterException {

        IStrategoList vars = TermUtils.toListAt(t, 0);
        List<String> realvars = new ArrayList<String>(vars.size());

        for (int i = 0; i < vars.size(); i++) {
            realvars.add(TermUtils.toJavaStringAt(vars, i));
        }

        Strategy body = parseStrategy(TermUtils.toApplAt(t, 1));

        return new Scope(realvars, body);
    }

    private Build parseBuild(IStrategoAppl t) {
        IStrategoAppl u = TermUtils.toApplAt(t, 0);
        return new Build(u);
    }

    @Deprecated
    public void load(String path) throws IOException, InterpreterException {
        doLoad(new TermReader(context.getProgramFactory()).parseFromFile(path));
    }

    public void load(IStrategoTerm prg) throws InterpreterException {
    	doLoad(prg);
    }

    private void doLoad(IStrategoTerm prg) throws InterpreterException {

        DebugUtil.debug(prg);

        IStrategoList list = TermUtils.toListAt(prg, 0);

        if (!list.isEmpty()) {
            IStrategoAppl sign = TermUtils.toApplAt(list, 0);
            IStrategoAppl strats = TermUtils.toApplAt(list, 1);

            loadConstructors(TermUtils.toListAt(TermUtils.toApplAt(TermUtils.toListAt(sign, 0), 0), 0));
            loadStrategies(TermUtils.toListAt(strats, 0));
        }
    }

    private void loadConstructors(IStrategoList list) {
        for (; !list.isEmpty(); list = list.tail()) {
         // TODO Added the ExtOpDeclInj here, not sure this should be handled differently,
         //      but this extra check will at least prevent the class cast exception otherwise
         //      thrown and allow the loading to recover.
            IStrategoAppl opDecl = (IStrategoAppl) list.head();
        	if (!opDecl.getConstructor().getName().equals("OpDeclInj") &&
        	    !opDecl.getConstructor().getName().equals("ExtOpDeclInj")  ) {
        		String name = TermUtils.toJavaStringAt(opDecl, 0);
        		ArgType argType = parseArgType(TermUtils.toApplAt(opDecl, 1));
        		context.addOpDecl(name, new OpDecl(name, argType));
        	}
        }
    }

    @SuppressWarnings("unused")
    private void loadStrategies(IStrategoList list) throws InterpreterException {
        for (; !list.isEmpty(); list = list.tail()) {
            IStrategoAppl t = (IStrategoAppl) list.head();
            if(Tools.isSDefT(t, context)) {
                SDefT def = parseSDefT(t);
                context.addSVar(def.getName(), def);
            }
            // FIXME: this has never run, since isExtSDef used to check for ExtSDefT (sic)
            else if (false && Tools.isExtSDef(t, context)) {
                ExtSDef def = parseExtSDef(t);
                context.addSVar(def.getName(), def);
                // int x = 0;
                // FIXME: Come up with a good solution for external
                // definitions
                throw new InterpreterException("Illegal ExtSDef in StrategoCore file");
            }
        }
    }

    @Deprecated
    public void load(InputStream stream) throws InterpreterException, IOException {
        if (stream == null)
            throw new IOException("Could not load Stratego core input from null stream");

        doLoad(new TermReader(context.getProgramFactory()).parseFromStream(stream));
    }

}
