package com.github.otr.practical_tdd_for_java_programmers;

/**
 *
 */
public class ISBNValidator {

    public boolean checkISBN(String isbnNumber) {
        // Check for String length
        if (isbnNumber.length() != 10) {
            throw new IsbnNumberException.IsNotTenDigitsLong();
        }

        // Check contains only digits
        boolean onlyDigits = isbnNumber
                .chars()
                .mapToObj(c -> (char) c)
                .allMatch(Character::isDigit);
        if (!onlyDigits) {
            throw new IsbnNumberException.IsNotAllDigits();
        }

        int total = 0;

        for (int i = 0; i < 10; i++) {
            total += Character.getNumericValue(isbnNumber.charAt(i)) * (10 - i);
            total += isbnNumber.charAt(i) * (10 - i);
        }

        if (total % 11 == 0) {
            return true;
        } else {
            return false;
        }
    }

}
