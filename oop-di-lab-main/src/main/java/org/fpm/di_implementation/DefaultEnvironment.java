package org.fpm.di_implementation;

import org.fpm.di.*;

import java.lang.reflect.InvocationTargetException;

public class DefaultEnvironment implements Environment {

    @Override
    public Container configure(Configuration configuration) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        BinderImplementationsMap binderImplementationsMap = new DefaultBinderImplMap();
        BinderCashMap binderCashMap = new DefaultBinderCashMap();

        Binder binder = new DefaultBinder(binderImplementationsMap, binderCashMap);

        configuration.configure(binder);

        return new DefaultContainer(binderImplementationsMap, binderCashMap);
    }
}
