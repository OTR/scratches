package otr.slug.domain.vo;

import org.junit.Test;

import otr.slug.domain.exception.GenericSpecificationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SlugTest {

    @Test
    public void provideNonAlpaDecimal_throwsException() {
        // GIVEN
        String input = "somethings ";

        // WHEN && THEN
        assertThrows(
            GenericSpecificationException.class,
            () -> new Slug(input)
        );
    }

    @Test
    public void provideNotAYearNumberUpperLimit_throwsException() {
        // GIVEN
        String input = "somethings_1234567890";

        // WHEN && THEN
        assertThrows(
            GenericSpecificationException.class,
            () -> new Slug(input)
        );
    }

    @Test
    public void provideNotAYearNumberLowerLimit_throwsException() {
        // GIVEN
        String input = "somethings_1";

        // WHEN && THEN
        assertThrows(
            GenericSpecificationException.class,
            () -> new Slug(input)
        );
    }

    @Test
    public void provideUpperCaseLatter_throwsException() {
        // GIVEN
        String input = "Best_book_in_the_word_published_2010_year";

        // WHEN && THEN
        assertThrows(
            GenericSpecificationException.class,
            () -> new Slug(input)
        );
    }

    @Test
    public void provideAValidPublishingYear_convertsToSlugAsExpected() {
        // GIVEN
        String input = "best_book_in_the_word_published_2010_year";

        // WHEN && THEN
        assertEquals(input, new Slug(input).value());
    }

}