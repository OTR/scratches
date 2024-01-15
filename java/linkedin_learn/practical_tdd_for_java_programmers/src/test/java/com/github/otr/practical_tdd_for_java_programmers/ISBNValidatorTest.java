package com.github.otr.practical_tdd_for_java_programmers;

import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 */
public class ISBNValidatorTest {

    private final int aValidISBN10Number = 1801816484;
    private final int anInvalidISBN10Number = 1801816474;

    /**
     * A positive test that should pass for a valid ISBN number
     * for an existing book
     */
    @Test
    public void testAValidISBNNumberShouldReturnTrue() {

        // GIVEN
        ISBNValidator validator = new ISBNValidator();
        int input = aValidISBN10Number;

        // WHEN
        boolean actual = validator.checkISBN(input);

        // THEN
        assertThat(
                "A valid number should return true",
                actual, is(true)
        );
    }

    /**
     * A negative test that ensures that for an invalid ISBN number
     * the method returns false
     */
    @Test
    public void testAnInvalidISBNNumberShouldReturnFalse() {

        // GIVEN
        ISBNValidator validator = new ISBNValidator();
        int input = anInvalidISBN10Number;

        // WHEN
        boolean actual = validator.checkISBN(input);

        // THEN
        assertThat(
                "an invalid ISBN number should return false",
                actual, is(false)
        );
    }

}
