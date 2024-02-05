package otr.slug.application.port.in;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import otr.slug.domain.vo.RawInput;
import otr.slug.domain.vo.Slug;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

public class SlugManagementInputPortTest {

    @Test
    public void provideNullRawInput_getValidSlug() {
        // GIVEN
        RawInput input = null;
        String expectedMsg = "RawInput shouldn't be NULL";

        // WHEN
        ThrowingRunnable targetMethod = () -> {
            SlugManagementInputPort inputPort = new SlugManagementInputPort();
            inputPort.createSlug(input);
        };

        // THEN
        assertThrows(
            expectedMsg,
            NullPointerException.class,
            targetMethod
        );
    }

    @Test
    public void provideValidRawInput_getValidSlug() {
        // GIVEN
        RawInput input = new RawInput(TestData.UNPARSED_USER_INPUT);
        Slug expected = new Slug("asdasdw_qwdqw_2020_year_the_rd_edition");

        // WHEN
        SlugManagementInputPort inputPort = new SlugManagementInputPort();
        Slug actual = inputPort.createSlug(input);

        // THEN
        assertThat(actual, equalTo(expected));
    }

}