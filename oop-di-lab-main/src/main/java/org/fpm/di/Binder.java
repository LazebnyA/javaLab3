package org.fpm.di;

import java.lang.reflect.InvocationTargetException;

public interface Binder {
    <T> void bind(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    <T> void bind(Class<T> clazz, Class<? extends T> implementation);

    <T> void bind(Class<T> clazz, T instance);
}
