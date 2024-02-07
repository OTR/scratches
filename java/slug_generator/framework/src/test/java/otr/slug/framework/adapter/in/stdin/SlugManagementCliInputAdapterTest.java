package otr.slug.framework.adapter.in.stdin;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import otr.slug.application.port.in.SlugManagementInputPort;

import java.util.Collections;
import java.util.List;


public class SlugManagementCliInputAdapterTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void invokeWithUnsupportedArguments_throwsWithMessage() {
        // GIVEN
        List<String> args = Collections.emptyList();
        String expectedMsg = "Arguments of type: `EmptyList` are not supported";

        // THEN
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(expectedMsg);

        // WHEN
            SlugManagementCliSlugInputAdapter adapter
                = new SlugManagementCliSlugInputAdapter(
                    new SlugManagementInputPort()
            );
            adapter.createSlug(args);
    }

}