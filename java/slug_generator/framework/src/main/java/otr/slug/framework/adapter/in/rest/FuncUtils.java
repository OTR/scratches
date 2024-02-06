package otr.slug.framework.adapter.in.rest;

import java.util.Arrays;
import java.util.function.Function;

public class FuncUtils {

    public static Function<String, String[]> getFirstTwoElements() {
        return s -> Arrays.copyOf(s.split("="), 2);
    }

}
