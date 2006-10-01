/*
 * Created on 27. sep.. 2006
 *
 * Copyright (c) 2005, Karl Trygve Kalleberg <karltk@ii.uib.no>
 * 
 * Licensed under the GNU General Public License, v2
 */
package org.spoofax.interpreter.adapters.ecj;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MemberRef;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.MethodRef;
import org.eclipse.jdt.core.dom.MethodRefParameter;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeLiteral;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WildcardType;
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

public class WrappedECJFactory implements ITermFactory {

    public IStrategoTerm parseFromFile(String path) throws IOException {
        throw new NotImplementedException();
    }

    public IStrategoTerm parseFromStream(InputStream inputStream) throws IOException {
        throw new NotImplementedException();
    }

    public IStrategoTerm parseFromString(String text) {
        throw new NotImplementedException();
    }

    public void unparseToFile(IStrategoTerm t, OutputStream ous) throws IOException {
        throw new NotImplementedException();
    }

    public boolean hasConstructor(String s, int i) {
        throw new NotImplementedException();
    }

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoList kids) {
        throw new NotImplementedException();
    }

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoTerm... terms) {
        throw new NotImplementedException();
    }

    public IStrategoConstructor makeConstructor(String string, int arity, boolean quoted) {
        throw new NotImplementedException();
    }

    public IStrategoInt makeInt(int i) {
        throw new NotImplementedException();
    }

    public IStrategoList makeList(IStrategoTerm... terms) {
        throw new NotImplementedException();
    }

    public IStrategoList makeList(Collection<IStrategoTerm> terms) {
        throw new NotImplementedException();
    }

    public IStrategoReal makeReal(double d) {
        throw new NotImplementedException();
    }

    public IStrategoString makeString(String s) {
        throw new NotImplementedException();
    }

    public IStrategoTuple makeTuple(IStrategoTerm... terms) {
        throw new NotImplementedException();
    }

    public static IStrategoTerm wrap(Javadoc javadoc) {
        throw new NotImplementedException();
    }

    public static IStrategoTerm wrap(List list) {
        if(list == null)
            return None.INSTANCE;
        else
            return new WrappedList(list);
    }

    static IStrategoTerm wrapName(Name name) {
        if(name == null)
            return None.INSTANCE;
        else
            return new WrappedName(name);
    }

    static IStrategoTerm genericWrap(ASTNode node) {
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
        if(node instanceof ExpressionStatement)
            return wrap((ExpressionStatement) node);
        if(node instanceof VariableDeclarationStatement)
            return wrap((VariableDeclarationStatement) node);
        if(node instanceof VariableDeclarationFragment)
            return wrap((VariableDeclarationFragment) node);
        if(node instanceof AnonymousClassDeclaration)
            return wrap((AnonymousClassDeclaration) node);
        if(node instanceof BodyDeclaration)
            return wrap((BodyDeclaration) node);
        if(node instanceof CatchClause)
            return wrap((CatchClause) node);
        if(node instanceof Comment) 
            return wrap((Comment) node);
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
            return wrap((Type) node);
        if(node instanceof TypeParameter)
            return wrap((TypeParameter) node);
        if(node instanceof VariableDeclaration)
            return wrap((VariableDeclaration) node);
        
        throw new NotImplementedException("" + node.getClass());
    }

    private static IStrategoTerm wrap(VariableDeclaration declaration) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(TypeParameter parameter) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(Type type) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(TextElement element) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(TagElement element) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrapStatement(Statement statement) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(Modifier modifier) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(MethodRefParameter parameter) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(MethodRef ref) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(MemberValuePair pair) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(MemberRef ref) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(Comment comment) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(CatchClause clause) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(BodyDeclaration declaration) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(AnonymousClassDeclaration declaration) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
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

    private static IStrategoTerm wrap(SingleVariableDeclaration declaration) {
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

    static IStrategoTerm wrap(PackageDeclaration declaration) {
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
        if(expr instanceof Annotation)
            return wrap((Annotation) expr);
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
        
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(VariableDeclarationExpression expression) {
        if(expression == null)
            return None.INSTANCE;
        else
            return new WrappedVariableDeclarationExpression(expression);
    }

    private static IStrategoTerm wrap(TypeLiteral literal) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(ThisExpression expression) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(SuperMethodInvocation invocation) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(SuperFieldAccess access) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(StringLiteral literal) {
        if(literal == null) 
            return None.INSTANCE;
        else
            return new WrappedStringLiteral(literal);
    }

    private static IStrategoTerm wrap(PrefixExpression expression) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(PostfixExpression expression) {
        
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(ParenthesizedExpression expression) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
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
        if(expression.getOperator() == InfixExpression.Operator.PLUS)
            return new WrappedPlus(expression); 
        if(expression.getOperator() == InfixExpression.Operator.MINUS)
            return new WrappedMinus(expression); 
        if(expression.getOperator() == InfixExpression.Operator.TIMES)
            return new WrappedTimes(expression); 
        if(expression.getOperator() == InfixExpression.Operator.DIVIDE)
            return new WrappedDivide(expression); 

        throw new NotImplementedException();
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
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(CharacterLiteral literal) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(CastExpression expression) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(BooleanLiteral literal) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(Assignment assignment) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(ArrayInitializer initializer) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(ArrayCreation creation) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(ArrayAccess access) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(Annotation annotation) {
        throw new NotImplementedException();
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
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(SimpleType type) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(QualifiedType type) {
        // TODO Auto-generated method stub
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(PrimitiveType type) {
        if(type == null)
            return None.INSTANCE;
        if(type.getPrimitiveTypeCode() == PrimitiveType.INT)
            return new WrappedIntType(type);
        if(type.getPrimitiveTypeCode() == PrimitiveType.BOOLEAN)
            return new WrappedBooleanType(type);
        
        throw new NotImplementedException();
    }

    private static IStrategoTerm wrap(ParameterizedType type) {
        if(type == null)
            return None.INSTANCE;
        else
            return new WrappedParametrizedType(type); 
    }
}
