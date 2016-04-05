package org.metaborg.util.inject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.util.Types;

public class GenericInjectUtils {
    public static <T> Key<T> key(Type rawType, Type... typeArgs) {
        final ParameterizedType type = Types.newParameterizedType(rawType, typeArgs);
        @SuppressWarnings("unchecked") final Key<T> key = (Key<T>) Key.get(type);
        return key;
    }

    public static <T> Key<T> key(TypeLiteral<T> typeLiteral, Type... typeArgs) {
        final Class<? super T> rawType = typeLiteral.getRawType();
        return key(rawType, typeArgs);
    }


    public static <T> T instance(Injector injector, Type rawType, Type... typeArgs) {
        final Key<T> key = key(rawType, typeArgs);
        return injector.getInstance(key);
    }

    public static <T> T instance(Injector injector, TypeLiteral<T> typeLiteral, Type... typeArgs) {
        final Key<T> key = key(typeLiteral, typeArgs);
        return injector.getInstance(key);
    }
}
