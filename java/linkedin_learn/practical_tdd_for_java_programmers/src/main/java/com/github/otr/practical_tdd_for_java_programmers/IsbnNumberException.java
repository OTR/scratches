package com.github.otr.practical_tdd_for_java_programmers;

/**
 *
 */
public class IsbnNumberException extends RuntimeException {

    static class IsNotTenDigitsLong extends IsbnNumberException {

        private static final String message = "an ISBN number must be 10 digits long";

        /**
         * Throws an exception when ISBN number length is not equal to 10 digits
         */
        public IsNotTenDigitsLong() {
            super(message);
        }

    }

    /**
     *
     */
    static class IsNotAllDigits extends IsbnNumberException {

        private static final String message = "an ISBN number should only contain digit characters";

        /**
         * Throws an exception when ISBN number contains any non-digit character
         */
        public IsNotAllDigits() {
            super(message);
        }

    }

    private IsbnNumberException(String message) {
        super(message);
    }

}
