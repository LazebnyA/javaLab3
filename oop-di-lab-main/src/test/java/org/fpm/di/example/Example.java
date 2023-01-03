package org.fpm.di.example;

import org.fpm.di.Container;
import org.fpm.di.Environment;
import org.fpm.di_implementation.DefaultEnvironment;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class Example {

    private Container container;

    @Before
    public void setUp() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Environment env = new DefaultEnvironment(); 
        container = env.configure(new MyConfiguration());
    }

    @Test
    public void shouldInjectSingleton() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertSame(container.getComponent(MySingleton.class), container.getComponent(MySingleton.class));
    }

    @Test
    public void shouldInjectPrototype() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertNotSame(container.getComponent(MyPrototype.class), container.getComponent(MyPrototype.class));
    }

    @Test
    public void shouldBuildInjectionGraph() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        /*
        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());
        */
        final B bAsSingleton = container.getComponent(B.class);
        assertSame(container.getComponent(A.class), bAsSingleton);
        assertSame(container.getComponent(B.class), bAsSingleton);
    }

    @Test
    public void shouldBuildInjectDependencies() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final UseA hasADependency = container.getComponent(UseA.class);
        assertSame(hasADependency.getDependency(), container.getComponent(B.class));
    }




// ShouldInjectSingletonWithDependencies
// ShouldInject

}
