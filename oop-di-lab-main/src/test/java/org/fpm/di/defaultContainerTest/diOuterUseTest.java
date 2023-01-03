package org.fpm.di.defaultContainerTest;

import org.fpm.di.Container;
import org.fpm.di.Environment;
import org.fpm.di_implementation.DefaultEnvironment;
import org.fpm.di_outerUse.*;
import org.fpm.di_outerUse.ReadableA;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class diOuterUseTest {
    private Container container;

    @Before
    public void setUp() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Environment env = new DefaultEnvironment();
        container = env.configure(new ReadablePresentationConfiguration());
    }

    @Test
    public void shouldInjectSingleton() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertSame(container.getComponent(ReadableSingleExhibition.class), container.getComponent(ReadableSingleExhibition.class));
    }

    @Test
    public void shouldInjectBoundSingletonWithoutDependencies() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertSame(container.getComponent(WelcomeToExhibitionReadableA.class), container.getComponent(WelcomeToExhibitionReadableA.class));
    }

    @Test
    public void shouldInjectSingletonWithDependencies() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertSame(container.getComponent(ReadableSingleExhibition.class), container.getComponent(ReadableSingleExhibition.class));
    }

    @Test
    public void shouldInjectNotSingletonClassWithPrimitiveTypesAsFields() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertNotSame(container.getComponent(Book.class), container.getComponent(Book.class));
        assertNotSame(container.getComponent(Magazine.class), container.getComponent(Magazine.class));
        assertNotSame(container.getComponent(Dictionary.class), container.getComponent(Dictionary.class));
    }

    @Test
    public void shouldInjectSingletonInterfaceImplementation() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertSame(container.getComponent(ReadableA.class), container.getComponent(WelcomeToExhibitionReadableA.class));
    }

    @Test
    public void shouldInjectDependency() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ReadablePresenter hasDependency = container.getComponent(ReadablePresenter.class);
        assertSame(hasDependency.getReadableSE(), container.getComponent(ReadableSingleExhibition.class));
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotInjectNotAnnotatedConstructors() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertNotSame(container.getComponent(Integer.class), container.getComponent(Integer.class));
    }



}
