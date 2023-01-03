package org.fpm.di_outerUse;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;

import java.lang.reflect.InvocationTargetException;

public class ReadablePresentationConfiguration implements Configuration {
    @Override
    public void configure(Binder binder) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        binder.bind(Book.class, new Book("1984", "George Orwell", 1949));
        binder.bind(Dictionary.class, new Dictionary("ukrainian", "english"));
        binder.bind(Magazine.class, new Magazine("The Times", "Tony Gallagher", 2015));

        binder.bind(Integer.class, 299);

        binder.bind(ReadableA.class, WelcomeToExhibitionReadableA.class);
        binder.bind(WelcomeToExhibitionReadableA.class);

        binder.bind(ReadableSingleExhibition.class);
        binder.bind(ReadablePresenter.class);
    }
}
