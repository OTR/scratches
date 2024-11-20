package app.service.basictextprocessor

import app.model.TextData
import app.model.WholeTextData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

/**
 * Test how well Basic Text Processor works on some prepared text samples
 */
class BasicTextProcessorTest {

    @Disabled("Expected data is not set yet.")
    @ParameterizedTest
    @CsvFileSource(
        resources = ["/test_samples/testSample.csv",],
        numLinesToSkip = 2,
        delimiter = ';'
    )
    fun testMain(input: String, expected: String?) {
        // when
        val testData: TextData = WholeTextData(text = input)
        val formattedText: TextData = BasicTextProcessor().main(textData = testData)
        val actual: String = formattedText.text
        // then
        assertEquals(expected, actual)
    }
}
