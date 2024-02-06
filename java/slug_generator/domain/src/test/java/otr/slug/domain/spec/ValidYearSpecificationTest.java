package otr.slug.domain.spec;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import otr.slug.domain.spec.shared.Specification;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ValidYearSpecificationTest {

    @Test
    public void provideGreaterThanInteger_notThrowsNumberFormatException() {
        // GIVEN
        String input = "9999999999awef2023 o352o5 3124v32c2&*@T^*!E#C!@C;masd";
        Specification<String> spec = new ValidYearSpecification();

        // WHEN && THEN
        assertFalse(spec.isSatisfiedBy(input));
    }

    @Test
    public void takeWhileRemovesInvalidInteger_nothingParsed() {
        List<Integer> result = Stream.of("9999999999")
            .takeWhile(i -> {
                try {
                    long parsedLong = Long.parseLong(i);
                    return parsedLong <= Integer.MAX_VALUE;
                } catch (NumberFormatException e) {
                    return false;
                }
            })
            .map(Integer::parseInt)
            .toList();

        assertTrue(result.isEmpty());
    }

    @Test
    public void numberExceedsMaxInteger_NumberFormatExceptionThrown() {
        ThrowingRunnable targetMethod = () -> {
            Stream.of("99999999999")
                .map(Integer::parseInt)
                .toList();
        };

        assertThrows(
            NumberFormatException.class,
            targetMethod
        );
    }

    /*
        An implementation may choose to not execute the stream pipeline
        (either sequentially or in parallel)
        if it is capable of computing the count directly
        from the stream source. In such cases no source elements
        will be traversed and no intermediate operations will be evaluated.
        Behavioral parameters with side-effects,
        which are strongly discouraged except for harmless cases
        such as debugging, may be affected.

        The number of elements covered by the stream source is known
        and the intermediate operation, peek, does not inject into
        or remove elements from the stream
        (as may be the case for flatMap or filter operations).
        Thus the count is 4 and there is no need to execute the pipeline and,
        as a side-effect, print out the elements.
     */
    @Test
    public void countSkipsStreamExecution_noExceptionThrown() {
        long result = Stream.of("99999999999")
            .mapToLong(Integer::parseInt)
            .count();
        assertEquals(1L, result);
    }

}