package otr.slug.domain.service;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import otr.slug.domain.TestData;
import otr.slug.domain.policy.FilterStringPolicy;
import otr.slug.domain.vo.RawInput;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class SlugServiceTest {

    @Test
    public void provideNullPolices_throwWithMessage() {
        // GIVEN
        String str = TestData.UNPARSED_USER_INPUT;
        RawInput input = new RawInput(str);
        List<FilterStringPolicy> nullPolicies = null;
        String expectedMsg = "Policies shouldn't be NULL";

        // WHEN
        ThrowingRunnable targetMethod = () ->
            SlugService.createSlug(input, nullPolicies);

        // THEN
        assertThrows(
            expectedMsg,
            NullPointerException.class,
            targetMethod
        );
    }

    @Test
    public void provideNullValue_throwWithMessage() {
        // GIVEN
        String str = null;
        List<FilterStringPolicy> nullPolicies = Collections.emptyList();
        String expectedMsg = "Value shouldn't be NULL";

        // WHEN
        ThrowingRunnable targetMethod = () -> {
            RawInput input = new RawInput(str);
            SlugService.createSlug(input, nullPolicies);
        };

        // THEN
        assertThrows(
            expectedMsg,
            NullPointerException.class,
            targetMethod
        );
    }

    @Test
    public void provideNullRawInput_throwWithMessage() {
        // GIVEN
        RawInput input = null;
        List<FilterStringPolicy> nullPolicies = Collections.emptyList();
        String expectedMsg = "RawInput shouldn't be NULL";

        // WHEN
        ThrowingRunnable targetMethod = () ->
            SlugService.createSlug(input, nullPolicies);

        // THEN
        assertThrows(
            expectedMsg,
            NullPointerException.class,
            targetMethod
        );
    }

}