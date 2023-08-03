package com.itblee;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

public class Provider {
    private static final Map<Class<?>, Object> DEPENDENCIES = new HashMap<>();

    public static <T> T get(Class<T> tClass) {
        if (tClass.isInterface()) {
            ServiceLoader<T> loader = ServiceLoader.load(tClass);
            return loader.iterator().next();
        }
        Object get = DEPENDENCIES.computeIfAbsent(tClass, type -> {
            try {
                return type.newInstance();
            } catch (InstantiationException | IllegalAccessException ignored) {
                return null;
            }
        });
        return tClass.cast(get);
    }

    public static <T> T get(String name, Class<T> tClass) {
        if (!tClass.isInterface())
            throw new IllegalArgumentException();
        ServiceLoader<T> loader = ServiceLoader.load(tClass);
        for (T t : loader) {
            if (t.getClass().getSimpleName().equals(name))
                return t;
        }
        throw new NoSuchElementException();
    }

}
