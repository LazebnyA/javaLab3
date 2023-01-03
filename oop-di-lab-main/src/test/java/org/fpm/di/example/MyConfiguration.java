package org.fpm.di.example;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;

import java.lang.reflect.InvocationTargetException;

public class MyConfiguration implements Configuration {
    @Override
    public void configure(Binder binder) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        binder.bind(MySingleton.class);
        binder.bind(MyPrototype.class);

        binder.bind(B.class, new B());

        binder.bind(A.class, B.class);
        binder.bind(UseA.class);
    }
}
