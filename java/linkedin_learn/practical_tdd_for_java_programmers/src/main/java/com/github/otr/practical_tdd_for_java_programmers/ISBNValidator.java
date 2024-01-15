package com.github.otr.practical_tdd_for_java_programmers;

/**
 *
 */
public class ISBNValidator {

    private static final int LONG_ISBN_MULTIPLIER = 10;
    private static final int SHORT_ISBN_MULTIPLIER = 11;
    private static final int SHORT_ISBN_LENGTH = 10;
    private static final int LONG_ISBN_LENGTH = 13;

    /**
     *
     */
    public boolean checkISBN(String isbnNumber) {
        // Check for String length
        if (isbnNumber.length() == SHORT_ISBN_LENGTH) {
            return isValidShortIsbnNumber(isbnNumber);
        } else if (isbnNumber.length() == LONG_ISBN_LENGTH) {
            return isValidLongIsbnNumber(isbnNumber);
        } else {
            throw new IsbnNumberException.IsNotTenOrThirteenDigitsLong();
        }

    }

    /**
     * Check contains only digits
     */
    private boolean checkContainsOnlyDigits(String isbnNumber) {
        boolean onlyDigits = isbnNumber
                .chars()
                .mapToObj(c -> (char) c)
                .allMatch(Character::isDigit);

        return onlyDigits;
    }

    /**
     *
     */
    private boolean isValidShortIsbnNumber(String isbnNumber) {
        int total = 0;

        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            char currChar = isbnNumber.charAt(i);
            if (!Character.isDigit(currChar)) {
                if (i == 9 && currChar == 'X') {
                    total += 10;
                } else {
                    throw new IsbnNumberException.IsNotAllDigits();
                }
            }else {
                total += Character.getNumericValue(currChar) * (SHORT_ISBN_LENGTH - i);
            }
        }

        if (total % SHORT_ISBN_MULTIPLIER == 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     *
     */
    private boolean isValidLongIsbnNumber(String isbnNumber) {
        int total = 0;

        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            char currChar = isbnNumber.charAt(i);
            if (i % 2 == 0) {
                total += Character.getNumericValue(currChar);
            } else {
                total += Character.getNumericValue(currChar) * 3;
            }
        }

        if (total % LONG_ISBN_MULTIPLIER == 0) {
            return true;
        } else {
            return false;
        }

    }

}
