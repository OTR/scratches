package app.model

/**
 * Data class to keep unformatted text as a big single line.
 */
data class WholeTextData(override val text: String): TextData

/**
 * Any data class to work with text should implement this interface
 */
interface TextData {
    val text: String
}
