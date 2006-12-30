/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.ecjadapter.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.AssertStatement;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EmptyStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.LabeledStatement;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MemberRef;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.MethodRef;
import org.eclipse.jdt.core.dom.MethodRefParameter;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;
import org.eclipse.jdt.core.dom.TypeLiteral;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.WildcardType;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.spoofax.NotImplementedException;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoConstructor;
import org.spoofax.interpreter.terms.IStrategoInt;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoReal;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.IStrategoTuple;
import org.spoofax.interpreter.terms.ITermFactory;

public class ECJFactory implements ITermFactory {

    private static final int ARRAY_ACCESS = 1;
    private static final int PACKAGE_DECLARATION = 2;
    private static final int NONE = 3;
    private static final int BOOLEAN_LITERAL = 4;
    private static final int BOOLEAN_TYPE = 5;
    private static final int BREAK_STATEMENT = 6;
    private static final int CATCH_CLAUSE = 7;
    private static final int CHARACTER_LITERAL = 8;
    private static final int CLASS_INSTANCE_CREATION = 9;
    private static final int ANNOTATION_TYPE_DECLARATION = 10;
    private static final int ENUM_DECLARATION = 11;
    private static final int TYPE_DECLARATION = 12;
    private static final int ANNOTATION_TYPE_MEMBER_DECLARATION = 13;
    private static final int ENUM_CONSTANT_DECLARATION = 14;
    private static final int FIELD_DECLARATION = 15;
    private static final int INITIALIZER = 16;
    private static final int METHOD_DECLARATION = 17;
    private static final int BLOCK_COMMENT = 18;
    private static final int JAVADOC = 19;
    private static final int LINE_COMMENT = 20;
    private static final int COMPILATION_UNIT = 21;
    private static final int MARKER_ANNOTATION = 22;
    private static final int NORMAL_ANNOTATION = 23;
    private static final int SINGLE_MEMBER_ANNOTATION = 24;
    private static final int ARRAY_CREATION = 25;
    private static final int ARRAY_INITIALIZER = 26;
    private static final int ASSIGNMENT = 27;
    private static final int CAST_EXPRESSION = 28;
    private static final int CONDITIONAL_EXPRESSION = 29;
    private static final int FIELD_ACCESS = 30;
    private static final int INFIX_EXPRESSION = 31;
    private static final int INSTANCEOF_EXPRESSION = 32;
    private static final int METHOD_INVOCATION = 33;
    private static final int QUALIFIED_NAME = 34;
    private static final int SIMPLE_NAME = 35;
    private static final int NULL_LITERAL = 36;
    private static final int NUMBER_LITERAL = 37;
    private static final int PARENTHESIZED_EXPRESSION = 38;
    private static final int POSTFIX_EXPRESSION = 39;
    private static final int PREFIX_EXPRESSION = 40;
    private static final int STRING_LITERAL = 41;
    private static final int SUPER_FIELD_ACCESS = 42;
    private static final int THIS_EXPRESSION = 43;
    private static final int VARIABLE_DECLARATION_EXPRESSION = 44;
    private static final int IMPORT_DECLARATION = 45;
    private static final int MEMBER_REF = 46;
    private static final int MEMBER_VALUE_PAIR = 47;
    private static final int METHOD_REF = 48;
    private static final int METHOD_REF_PARAMETER = 49;
    private static final int MODIFIER = 50;
    private static final int MODIFIER_KEYWORD = 51;
    private static final int POSTFIX_EXPRESSION_OPERATOR = 52;
    private static final int PREFIX_EXPRESSION_OPERATOR = 53;
    private static final int ASSERT_STATEMENT = 54;
    private static final int BLOCK = 55;
    private static final int CONSTRUCTOR_INVOCATION = 56;
    private static final int CONTINUE_STATEMENT = 57;
    private static final int DO_STATEMENT = 58;
    private static final int EMPTY_STATEMENT = 59;
    private static final int ENHANCED_FOR_STATEMENT = 60;
    private static final int EXPRESSION_STATEMENT = 61;
    private static final int FOR_STATEMENT = 62;
    private static final int IF_STATEMENT = 63;
    private static final int LABELED_STATEMENT = 64;
    private static final int RETURN_STATEMENT = 65;
    private static final int SUPER_CONSTRUCTOR_INVOCATION = 66;
    private static final int SWITCH_CASE = 67;
    private static final int SWITCH_STATEMENT = 68;
    private static final int SYNCHRONIZED_STATEMENT = 69;
    private static final int THROW_STATEMENT = 70;
    private static final int TRY_STATEMENT = 71;
    private static final int TYPE_DECLARATION_STATEMENT = 72;
    private static final int TYPE_LITERAL = 73;
    private static final int VARIABLE_DECLARATION_STATEMENT = 74;
    private static final int WHILE_STATEMENT = 75;
    private static final int SUPER_METHOD_INVOCATION = 76;
    private static final int TAG_ELEMENT = 77;
    private static final int TEXT_ELEMENT = 78;
    private static final int ARRAY_TYPE = 79;
    private static final int DOUBLE_TYPE = 80;
    private static final int FLOAT_TYPE = 81;
    private static final int INT_TYPE = 82;
    private static final int LONG_TYPE = 83;
    private static final int PARAMETERIZED_TYPE = 84;
    private static final int PRIMITIVE_TYPE = 85;
    private static final int QUALIFIED_TYPE = 86;
    private static final int SIMPLE_TYPE = 87;
    private static final int WILDCARD_TYPE = 88;
    private static final int TYPE_PARAMETER = 89;
    private static final int SINGLE_VARIABLE_DECLARATION = 90;
    private static final int VARIABLE_DECLARATION_FRAGMENT = 91;
    
    private Map<String,Integer> ctorNameToIndexMap;

    public ECJFactory() {
        ctorNameToIndexMap = new HashMap<String,Integer>();
        ctorNameToIndexMap.put("ArrayAccess", ARRAY_ACCESS);
        ctorNameToIndexMap.put("PackageDeclaration", PACKAGE_DECLARATION);
        ctorNameToIndexMap.put("BooleanLiteral", BOOLEAN_LITERAL);
        ctorNameToIndexMap.put("BooleanType", BOOLEAN_TYPE);
        ctorNameToIndexMap.put("BreakStatement", BREAK_STATEMENT);
        ctorNameToIndexMap.put("CatchClause", CATCH_CLAUSE);
        ctorNameToIndexMap.put("CharacterLiteral", CHARACTER_LITERAL);
        ctorNameToIndexMap.put("ClassInstanceCreation", CLASS_INSTANCE_CREATION);
        ctorNameToIndexMap.put("AnnotationTypeDeclaration", ANNOTATION_TYPE_DECLARATION);
        ctorNameToIndexMap.put("EnumDeclaration", ENUM_DECLARATION);
        ctorNameToIndexMap.put("TypeDeclaration", TYPE_DECLARATION);
        ctorNameToIndexMap.put("AnnotatioTypeMemberDeclaration", ANNOTATION_TYPE_MEMBER_DECLARATION);
        ctorNameToIndexMap.put("EnumConstantDeclaration", ENUM_CONSTANT_DECLARATION);
        ctorNameToIndexMap.put("FieldDeclaration", FIELD_DECLARATION);
        ctorNameToIndexMap.put("Initializer", INITIALIZER);
        ctorNameToIndexMap.put("MethodDeclaration", METHOD_DECLARATION);
        ctorNameToIndexMap.put("BlockComment", BLOCK_COMMENT);
        ctorNameToIndexMap.put("Javadoc", JAVADOC);
        ctorNameToIndexMap.put("LineComment", LINE_COMMENT);
        ctorNameToIndexMap.put("CompilationUnit", COMPILATION_UNIT);
        ctorNameToIndexMap.put("MarkerAnnotation", MARKER_ANNOTATION);
        ctorNameToIndexMap.put("NormalAnnotation", NORMAL_ANNOTATION);
        ctorNameToIndexMap.put("SingleMemberAnnotation", SINGLE_MEMBER_ANNOTATION);
        ctorNameToIndexMap.put("ArrayCreation", ARRAY_CREATION);
        ctorNameToIndexMap.put("ArrayInitializer", ARRAY_INITIALIZER);
        ctorNameToIndexMap.put("Assignment", ASSIGNMENT);
        ctorNameToIndexMap.put("CastExpression", CAST_EXPRESSION);
        ctorNameToIndexMap.put("ConditionalExpression", CONDITIONAL_EXPRESSION);
        ctorNameToIndexMap.put("FieldAccess", FIELD_ACCESS);
        ctorNameToIndexMap.put("InfixExpression", INFIX_EXPRESSION);
        ctorNameToIndexMap.put("InstanceofExpression", INSTANCEOF_EXPRESSION);
        ctorNameToIndexMap.put("MethodInvocation", METHOD_INVOCATION);
        ctorNameToIndexMap.put("QualifiedName", QUALIFIED_NAME);
        ctorNameToIndexMap.put("SimpleName", SIMPLE_NAME);
        ctorNameToIndexMap.put("NullLiteral", NULL_LITERAL);
        ctorNameToIndexMap.put("NumberLiteral", NUMBER_LITERAL);
        ctorNameToIndexMap.put("ParenthesizedExpression", PARENTHESIZED_EXPRESSION);
        ctorNameToIndexMap.put("PostfixExpression", POSTFIX_EXPRESSION);
        ctorNameToIndexMap.put("PrefixExpression", PREFIX_EXPRESSION);
        ctorNameToIndexMap.put("StringLiteral", STRING_LITERAL);
        ctorNameToIndexMap.put("SuperFieldAccess", SUPER_FIELD_ACCESS);
        ctorNameToIndexMap.put("ThisExpression", THIS_EXPRESSION);
        ctorNameToIndexMap.put("VariableDeclarationExpression", VARIABLE_DECLARATION_EXPRESSION);
        ctorNameToIndexMap.put("ImportDeclaration", IMPORT_DECLARATION);
        ctorNameToIndexMap.put("MemberRef", MEMBER_REF);
        ctorNameToIndexMap.put("MemberValuePair", MEMBER_VALUE_PAIR);
        ctorNameToIndexMap.put("MethodRef", METHOD_REF);
        ctorNameToIndexMap.put("MethodRefParameter", METHOD_REF_PARAMETER);
        ctorNameToIndexMap.put("Modifier", MODIFIER);
        ctorNameToIndexMap.put("ModifiedKeyword", MODIFIER_KEYWORD);
        ctorNameToIndexMap.put("PackageDeclaration", PACKAGE_DECLARATION);
        ctorNameToIndexMap.put("PostfixExpressionOperator", POSTFIX_EXPRESSION_OPERATOR);
        ctorNameToIndexMap.put("PrefixExpressionOperator", PREFIX_EXPRESSION_OPERATOR);
        ctorNameToIndexMap.put("AssertStatement", ASSERT_STATEMENT);
        ctorNameToIndexMap.put("Block", BLOCK);
        ctorNameToIndexMap.put("ConstructorInvocation", CONSTRUCTOR_INVOCATION);
        ctorNameToIndexMap.put("ContinueStatement", CONTINUE_STATEMENT);
        ctorNameToIndexMap.put("DoStatement", DO_STATEMENT);
        ctorNameToIndexMap.put("EmptyStatement", EMPTY_STATEMENT);
        ctorNameToIndexMap.put("EnhancedForStatement", ENHANCED_FOR_STATEMENT);
        ctorNameToIndexMap.put("ExpressionStatement", EXPRESSION_STATEMENT);
        ctorNameToIndexMap.put("ForStatement", FOR_STATEMENT);
        ctorNameToIndexMap.put("IfStatement", IF_STATEMENT);
        ctorNameToIndexMap.put("LabeledStatement", LABELED_STATEMENT);
        ctorNameToIndexMap.put("ReturnStatement", RETURN_STATEMENT);
        ctorNameToIndexMap.put("SuperConstructorInvocation", SUPER_CONSTRUCTOR_INVOCATION);
        ctorNameToIndexMap.put("SwitchCase", SWITCH_CASE);
        ctorNameToIndexMap.put("SwitchStatement", SWITCH_STATEMENT);
        ctorNameToIndexMap.put("SynchronizedStatement", SYNCHRONIZED_STATEMENT);
        ctorNameToIndexMap.put("ThrowStatement", THROW_STATEMENT);
        ctorNameToIndexMap.put("TryStatement", TRY_STATEMENT);
        ctorNameToIndexMap.put("TypeDeclarationStatement", TYPE_DECLARATION_STATEMENT);
        ctorNameToIndexMap.put("TypeLiteral", TYPE_LITERAL);
        ctorNameToIndexMap.put("VariableDeclarationStatement", VARIABLE_DECLARATION_STATEMENT);
        ctorNameToIndexMap.put("WhileStatement", WHILE_STATEMENT);
        ctorNameToIndexMap.put("SuperMethodInvocation", SUPER_METHOD_INVOCATION);
        ctorNameToIndexMap.put("TagElement", TAG_ELEMENT);
        ctorNameToIndexMap.put("TextElement", TEXT_ELEMENT);
        ctorNameToIndexMap.put("ArrayType", ARRAY_TYPE);
        ctorNameToIndexMap.put("DoubleType", DOUBLE_TYPE);
        ctorNameToIndexMap.put("FloatType", FLOAT_TYPE);
        ctorNameToIndexMap.put("IntType", INT_TYPE);
        ctorNameToIndexMap.put("LongType", LONG_TYPE);
        ctorNameToIndexMap.put("ParameterizedType", PARAMETERIZED_TYPE);
        ctorNameToIndexMap.put("PrimitiveType", PRIMITIVE_TYPE);
        ctorNameToIndexMap.put("QualifiedType", QUALIFIED_TYPE);
        ctorNameToIndexMap.put("SimpleType", SIMPLE_TYPE);
        ctorNameToIndexMap.put("WildcardType", WILDCARD_TYPE);
        ctorNameToIndexMap.put("TypeParameter", TYPE_PARAMETER);
        ctorNameToIndexMap.put("SingleVariableDeclaration", SINGLE_VARIABLE_DECLARATION);
        ctorNameToIndexMap.put("VariableDeclarationFragment", VARIABLE_DECLARATION_FRAGMENT);
    }
    
    public IStrategoTerm parseFromFile(String path) throws IOException {
        throw new NotImplementedException();
    }

    public IStrategoTerm parseFromStream(InputStream inputStream) throws IOException {
        throw new NotImplementedException();
    }

    public IStrategoTerm parseFromString(String text) {
        if(text.equals("()")) {
            return makeTuple();
        } else if(text.charAt(0) == '"') {
            return makeString(text.substring(1).substring(0, text.length() - 2));
        }
        throw new NotImplementedException();
    }

    public void unparseToFile(IStrategoTerm t, OutputStream ous) throws IOException {
        throw new NotImplementedException();
    }

    public boolean hasConstructor(String s, int i) {
        throw new NotImplementedException();
    }

    private boolean ensureNone(IStrategoTerm term) {
        return term instanceof None;
    }

    private boolean ensureName(IStrategoTerm term) {
        return term instanceof WrappedName;
    }

    private boolean ensureAnnotations(IStrategoTerm term) {
        return term instanceof WrappedASTNodeList;
    }

    private boolean ensureJavadoc(IStrategoTerm term) {
        return term instanceof WrappedJavadoc;
    }

    private Name getName(IStrategoTerm term) {
        return ((WrappedName)term).getWrappee();
    }

    private List getAnnotations(IStrategoTerm term) {
        return ((WrappedASTNodeList)term).getWrappee();
    }

    private Javadoc getJavadoc(IStrategoTerm term) {
        return ((WrappedJavadoc)term).getWrappee();
    }

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoList terms) {
        IStrategoTerm[] kids = new IStrategoTerm[terms.size()];
        for(int i = 0; i < terms.size(); i++)
            kids[i] = terms.get(i);
        return makeAppl(ctr, kids);
    }

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoTerm... terms) {
        IStrategoAppl t = constructASTNode(ctr, terms);
        if(t == null)
            return ctr.instantiate(this, terms);
        return t;
    }

    @SuppressWarnings("unchecked")
    private IStrategoAppl constructASTNode(IStrategoConstructor ctr, IStrategoTerm[] kids) {
        int index = ctorNameToIndex(ctr);
        AST ast = AST.newAST(AST.JLS3);
        switch(index) {
        case ANNOTATION_TYPE_DECLARATION:
            break;
        case ANNOTATION_TYPE_MEMBER_DECLARATION:
            break;
        case ARRAY_ACCESS: 
            ArrayAccess x = ast.newArrayAccess();
            if(!ensureExpression(kids[0]) || !ensureExpression(kids[1]))
                return null;
            x.setArray(asExpression(kids[0]));
            x.setIndex(asExpression(kids[1]));
            return wrap(x);
        case ARRAY_CREATION:
            break;
        case ARRAY_INITIALIZER:
            break;
        case ARRAY_TYPE:
            break;
        case ASSERT_STATEMENT:
            break;
        case ASSIGNMENT:
            break;
        case BLOCK:
            break;
        case BLOCK_COMMENT:
            break;
        case BOOLEAN_LITERAL:
            break;
        case BOOLEAN_TYPE:
            break;
        case BREAK_STATEMENT:
            break;
        case CAST_EXPRESSION:
            break;
        case CATCH_CLAUSE:
            break;
        case CHARACTER_LITERAL:
            break;
        case CLASS_INSTANCE_CREATION:
            break;
        case COMPILATION_UNIT:
            break;
        case CONDITIONAL_EXPRESSION:
            break;
        case CONSTRUCTOR_INVOCATION:
            break;
        case CONTINUE_STATEMENT:
            break;
        case DO_STATEMENT:
            break;
        case DOUBLE_TYPE:
            break;
        case EMPTY_STATEMENT:
            break;
        case ENHANCED_FOR_STATEMENT:
            break;
        case ENUM_CONSTANT_DECLARATION:
            break;
        case ENUM_DECLARATION:
            break;
        case FIELD_ACCESS:
            break;
        case FIELD_DECLARATION:
            break;
        case FLOAT_TYPE:
            break;
        case FOR_STATEMENT:
            break;
        case IF_STATEMENT:
            break;
        case IMPORT_DECLARATION:
            break;
        case INFIX_EXPRESSION:
            break;
        case INITIALIZER:
            break;
        case INSTANCEOF_EXPRESSION:
            break;
        case INT_TYPE:
            break;
        case JAVADOC:
            break;
        case LABELED_STATEMENT:
            break;
        case LINE_COMMENT:
            break;
        case LONG_TYPE:
            break;
        case MARKER_ANNOTATION:
            break;
        case MEMBER_REF:
            break;
        case MEMBER_VALUE_PAIR:
            break;
        case METHOD_DECLARATION:
            break;
        case METHOD_INVOCATION:
            break;
        case METHOD_REF:
            break;
        case METHOD_REF_PARAMETER:
            break;
        case MODIFIER:
            break;
        case MODIFIER_KEYWORD:
            break;
        case NONE:
            return None.INSTANCE;
        case NORMAL_ANNOTATION:
            break;
        case NULL_LITERAL:
            break;
        case NUMBER_LITERAL:
            break;
        case PACKAGE_DECLARATION:
            if(!(ensureJavadoc(kids[0]) || ensureNone(kids[0])))
                return null;
            if(!ensureAnnotations(kids[1]))
                return null;
            if(!ensureName(kids[2]))
                return null;
            
            PackageDeclaration pd = ast.newPackageDeclaration();
            pd.setJavadoc(getJavadoc(kids[0]));
            pd.annotations().addAll(getAnnotations(kids[1]));
            pd.setName(getName(kids[2]));
            return wrap(pd);
        case PARAMETERIZED_TYPE:
            break;
        case PARENTHESIZED_EXPRESSION:
            break;
        case POSTFIX_EXPRESSION:
            break;
        case POSTFIX_EXPRESSION_OPERATOR:
            break;
        case PREFIX_EXPRESSION:
            break;
        case PREFIX_EXPRESSION_OPERATOR:
            break;
        case PRIMITIVE_TYPE:
            break;
        case QUALIFIED_NAME:
            break;
        case QUALIFIED_TYPE:
            break;
        case RETURN_STATEMENT:
            break;
        case SIMPLE_NAME:
            break;
        case SIMPLE_TYPE:
            break;
        case SINGLE_MEMBER_ANNOTATION:
            break;
        case SINGLE_VARIABLE_DECLARATION:
            break;
        case STRING_LITERAL:
            break;
        case SUPER_CONSTRUCTOR_INVOCATION:
            break;
        case SUPER_FIELD_ACCESS:
            break;
        case SUPER_METHOD_INVOCATION:
            break;
        case TAG_ELEMENT:
            break;
        case TEXT_ELEMENT:
            break;
        case THIS_EXPRESSION:
            break;
        case THROW_STATEMENT:
            break;
        case TRY_STATEMENT:
            break;
        case TYPE_DECLARATION:
            break;
        case TYPE_DECLARATION_STATEMENT:
            break;
        case TYPE_LITERAL:
            break;
        case TYPE_PARAMETER:
            break;
        case VARIABLE_DECLARATION_EXPRESSION:
            break;
        case VARIABLE_DECLARATION_FRAGMENT:
            break;
        case VARIABLE_DECLARATION_STATEMENT:
            break;
        case WHILE_STATEMENT:
            break;
        case WILDCARD_TYPE:
            break;
        default:
            return null;
        }
        throw new NotImplementedException();
    }

    private boolean ensureExpression(IStrategoTerm term) {
        return term instanceof WrappedExpression;
    }

    private Expression asExpression(IStrategoTerm term) {
        if(term instanceof WrappedASTNode) {
            ASTNode n = ((WrappedASTNode) term).getWrappee(); 
            if(n instanceof Expression)
                return (Expression) n;
        }
        throw new NotImplementedException();
    }

    private int ctorNameToIndex(IStrategoConstructor ctr) {
        return ctorNameToIndexMap.get(ctr.getName());
    }

    public IStrategoConstructor makeConstructor(String string, int arity, boolean quoted) {
        return new ASTCtor(string, arity);
        //throw new NotImplementedException();
    }

    public IStrategoInt makeInt(int i) {
        return new WrappedInt(i);
    }

    @SuppressWarnings("unchecked")
    public IStrategoList makeList(IStrategoTerm... terms) {
        
        boolean mustUseGeneric = false;
        for(IStrategoTerm t : terms)
            if(!(t instanceof WrappedASTNode))
                mustUseGeneric = true;
        
        if(mustUseGeneric) {
            return new WrappedGenericList(terms);
        }
        
        List r = new ArrayList();
        for(IStrategoTerm t : terms)
            r.add(t);
        return new WrappedASTNodeList(r);
        //throw new NotImplementedException();
    }

    public IStrategoList makeList(Collection<IStrategoTerm> terms) {
        throw new NotImplementedException();
    }

    public IStrategoReal makeReal(double d) {
        throw new NotImplementedException();
    }

    public IStrategoString makeString(String s) {
        return new WrappedString(s);
        //throw new NotImplementedException();
    }

    public IStrategoTuple makeTuple(IStrategoTerm... terms) {
        return new WrappedTuple(terms);
    }

    public static IStrategoTerm wrap(Javadoc javadoc) {
        if(javadoc == null)
            return None.INSTANCE;
        else
            return new WrappedJavadoc(javadoc);
    }

    public static IStrategoTerm wrap(List list) {
        if(list == null)
            return None.INSTANCE;
        else
            return new WrappedASTNodeList(list);
    }

    static IStrategoTerm wrapName(Name name) {
        
        if(name == null)
            return None.INSTANCE;
        
        if(name instanceof QualifiedName)
            return wrap((QualifiedName) name);
        if(name instanceof SimpleName)
            return wrap((SimpleName) name);
        
        throw new NotImplementedException("Unknown Name type: " + name.getClass());
    }

    static IStrategoTerm wrap(SimpleName name) {
        if(name == null)
            return None.INSTANCE;
        else
            return new WrappedSimpleName(name);
    }

    private static IStrategoTerm wrap(QualifiedName name) {
        if(name == null)
            return None.INSTANCE;
        else
            return new WrappedQualifiedName(name);
    }

    public static IStrategoTerm genericWrap(ASTNode node) {
        
        if(node instanceof ImportDeclaration)
            return wrap((ImportDeclaration) node);
        if(node instanceof Name)
            return wrapName((Name) node);
        if(node instanceof List)
            return wrap((List) node);
        if(node instanceof Javadoc)
            return wrap((Javadoc) node);
        if(node instanceof CompilationUnit)
            return wrap((CompilationUnit) node);
        if(node instanceof PackageDeclaration)
            return wrap((PackageDeclaration) node);
        if(node instanceof TypeDeclaration)
            return wrap((TypeDeclaration) node);
        if(node instanceof MethodDeclaration)
            return wrap((MethodDeclaration) node);
        if(node instanceof SingleVariableDeclaration)
            return wrap((SingleVariableDeclaration) node);
        if(node instanceof Expression)
            return wrapExpression((Expression) node);
        if(node instanceof VariableDeclarationFragment)
            return wrap((VariableDeclarationFragment) node);
        if(node instanceof AnonymousClassDeclaration)
            return wrap((AnonymousClassDeclaration) node);
        if(node instanceof BodyDeclaration)
            return wrapBody((BodyDeclaration) node);
        if(node instanceof CatchClause)
            return wrap((CatchClause) node);
        if(node instanceof Comment) 
            return wrapComment((Comment) node);
        if(node instanceof MemberRef)
            return wrap((MemberRef) node);
        if(node instanceof MemberValuePair)
            return wrap((MemberValuePair) node);
        if(node instanceof MethodRef)
            return wrap((MethodRef) node);
        if(node instanceof MethodRefParameter)
            return wrap((MethodRefParameter) node);
        if(node instanceof Modifier)
            return wrap((Modifier) node);
        if(node instanceof PackageDeclaration)
            return wrap((PackageDeclaration) node);
        if(node instanceof Statement)
            return wrapStatement((Statement) node);
        if(node instanceof TagElement)
            return wrap((TagElement) node);
        if(node instanceof TextElement)
            return wrap((TextElement) node);
        if(node instanceof Type)
            return wrapType((Type) node);
        if(node instanceof TypeParameter)
            return wrap((TypeParameter) node);
        if(node instanceof VariableDeclaration)
            return wrapVarDecl((VariableDeclaration) node);
        
        throw new NotImplementedException("Unknown ASTNode type" + node.getClass());
    }

    private static IStrategoTerm wrapVarDecl(VariableDeclaration decl) {
        if(decl instanceof SingleVariableDeclaration)
            return wrap((SingleVariableDeclaration) decl);
        if(decl instanceof VariableDeclarationFragment)
            return wrap((VariableDeclarationFragment) decl);
        
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrapComment(Comment comment) {
        if(comment instanceof BlockComment)
            return wrap((BlockComment) comment);
        if(comment instanceof Javadoc)
            return wrap((Javadoc) comment);
        if(comment instanceof LineComment)
            return wrap((LineComment) comment);
        
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(LineComment comment) {
        if(comment == null)
            return None.INSTANCE;
        else
            return new WrappedLineComment(comment);
    }

    private static IStrategoTerm wrap(BlockComment comment) {
        if(comment == null)
            return None.INSTANCE;
        else
            return new WrappedBlockComment(comment);
    }

    private static IStrategoTerm wrapBody(BodyDeclaration decl) {
       
        if(decl instanceof AbstractTypeDeclaration)
            return wrapTypeDecl((AbstractTypeDeclaration) decl);
        if(decl instanceof AnnotationTypeMemberDeclaration)
            return wrap((AnnotationTypeMemberDeclaration) decl);
        if(decl instanceof EnumConstantDeclaration)
            return wrap((EnumConstantDeclaration) decl);
        if(decl instanceof FieldDeclaration)
            return wrap((FieldDeclaration) decl);
        if(decl instanceof Initializer)
            return wrap((Initializer) decl);
        if(decl instanceof MethodDeclaration)
            return wrap((MethodDeclaration) decl);
        
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(AnnotationTypeMemberDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedAnnotationTypeMemberDeclaration(declaration);
    }

    private static IStrategoTerm wrap(EnumConstantDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedEnumConstantDeclaration(declaration);
    }

    private static IStrategoTerm wrap(FieldDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedFieldDeclaration(declaration);
    }

    private static IStrategoTerm wrap(Initializer initializer) {
        if(initializer == null)
                return None.INSTANCE;
        else
            return new WrappedInitializer(initializer);
    }

    private static IStrategoTerm wrap(TypeParameter parameter) {
        if(parameter == null)
            return None.INSTANCE;
        else
            return new WrappedTypeParameter(parameter);
    }

    private static IStrategoTerm wrap(TextElement element) {
        if(element == null)
            return None.INSTANCE;
        else
            return new WrappedTextElement(element);
    }

    private static IStrategoTerm wrap(TagElement element) {
        if(element == null)
            return None.INSTANCE;
        else
            return new WrappedTagElement(element);
    }

    static IStrategoTerm wrapStatement(Statement stat) {
        
        if(stat == null)
            return None.INSTANCE;
        
        if(stat instanceof ExpressionStatement)
            return wrap((ExpressionStatement) stat);
        if(stat instanceof VariableDeclarationStatement)
            return wrap((VariableDeclarationStatement) stat);
        if(stat instanceof AssertStatement)
            return wrap((AssertStatement) stat);
        if(stat instanceof Block)
            return wrap((Block) stat);
        if(stat instanceof BreakStatement)
            return wrap((BreakStatement) stat);
        if(stat instanceof ConstructorInvocation)
            return wrap((ConstructorInvocation) stat);
        if(stat instanceof ContinueStatement)
            return wrap((ContinueStatement) stat);
        if(stat instanceof DoStatement)
            return wrap((DoStatement) stat);
        if(stat instanceof EmptyStatement)
            return wrap((EmptyStatement) stat);
        if(stat instanceof EnhancedForStatement)
            return wrap((EnhancedForStatement) stat);
        if(stat instanceof ForStatement)
            return wrap((ForStatement) stat);
        if(stat instanceof IfStatement)
            return wrap((IfStatement) stat);
        if(stat instanceof LabeledStatement)
            return wrap((LabeledStatement) stat);
        if(stat instanceof ReturnStatement)
            return wrap((ReturnStatement) stat);
        if(stat instanceof SuperConstructorInvocation)
            return wrap((SuperConstructorInvocation) stat);
        if(stat instanceof SwitchCase)
            return wrap((SwitchCase) stat);
        if(stat instanceof SwitchStatement)
            return wrap((SwitchStatement) stat);
        if(stat instanceof SynchronizedStatement)
            return wrap((SynchronizedStatement) stat);
        if(stat instanceof ThrowStatement)
            return wrap((ThrowStatement) stat);
        if(stat instanceof TryStatement)
            return wrap((TryStatement) stat);
        if(stat instanceof TypeDeclarationStatement)
            return wrap((TypeDeclarationStatement) stat);
        if(stat instanceof WhileStatement)
            return wrap((WhileStatement) stat);

        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(IfStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedIfStatement(statement);
    }

    private static IStrategoTerm wrap(SuperConstructorInvocation invocation) {
        if(invocation == null)
            return None.INSTANCE; 
        else
            return new WrappedSuperConstructorInvocation(invocation);
    }

    private static IStrategoTerm wrap(SwitchCase switchcase) {
        if(switchcase == null)
            return None.INSTANCE; 
        else
            return new WrappedSwitchCase(switchcase);
    }

    private static IStrategoTerm wrap(SwitchStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedSwitchStatement(statement);
    }

    private static IStrategoTerm wrap(SynchronizedStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedSynchronizedStatement(statement);
    }

    private static IStrategoTerm wrap(ThrowStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedThrowStatement(statement);
    }

    private static IStrategoTerm wrap(TryStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedTryStatement(statement);
    }

    private static IStrategoTerm wrap(TypeDeclarationStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedTypeDeclarationStatement(statement);
    }

    private static IStrategoTerm wrap(WhileStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedWhileStatement(statement);
    }

    private static IStrategoTerm wrap(ReturnStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedReturnStatement(statement);
    }

    private static IStrategoTerm wrap(LabeledStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedLabeledStatement(statement);
    }

    private static IStrategoTerm wrap(ForStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedForStatement(statement);
    }

    private static IStrategoTerm wrap(EnhancedForStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedEnhancedForStatement(statement);
    }

    private static IStrategoTerm wrap(EmptyStatement statement) {   
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedEmptyStatement(statement);
    }

    private static IStrategoTerm wrap(DoStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedDoStatement(statement);
    }

    private static IStrategoTerm wrap(ContinueStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedContinueStatement(statement);
    }

    private static IStrategoTerm wrap(ConstructorInvocation invocation) {
        if(invocation == null)
            return None.INSTANCE;
        else
            return new WrappedConstructorInvocation(invocation);

    }

    private static IStrategoTerm wrap(BreakStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedBreakStatement(statement);
    }

    static IStrategoTerm wrap(Block block) {
        if(block == null)
            return None.INSTANCE;
        else
            return new WrappedBlock(block);
    }

    private static IStrategoTerm wrap(AssertStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedAssertStatement(statement);
    }

    private static IStrategoTerm wrap(Modifier modifier) {
        if(modifier == null)
            return None.INSTANCE;
        else
            return new WrappedModifier(modifier);
    }

    private static IStrategoTerm wrap(MethodRefParameter parameter) {
        if(parameter == null)
            return None.INSTANCE;
        else
            return new WrappedMethodRefParameter(parameter);
    }

    private static IStrategoTerm wrap(MethodRef ref) {
        if(ref == null)
            return None.INSTANCE;
        else
            return new WrappedMethodRef(ref);
    }

    private static IStrategoTerm wrap(MemberValuePair pair) {
        if(pair == null)
            return None.INSTANCE;
        else
            return new WrappedMemberValuePair(pair);
    }

    private static IStrategoTerm wrap(MemberRef ref) {
        if(ref == null)
            return None.INSTANCE;
        else
            return new WrappedMemberRef(ref);
    }

    private static IStrategoTerm wrap(CatchClause clause) {
        if(clause == null)
            return None.INSTANCE;
        else
            return new WrappedCatchClause(clause);
    }

    static IStrategoTerm wrap(AnonymousClassDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedAnonymousClassDeclaration(declaration);
    }

    private static IStrategoTerm wrap(VariableDeclarationFragment fragment) {
        if(fragment == null)
            return None.INSTANCE;
        else
            return new WrappedVariableDeclarationFragment(fragment);
    }

    private static IStrategoTerm wrap(VariableDeclarationStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedVariableDeclarationStatement(statement);
    }

    static IStrategoTerm wrap(ExpressionStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedExpressionStatement(statement);
    }

    static IStrategoTerm wrap(SingleVariableDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedSingleVariableDeclaration(declaration);
    }

    private static IStrategoTerm wrap(MethodDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedMethodDeclaration(declaration);
    }

    private static IStrategoTerm wrap(TypeDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedTypeDeclaration(declaration);
    }

    static IStrategoTerm wrap(CompilationUnit unit) {
        if(unit == null)
            return None.INSTANCE;
        else
            return new WrappedCompilationUnit(unit);
    }

    static IStrategoAppl wrap(PackageDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedPackageDeclaration(declaration);
    }

    static IStrategoTerm wrap(ImportDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedImportDeclaration(declaration);
    }

    public IStrategoTerm parseFromTree(ASTNode n) {
        return genericWrap(n);
    }

    public static IStrategoTerm wrap(int val) {
        return new WrappedInt(val);
    }

    public static IStrategoTerm wrap(ArrayType type) {
        if(type == null)
            return None.INSTANCE;
        else
            return new WrappedArrayType(type);
    }

    public static IStrategoTerm wrap(String identifier) {
        if(identifier == null)
            return None.INSTANCE;
        else
            return new WrappedString(identifier);
    }

    static IStrategoTerm wrapExpression(Expression expr) {

        if(expr == null)
            return None.INSTANCE;

        if(expr instanceof Annotation)
            return wrapAnnotation((Annotation) expr);
        if(expr instanceof ArrayAccess)
            return wrap((ArrayAccess) expr);
        if(expr instanceof ArrayCreation)
            return wrap((ArrayCreation) expr);
        if(expr instanceof ArrayInitializer)
            return wrap((ArrayInitializer) expr);
        if(expr instanceof Assignment)
            return wrap((Assignment) expr);
        if(expr instanceof BooleanLiteral)
            return wrap((BooleanLiteral) expr);
        if(expr instanceof CastExpression)
            return wrap((CastExpression) expr);
        if(expr instanceof CharacterLiteral)
            return wrap((CharacterLiteral) expr);
        if(expr instanceof ClassInstanceCreation)
            return wrap((ClassInstanceCreation) expr);
        if(expr instanceof ConditionalExpression)
            return wrap((ConditionalExpression) expr);
        if(expr instanceof FieldAccess)
            return wrap((FieldAccess) expr);
        if(expr instanceof InfixExpression)
            return wrap((InfixExpression) expr);
        if(expr instanceof InstanceofExpression)
            return wrap((InstanceofExpression) expr);
        if(expr instanceof MethodInvocation)
            return wrap((MethodInvocation) expr);
        if(expr instanceof Name)
            return wrapName((Name) expr);
        if(expr instanceof NullLiteral)
            return wrap((NullLiteral) expr);
        if(expr instanceof NumberLiteral)
            return wrap((NumberLiteral) expr);
        if(expr instanceof ParenthesizedExpression)
            return wrap((ParenthesizedExpression) expr);
        if(expr instanceof PostfixExpression)
            return wrap((PostfixExpression) expr);
        if(expr instanceof PrefixExpression)
            return wrap((PrefixExpression) expr);
        if(expr instanceof StringLiteral)
            return wrap((StringLiteral) expr);
        if(expr instanceof SuperFieldAccess)
            return wrap((SuperFieldAccess) expr);
        if(expr instanceof SuperMethodInvocation)
            return wrap((SuperMethodInvocation) expr);
        if(expr instanceof ThisExpression)
            return wrap((ThisExpression) expr);
        if(expr instanceof TypeLiteral)
            return wrap((TypeLiteral) expr);
        if(expr instanceof VariableDeclarationExpression)
            return wrap((VariableDeclarationExpression) expr);
        
        throw new NotImplementedException("Unknown Expression Type:" + expr.getClass());
    }

    private static IStrategoTerm wrapAnnotation(Annotation anno) {
        
        if(anno instanceof MarkerAnnotation)
            return wrap((MarkerAnnotation) anno);
        if(anno instanceof NormalAnnotation)
            return wrap((NormalAnnotation) anno);
        if(anno instanceof SingleMemberAnnotation)
            return wrap((SingleMemberAnnotation) anno);

        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(SingleMemberAnnotation annotation) {
        if(annotation == null)
            return None.INSTANCE;
        else
            return new WrappedSingleMemberAnnotation(annotation);
    }

    private static IStrategoTerm wrap(NormalAnnotation annotation) {
        if(annotation == null)
            return None.INSTANCE;
        else
            return new WrappedNormalAnnotation(annotation);
    }

    private static IStrategoTerm wrap(MarkerAnnotation annotation) {
        if(annotation == null) 
            return None.INSTANCE;
        else
            return new WrappedMarkerAnnotation(annotation);
    }

    private static IStrategoTerm wrap(VariableDeclarationExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedVariableDeclarationExpression(expression);
    }

    private static IStrategoTerm wrap(TypeLiteral literal) {
        if(literal == null)
            return None.INSTANCE;
        else
            return new WrappedTypeLiteral(literal);
    }

    private static IStrategoTerm wrap(ThisExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedThisExpression(expression);
    }

    private static IStrategoTerm wrap(SuperMethodInvocation invocation) {
        if(invocation == null)
            return None.INSTANCE;
        else
            return new WrappedSuperMethodInvocation(invocation);
    }

    private static IStrategoTerm wrap(SuperFieldAccess access) {
        if(access == null)
            return None.INSTANCE;
        else
            return new WrappedSuperFieldAccess(access);
    }

    private static IStrategoTerm wrap(StringLiteral literal) {
        if(literal == null) 
            return None.INSTANCE;
        else
            return new WrappedStringLiteral(literal);
    }

    private static IStrategoTerm wrap(PrefixExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedPrefixExpression(expression);
    }

    private static IStrategoTerm wrap(PostfixExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedPostfixExpression(expression);
    }

    private static IStrategoTerm wrap(ParenthesizedExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedParenthesizedExpression(expression);
    }

    private static IStrategoTerm wrap(NumberLiteral literal) {
        if(literal == null)
            return None.INSTANCE;
        else
            return new WrappedNumberLiteral(literal);
    }

    private static IStrategoTerm wrap(NullLiteral literal) {
        if(literal == null)
            return None.INSTANCE;
        else
            return new WrappedNullLiteral(literal);
    }

    private static IStrategoTerm wrap(MethodInvocation invocation) {
        if(invocation == null)
            return None.INSTANCE;
        else
            return new WrappedMethodInvocation(invocation);
    }

    private static IStrategoTerm wrap(InstanceofExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedInstanceofExpression(expression);
    }

    private static IStrategoTerm wrap(InfixExpression expression) {
        
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedInfixExpression(expression);

/*
        InfixExpression.Operator op = expression.getOperator();
        
        if(op == InfixExpression.Operator.PLUS)
            return new WrappedPlus(expression); 
        if(op == InfixExpression.Operator.MINUS)
            return new WrappedMinus(expression); 
        if(op == InfixExpression.Operator.TIMES)
            return new WrappedTimes(expression); 
        if(op == InfixExpression.Operator.DIVIDE)
            return new WrappedDivide(expression); 

        throw new NotImplementedException("Unknown InfixExpression Operator " + expression.getO);
*/  
    }

    private static IStrategoTerm wrap(FieldAccess access) {
        if(access == null)
            return None.INSTANCE;
        else
            return new WrappedFieldAccess(access);
    }

    private static IStrategoTerm wrap(ConditionalExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedConditionalExpression(expression);
    }

    private static IStrategoTerm wrap(ClassInstanceCreation creation) {
        if(creation == null)
            return None.INSTANCE;
        else
            return new WrappedClassInstanceCreation(creation);
    }

    private static IStrategoTerm wrap(CharacterLiteral literal) {
        if(literal == null)
            return None.INSTANCE;
        else
            return new WrappedCharacterLiteral(literal);
    }

    private static IStrategoTerm wrap(CastExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedCastExpression(expression);
    }

    private static IStrategoTerm wrap(BooleanLiteral literal) {
        if(literal == null)
            return None.INSTANCE;
        else
            return new WrappedBooleanLiteral(literal);
    }

    private static IStrategoTerm wrap(Assignment assignment) {
        if(assignment == null)
            return None.INSTANCE;
        else
            return new WrappedAssignment(assignment);
    }

    private static IStrategoTerm wrap(ArrayInitializer initializer) {
        if(initializer == null)
            return None.INSTANCE;
        else 
            return new WrappedArrayInitializer(initializer);
    }

    private static IStrategoTerm wrap(ArrayCreation creation) {
        if(creation == null)
            return None.INSTANCE;
        else
            return new WrappedArrayCreation(creation);
    }

    private static IStrategoAppl wrap(ArrayAccess access) {
        if(access == null)
            return None.INSTANCE;
        else
            return new WrappedArrayAccess(access);
    }

    public static IStrategoTerm wrapType(Type type) {
        
        if(type instanceof ArrayType)
            return wrap((ArrayType) type);
        if(type instanceof ParameterizedType)
            return wrap((ParameterizedType) type);
        if(type instanceof PrimitiveType)
            return wrap((PrimitiveType) type);
        if(type instanceof QualifiedType)
            return wrap((QualifiedType) type);
        if(type instanceof SimpleType)
            return wrap((SimpleType) type);
        if(type instanceof WildcardType)
            return wrap((WildcardType) type);
        
        if(type == null)
            return None.INSTANCE;
        
        throw new NotImplementedException(" " + type.getClass());
    }

    private static IStrategoTerm wrap(WildcardType type) {
        if(type == null)
            return None.INSTANCE;
        else
            return new WrappedWildcardType(type);
    }

    private static IStrategoTerm wrap(SimpleType type) {
        if(type == null)
            return None.INSTANCE;
        else
            return new WrappedSimpleType(type);
    }

    private static IStrategoTerm wrap(QualifiedType type) {
        if(type == null)
            return None.INSTANCE;
        else
            return new WrappedQualifiedType(type);
    }

    private static IStrategoTerm wrap(PrimitiveType type) {
        
        if(type == null)
            return None.INSTANCE;
        else
            return new WrappedPrimitiveType(type);
        /*
        if(type.getPrimitiveTypeCode() == PrimitiveType.INT)
            return new WrappedIntType(type);
        if(type.getPrimitiveTypeCode() == PrimitiveType.BOOLEAN)
            return new WrappedBooleanType(type);
        if(type.getPrimitiveTypeCode() == PrimitiveType.DOUBLE)
            return new WrappedDoubleType(type);
        if(type.getPrimitiveTypeCode() == PrimitiveType.FLOAT)
            return new WrappedFloatType(type);
        if(type.getPrimitiveTypeCode() == PrimitiveType.BYTE)
            return new WrappedByteType(type);
        if(type.getPrimitiveTypeCode() == PrimitiveType.LONG)
            return new WrappedLongType(type);
        
        throw new NotImplementedException();
        */
    }

    private static IStrategoTerm wrap(ParameterizedType type) {
        if(type == null)
            return None.INSTANCE;
        else
            return new WrappedParameterizedType(type); 
    }

    public static IStrategoTerm wrap(ModifierKeyword keyword) {
        if(keyword == null)
            return None.INSTANCE;
        else 
            return new WrappedModifierKeyword(keyword);
    }

    public static IStrategoTerm wrap(PostfixExpression.Operator operator) {
        if(operator == null)
            return None.INSTANCE;
        else
            return new WrappedPostfixExpressionOperator(operator);
    }

    public static IStrategoTerm wrap(PrefixExpression.Operator operator) {
        if(operator == null)
            return None.INSTANCE;
        else
            return new WrappedPrefixExpressionOperator(operator);
    }

    public static IStrategoTerm wrapTypeDecl(AbstractTypeDeclaration decl) {
        if(decl instanceof AnnotationTypeDeclaration)
            return wrap((AnnotationTypeDeclaration) decl);
        if(decl instanceof EnumDeclaration)
            return wrap((EnumDeclaration) decl);
        if(decl instanceof TypeDeclaration)
            return wrap((TypeDeclaration) decl);
        
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(EnumDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedEnumDeclaration(declaration);
    }

    private static IStrategoTerm wrap(AnnotationTypeDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedAnnotationTypeDeclaration(declaration);
    }

    public static IStrategoTerm wrap(ITypeBinding binding) {
        if(binding == null)
            return None.INSTANCE;
        else
            return new WrappedITypeBinding(binding);
    }

    public static IStrategoTerm wrap(ITypeBinding[] bindings) {
        IStrategoTerm[] terms = new IStrategoTerm[bindings.length];
        for(int i = 0, sz = bindings.length; i < sz; i++)
            terms[i] = ECJFactory.wrap(bindings[i]);
        return new WrappedGenericList(terms);
    }

    public static IStrategoTerm wrap(IProject proj) {
        if(proj == null)
            return None.INSTANCE;
        else
            return new WrappedProject(proj);
    }

    public static IStrategoTerm wrap(String[] strs) {
        IStrategoTerm[] r = new IStrategoTerm[strs.length];
        for(int i = 0; i < r.length; i++)
            r[i] = wrap(strs[i]);
        return new WrappedGenericList(r);
    }

    public static IStrategoTerm wrap(IMethodBinding mb) {
        if(mb == null)
            return None.INSTANCE;
        else
            return new WrappedIMethodBinding(mb);
    }
}
