/*
 * Created on 9. okt.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.library.AbstractPrimitive;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

public class ECJ_search_for_type extends AbstractPrimitive {

    public ECJ_search_for_type() {
        super("ECJ_search_for_type", 0, 2);
    }
    
    @Override
    public boolean call(IContext env, Strategy[] svars, IStrategoTerm[] tvars)
            throws InterpreterException {

        if(!ECJTools.isIJavaElement(tvars[0]))
            return false;
        if(!Tools.isTermString(tvars[1]))
            return false;
        
        final String className = Tools.asJavaString(tvars[1]);
        SearchPattern sp = SearchPattern.createPattern(className, IJavaSearchConstants.TYPE, IJavaSearchConstants.DECLARATIONS, SearchPattern.R_PATTERN_MATCH | SearchPattern.R_CASE_SENSITIVE);
        IJavaSearchScope ss = SearchEngine.createJavaSearchScope(new IJavaElement[] { ECJTools.asIJavaElement(tvars[0]) });
        
        SearchRequestor requestor = new SearchRequestor() {

            @Override
            public void acceptSearchMatch(SearchMatch match) throws CoreException {
                // TODO Auto-generated method stub
                throw new NotImplementedException();
            }
            
        };
        
        SearchEngine se = new SearchEngine();
        
        try {
            se.search(sp, 
                  new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() },
                  ss, 
                  requestor,
                  null);
        } catch(CoreException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

}
