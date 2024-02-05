package otr.slug.framework.exception;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnrecognizedOptionHandlerTest {

    private static final String BROKEN_ARGS
        = "Hello bea%$@#@$uT4ti5246--- ---- F$$$ul 235426 33 2004 WorlD --- .pdf";

    @Test
    public void provideBrokenArgs_throwUnrecognizedOptionException() {
        // GIVEN
        String[] input = BROKEN_ARGS.split(" ");

        // WHEN
        CommandLineParser parser = new DefaultParser();

        try {
            parser.parse(new Options(), input);
        } catch (ParseException cause) {
            assertThat(
                cause.getClass(),
                equalTo(UnrecognizedOptionException.class)
            );
        }

    }

    @Test
    public void provideBrokenArgs_handleGracefully() {
        // GIVEN
        String[] input = BROKEN_ARGS.split(" ");
        String[] expected = {
            "Hello", "bea%$@#@$uT4ti5246---", "F$$$ul", "235426", "33",
            "2004", "WorlD", ".pdf"
        };
        CommandLineParser parser = new DefaultParser();

        // WHEN
        try {
            CommandLine parsed = parser.parse(
                new Options(), input
            );
        } catch (ParseException cause) {
            if (cause instanceof UnrecognizedOptionException) {
                var exactCause = (UnrecognizedOptionException) cause;
                String[] actual = UnrecognizedOptionHandler.handle(
                    input,
                    exactCause
                );

                // THEN
                assertThat(actual, is(equalTo(expected)));
            }
        }
    }

    @Test
    public void regExpWithoutAnchors_replacesSubstringsToo() {
        // GIVEN
        String multipleDashes = "-+";
        String input = "bea%$@#@$uT4ti5246---";
        String expected = "bea%$@#@$uT4ti5246";

        // WHEN
        String actual = input.replaceAll(multipleDashes, "");

        // THEN
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void regExpWithAnchors_replacesWholeString() {
        // GIVEN
        String multiDashes = "^-+$";
        String input = "bea%$@#@$uT4ti5246---";
        String expected = "bea%$@#@$uT4ti5246---";
        // AND
        String input2 = "---";
        String expected2 = "";

        // WHEN
        String actual = input.replaceAll(multiDashes, "");
        // AND
        String actual2 = input2.replaceAll(multiDashes, "");

        // THEN
        assertThat(actual, equalTo(expected));
        // AND
        assertThat(actual2, equalTo(expected2));
    }

    @Test
    public void shouldMatchOnlyLeadingDashes() {
        // GIVEN
        String regEx = "-+.*";

        String input1 = "-i"; // true expected
        String input2 = "--i"; // true expected
        String input3 = "-input"; // true expected
        String input4 = "--input"; // true expected
        String input5 = "---input"; // true expected
        String input6 = "---"; // true expected

        String input7 = "input-"; // false expected
        String input8 = "input--"; // false expected
        String input9 = "input-file"; // false expected
        String input10 = "input--file"; // false expected


        // WHEN && THEN
        assertTrue(input1.matches(regEx));
        assertTrue(input2.matches(regEx));
        assertTrue(input3.matches(regEx));
        assertTrue(input4.matches(regEx));
        assertTrue(input5.matches(regEx));
        assertTrue(input6.matches(regEx));
        // AND
        assertFalse(input7.matches(regEx));
        assertFalse(input8.matches(regEx));
        assertFalse(input9.matches(regEx));
        assertFalse(input10.matches(regEx));

    }

}