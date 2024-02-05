package otr.slug.framework.adapter.in.stdin;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
            SlugManagementCliInputAdapter adapter
                = new SlugManagementCliInputAdapter();
            adapter.invoke(args);
    }

}