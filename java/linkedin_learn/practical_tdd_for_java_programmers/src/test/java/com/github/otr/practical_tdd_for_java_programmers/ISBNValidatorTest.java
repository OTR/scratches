package com.github.otr.practical_tdd_for_java_programmers;

import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 */
public class ISBNValidatorTest {

    private final String VALID_ISBN_NUMBER = "1801816484";
    private final String INVALID_ISBN_NUMBER = "1801816474";
    private final String NINE_DIGIT_ISBN_NUMBER = "123456789";
    private final String NOT_ALL_DIGITS_ISBN_NUMBER = "123456789X";

    /**
     * A positive test case that should pass for a valid ISBN number
     * for an existing book
     */
    @Test
    public void testAValidISBNNumberShouldReturnTrue() {

        // GIVEN
        ISBNValidator validator = new ISBNValidator();
        String input = VALID_ISBN_NUMBER;

        // WHEN
        boolean actual = validator.checkISBN(input);

        // THEN
        assertThat(
                "A valid number should return true",
                actual, is(true)
        );
    }

    /**
     * A negative test case that ensures that for an invalid ISBN number
     * the method returns `false`
     */
    @Test
    public void testAnInvalidISBNNumberShouldReturnFalse() {

        // GIVEN
        ISBNValidator validator = new ISBNValidator();
        String input = INVALID_ISBN_NUMBER;

        // WHEN
        boolean actual = validator.checkISBN(input);

        // THEN
        assertThat(
                "an invalid ISBN number should return false",
                actual, is(false)
        );
    }

    /**
     * A negative test case that ensures that passing a 9-digit ISBN number
     * the method returns `false`
     */
    @Test(expected = IsbnNumberException.IsNotTenDigitsLong.class)
    public void testNineDigitISBNNumberIsNotAllowed() {

        // GIVEN
        ISBNValidator validator = new ISBNValidator();
        String input = NINE_DIGIT_ISBN_NUMBER;

        // WHEN
        boolean actual = validator.checkISBN(input);

        // THEN
        // An exception should be thrown
    }

    /**
     * A negative test case that ensures that if ISBN Number containing non-digit
     * number is provided then the methods throws custom exception
     */
    @Test(expected = IsbnNumberException.IsNotAllDigits.class)
    public void testNotAllDigitsISBNNumberIsNotAllowed() {

        // GIVEN
        ISBNValidator validator = new ISBNValidator();
        String input = NOT_ALL_DIGITS_ISBN_NUMBER;

        // WHEN
        boolean actual = validator.checkISBN(input);

        // THEN
        // an Exception should be thrown
    }

}
