package otr.slug.domain.vo;

import org.junit.Test;

import otr.slug.domain.exception.GenericSpecificationException;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class SlugTest {

    @Test
    public void provideNonAlpaDecimal_throwsException() {
        // GIVEN
        String input = "somethings";

        // WHEN && THEN
        assertThrows(
            GenericSpecificationException.class,
            () -> new Slug(input)
        );
    }

    @Test
    public void provideNotAYearNumber_throwsException() {
        assertTrue("something s".matches("[a-z0-9_]+"));
    }

    public static void main(String[] args) {
        new Slug("something");
    }

}