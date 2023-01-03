package org.fpm.di.example;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;
import org.fpm.di.Container;
import org.fpm.di.Environment;

import java.lang.reflect.InvocationTargetException;

public class DummyEnvironment implements Environment {

    @Override
    public Container configure(Configuration configuration) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Binder binder = new DummyBinder();
        configuration.configure(binder);
        return new DummyContainer();
    }
}
