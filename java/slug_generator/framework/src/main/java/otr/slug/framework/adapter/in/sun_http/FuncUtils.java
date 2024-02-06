package otr.slug.framework.adapter.in.sun_http;

import java.util.Arrays;
import java.util.function.Function;

class FuncUtils {

    static Function<String, String[]> getFirstTwoElements() {
        return s -> Arrays.copyOf(s.split("="), 2);
    }

}
