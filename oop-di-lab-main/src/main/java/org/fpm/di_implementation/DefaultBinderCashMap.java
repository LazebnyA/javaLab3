package org.fpm.di_implementation;

import org.fpm.di.BinderCashMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBinderCashMap implements BinderCashMap {
    private final Map<Class, Object> cashMap = new ConcurrentHashMap<>();

    @Override
    public <T> T getComponentFromCash(Class<T> componentClass) {
        return (T) cashMap.get(componentClass);
    }

    @Override
    public Map<Class, Object> getCashMap() {
        return cashMap;
    }
}
