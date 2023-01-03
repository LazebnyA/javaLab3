package org.fpm.di_outerUse;

import javax.inject.Inject;

public class Book implements ReadableA {

    private final String name;
    private final String author;
    private final int year;

    @Inject
    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }


    @Override
    public void read() { System.out.println("Reading book ..."); }

    @Override
    public void readWithDictionary(Dictionary dictionary) { System.out.println("Reading book with dictionary ..."); }

    public String getName() { return this.name; }
    public String getAuthor() { return this.author; }
    public int getYear() { return this.year; }

}
