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

    /**
     * FIXME: SRP violated, the method gets data from external services
     *  and calculates  check sum number at once. Too much responsibility
     *  for a single method
     */
    public String getLocatorCode(String input) {
        // Retrieve a book from external services
        Book book = externalDatabaseService.lookup(input);
        if (book == null) book = externalWebService.lookup(input);
        // Calculate Locator Code
        String locator = "";
        String isbnNumber = book.getIsbnNumber();
        locator += book.getIsbnNumber().substring(isbnNumber.length() - 4);
        locator += book.getAuthor().charAt(0);
        locator += book.getTitle().split(" ").length;

        return locator;
    }

}
