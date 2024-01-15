package com.github.otr.practical_tdd_for_java_programmers;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 *
 */
public class ISBNValidatorTest {

    private final int aValidISBN10Number = 1801816484;

    @Test
    public void testAValidISBNOfExistingBook() {

        // GIVEN
        ISBNValidator validator = new ISBNValidator();

        // WHEN
        boolean isValid = validator.checkISBN(aValidISBN10Number);

        // THEN
        assertTrue(isValid);
    }

}
