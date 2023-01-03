package org.fpm.di;

import java.util.Map;

public interface BinderCashMap {
    <T> T getComponentFromCash(Class<T> componentClass);

    Map<Class, Object> getCashMap();

}
