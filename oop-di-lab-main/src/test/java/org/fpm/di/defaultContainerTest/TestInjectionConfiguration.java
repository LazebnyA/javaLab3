package org.fpm.di.defaultContainerTest;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;
import org.fpm.di_outerUse.*;
import org.fpm.di_outerUse.ReadableA;

import java.lang.reflect.InvocationTargetException;

public class TestInjectionConfiguration implements Configuration {
    @Override
    public void configure(Binder binder) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        binder.bind(Book.class, new Book("1984", "George Orwell", 1949));
        binder.bind(Dictionary.class, new Dictionary("ukrainian", "english"));
        binder.bind(Magazine.class, new Magazine("The Times", "Tony Gallagher", 2015));

        binder.bind(ReadableA.class, Dictionary.class);
        binder.bind(ReadableSingleExhibition.class);
        binder.bind(ReadablePresenter.class);
    }
}
