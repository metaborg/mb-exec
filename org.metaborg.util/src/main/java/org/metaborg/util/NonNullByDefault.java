package org.metaborg.util;

import jakarta.annotation.Nonnull;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * This annotation can be applied to a package, class or method to indicate that the class fields,
 * method return types and parameters in that element are not null by default unless there is:
 * <ul>
 * <li>an explicit nullness annotation;
 * <li>the method overrides a method in a superclass (in which case the annotation of the
 * corresponding parameter in the superclass applies);
 * <li>there is a default parameter annotation applied to a more tightly nested element.
 * </ul>
 */
@Documented
@Nonnull
@Target({
    ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR,
    ElementType.FIELD,
    ElementType.LOCAL_VARIABLE,
    ElementType.METHOD,
    ElementType.PACKAGE,
    ElementType.PARAMETER,
    ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonNullByDefault {}
