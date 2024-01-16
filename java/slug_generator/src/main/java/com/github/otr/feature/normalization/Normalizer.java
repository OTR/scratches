package com.github.otr.feature.normalization;

/*

 */
public class Normalizer {

    private static String removeExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex != -1) {
            fileName = fileName.substring(0, lastIndex);
        }
        return fileName;
    }

    public String normalize(String input) {
        // 0. Remove extension
        input = removeExtension(input);
        // 1. To lower case
        input = input.toLowerCase();
        // 2. Replace all the spaces with underscore
        input = input.replaceAll("[^a-zA-Z_\\- ]", "");
        input = input.replaceAll(" ", "_");
        input = input.replaceAll("-", "_");
        // 3.
        input = input.replaceAll("_+", "_");
        input = input.replaceAll("_", " ");
        input = input.strip();
        input = input.replaceAll(" ", "_");
        //
        return input;
    }

}
