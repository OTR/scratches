package com.github.otr.practical_tdd_for_java_programmers;

import com.github.otr.practical_tdd_for_java_programmers.domain.Book;
import com.github.otr.practical_tdd_for_java_programmers.domain.ExternalService;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import static org.mockito.Mockito.mock;

/**
 *
 */
public class StockManagementTest {

    private static final String CORRECT_BOOK_LOCATION = "";
    private static final String CORRECT_13_ISBN_NUMBER = "9781801816489";

    private final ExternalService externalWebService = new ExternalService() {
        @Override
        public Book lookup(String isbnNumber) {
            return new Book(
                    "9781801816489",
                    "Davi Vieira",
                    "esigning Hexagonal Architecture with Java"
            );
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
    public void testCorrectLocationCodeFromBookFields() {

        // GIVEN
        StockManager stockManager = new StockManager();
        stockManager.setWebService(externalWebService);
        stockManager.setDatabaseService(externalDatabaseService);

        String expected = "6489" + "D" + "5";
        String input = CORRECT_13_ISBN_NUMBER;

        // WHEN
        String actual = stockManager.getLocatorCode(input);

        // THEN
        assertThat(expected, equalTo(actual));
    }

    /**
     *
     */
    @Test
    public void testDatabaseIsUsedIfDataIsPresent() {

        // GIVEN
        // Creates a dummy class that implements given interface
        ExternalService mockedDatabaseService = mock(ExternalService.class);
        ExternalService mockedWebService = mock(ExternalService.class);

        StockManager stockManager = new StockManager();
        stockManager.setWebService(mockedWebService);
        stockManager.setDatabaseService(mockedDatabaseService);

        String expected = "6489" + "D" + "5";
        String input = CORRECT_13_ISBN_NUMBER;

        // WHEN
        String actual = stockManager.getLocatorCode(input);

        // THEN
        assertThat(expected, equalTo(actual));


    }

    /**
     *
     */
    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {

    }

}
