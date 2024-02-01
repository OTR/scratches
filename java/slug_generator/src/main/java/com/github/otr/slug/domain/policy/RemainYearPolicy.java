package com.github.otr.slug.domain.policy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class RemainYearPolicy implements FilterStringPolicy {

    @Override
    public String filter(String input) {
        String anyDigits = "\\d+";
        String yearDigits = "20\\d\\d";
        Pattern anyDigitsPattern = Pattern.compile(anyDigits);
        Pattern yearDigitsPattern = Pattern.compile(yearDigits);
        Matcher matcher = anyDigitsPattern.matcher(input);
//        return matcher.replaceAll(Matcher.quoteReplacement("XXX"));
        return matcher.replaceAll(matchResult -> {
            String found = matchResult.group();
            Matcher subMatcher = yearDigitsPattern.matcher(found);
            if (subMatcher.find()) {
                return subMatcher.group();
            } else {
                return "";
            }
        });
    }

}
