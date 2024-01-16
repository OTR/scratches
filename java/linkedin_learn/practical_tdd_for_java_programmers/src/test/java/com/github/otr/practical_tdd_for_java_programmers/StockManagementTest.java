package com.github.otr.practical_tdd_for_java_programmers;

import com.github.otr.practical_tdd_for_java_programmers.domain.Book;
import com.github.otr.practical_tdd_for_java_programmers.domain.ExternalService;
import org.junit.Test;

/**
 *
 */
public class StockManagementTest {

    private static final String CORRECT_BOOK_LOCATION = "";
    private static final String CORRECT_13_ISBN_NUMBER = "9781801816489";

    private final ExternalService externalWebService = new ExternalService() {
        @Override
        public Book lookup(String isbnNumber) {
            return null;
        }
    };
    private final ExternalService externalDatabaseService = new ExternalService() {
        @Override
        public Book lookup(String isbnNumber) {
            return null;
        }
    };

    /**
     *
     */
    @Test
    public void testCorrectBookLocationFromBookFields() {

        // GIVEN
        String input = CORRECT_13_ISBN_NUMBER;

        // WHEN
        Book book = externalDatabaseService.lookup(input);
        if (book == null) book = externalWebService.lookup(input);

        // THEN
        
    }

}
