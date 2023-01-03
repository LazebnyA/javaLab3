package org.fpm.di.defaultContainerTest;

import org.fpm.di.Container;
import org.fpm.di.Environment;
import org.fpm.di_implementation.DefaultEnvironment;
import org.fpm.di_outerUse.ReadablePresenter;
import org.fpm.di_outerUse.ReadableSingleExhibition;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class TestingTestConfigurations {
    private Container container;

    @Before
    public void setUp() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Environment env = new DefaultEnvironment();
        container = env.configure(new TestInjectionConfiguration());
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotInjectClassesWithNotBoundDependency() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Environment env = new DefaultEnvironment();
        Container container = env.configure(new TestExceptionConfiguration());
    }


    @Test
    public void shouldInjectSingletonWithPrimitiveTypesAsDependencies() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertSame(container.getComponent(ReadableSingleExhibition.class), container.getComponent(ReadableSingleExhibition.class));
    }

    @Test
    public void shouldInjectClassWithSingletonAsDependency() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertNotSame(container.getComponent(ReadablePresenter.class), container.getComponent(ReadablePresenter.class));
    }



}
