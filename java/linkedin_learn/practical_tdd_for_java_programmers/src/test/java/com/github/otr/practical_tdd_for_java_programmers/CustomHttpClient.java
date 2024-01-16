package com.github.otr.practical_tdd_for_java_programmers;

import java.io.IOException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @see
 * <a href="https://naveen-metta.medium.com/java-11-http-client-api-unleashing-the-power-of-modern-web-communications-d7c16753d982">
 * Meduim tutorial
 * </a>
 */
public class CustomHttpClient {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

    public String readFromUrl(String url) {
        HttpRequest req = HttpRequest
                .newBuilder(java.net.URI.create(url))
                .build();

        try {
            HttpResponse<String> resp = client.send(req, handler);
            return resp.body();
        } catch (IOException | InterruptedException ignored) {
            // Ignored
        }

        return "";
    }

}
