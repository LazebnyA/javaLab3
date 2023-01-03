package org.fpm.di.example;

import javax.inject.Inject;

public class UseA {

    private final A dependency;

    @Inject
    public UseA(A dependency) {
        this.dependency = dependency;
    }

    public A getDependency() {
        return dependency;
    }
}
