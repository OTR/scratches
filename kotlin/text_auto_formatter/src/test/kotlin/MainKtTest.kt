import app.model.TextData
import app.model.WholeTextData
import app.service.basictextprocessor.BasicTextProcessor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource


/**
 * This test is used ONLY for refactoring purposes.
 * To ensure everything behaves the same as before refactoring.
 */
class MainKtTest {

    @ParameterizedTest
    @CsvFileSource(
        resources = ["/testData1_split_by_dot.csv"],
        numLinesToSkip = 1,
        delimiter = ';'
    )
    fun testBusinessLogic(input: String, expected: String) {
        // when
        val testData: TextData = WholeTextData(text = input)
        val formattedText: TextData = BasicTextProcessor().main(textData = testData)
        val actual: String = formattedText.text
        // then
        assertEquals(expected, actual)
    }
}
