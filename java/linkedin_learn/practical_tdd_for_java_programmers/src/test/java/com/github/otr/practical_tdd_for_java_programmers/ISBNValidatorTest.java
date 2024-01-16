package com.github.otr.practical_tdd_for_java_programmers;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 */
public class ISBNValidatorTest {

    // 10 Digit ISBN related
    private final String VALID_10_ISBN_NUMBER = "1801816484";
    private final String VALID_10_ISBN_NUMBER_ENDING_WITH_X = "180323623X";
    private final String INVALID_10_ISBN_NUMBER = "1801816474";
    private final String NINE_DIGIT_ISBN_NUMBER = "123456789";
    private final String NOT_ALL_DIGITS_ISBN_NUMBER = "012345678X";
    private final String NON_NUMERICAL_ISBN_NUMBER = "helloworld";

    // 13 Digit ISBN related
    private final String VALID_13_ISBN_NUMBER = "9781803236230";
    private final String INVALID_13_ISBN_NUMBER = "1234567890123";

    // 10 Digit ISBN related test cases

    /**
     * A positive test case that should pass for a valid ISBN number
     * for an existing book
     */
    @Test
    public void testValid10DigitIsbnNumberShouldReturnTrue() {

        // GIVEN
        ISBNValidator validator = new ISBNValidator();
        String input = VALID_10_ISBN_NUMBER;

        // WHEN
        boolean actual = validator.checkISBN(input);

        // THEN
        assertThat(
                "A valid number should return true",
                actual, is(true)
        );
    }

    /**
     * A positive test case that ensures that passing a valid 10 digit ISBN number
     * endings with X character the method returns `true`
     */
    @Test
    public void testValid10DigitIsbnNumberEndingWithXShouldReturnTrue() {

        // GIVEN
        ISBNValidator validator = new ISBNValidator();
        String input = VALID_10_ISBN_NUMBER_ENDING_WITH_X;

        // WHEN
        boolean actual = validator.checkISBN(input);

        // THEN
        assertThat(actual, is(true));
    }

    /**
     * A negative test case that ensures that for an invalid ISBN number
     * the method returns `false`
     */
    @Test
    public void testInvalid10DigitIsbnNumberShouldReturnFalse() {

        // GIVEN
        ISBNValidator validator = new ISBNValidator();
        String input = INVALID_10_ISBN_NUMBER;

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
    @Test(expected = IsbnNumberException.IsNotTenOrThirteenDigitsLong.class)
    public void testNineDigitIsbnNumberIsNotAllowed() {

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
    @Test
    public void testNotAllDigitsISBNNumberIsNotAllowed() {

        // GIVEN
        ISBNValidator validator = new ISBNValidator();
        String input = NOT_ALL_DIGITS_ISBN_NUMBER;

        // WHEN
        boolean actual = validator.checkISBN(input);

        // THEN
        // an Exception should be thrown
        assertThat(actual, is(false));

    }

    // 13 Digits ISBN related test cases

    /**
     * A positive test case ensures that for a valid 13 digit ISBN number
     * the method returns `true`
     */
    @Test
    public void testValid13DigitIsbnNumberShouldReturnTrue() {

        // GIVEN
        ISBNValidator validator = new ISBNValidator();
        String input = VALID_13_ISBN_NUMBER;

        // WHEN
        boolean actual = validator.checkISBN(input);

        // THEN
        assertThat(actual, is(true));
    }

    /**
     * A negative test case ensures that for an invalid 13 digit ISBN number
     * the method returns `false`
     */
    @Test
    public void testInvalid13DigitIsbnNumberShouldReturnTrue() {

        // GIVEN
        ISBNValidator validator = new ISBNValidator();
        String input = INVALID_13_ISBN_NUMBER;

        // WHEN
        boolean actual = validator.checkISBN(input);

        // THEN
        assertThat(actual, is(false));
    }

}
