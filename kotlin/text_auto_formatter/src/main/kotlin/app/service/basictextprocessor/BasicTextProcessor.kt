/**
 *
 */
package app.service.basictextprocessor

import app.model.TextData
import app.model.WholeTextData
import app.service.TextProcessor


/**
 *
 */
class BasicTextProcessor: TextProcessor {

    override val WORD_SEPARATOR: String // What character use when compiling sentence from words
        get() = " "
    override val SENTENCE_SEPARATOR: Regex // What character use when splitting whole text into sentences
        get() = Regex("(?<=\\.)")

    /**
     * Splits a whole piece of a text into separated sentences using dot character
     * as a delimiter.
     * Pattern, vararg String, vararg Char, Regex
     */
    fun splitByDot(wholeText: String): List<String> {
        return wholeText.split(SENTENCE_SEPARATOR)
    }

    /**
     * Get rid of empty strings and trim extra white spaces at the beginning.
     */
    fun trimWhiteSpaces(separatedSentences: List<String>): List<String> {
        val trimmedSentences: MutableList<String> = emptyList<String>().toMutableList()

        separatedSentences.forEachIndexed { index, sentence ->
            if (sentence.isNotBlank()) {
                if (sentence.startsWith(" ")) {
                    trimmedSentences.add(sentence.trimStart())
                } else {
                    trimmedSentences.add(sentence)
                }
            }
        }

        return trimmedSentences.toList()
    }

    /**
     *
     */
    override fun main(textData: TextData): TextData {
        val wholeText: String = textData.text
        val separatedSentences: List<String> = splitByDot(wholeText)
        val trimmedSeparatedSentences: List<String> = trimWhiteSpaces(separatedSentences)
        val trimmedSingleLine: String = trimmedSeparatedSentences.joinToString("\n")
        val formattedData: TextData = WholeTextData(text = trimmedSingleLine)

        return formattedData
    }
}

