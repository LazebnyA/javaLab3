package org.fpm.di_outerUse;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class WelcomeToExhibitionReadableA implements ReadableA {

    @Inject
    public WelcomeToExhibitionReadableA() {}

    @Override
    public void read() {
        System.out.println("Welcome to the Exhibition!");
    }

    @Override
    public void readWithDictionary(Dictionary dictionary) {
        System.out.println("Welcome to the Exhibition (... reading with dictionary" + " from " + dictionary.getLanguage1() + " to " + dictionary.getLanguage2());
    }
}
