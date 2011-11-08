/*
 * Created on 9. sep. 2008
 *
 * Copyright (c) 2005-2008, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.spoofax.interpreter.adapter.ecj.ECJFactory;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_search_for_supertype_type_refs extends AbstractPrimitive {

	public ECJ_search_for_supertype_type_refs() {
		super("ECJ_search_for_supertype_type_refs", 0, 2);
	}

	@Override
	public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
	throws InterpreterException {

		if(!ECJTools.isIJavaElement(tvars[0]))
			return false;
		if(!Tools.isTermString(tvars[1]))
			return false;

		// TODO: This only works in 3.4, what to do for other versions? 

		// FIXME this method will currently only return exact type matches
		//       it is unclear whether searches for the supertype Foo<Object>
		//       should also allow Foo<Bar> to be returned; for now, it's explicitly
		//       forbidden

		final String className = Tools.asJavaString(tvars[1]);
		SearchPattern sp = SearchPattern.createPattern(className, 
				IJavaSearchConstants.TYPE, 
				IJavaSearchConstants.SUPERTYPE_TYPE_REFERENCE, 
				SearchPattern.R_EXACT_MATCH | SearchPattern.R_CASE_SENSITIVE);


		try { 
			final IJavaSearchScope ss =  SearchEngine.createJavaSearchScope(new IJavaElement[] { ECJTools.asIJavaElement(tvars[0]) });

			final Collection<IType> results = new LinkedList<IType>();

			SearchRequestor requestor = new SearchRequestor() {

				@Override
				public void acceptSearchMatch(SearchMatch match) throws CoreException {
					System.out.println(match.getElement() + "/" + match.getClass().toString() + "/" + match.getElement().getClass().toString());
					IType t = (IType)match.getElement();
					System.out.println(t.getFullyQualifiedName());
					for(String s : t.getSuperInterfaceNames())
						if(s.equals(className)) {
							results.add(t);
							return;
						}
				}

			};

			SearchEngine se = new SearchEngine();


			se.search(sp, 
					new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() },
					ss, 
					requestor,
					null);
			IStrategoTerm[] r = new IStrategoTerm[results.size()];
			int pos = 0;
			for(IType t : results)
				r[pos++] = ECJFactory.wrap(t);
			env.setCurrent(env.getFactory().makeList(r));
		} catch(CoreException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
