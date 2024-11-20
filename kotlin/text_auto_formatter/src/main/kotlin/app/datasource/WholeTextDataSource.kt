package app.datasource

import app.model.TextData
import app.model.WholeTextData

/**
 *
 */
class WholeTextDataSource: TextDataSource {

    /**
     * Load unformatted text as a big single string from standard system input
     */
    fun loadTextFromSTDIN(): TextData {
        val stdInLine: String? = readlnOrNull()
        val wholeText: String = if (stdInLine != null) stdInLine.toString() else ""
        val wholeTextData: TextData = WholeTextData(text = wholeText)

        return wholeTextData
    }

    /**
     * Print out formatted text into standard system output
     */
    fun saveTextToSTDOUT(textData: TextData) {
        println(textData.text)
    }

    override fun loadText(): TextData {
        return loadTextFromSTDIN()
    }

    override fun saveText(dataToSave: TextData) {
        saveTextToSTDOUT(textData = dataToSave)
    }
}

/**
 * Every class who wants to load into program or save anywhere (on disk, on database, etc) text data
 * should implement this interface
 */
interface TextDataSource {

    fun loadText(): TextData

    fun saveText(dataToSave: TextData)
}