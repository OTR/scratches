package app.service

import app.model.TextData


/**
 * Every business logic that takes some text data and does some formatting on it
 * should implement this interface
 */
interface TextProcessor {

    // What character use when compiling sentence from words
    val WORD_SEPARATOR: String
    // What character use when splitting whole text into sentences
    // LineEndedByDot
    // TODO: Type should be Pattern, vararg String, vararg Char, Regex
    val SENTENCE_SEPARATOR: Regex

    /**
     * An entry point for text processing
     * TODO: `textData` could be an instance of Any class which implements property `text` type String
     */
    fun main (textData: TextData): TextData
}
