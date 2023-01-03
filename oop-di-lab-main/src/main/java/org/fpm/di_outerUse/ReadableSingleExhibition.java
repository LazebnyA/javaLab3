package org.fpm.di_outerUse;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ReadableSingleExhibition {
    private final ReadableA readableA;
    
    @Inject
    public ReadableSingleExhibition(ReadableA readableA) {
        this.readableA = readableA;
    }

    public ReadableA getReadable() { return readableA; }

}
