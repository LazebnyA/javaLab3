package org.fpm.di;

import java.lang.reflect.InvocationTargetException;

public interface Container {
    <T> T getComponent(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
