/**
 * This is an educational project in Kotlin. This is a program that takes some
 * piece of a text and makes some predefined string formatting to it. Such as
 * trims extra spaces, puts a dot at the end of a sentence, puts a tab character
 * before a paragraph and so on. Makes some addition measurement such as counts
 * characters, words, sentences, paragraphs.
 *
 * 02.02.2023
 * version 0.0.0
 */
import app.datasource.TextDataSource
import app.datasource.WholeTextDataSource
import app.model.TextData
import app.service.basictextprocessor.BasicTextProcessor


fun main(args: Array<String>) {
    // Input data
    val textLoader: TextDataSource = WholeTextDataSource()
    val unformattedText: TextData = textLoader.loadText()

    // Business logic
    val formattedText: TextData = BasicTextProcessor().main(textData = unformattedText)

    // Output data
    textLoader.saveText(formattedText)
}
