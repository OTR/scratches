package otr.slug.framework.adapter.in.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class MapStringCollector {

    public static final String ENCODING_SCHEME = "UTF-8";

    public static Collector<String[], ?, Map<String, List<String>>> collect() {
        return groupingBy(
            s -> decodeUrl(s[0]),
            mapping(
                s -> decodeUrl(s[1]),
                toList())
        );
    }

    private static String decodeUrl(final String encoded) {
        try {
            if (encoded != null) {
                return URLDecoder.decode(encoded, ENCODING_SCHEME);
            } else {
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 is a required encoding", e);
        }
    }

}
