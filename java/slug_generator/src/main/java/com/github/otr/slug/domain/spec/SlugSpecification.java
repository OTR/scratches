package com.github.otr.slug.domain.spec;

import java.time.Year;

public class SlugSpecification {
    public boolean isCharacterSetValid(String input) {
        // Check if the input contains only Latin letters in lowercase
        return input.matches("[a-z]+");
    }

    public boolean isYearValid(String input) {
        // Check if the input contains a valid year between 2000 and the current year
        try {
            int year = Integer.parseInt(input);
            int currentYear = Year.now().getValue();
            return year >= 2000 && year <= currentYear;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String applyFormattingRules(String input) {
        // Apply specific formatting rules to the input
        String formattedInput = input.toLowerCase().replaceAll("[^a-z0-9]", "_");
        // Collapse multiple underscores into a single underscore
        formattedInput = formattedInput.replaceAll("_+", "_");
        // Trim leading and trailing underscores
        formattedInput = formattedInput.replaceAll("^_|_$", "");
        return formattedInput;
    }

}
