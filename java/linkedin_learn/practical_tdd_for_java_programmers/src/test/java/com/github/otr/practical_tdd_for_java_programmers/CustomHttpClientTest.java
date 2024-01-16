package com.github.otr.practical_tdd_for_java_programmers;

import org.junit.Test;

import org.mockito.Spy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 *
 */
public class CustomHttpClientTest {

    private static final String MEDIUM_URL = "https://naveen-metta.medium.com/" +
            "java-11-http-client-api-unleashing-the-power-of-modern-web-communications-d7c16753d982";
    private static final String LOOKUP_QUERY = "Communications";
    private static final String FAKE_HTML_BODY = "<html><body>Our Communications went wrong</body></html>";
    @Spy
    private static CustomHttpClient spyOnCustomClient;

    /**
     *
     */
    @Test
    public void testCustomClient() {

        // GIVEN
        spyOnCustomClient = new CustomHttpClient();

        // WHEN
        String resp = spyOnCustomClient.readFromUrl(MEDIUM_URL);

        // THEN
        System.out.println(resp);
        assertThat(resp, containsString(LOOKUP_QUERY));
    }

    /**
     * Run the same test, but this time we don't do any HTTP Requests
     * we just substitute it's HTTP Client's method with predefined response
     * using `spying` technique
     */
    @Test
    public void testSpyOnCustomClient() {

        // GIVEN
        spyOnCustomClient = spy(new CustomHttpClient());
        doReturn(FAKE_HTML_BODY).when(spyOnCustomClient).readFromUrl(anyString());

        // WHEN
        String resp = spyOnCustomClient.readFromUrl(MEDIUM_URL);

        // THEN
        System.out.println(resp);
        assertThat(resp, containsString(LOOKUP_QUERY));
    }

}
