package org.fpm.di_outerUse;

import javax.inject.Inject;

public class Magazine implements ReadableA {
    public final String name;
    public final String editor;
    public final int year;

    @Inject
    public Magazine(String name, String editor, int year) {
        this.name = name;
        this.editor = editor;
        this.year = year;
    }

    @Override
    public void read() { System.out.println("Reading magazine ..."); }

    @Override
    public void readWithDictionary(Dictionary dictionary) { System.out.println("Reading magazine with dictionary ..."); }

    public String getName() { return this.name; }
    public String getEditor() { return this.name; }
    public int getYear() { return this.year; }
}
