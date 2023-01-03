package org.fpm.di_implementation;

import org.fpm.di.BinderImplementationsMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DefaultBinderImplMap implements BinderImplementationsMap {
    private final Map<Class, Class> implementationsMap = new ConcurrentHashMap<>();

    @Override
    public <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass) {
        return implementationsMap.get(interfaceClass);
    }

    @Override
    public Map<Class, Class> getImplementationsMap() {
        return implementationsMap;
    }
}
