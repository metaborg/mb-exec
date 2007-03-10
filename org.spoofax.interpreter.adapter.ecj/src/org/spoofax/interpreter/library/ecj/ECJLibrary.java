/*
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.library.ecj;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.spoofax.interpreter.library.AbstractStrategoOperatorRegistry;

public class ECJLibrary extends AbstractStrategoOperatorRegistry {

    public static final String REGISTRY_NAME = "ECJ";
    public IProject currentProject;
    private IJavaProject currentJavaProject;

    public ECJLibrary() {
        init();
    }
    
    private void init() {
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
        add(new ECJ_search_project_for_type());
        add(new ECJ_current_java_project());
        add(new ECJ_compilation_unit_for_type());
        add(new ECJ_ast_for_compilation_unit());
        add(new ECJ_is_valid_ast_node());
        add(new ECJ_is_valid_ast_nodelist());
        add(new ECJ_create_compilation_unit_buffer());
        add(new ECJ_project_get_file());
        add(new ECJ_file_exists());
        add(new ECJ_path_of_compilationunit());
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
}
