package com.github.otr.practical_tdd_for_java_programmers;

import com.github.otr.practical_tdd_for_java_programmers.domain.Book;
import com.github.otr.practical_tdd_for_java_programmers.domain.ExternalService;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
public class StockManagementTest {

    private static final String VALID_13_ISBN_NUMBER = "9781801816489";
    private static final String VALID_LOCATOR_CODE = "6489" + "D" + "5";

    /**
     * A positive test case that ensures that stock manager makes lookup in
     * a database service and retrieves a valid book
     */
    @Test
    public void testCorrectLocationCodeFromBookFields() {

        // GIVEN
        // Create a stub for a web service
        ExternalService externalWebService = new ExternalService() {
            @Override
            public Book lookup(String isbnNumber) {
                return new Book(
                        "9781801816489",
                        "Davi Vieira",
                        "Designing Hexagonal Architecture with Java"
                );
            }
        };
        // Create a stub for a database service
        ExternalService externalDatabaseService = new ExternalService() {
            @Override
            public Book lookup(String isbnNumber) {
                return null;
            }
        };
        // Set up stock manager
        StockManager stockManager = new StockManager();
        stockManager.setWebService(externalWebService);
        stockManager.setDatabaseService(externalDatabaseService);

        String expected = VALID_LOCATOR_CODE;
        String input = VALID_13_ISBN_NUMBER;

        // WHEN
        String actual = stockManager.getLocatorCode(input);

        // THEN
        assertThat(expected, equalTo(actual));
    }

    /**
     * The same as the previous test case but using Mockito stubs
     */
    @Test
    public void testCorrectLocationCodeFromBookFieldsWithMockito() {

        // GIVEN
        String input = VALID_13_ISBN_NUMBER;
        String expected = VALID_LOCATOR_CODE;

        StockManager mockStockManager = mock(StockManager.class);
        when(mockStockManager.getLocatorCode(input)).thenReturn(expected);

        // WHEN
        String actual = mockStockManager.getLocatorCode(input);

        // THEN
        assertThat(expected, equalTo(actual));
    }

    /**
     * A positive test case ensures that database service was called only once
     * and web service was never called
     */
    @Test
    public void testDatabaseIsUsedIfDataIsPresent() {

        // GIVEN
        // Creates a dummy class that implements given interface
        ExternalService mockedDatabaseService = mock(ExternalService.class);
        ExternalService mockedWebService = mock(ExternalService.class);

        String input = VALID_13_ISBN_NUMBER;

        Book someFakeBook = new Book(input, "Abc", "Abc");

        when(mockedDatabaseService.lookup(input)).thenReturn(someFakeBook);

        StockManager stockManager = new StockManager();
        stockManager.setWebService(mockedWebService);
        stockManager.setDatabaseService(mockedDatabaseService);

        // WHEN
        String actual = stockManager.getLocatorCode(input);

        // THEN
        verify(mockedDatabaseService, times(1)).lookup(input);
        verify(mockedWebService, never()).lookup(anyString());

    }

    /**
     * A positive test case ensures that database service was called only once
     * and web service was called once, because database didn't return any value
     */
    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {

        // GIVEN
        // Creates a dummy class that implements given interface
        ExternalService mockedDatabaseService = mock(ExternalService.class);
        ExternalService mockedWebService = mock(ExternalService.class);

        String input = VALID_13_ISBN_NUMBER;
        Book someFakeBook = new Book(input, "Abc", "Abc");

        when(mockedDatabaseService.lookup(input)).thenReturn(null);
        when(mockedWebService.lookup(input)).thenReturn(someFakeBook);

        StockManager stockManager = new StockManager();
        stockManager.setWebService(mockedWebService);
        stockManager.setDatabaseService(mockedDatabaseService);

        // WHEN
        String actual = stockManager.getLocatorCode(input);

        // THEN
        verify(mockedDatabaseService).lookup(input); // exactly once
        verify(mockedWebService, times(1)).lookup(input);

    }

}
