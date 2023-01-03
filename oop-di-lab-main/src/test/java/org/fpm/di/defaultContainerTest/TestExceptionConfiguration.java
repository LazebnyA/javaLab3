package org.fpm.di.defaultContainerTest;


import org.fpm.di.Binder;
import org.fpm.di.Configuration;
import org.fpm.di_outerUse.ReadableA;
import org.fpm.di_outerUse.ReadablePresenter;
import org.fpm.di_outerUse.WelcomeToExhibitionReadableA;

import java.lang.reflect.InvocationTargetException;

public class TestExceptionConfiguration implements Configuration {

    @Override
    public void configure(Binder binder) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        binder.bind(ReadableA.class, WelcomeToExhibitionReadableA.class);

        binder.bind(ReadablePresenter.class);
    }
}
