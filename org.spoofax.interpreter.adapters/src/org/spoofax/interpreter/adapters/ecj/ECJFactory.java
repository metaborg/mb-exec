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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoList kids) {
        return ctr.instantiate(this, kids);
        //throw new NotImplementedException();
    }

    public IStrategoAppl makeAppl(IStrategoConstructor ctr, IStrategoTerm... terms) {
        return ctr.instantiate(this, terms);
    }

    public IStrategoConstructor makeConstructor(String string, int arity, boolean quoted) {
        return new ASTCtor(string, arity);
        //throw new NotImplementedException();
    }

    public IStrategoInt makeInt(int i) {
        throw new NotImplementedException();
    }

    public IStrategoList makeList(IStrategoTerm... terms) {
        List r = new ArrayList();
        for(IStrategoTerm t : terms)
            r.add(t);
        return new WrappedList(r);
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
            return new WrappedList(list);
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

    private static IStrategoTerm wrap(ArrayAccess access) {
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
}
