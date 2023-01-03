package org.fpm.di;

import java.util.Map;

public interface BinderImplementationsMap {
    <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass);

    Map<Class, Class> getImplementationsMap();
}
