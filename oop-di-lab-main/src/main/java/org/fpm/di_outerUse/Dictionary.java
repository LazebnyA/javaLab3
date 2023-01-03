package org.fpm.di_outerUse;

import javax.inject.Inject;

public class Dictionary implements ReadableA {
    public final String language1;
    public final String language2;

    @Inject
    public Dictionary(String language1, String language2) {
        this.language1 = language1;
        this.language2 = language2;
    }

    public String getLanguage1() { return this.language1; }

    public String getLanguage2() { return this.language2; }

    @Override
    public void read() { System.out.println("Reading the dictionary ..." + language1 + " to " + language2); }

    @Override
    public void readWithDictionary(Dictionary dictionary) {
        System.out.println("Reading dictionary with dictionary: " + dictionary.language1 + " to " + dictionary.language2);
    }

}
