package org.fpm.di_outerUse;

import org.fpm.di.Container;
import org.fpm.di.Environment;
import org.fpm.di_implementation.DefaultEnvironment;

public class Main {
    public static void main(String[] args) throws Exception {
        Environment defaultEnvironment = new DefaultEnvironment();
        Container defaultContainer = defaultEnvironment.configure(new ReadablePresentationConfiguration());

        ReadablePresenter readablePresenter = defaultContainer.getComponent(ReadablePresenter.class);

        ReadableA readableA = defaultContainer.getComponent(WelcomeToExhibitionReadableA.class);
        readableA.read();

        readablePresenter.toPresent();

        readablePresenter.toPresentWithDictionary(defaultContainer.getComponent(Dictionary.class));
    }
}
