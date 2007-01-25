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
import org.eclipse.jdt.core.dom.InfixExpression.Operator;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;
import org.eclipse.jdt.core.dom.PrimitiveType.Code;
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
import org.spoofax.interpreter.terms.InlinePrinter;

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
    private static final int BYTE_TYPE = 92;
    private static final int IMPORT_REFERENCE = 93;
        
    private Map<String,Integer> ctorNameToIndexMap;
    private AST ast;
    
    public ECJFactory(AST ast) {
        this.ast = ast;
        initCtorMap();
    }
    
    public ECJFactory() {
        initCtorMap();
    }
    
    private void initCtorMap() {
        ctorNameToIndexMap = new HashMap<String,Integer>();
        ctorNameToIndexMap.put("ArrayAccess", ARRAY_ACCESS);
        ctorNameToIndexMap.put("PackageDeclaration", PACKAGE_DECLARATION);
        ctorNameToIndexMap.put("BooleanLiteral", BOOLEAN_LITERAL);
        ctorNameToIndexMap.put("BooleanType", BOOLEAN_TYPE);
        ctorNameToIndexMap.put("ByteType", BYTE_TYPE);
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
        ctorNameToIndexMap.put("None", NONE);
        ctorNameToIndexMap.put("ImportReference", IMPORT_REFERENCE);
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
        InlinePrinter pp = new InlinePrinter();
        t.prettyPrint(pp);
        ous.write(pp.getString().getBytes());
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
        if(t == null) {
            System.err.println("Generic fallback");
            return ctr.instantiate(this, terms);
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    private IStrategoAppl constructASTNode(IStrategoConstructor ctr, IStrategoTerm[] kids) {
        System.err.println("Construct: " + ctr.getName() + "/" + ctr.getArity() + " with " + kids.length + " kids");
        for(int i = 0; i < kids.length; i++) 
            System.err.println("  " + kids[i]); 
        int index = ctorNameToIndex(ctr);
        switch(index) {
        case ANNOTATION_TYPE_DECLARATION: {
            if(!ensureModifierList(kids[0]) || !ensureSimpleName(kids[1]) || !ensureBodyDeclarationList(kids[1]))
                return null;
            AnnotationTypeDeclaration x = ast.newAnnotationTypeDeclaration();
            x.modifiers().addAll(asModifierList(kids[0]));
            x.setName(asSimpleName(kids[1]));
            x.bodyDeclarations().addAll(asBodyDeclarationList(kids[2]));
            return wrap(x);
        }
        case ANNOTATION_TYPE_MEMBER_DECLARATION: {
            if(!ensureModifierList(kids[0]) || !ensureType(kids[1]) || !ensureSimpleName(kids[2]) || !ensureExpression(kids[3]))
                return null;
            AnnotationTypeMemberDeclaration x = ast.newAnnotationTypeMemberDeclaration();
            x.modifiers().addAll(asModifierList(kids[0]));
            x.setType(asType(kids[1]));
            x.setName(asSimpleName(kids[2]));
            x.setDefault(asExpression(kids[3]));
            return wrap(x);
        }
        case ARRAY_ACCESS: { 
            if(!ensureExpression(kids[0]) || !ensureExpression(kids[1]))
                return null;
            ArrayAccess x = ast.newArrayAccess();
            x.setArray(asExpression(kids[0]));
            x.setIndex(asExpression(kids[1]));
            return wrap(x);
        }
        case ARRAY_CREATION: {
            if(!ensureArrayType(kids[0]) || !ensureExpressionList(kids[1]) || !ensureArrayInitializer(kids[2]))
                return null;
            ArrayCreation x = ast.newArrayCreation();
            x.setType(asArrayType(kids[0]));
            x.dimensions().addAll(asExpressionList(kids[1]));
            x.setInitializer(asArrayInitializer(kids[2]));
            return wrap(x);
        }
        case ARRAY_INITIALIZER: {
            if(!ensureExpressionList(kids[0]))
                return null;
            ArrayInitializer x = ast.newArrayInitializer();
            x.expressions().addAll(asExpressionList(kids[0]));
            return wrap(x);
        }
        case ARRAY_TYPE: {
            if(!ensureType(kids[0]) || !ensureInt(kids[1]) || !ensureType(kids[1]))
                return null;
            ArrayType x = ast.newArrayType(asType(kids[2]), asInt(kids[1]));
            x.setComponentType(asType(kids[0]));
            return wrap(x);
        }
        case ASSERT_STATEMENT: {
            if(!ensureExpression(kids[0]) || !ensureExpression(kids[1]))
                return null;
            AssertStatement x = ast.newAssertStatement();
            x.setExpression(asExpression(kids[0]));
            x.setMessage(asExpression(kids[1]));
            return wrap(x);
        }
        case ASSIGNMENT: {
            if(!ensureExpression(kids[0]) || !ensureExpression(kids[1]))
                return null;
            Assignment x = ast.newAssignment();
            x.setLeftHandSide(asExpression(kids[0]));
            x.setRightHandSide(asExpression(kids[1]));
            return wrap(x);
        }
        case BLOCK: {
            if(!ensureStatementList(kids[0]))
                return null;
            Block x = ast.newBlock();
            x.statements().addAll(asStatementList(kids[0]));
            return wrap(x);
        }
        case BLOCK_COMMENT: {
            BlockComment x = ast.newBlockComment();
            return wrap(x);
        }
        case BOOLEAN_LITERAL: {
            if(!ensureInt(kids[0]))
                return null;
            return wrap(ast.newBooleanLiteral(asInt(kids[0]) == 1));
        }
        case BOOLEAN_TYPE: {
            return wrap(ast.newPrimitiveType(PrimitiveType.BOOLEAN));
        }
        case BREAK_STATEMENT: {
            if(!ensureSimpleName(kids[0]))
                return null;
            BreakStatement x = ast.newBreakStatement();
            x.setLabel(asSimpleName(kids[0]));
            return wrap(x);
        }
        case BYTE_TYPE: {
            return wrap(ast.newPrimitiveType(PrimitiveType.BYTE));
        }
        case CAST_EXPRESSION: {
            if(!ensureType(kids[0]) || !ensureExpression(kids[1]))
                return null;
            CastExpression x = ast.newCastExpression();
            x.setType(asType(kids[0]));
            x.setExpression(asExpression(kids[1]));
            return wrap(x);
        }
        case CATCH_CLAUSE: {
            if(!ensureSingleVariableDeclaration(kids[0]) || !ensureBlock(kids[1]))
                return null;
            CatchClause x = ast.newCatchClause();
            x.setException(asSingleVariableDeclaration(kids[0]));
            x.setBody(asBlock(kids[1]));
            return wrap(x);
        }
        case CHARACTER_LITERAL: {
            if(!ensureInt(kids[0]))
                return null;
            CharacterLiteral x = ast.newCharacterLiteral();
            x.setCharValue((char)asInt(kids[0]));
            return wrap(x);
        }
        case CLASS_INSTANCE_CREATION: {
            if(!ensureExpression(kids[0]) || !ensureType(kids[1]) || !ensureAnonymousClassDeclaration(kids[2]) || !ensureExpressionList(kids[3]))
                return null;
            ClassInstanceCreation x = ast.newClassInstanceCreation();
            x.setExpression(asExpression(kids[0]));
            x.setType(asType(kids[1]));
            x.setAnonymousClassDeclaration(asAnonymousClassDeclaration(kids[2]));
            x.arguments().addAll(asExpressionList(kids[3]));
            break;
        }
        case COMPILATION_UNIT: {
            if((!ensurePackageDeclaration(kids[0]) && !ensureNone(kids[0])) 
                    || !ensureImportDeclarationList(kids[1]) 
                    || !ensureAbstractTypeDeclarationList(kids[2]))
                return null;
            CompilationUnit x = ast.newCompilationUnit();
            if(ensureNone(kids[0]))
                x.setPackage(null);
            else 
                x.setPackage(asPackageDeclaration(kids[0]));
            x.imports().addAll(asImportDeclarationList(kids[1]));
            x.types().addAll(asAbstractTypeDeclarationList(kids[2]));
            return wrap(x);
        }
        case CONDITIONAL_EXPRESSION: {
            if(!ensureExpression(kids[0]) || !ensureExpression(kids[1]) || !ensureExpression(kids[2]))
                return null;
            ConditionalExpression x = ast.newConditionalExpression();
            x.setExpression(asExpression(kids[0]));
            x.setThenExpression(asExpression(kids[0]));
            x.setElseExpression(asExpression(kids[0]));
            return wrap(x);
        }
        case CONSTRUCTOR_INVOCATION: {
            if(!ensureExpressionList(kids[0]))
                return null;
            ConstructorInvocation x = ast.newConstructorInvocation();
            x.arguments().addAll(asExpressionList(kids[0]));
            return wrap(x);
        }
        case CONTINUE_STATEMENT: {
            if(!ensureSimpleName(kids[0]))
                return null;
            ContinueStatement x = ast.newContinueStatement();
            x.setLabel(asSimpleName(kids[0]));
            return wrap(x);
        }
        case DO_STATEMENT: {
            DoStatement x = ast.newDoStatement();
            x.setExpression(asExpression(kids[0]));
            x.setBody(asStatement(kids[1]));
            return wrap(x);
        }
        case DOUBLE_TYPE: {
            return wrap(ast.newPrimitiveType(PrimitiveType.DOUBLE));
        }
        case EMPTY_STATEMENT: {
            return wrap(ast.newEmptyStatement());
        }
        case ENHANCED_FOR_STATEMENT: {
            if(!ensureSingleVariableDeclaration(kids[0]) 
                    || !ensureExpression(kids[1])
                    || !ensureStatement(kids[3]))
                return null;
            EnhancedForStatement x = ast.newEnhancedForStatement();
            x.setParameter(asSingleVariableDeclaration(kids[0]));
            x.setExpression(asExpression(kids[1]));
            x.setBody(asStatement(kids[3]));
            return wrap(x);
        }
        case ENUM_CONSTANT_DECLARATION: {
            if(!ensureModifierList(kids[0]) 
                    || !ensureSimpleName(kids[1]) 
                    || !ensureExpressionList(kids[2]) 
                    || !ensureAnonymousClassDeclaration(kids[3]))
                return null;
            EnumConstantDeclaration x = ast.newEnumConstantDeclaration();
            x.modifiers().addAll(asModifierList(kids[0]));
            x.setName(asSimpleName(kids[1]));
            x.arguments().addAll(asExpressionList(kids[2]));
            x.setAnonymousClassDeclaration(asAnonymousClassDeclaration(kids[3]));
            return wrap(x);
        }
        case ENUM_DECLARATION: {
            if(!ensureModifierList(kids[0])
                    || !ensureSimpleName(kids[1])
                    || !ensureTypeList(kids[2])
                    || !ensureEnumConstantDeclarationList(kids[3])
                    || !ensureBodyDeclarationList(kids[4]))
                return null;
            EnumDeclaration x = ast.newEnumDeclaration();
            x.modifiers().addAll(asModifierList(kids[0]));
            x.setName(asSimpleName(kids[1]));
            x.superInterfaceTypes().addAll(asTypeList(kids[2]));
            x.enumConstants().addAll(asEnumConstantDeclarationList(kids[3]));
            x.bodyDeclarations().addAll(asBodyDeclarationList(kids[4]));
            return wrap(x);
        }
        case FIELD_ACCESS: {
            if(!ensureExpression(kids[0]) || !ensureSimpleName(kids[1]))
                return null;
            FieldAccess x = ast.newFieldAccess();
            x.setExpression(asExpression(kids[0]));
            x.setName(asSimpleName(kids[1]));
            return wrap(x);
        }
        case FIELD_DECLARATION: {
            if(!ensureModifierList(kids[0])
                    || !ensureType(kids[1])
                    || !ensureFragmentList(kids[1]))
                return null;
            List y = asFragmentList(kids[2]);
            FieldDeclaration x = ast.newFieldDeclaration((VariableDeclarationFragment)y.remove(0));
            x.modifiers().addAll(asModifierList(kids[0]));
            x.setType(asType(kids[1]));
            x.fragments().addAll(y);
            return wrap(x);
        }
        case FLOAT_TYPE: {
            return wrap(ast.newPrimitiveType(PrimitiveType.FLOAT));
        }
        case FOR_STATEMENT: {
            if(!ensureExpressionList(kids[0]) 
                    || !ensureExpression(kids[1])
                    || !ensureExpressionList(kids[2])
                    || !ensureStatement(kids[3]))
                return null;
            ForStatement x = ast.newForStatement();
            x.initializers().addAll(asExpressionList(kids[0]));
            x.setExpression(asExpression(kids[1]));
            x.updaters().addAll(asExpressionList(kids[2]));
            x.setBody(asStatement(kids[3]));
            return wrap(x);
        }
        case IF_STATEMENT: {
            if(!ensureExpression(kids[0])
                    || !ensureStatement(kids[1])
                    || !ensureStatement(kids[2]))
                return null;
            IfStatement x = ast.newIfStatement();
            x.setExpression(asExpression(kids[0]));
            x.setThenStatement(asStatement(kids[1]));
            x.setElseStatement(asStatement(kids[2]));
            return wrap(x);
        }
        case IMPORT_DECLARATION: {
            if(!ensureName(kids[0]))
                return null;
            ImportDeclaration x = ast.newImportDeclaration();
            x.setName(asName(kids[0]));
            return wrap(x);
        }
        case INFIX_EXPRESSION: {
            if(!ensureOperator(kids[0])
                    || !ensureExpression(kids[1])
                    || !ensureExpression(kids[2]))
                return null;
            InfixExpression x = ast.newInfixExpression();
            x.setOperator(asOperator(kids[0]));
            x.setLeftOperand(asExpression(kids[1]));
            x.setRightOperand(asExpression(kids[1]));
            return wrap(x);
        }
        case INITIALIZER:
            break;
        case INSTANCEOF_EXPRESSION:
            break;
        case INT_TYPE: {
            return wrap(ast.newPrimitiveType(PrimitiveType.INT));
        }
        case JAVADOC: {
            if(!ensureTagElementList(kids[0]))
                return null;
            Javadoc x = ast.newJavadoc();
            x.tags().addAll(asTagElementList(kids[0]));
            return wrap(x);
        }
        case LABELED_STATEMENT: {
            if(!ensureStatement(kids[0]))
                return null;
            LabeledStatement x = ast.newLabeledStatement();
            x.setBody(asStatement(kids[0]));
            return wrap(x);
        }
        case LINE_COMMENT: {
            LineComment x = ast.newLineComment();
            return wrap(x);
        }
        case LONG_TYPE: {
            return wrap(ast.newPrimitiveType(PrimitiveType.INT));
        }
        case MARKER_ANNOTATION: {
            if(!ensureName(kids[0]))
                return null;
            MarkerAnnotation x = ast.newMarkerAnnotation();
            x.setTypeName(asName(kids[0]));
            return wrap(x);
            
        }
        case MEMBER_REF: {
            if(!ensureSimpleName(kids[0]) || !ensureName(kids[1]))
                return null;
            MemberRef x = ast.newMemberRef();
            x.setName(asSimpleName(kids[0]));
            x.setQualifier(asName(kids[1]));
            return wrap(x);
        }
        case MEMBER_VALUE_PAIR: {
            if(!ensureSimpleName(kids[0]) || !ensureExpression(kids[1]))
                return null;
            MemberValuePair x = ast.newMemberValuePair();
            x.setName(asSimpleName(kids[0]));
            x.setValue(asExpression(kids[1]));
            return wrap(x);
        }
        case METHOD_DECLARATION: {
            if(!ensureModifierList(kids[0])
                    || !ensureTypeParameterList(kids[1])
                    || !ensureSimpleName(kids[2])
                    || !ensureSingleVariableDeclarationList(kids[3])
                    || !ensureNameList(kids[4])
                    || !ensureBlock(kids[5]))
                return null;
            MethodDeclaration x = ast.newMethodDeclaration();
            x.modifiers().addAll(asModifierList(kids[0]));
            x.typeParameters().addAll(asTypeParameterList(kids[1]));
            x.setName(asSimpleName(kids[2]));
            x.parameters().addAll(asSingleVariableDeclarationList(kids[3]));
            x.thrownExceptions().addAll(asNameList(kids[4]));
            x.setBody(asBlock(kids[5]));
            return wrap(x);
        }
        case METHOD_INVOCATION: {
            if(!ensureExpression(kids[0])
                    || !ensureSimpleName(kids[1])
                    || !ensureTypeList(kids[2])
                    || !ensureExpressionList(kids[3]))
                return null;
            MethodInvocation x = ast.newMethodInvocation();
            x.setExpression(asExpression(kids[0]));
            x.setName(asSimpleName(kids[1]));
            x.typeArguments().addAll(asTypeList(kids[2]));
            x.arguments().addAll(asExpressionList(kids[3]));
            return wrap(x);
        }
        case METHOD_REF: {
            if(!ensureSimpleName(kids[0])
                    || !ensureName(kids[1])
                    || !ensureMethodRefParameterList(kids[2]))
                return null;
            MethodRef x = ast.newMethodRef();
            x.setName(asSimpleName(kids[0]));
            x.setQualifier(asName(kids[1]));
            x.parameters().addAll(asMethodRefParameterList(kids[2]));
            return wrap(x);
        }
        case METHOD_REF_PARAMETER: {
            if(!ensureType(kids[0])
                    || !ensureSimpleName(kids[1]))
                return null;
            MethodRefParameter x = ast.newMethodRefParameter();
            x.setType(asType(kids[0]));
            x.setName(asSimpleName(kids[1]));
            return wrap(x);
        }
        case MODIFIER: {
            if(!ensureModifierKeyword(kids[0]))
                return null;
            return wrap(ast.newModifier(asModifierKeyword(kids[0])));
        }
        case MODIFIER_KEYWORD: {
            if(!ensureInt(kids[0]))
                return null;
            return wrap(Modifier.ModifierKeyword.fromFlagValue(asInt(kids[0])));
        }
        case NONE:
            return None.INSTANCE;
        case NORMAL_ANNOTATION: {
            if(!ensureName(kids[0]) || !ensureMemberValuePairList(kids[1]))
                return null;
            NormalAnnotation x = ast.newNormalAnnotation();
            x.setTypeName(asName(kids[0]));
            x.values().addAll(asMemberValuePairList(kids[1]));
            return wrap(x);
        }
        case NULL_LITERAL: {
            return wrap(ast.newNullLiteral());
        }
        case NUMBER_LITERAL: {
            if(!ensureString(kids[0]))
                return null;
            return wrap(ast.newNumberLiteral(asString(kids[0])));
        }
        case PACKAGE_DECLARATION: {
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
        }
        case PARAMETERIZED_TYPE: {
            if(!ensureType(kids[0]) || !ensureTypeList(kids[1]))
                return null;
            ParameterizedType x = ast.newParameterizedType(asType(kids[0]));
            x.typeArguments().addAll(asTypeList(kids[1]));
            return wrap(x);
        }
        case PARENTHESIZED_EXPRESSION: {
            if(!ensureExpression(kids[0]))
                return null;
            ParenthesizedExpression x = ast.newParenthesizedExpression();
            x.setExpression(asExpression(kids[0]));
            return wrap(x);
        }
        case POSTFIX_EXPRESSION: {
            if(!ensurePostfixOperator(kids[0]) || !ensureExpression(kids[1]))
                return null;
            PostfixExpression x = ast.newPostfixExpression();
            x.setOperator(asPostfixOperator(kids[0]));
            x.setOperand(asExpression(kids[1]));
            return wrap(x);
        }
        case POSTFIX_EXPRESSION_OPERATOR:
            // FIXME
            break;
        case PREFIX_EXPRESSION: {
            if(!ensurePrefixOperator(kids[0]) || !ensureExpression(kids[1]))
                return null;
            PrefixExpression x = ast.newPrefixExpression();
            x.setOperator(asPrefixOperator(kids[0]));
            x.setOperand(asExpression(kids[1]));
            return wrap(x);
        }
        case PREFIX_EXPRESSION_OPERATOR:
            break;
        case PRIMITIVE_TYPE: {
            if(!ensureString(kids[0]))
                return null;
            return wrap(ast.newPrimitiveType(asTypeCode(kids[0])));
        }
        case QUALIFIED_NAME: {
            if(!ensureName(kids[0]) || !ensureSimpleName(kids[1]))
                return null;
            return wrap(ast.newQualifiedName(asName(kids[0]), asSimpleName(kids[1])));
        }
        case QUALIFIED_TYPE: {
            if(!ensureType(kids[0]) || !ensureSimpleName(kids[1]))
                return null;
            return wrap(ast.newQualifiedType(asType(kids[0]), asSimpleName(kids[1])));
        }
        case RETURN_STATEMENT: {
            if(!ensureExpression(kids[0]))
                return null;
            ReturnStatement x = ast.newReturnStatement();
            x.setExpression(asExpression(kids[0]));
            return wrap(x);
        }
        case SIMPLE_NAME: {
            if(!ensureString(kids[0]))
                return null;
            return wrap(ast.newSimpleName(asString(kids[0])));
        }
        case SIMPLE_TYPE: {
            if(!ensureName(kids[0]))
                return null;
            
            Name n = asName(kids[0]);
            System.out.println(n);
            if(n.getParent() != null)
                n = (Name) ASTNode.copySubtree(ast, n);
            System.out.println(n);
            return wrap(ast.newSimpleType(n));
        }
        case SINGLE_MEMBER_ANNOTATION: {
            if(!ensureName(kids[0]) || !ensureExpression(kids[1]))
                return null;
            SingleMemberAnnotation x = ast.newSingleMemberAnnotation();
            x.setTypeName(asName(kids[0]));
            x.setValue(asExpression(kids[1]));
            return wrap(x);
        }
        case SINGLE_VARIABLE_DECLARATION: {
            if(!ensureModifierList(kids[0])
                    || !ensureType(kids[1])
                    || !ensureName(kids[2])
                    || !ensureInt(kids[3])
                    || !ensureExpression(kids[4]))
                return null;
            SingleVariableDeclaration x = ast.newSingleVariableDeclaration();
            x.modifiers().addAll(asModifierList(kids[0]));
            x.setType(asType(kids[1]));
            x.setName(asSimpleName(kids[2]));
            x.setExtraDimensions(asInt(kids[3]));
            x.setInitializer(asExpression(kids[4]));
            return wrap(x);
        }
        case STRING_LITERAL: {
            if(!ensureString(kids[0]))
                return null;
            StringLiteral x = ast.newStringLiteral();
            x.setLiteralValue(asString(kids[0]));
            return wrap(x);
        }
        case SUPER_CONSTRUCTOR_INVOCATION: {
            if(!ensureExpression(kids[0]) || !ensureTypeList(kids[1]) || !ensureExpressionList(kids[2]))
                return null;
            SuperConstructorInvocation x = ast.newSuperConstructorInvocation();
            x.setExpression(asExpression(kids[0]));
            x.typeArguments().addAll(asTypeList(kids[1]));
            x.arguments().addAll(asExpressionList(kids[2]));
            return wrap(x);
        }
        case SUPER_FIELD_ACCESS: {
            if(!ensureName(kids[0]) || !ensureSimpleName(kids[1]))
                return null;
            SuperFieldAccess x = ast.newSuperFieldAccess();
            x.setQualifier(asName(kids[0]));
            x.setName(asSimpleName(kids[1]));
            return wrap(x);
        }
        case SUPER_METHOD_INVOCATION: {
            if(!ensureName(kids[0]) 
                    || !ensureTypeList(kids[1])
                    || !ensureSimpleName(kids[2])
                    || !ensureExpressionList(kids[3]))
                return null;
            SuperMethodInvocation x = ast.newSuperMethodInvocation();
            x.setQualifier(asName(kids[0]));
            x.typeArguments().addAll(asTypeList(kids[1]));
            x.setName(asSimpleName(kids[2]));
            x.arguments().addAll(asExpressionList(kids[3]));
            return wrap(x);
        }
        case TAG_ELEMENT: {
            if(!ensureString(kids[0]) || !ensureASTNodeList(kids[1]))
                return null;
            TagElement x = ast.newTagElement();
            x.setTagName(asString(kids[0]));
            x.fragments().addAll(asASTNodeList(kids[1]));
            return wrap(x);
        }
        case TEXT_ELEMENT: {
            if(!ensureString(kids[0]))
                return null;
            TextElement x = ast.newTextElement();
            x.setText(asString(kids[0]));
            return wrap(x);
        }
        case THIS_EXPRESSION: {
            if(!ensureName(kids[0]))
                return null;
            ThisExpression x = ast.newThisExpression();
            x.setQualifier(asName(kids[1]));
            break;
        }
        case THROW_STATEMENT: {
            if(!ensureExpression(kids[0]))
                return null;
            ThrowStatement x = ast.newThrowStatement();
            x.setExpression(asExpression(kids[0]));
            return wrap(x);
        }
        case TRY_STATEMENT: {
            if(!ensureBlock(kids[0]) || !ensureCatchClauseList(kids[1]) || !ensureBlock(kids[2]))
                return null;
            TryStatement x = ast.newTryStatement();
            x.setBody(asBlock(kids[0]));
            x.catchClauses().addAll(asCatchClauseList(kids[1]));
            x.setFinally(asBlock(kids[2]));
            return wrap(x);
        }
        case TYPE_DECLARATION: {
            if(!ensureModifierList(kids[0])
                    || !ensureSimpleName(kids[1])
                    || !ensureTypeList(kids[2])
                    || (!ensureType(kids[3]) && !ensureNone(kids[3]))
                    || !ensureTypeList(kids[4])
                    || !ensureBodyDeclarationList(kids[5]))
                return null;
            TypeDeclaration x = ast.newTypeDeclaration();
            x.modifiers().addAll(asModifierList(kids[0]));
            x.setName(asSimpleName(kids[1]));
            x.typeParameters().addAll(asTypeList(kids[2]));
            if(ensureNone(kids[3])) 
                x.setSuperclassType(null);
            else 
                x.setSuperclassType(asType(kids[3]));
            x.superInterfaceTypes().addAll(asTypeList(kids[4]));
            x.bodyDeclarations().addAll(asBodyDeclarationList(kids[5]));
            return wrap(x);
        }
        case TYPE_DECLARATION_STATEMENT: {
            if(!ensureTypeDecl(kids[0]))
                return null;
            return wrap(ast.newTypeDeclarationStatement(asTypeDecl(kids[0])));
        }
        case TYPE_LITERAL: {
            if(!ensureType(kids[0]))
                return null;
            TypeLiteral x = ast.newTypeLiteral();
            x.setType(asType(kids[0]));
            return wrap(x);
        }
        case TYPE_PARAMETER: {
            if(!ensureSimpleName(kids[0]) || !ensureTypeList(kids[1]))
                return null;
            TypeParameter x = ast.newTypeParameter();
            x.setName(asSimpleName(kids[0]));
            x.typeBounds().addAll(asTypeList(kids[1]));
            return wrap(x);
        }
        case VARIABLE_DECLARATION_EXPRESSION: {
            if(!ensureModifierList(kids[0]) || !ensureType(kids[1]) || !ensureFragmentList(kids[2]))
                return null;
            List y = asFragmentList(kids[2]);
            VariableDeclarationExpression x = ast.newVariableDeclarationExpression((VariableDeclarationFragment)y.remove(0));
            x.modifiers().addAll(asModifierList(kids[0]));
            x.setType(asType(kids[1]));
            x.fragments().addAll(y);
            return wrap(x);
        }
        case VARIABLE_DECLARATION_FRAGMENT: {
            if(!ensureSimpleName(kids[0]) || !ensureInt(kids[1]) || !ensureExpression(kids[2]))
                return null;
            VariableDeclarationFragment x = ast.newVariableDeclarationFragment();
            x.setName(asSimpleName(kids[0]));
            x.setExtraDimensions(asInt(kids[1]));
            x.setInitializer(asExpression(kids[2]));
            return wrap(x);
        }
        case VARIABLE_DECLARATION_STATEMENT: {
            if(!ensureModifierList(kids[0]) || !ensureType(kids[1]) || !ensureFragmentList(kids[2]))
                return null;
            List y = asFragmentList(kids[2]);
            VariableDeclarationStatement x = ast.newVariableDeclarationStatement((VariableDeclarationFragment)y.remove(0));
            x.modifiers().addAll(asModifierList(kids[0]));
            x.setType(asType(kids[1]));
            x.fragments().addAll(y);
            return wrap(x);
        }
        case WHILE_STATEMENT: {
            if(!ensureExpression(kids[0]) || !ensureStatement(kids[1]))
                return null;
            WhileStatement x = ast.newWhileStatement();
            x.setExpression(asExpression(kids[0]));
            x.setBody(asStatement(kids[1]));
            return wrap(x);
        }
        case WILDCARD_TYPE: {
            if(!ensureType(kids[0]))
                return null;
            WildcardType x = ast.newWildcardType();
            x.setBound(asType(kids[1]));
            return wrap(x);
        }
        default:
            return null;
        }
        throw new NotImplementedException();
    }

    @SuppressWarnings("unchecked")
    private Collection asAbstractTypeDeclarationList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asAbstractTypeDeclaration(k));
        }
        return r;    
        
    }

    private AbstractTypeDeclaration asAbstractTypeDeclaration(IStrategoTerm term) {
        return ((WrappedAbstractTypeDeclaration)term).getWrappee();
    }

    private boolean ensureAbstractTypeDeclarationList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureAbstractTypeDeclaration(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureAbstractTypeDeclaration(IStrategoTerm term) {
        return term instanceof WrappedAbstractTypeDeclaration;
    }

    @SuppressWarnings("unchecked")
    private Collection asMethodRefParameterList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asMethodRefParameter(k));
        }
        return r;    
    }

    private MethodRefParameter asMethodRefParameter(IStrategoTerm term) {
        return ((WrappedMethodRefParameter)term).getWrappee();
    }

    private boolean ensureMethodRefParameterList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureMethodRefParameter(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureMethodRefParameter(IStrategoTerm term) {
        return term instanceof MethodRefParameter;
    }

    @SuppressWarnings("unchecked")
    private Collection asNameList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asName(k));
        }
        return r;    
    }

    @SuppressWarnings("unchecked")
    private Collection asSingleVariableDeclarationList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asEnumConstantDeclaration(k));
        }
        return r;    
    }

    @SuppressWarnings("unchecked")
    private Collection asTypeParameterList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asEnumConstantDeclaration(k));
        }
        return r;    
    }

    private boolean ensureNameList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureName(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureSingleVariableDeclarationList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureSingleVariableDeclaration(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureTypeParameterList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureTypeParameter(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureTypeParameter(IStrategoTerm term) {
        return term instanceof TypeParameter;
    }

    @SuppressWarnings("unchecked")
    private Collection asTagElementList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asEnumConstantDeclaration(k));
        }
        return r;    
    }

    private boolean ensureTagElementList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureTagElement(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureTagElement(IStrategoTerm term) {
        return term instanceof WrappedTagElement;
    }

    private Operator asOperator(IStrategoTerm term) {
        return InfixExpression.Operator.toOperator(((IStrategoString)term).getValue());
    }

    private boolean ensureOperator(IStrategoTerm term) {
        return term instanceof IStrategoString 
        && InfixExpression.Operator.toOperator(((IStrategoString)term).getValue()) != null;
    }

    @SuppressWarnings("unchecked")
    private Collection asEnumConstantDeclarationList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asEnumConstantDeclaration(k));
        }
        return r;    
    }

    private EnumConstantDeclaration asEnumConstantDeclaration(IStrategoTerm term) {
        return ((WrappedEnumConstantDeclaration)term).getWrappee();
    }

    private boolean ensureEnumConstantDeclarationList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureEnumConstantDeclaration(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureEnumConstantDeclaration(IStrategoTerm term) {
        return term instanceof WrappedEnumConstantDeclaration;
    }

    @SuppressWarnings("unchecked")
    private Collection asTypeDeclarationList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asTypeDeclaration(k));
        }
        return r;    
    }
    
    private TypeDeclaration asTypeDeclaration(IStrategoTerm term) {
        return ((WrappedTypeDeclaration)term).getWrappee();
    }

    @SuppressWarnings("unchecked")
    private Collection asImportDeclarationList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asImportDeclaration(k));
        }
        return r;    
    }

    private ImportDeclaration asImportDeclaration(IStrategoTerm term) {
        return ((WrappedImportDeclaration)term).getWrappee();
    }

    private PackageDeclaration asPackageDeclaration(IStrategoTerm term) {
        return ((WrappedPackageDeclaration)term).getWrappee();
    }

    private boolean ensureTypeDeclarationList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureTypeDeclaration(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureTypeDeclaration(IStrategoTerm term) {
        return term instanceof TypeDeclaration;
    }

    private boolean ensureImportDeclarationList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureImportDeclaration(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureImportDeclaration(IStrategoTerm term) {
        return term instanceof WrappedImportDeclaration;
    }

    private boolean ensurePackageDeclaration(IStrategoTerm term) {
        return term instanceof WrappedPackageDeclaration;
    }

    @SuppressWarnings("unchecked")
    private Collection asMemberValuePairList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asMemberValuePair(k));
        }
        return r;    
    }

    private MemberValuePair asMemberValuePair(IStrategoTerm term) {
        return ((WrappedMemberValuePair)term).getWrappee();
    }

    private boolean ensureMemberValuePairList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureMemberValuePair(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureMemberValuePair(IStrategoTerm term) {
        return term instanceof MemberValuePair;
    }

    private ModifierKeyword asModifierKeyword(IStrategoTerm term) {
        return ((WrappedModifierKeyword)term).getModifier();
    }

    private boolean ensureModifierKeyword(IStrategoTerm term) {
        return term instanceof ModifierKeyword;
    }

    @SuppressWarnings("unchecked")
    private Collection asASTNodeList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asASTNode(k));
        }
        return r;    
    }

    private ASTNode asASTNode(IStrategoTerm term) {
        return ((WrappedASTNode)term).getWrappee();
    }

    private boolean ensureASTNodeList(IStrategoTerm term) {
        return term instanceof WrappedASTNodeList;
    }

    private Code asTypeCode(IStrategoTerm term) {
        final String s = ((IStrategoString)term).getValue();
        if(s.equals("boolean"))
            return PrimitiveType.BOOLEAN;
        else if(s.equals("byte"))
            return PrimitiveType.BYTE;
        else if(s.equals("float"))
            return PrimitiveType.FLOAT;
        else if(s.equals("int"))
            return PrimitiveType.INT;
        else if(s.equals("char"))
            return PrimitiveType.CHAR;
        else if(s.equals("long"))
            return PrimitiveType.LONG;
        else if(s.equals("short"))
            return PrimitiveType.SHORT;
        else if(s.equals("void"))
            return PrimitiveType.VOID;
        throw new NotImplementedException("Unknown primitive type: " + s);
    }

    private String asString(IStrategoTerm term) {
        return ((IStrategoString)term).getValue();
    }

    private boolean ensureString(IStrategoTerm term) {
        return term instanceof IStrategoString;
    }

    private Name asName(IStrategoTerm term) {
        return ((WrappedName)term).getWrappee();
    }

    @SuppressWarnings("unchecked")
    private Collection asCatchClauseList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asCatchClause(k));
        }
        return r;    
    }

    private CatchClause asCatchClause(IStrategoTerm term) {
        return ((WrappedCatchClause)term).getWrappee();
    }

    private boolean ensureCatchClauseList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureCatchClause(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureCatchClause(IStrategoTerm term) {
        return term instanceof WrappedCatchClause;
    }

    private boolean ensureBodyDeclarationList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureBodyDeclaration(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureTypeDecl(IStrategoTerm term) {
        return term instanceof AbstractTypeDeclaration;
    }

    private AbstractTypeDeclaration asTypeDecl(IStrategoTerm term) {
        return ((WrappedAbstractTypeDeclaration)term).getWrappee();
    }

    @SuppressWarnings("unchecked")
    private List asFragmentList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asVariableDeclarationFragment(k));
        }
        return r;    
    }

    private VariableDeclarationFragment asVariableDeclarationFragment(IStrategoTerm term) {
        return ((WrappedVariableDeclarationFragment)term).getWrappee();
    }

    private boolean ensureFragmentList(IStrategoTerm term) {
        // Must contain at least one element
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() < 0)
                return false;
            return ensureVariableDeclarationFragment(list.get(0));
            
        }
        return false;
    }

    private boolean ensureVariableDeclarationFragment(IStrategoTerm term) {
        return term instanceof WrappedVariableDeclarationFragment;
    }

    private boolean ensureAnonymousClassDeclaration(IStrategoTerm term) {
        return term instanceof WrappedAnonymousClassDeclaration;
    }

    private AnonymousClassDeclaration asAnonymousClassDeclaration(IStrategoTerm term) {
        return ((WrappedAnonymousClassDeclaration)term).getWrappee();
    }

    private boolean ensureBlock(IStrategoTerm term) {
        return term instanceof WrappedBlock;
    }

    private boolean ensureSingleVariableDeclaration(IStrategoTerm term) {
        return term instanceof WrappedSingleVariableDeclaration;
    }

    private SingleVariableDeclaration asSingleVariableDeclaration(IStrategoTerm term) {
        return ((WrappedSingleVariableDeclaration)term).getWrappee();
    }

    private Block asBlock(IStrategoTerm term) {
        return ((WrappedBlock)term).getWrappee();
    }

    private PrefixExpression.Operator asPrefixOperator(IStrategoTerm term) {
        return ((WrappedPrefixExpressionOperator)term).getOperator();
    }

    private boolean ensurePrefixOperator(IStrategoTerm term) {
        return term instanceof WrappedPrefixExpressionOperator;
    }

    private PostfixExpression.Operator asPostfixOperator(IStrategoTerm term) {
        return ((WrappedPostfixExpressionOperator)term).getOperator();
    }

    private boolean ensurePostfixOperator(IStrategoTerm term) {
        return term instanceof WrappedPostfixExpressionOperator;
    }

    @SuppressWarnings("unchecked")
    private Collection asTypeList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asType(k));
        }
        return r;    
    }

    private boolean ensureTypeList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureType(list.get(0));
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private Collection asStatementList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asStatement(k));
        }
        return r;
    }

    private Statement asStatement(IStrategoTerm term) {
        return ((WrappedStatement)term).getWrappee();
    }

    private boolean ensureStatementList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureStatement(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureStatement(IStrategoTerm term) {
        return term instanceof WrappedStatement;
    }

    private int asInt(IStrategoTerm term) {
        return ((IStrategoInt)term).getValue();
    }

    private boolean ensureInt(IStrategoTerm term) {
        return term instanceof IStrategoInt;
    }

    @SuppressWarnings("unchecked")
    private Collection asExpressionList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asExpression(k));
        }
        return r;
    }

    private boolean ensureExpressionList(IStrategoTerm term) {
        return term instanceof WrappedExpression;
    }

    private boolean ensureArrayInitializer(IStrategoTerm term) {
        return term instanceof WrappedArrayInitializer;
    }


    private boolean ensureArrayType(IStrategoTerm term) {
        return term instanceof WrappedArrayType;
    }

    private ArrayInitializer asArrayInitializer(IStrategoTerm term) {
        return ((WrappedArrayInitializer)term).getWrappee();
    }

    private ArrayType asArrayType(IStrategoTerm term) {
        return ((WrappedArrayType)term).getWrappee();
    }

    private Type asType(IStrategoTerm term) {
        return ((WrappedType)term).getWrappee();
    }

    private boolean ensureSimpleName(IStrategoTerm term) {
        return term instanceof WrappedSimpleName;
    }

    private boolean ensureType(IStrategoTerm term) {
        return term instanceof WrappedType;
    }

    @SuppressWarnings("unchecked")
    private Collection asModifierList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asModifier(k));
        }
        return r;
    }

    private boolean ensureModifierList(IStrategoTerm term) {
        if(term instanceof IStrategoList) {
            IStrategoList list = (IStrategoList)term;
            if(list.size() > 0) 
                return ensureModifier(list.get(0));
            return true;
        }
        return false;
    }

    private boolean ensureModifier(IStrategoTerm term) {
        return term instanceof WrappedModifier;
    }

    @SuppressWarnings("unchecked")
    private Collection asBodyDeclarationList(IStrategoTerm term) {
        IStrategoTerm[] kids = term.getAllSubterms();
        List r = new ArrayList(kids.length);
        for(IStrategoTerm k : kids) {
            r.add(asBodyDeclaration(k));
        }
        return r;
    }

    private BodyDeclaration asBodyDeclaration(IStrategoTerm k) {
        return ((WrappedBodyDeclaration)k).getWrappee();
    }

    private SimpleName asSimpleName(IStrategoTerm term) {
        return ((WrappedSimpleName)term).getWrappee();
    }

    private Modifier asModifier(IStrategoTerm term) {
        if(term instanceof WrappedModifier) {
            ((WrappedModifier)term).getWrappee();
        }
        return null;
    }

    private boolean ensureBodyDeclaration(IStrategoTerm term) {
        return term instanceof WrappedBodyDeclaration;
    }

    private boolean ensureExpression(IStrategoTerm term) {
        return term instanceof WrappedExpression;
    }

    private Expression asExpression(IStrategoTerm term) {
        if(term instanceof WrappedExpression) {
            Expression e = ((WrappedExpression) term).getWrappee();
            if(e.getParent() != null)
                return (Expression) ASTNode.copySubtree(ast, e);
            return e;
        }
        return null;
    }

    private int ctorNameToIndex(IStrategoConstructor ctr) {
        Integer x = ctorNameToIndexMap.get(ctr.getName());
        return x == null ? -1 : x.intValue();
    }

    public IStrategoConstructor makeConstructor(String string, int arity, boolean quoted) {
        return new ASTCtor(string, arity);
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
        
        List<ASTNode> r = new ArrayList();
        for(IStrategoTerm t : terms)
            r.add(((WrappedASTNode)t).getWrappee());
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

    public static IStrategoAppl wrap(Javadoc javadoc) {
        if(javadoc == null)
            return None.INSTANCE;
        else
            return new WrappedJavadoc(javadoc);
    }

    @SuppressWarnings("unchecked")
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

    static IStrategoAppl wrap(SimpleName name) {
        if(name == null)
            return None.INSTANCE;
        else
            return new WrappedSimpleName(name);
    }

    private static IStrategoAppl wrap(QualifiedName name) {
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

    private static IStrategoAppl wrap(LineComment comment) {
        if(comment == null)
            return None.INSTANCE;
        else
            return new WrappedLineComment(comment);
    }

    private static IStrategoAppl wrap(BlockComment comment) {
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

    private static IStrategoAppl wrap(AnnotationTypeMemberDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedAnnotationTypeMemberDeclaration(declaration);
    }

    private static IStrategoAppl wrap(EnumConstantDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedEnumConstantDeclaration(declaration);
    }

    private static IStrategoAppl wrap(FieldDeclaration declaration) {
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

    private static IStrategoAppl wrap(TypeParameter parameter) {
        if(parameter == null)
            return None.INSTANCE;
        else
            return new WrappedTypeParameter(parameter);
    }

    private static IStrategoAppl wrap(TextElement element) {
        if(element == null)
            return None.INSTANCE;
        else
            return new WrappedTextElement(element);
    }

    private static IStrategoAppl wrap(TagElement element) {
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

    private static IStrategoAppl wrap(IfStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedIfStatement(statement);
    }

    private static IStrategoAppl wrap(SuperConstructorInvocation invocation) {
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

    private static IStrategoAppl wrap(ThrowStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedThrowStatement(statement);
    }

    private static IStrategoAppl wrap(TryStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedTryStatement(statement);
    }

    private static IStrategoAppl wrap(TypeDeclarationStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedTypeDeclarationStatement(statement);
    }

    private static IStrategoAppl wrap(WhileStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedWhileStatement(statement);
    }

    private static IStrategoAppl wrap(ReturnStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedReturnStatement(statement);
    }

    private static IStrategoAppl wrap(LabeledStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedLabeledStatement(statement);
    }

    private static IStrategoAppl wrap(ForStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedForStatement(statement);
    }

    private static IStrategoAppl wrap(EnhancedForStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedEnhancedForStatement(statement);
    }

    private static IStrategoAppl wrap(EmptyStatement statement) {   
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedEmptyStatement(statement);
    }

    private static IStrategoAppl wrap(DoStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedDoStatement(statement);
    }

    private static IStrategoAppl wrap(ContinueStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedContinueStatement(statement);
    }

    private static IStrategoAppl wrap(ConstructorInvocation invocation) {
        if(invocation == null)
            return None.INSTANCE;
        else
            return new WrappedConstructorInvocation(invocation);

    }

    private static IStrategoAppl wrap(BreakStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedBreakStatement(statement);
    }

    static IStrategoAppl wrap(Block block) {
        if(block == null)
            return None.INSTANCE;
        else
            return new WrappedBlock(block);
    }

    private static IStrategoAppl wrap(AssertStatement statement) {
        if(statement == null)
            return None.INSTANCE;
        else
            return new WrappedAssertStatement(statement);
    }

    private static IStrategoAppl wrap(Modifier modifier) {
        if(modifier == null)
            return None.INSTANCE;
        else
            return new WrappedModifier(modifier);
    }

    private static IStrategoAppl wrap(MethodRefParameter parameter) {
        if(parameter == null)
            return None.INSTANCE;
        else
            return new WrappedMethodRefParameter(parameter);
    }

    private static IStrategoAppl wrap(MethodRef ref) {
        if(ref == null)
            return None.INSTANCE;
        else
            return new WrappedMethodRef(ref);
    }

    private static IStrategoAppl wrap(MemberValuePair pair) {
        if(pair == null)
            return None.INSTANCE;
        else
            return new WrappedMemberValuePair(pair);
    }

    private static IStrategoAppl wrap(MemberRef ref) {
        if(ref == null)
            return None.INSTANCE;
        else
            return new WrappedMemberRef(ref);
    }

    private static IStrategoAppl wrap(CatchClause clause) {
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

    private static IStrategoAppl wrap(VariableDeclarationFragment fragment) {
        if(fragment == null)
            return None.INSTANCE;
        else
            return new WrappedVariableDeclarationFragment(fragment);
    }

    private static IStrategoAppl wrap(VariableDeclarationStatement statement) {
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

    static IStrategoAppl wrap(SingleVariableDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedSingleVariableDeclaration(declaration);
    }

    private static IStrategoAppl wrap(MethodDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedMethodDeclaration(declaration);
    }

    private static IStrategoAppl wrap(TypeDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedTypeDeclaration(declaration);
    }

    static IStrategoAppl wrap(CompilationUnit unit) {
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

    static IStrategoAppl wrap(ImportDeclaration declaration) {
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

    public static IStrategoAppl wrap(ArrayType type) {
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

    private static IStrategoAppl wrap(SingleMemberAnnotation annotation) {
        if(annotation == null)
            return None.INSTANCE;
        else
            return new WrappedSingleMemberAnnotation(annotation);
    }

    private static IStrategoAppl wrap(NormalAnnotation annotation) {
        if(annotation == null)
            return None.INSTANCE;
        else
            return new WrappedNormalAnnotation(annotation);
    }

    private static IStrategoAppl wrap(MarkerAnnotation annotation) {
        if(annotation == null) 
            return None.INSTANCE;
        else
            return new WrappedMarkerAnnotation(annotation);
    }

    private static IStrategoAppl wrap(VariableDeclarationExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedVariableDeclarationExpression(expression);
    }

    private static IStrategoAppl wrap(TypeLiteral literal) {
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

    private static IStrategoAppl wrap(SuperMethodInvocation invocation) {
        if(invocation == null)
            return None.INSTANCE;
        else
            return new WrappedSuperMethodInvocation(invocation);
    }

    private static IStrategoAppl wrap(SuperFieldAccess access) {
        if(access == null)
            return None.INSTANCE;
        else
            return new WrappedSuperFieldAccess(access);
    }

    private static IStrategoAppl wrap(StringLiteral literal) {
        if(literal == null) 
            return None.INSTANCE;
        else
            return new WrappedStringLiteral(literal);
    }

    private static IStrategoAppl wrap(PrefixExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedPrefixExpression(expression);
    }

    private static IStrategoAppl wrap(PostfixExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedPostfixExpression(expression);
    }

    private static IStrategoAppl wrap(ParenthesizedExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedParenthesizedExpression(expression);
    }

    private static IStrategoAppl wrap(NumberLiteral literal) {
        if(literal == null)
            return None.INSTANCE;
        else
            return new WrappedNumberLiteral(literal);
    }

    private static IStrategoAppl wrap(NullLiteral literal) {
        if(literal == null)
            return None.INSTANCE;
        else
            return new WrappedNullLiteral(literal);
    }

    private static IStrategoAppl wrap(MethodInvocation invocation) {
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

    private static IStrategoAppl wrap(InfixExpression expression) {
        
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

    private static IStrategoAppl wrap(FieldAccess access) {
        if(access == null)
            return None.INSTANCE;
        else
            return new WrappedFieldAccess(access);
    }

    private static IStrategoAppl wrap(ConditionalExpression expression) {
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

    private static IStrategoAppl wrap(CharacterLiteral literal) {
        if(literal == null)
            return None.INSTANCE;
        else
            return new WrappedCharacterLiteral(literal);
    }

    private static IStrategoAppl wrap(CastExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedCastExpression(expression);
    }

    private static IStrategoAppl wrap(BooleanLiteral literal) {
        if(literal == null)
            return None.INSTANCE;
        else
            return new WrappedBooleanLiteral(literal);
    }

    private static IStrategoAppl wrap(Assignment assignment) {
        if(assignment == null)
            return None.INSTANCE;
        else
            return new WrappedAssignment(assignment);
    }

    private static IStrategoAppl wrap(ArrayInitializer initializer) {
        if(initializer == null)
            return None.INSTANCE;
        else 
            return new WrappedArrayInitializer(initializer);
    }

    private static IStrategoAppl wrap(ArrayCreation creation) {
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

    private static IStrategoAppl wrap(WildcardType type) {
        if(type == null)
            return None.INSTANCE;
        else
            return new WrappedWildcardType(type);
    }

    private static IStrategoAppl wrap(SimpleType type) {
        if(type == null)
            return None.INSTANCE;
        else
            return new WrappedSimpleType(type);
    }

    private static IStrategoAppl wrap(QualifiedType type) {
        if(type == null)
            return None.INSTANCE;
        else
            return new WrappedQualifiedType(type);
    }

    private static IStrategoAppl wrap(PrimitiveType type) {
        
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

    private static IStrategoAppl wrap(ParameterizedType type) {
        if(type == null)
            return None.INSTANCE;
        else
            return new WrappedParameterizedType(type); 
    }

    public static IStrategoAppl wrap(ModifierKeyword keyword) {
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

    private static IStrategoAppl wrap(EnumDeclaration declaration) {
        if(declaration == null)
            return None.INSTANCE;
        else
            return new WrappedEnumDeclaration(declaration);
    }

    private static IStrategoAppl wrap(AnnotationTypeDeclaration declaration) {
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

    public void setAST(AST ast) {
        this.ast = ast;
    }
}
