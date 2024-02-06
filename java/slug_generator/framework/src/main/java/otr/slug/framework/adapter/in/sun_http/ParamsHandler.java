package otr.slug.framework.adapter.in.sun_http;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

class ParamsHandler {

    private final static String USER_INPUT_PARAMS_KEY = "user_input";
    private static final String DEFAULT_USER_INPUT = "";
    private static final String URL_QUERY_SEPARATOR = "&";

    static Map<String, String> extractUserInputFromQuery(String query) {
        Map<String, List<String>> requestParams = extractParamsFrom(query);
        String userInput = getUserInputOrDefault(requestParams);

        return Map.of(USER_INPUT_PARAMS_KEY, userInput);
    }

    private static String getUserInputOrDefault(
        Map<String, List<String>> requestParams
    ) {
        String userInput = requestParams.getOrDefault(
                USER_INPUT_PARAMS_KEY,
                List.of(DEFAULT_USER_INPUT)
            )
            .stream()
            .findFirst()
            .orElse(DEFAULT_USER_INPUT);

        return userInput;
    }


    private static Map<String, List<String>> extractParamsFrom(String query) {
        Map<String, List<String>> requestParams = Pattern
            .compile(URL_QUERY_SEPARATOR)
            .splitAsStream(query)
            .map(FuncUtils.getFirstTwoElements())
            .collect(MapStringCollector.collect());

        return requestParams;
    }

    static String retrieveUserInputFrom(Map<String, String> params) {
        String userInput = params.get(USER_INPUT_PARAMS_KEY);

        return userInput;
    }

}
