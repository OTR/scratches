package com.github.otr.practical_tdd_for_java_programmers;

import com.github.otr.practical_tdd_for_java_programmers.domain.Book;
import com.github.otr.practical_tdd_for_java_programmers.domain.ExternalService;

/**
 *
 */
public class StockManager {

    private ExternalService externalWebService;
    private ExternalService externalDatabaseService;

    public void setWebService(ExternalService externalWebService) {
        this.externalWebService = externalWebService;
    }

    public void setDatabaseService(ExternalService externalDatabaseService) {
        this.externalDatabaseService = externalDatabaseService;
    }

    public String getLocatorCode(String input) {
        Book book = externalDatabaseService.lookup(input);
        if (book == null) book = externalWebService.lookup(input);
        //
        String locator = "";
        String isbnNumber = book.getIsbnNumber();
        locator += book.getIsbnNumber().substring(isbnNumber.length() - 4);
        locator += book.getAuthor().charAt(0);
        locator += book.getTitle().split(" ").length;

        return locator;
    }

}
