package org.fpm.di.example;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class B extends A {
    @Inject
    public B() { }
}
