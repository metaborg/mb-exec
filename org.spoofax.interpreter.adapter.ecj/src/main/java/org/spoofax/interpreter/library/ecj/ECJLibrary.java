/*
 *
 * Copyright (c) 2005-2011, Karl Trygve Kalleberg <karltk near strategoxt dot org>
 *
 * Licensed under the GNU Lesser Public License, v2.1
 */
package org.spoofax.interpreter.library.ecj;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.Interpreter;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;

public class ECJLibrary extends AbstractStrategoOperatorRegistry {

    public static final String REGISTRY_NAME = "ECJ";
    public IProject currentProject;
    private IJavaProject currentJavaProject;
	private ASTParser astParser = null;
	private NullProgressMonitor nullProgressMonitor;
	private ProjectUtils projectUtils;

    private ECJLibrary() {
        init();
    }

    private void init() {
        add(new ECJ_parse_file());
        add(new ECJ_parse_only());
        add(new ECJ_parse_and_resolve());
        add(new ECJ_open_project());
        add(new ECJ_create_project());
        add(new ECJ_add_source_folder());
        add(new ECJ_add_jar());
        add(new ECJ_binding_of_name());
        add(new ECJ_type_of_type());
        add(new ECJ_type_of_typedecl());
        add(new ECJ_type_of_typeparameter());
        add(new ECJ_method_of_methoddecl());
        add(new ECJ_method_of_methodinvoc());
        add(new ECJ_method_of_supermethodinvoc());
        add(new ECJ_method_of_superctorinvoc());
        add(new ECJ_type_of_expr());
        add(new ECJ_is_cast_compatible());
        add(new ECJ_is_subtype_compatible());
        add(new ECJ_current_project());
        add(new ECJ_search_for_type());
        add(new ECJ_search_for_supertype_type_refs());
        add(new ECJ_search_project_for_type());
        add(new ECJ_current_java_project());
        add(new ECJ_icompilation_unit_for_type());
        add(new ECJ_ast_for_compilation_unit());
        add(new ECJ_is_valid_ast_node());
        add(new ECJ_is_valid_ast_nodelist());
        add(new ECJ_create_compilation_unit_buffer());
        add(new ECJ_project_get_file());
        add(new ECJ_file_exists());
        add(new ECJ_path_of_compilationunit());
        add(new ECJ_is_binding_primitive_type());
        add(new ECJ_hierarchy_for_type());
        add(new ECJ_rewrite_compilation_unit());
        add(new ECJ_rewrite_file());
        add(new ECJ_search_for_supertype_type_refs_in_hierarchy());
        add(new ECJ_unbacked_compilation_unit_for_type());
        add(new ECJ_create_package());
        add(new ECJ_signature_to_type());
        add(new ECJ_search_for_supertype_type_refs_no_generics());
        add(new ECJ_binding_of_methoddecl());
        add(new ECJ_create_java_project());
        add(new ECJ_delete_project());
        add(new ECJ_list_projects());
    }

    public IProject getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(IProject currentProject) {
        this.currentProject = currentProject;
    }

    public IJavaProject getCurrentJavaProject() {
        return currentJavaProject;
    }

    public void setCurrentJavaProject(IJavaProject currentJavaProject) {
        this.currentJavaProject = currentJavaProject;
    }

	public void log(String string) {
		System.err.println("FIXME logger");
	}

	public ASTParser getParser() {
		if(astParser  == null)
			astParser = ASTParser.newParser(AST.JLS3);
		return astParser;
	}

	public IProgressMonitor getNullProgressMonitor() {
		if(nullProgressMonitor == null)
			nullProgressMonitor = new NullProgressMonitor();
		return nullProgressMonitor;
	}

	public String getOperatorRegistryName() {
		return REGISTRY_NAME;
	}

    public static void attach(Interpreter intp) throws IOException, InterpreterException {
        attach(intp, new ECJLibrary(), "/share/ecj-library.ctree");
    }

    ProjectUtils getProjectUtils() {
        if(projectUtils == null) {
            projectUtils = new ProjectUtils(getNullProgressMonitor());
        }
        return projectUtils;
    }

    static ECJLibrary registry(IContext env) {
        return (ECJLibrary) env.getOperatorRegistry(REGISTRY_NAME);
    }

    public static boolean invokeExceptionHandler(IContext env, Exception e) {
        return false;
    }
}
