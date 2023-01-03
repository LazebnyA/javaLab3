package org.fpm.di_outerUse;

import javax.inject.Inject;

public class ReadablePresenter {
    private final ReadableSingleExhibition readableSE;

    @Inject
    public ReadablePresenter(ReadableSingleExhibition readableSE) {
        this.readableSE = readableSE;
    }

    public void toPresent() {
        System.out.println("Presenting ...");
        readableSE.getReadable().read();
    }

    public void toPresentWithDictionary(Dictionary dictionary) {
        readableSE.getReadable().readWithDictionary(dictionary);
    }

    public ReadableSingleExhibition getReadableSE() { return this.readableSE;   }
}
