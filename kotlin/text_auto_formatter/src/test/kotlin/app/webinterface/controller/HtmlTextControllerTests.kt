package app.webinterface.controller

import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

/**
 * Test cases for Html Text Controller:
 *
 * GET /text/{id} | Negative: return valid HTML page with status NOT FOUND,
 *                | encoding: UTF-8 and with HTML tag <h3> inside <body> with
 *                | the text describing `Text Entity with ID #{id} not found.`
 *
 * GET /text/{id} | Negative: ID value cannot be converted to type Int
 *
 * GET /text/{id} | Positive: return an HTML form with Text Entity properties as input fields
 *
 * GET /text/all  | Positive: return HTML page with <un><li> unordered list of Text Entities
 *
 * POST /text/{id} | Positive: will create Text Entity if there is none with such ID
 *                  or update Text Entity if there is one with the ID
 *
 * POST /text/{id} | Negative: return status code 422 if any of the form fields is not valid
 *
 * DELETE /text/{id} | Positive: will delete Text Entity with the ID from database
 *
 * DELETE /text/{id} | Negative: returns Not Found by not existing ID
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class HtmlTextControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    companion object {
        private const val baseTextUrl: String = "/text"

    }

    // GIVEN
    private val textId: Int = 1
    private val unformattedText: String = "hello kotlin world"
    private val textId2: Int = 2
    private val unformattedText2: String = "Roses are red"

    @Test
    @DirtiesContext
    @DisplayName("GET $baseTextUrl/{id} | Negative: returns Not Found with not existing ID")
    fun testNegativeGetWithNotExistingId() {
        // WHEN
        val response = mockMvc.get("$baseTextUrl/$textId")
            // THEN
            .andDo { print() }
            .andExpect { status { isNotFound() } }
            .andExpect { content { contentTypeCompatibleWith(MediaType.TEXT_HTML) } }
            .andExpect { content { encoding("UTF-8") } }
            .andExpect { content { string(containsString(
                "<h3>Text Entity with ID: #$textId not found.</h3>"
            )) } }
    }

    @Test
    @DirtiesContext
    @DisplayName("POST $baseTextUrl/{id} | Positive: will create or update Text Entity")
    fun testPositiveCreateOrUpdateTextEntityById() {
        // TODO: divide into two tests, one for creation and other for modification
        // GIVEN
        val requestParams: MultiValueMap<String, String> = LinkedMultiValueMap()
        requestParams.add("id", textId.toString())
        requestParams.add("unformattedText", unformattedText)
        // WHEN
        val response = mockMvc.perform(
            post("$baseTextUrl/$textId")
                .params(requestParams)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
            //THEN
            .andDo(print())
            .andExpect(status().isAccepted)
            .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
            .andExpect(content().encoding("UTF-8"))
    }

    @Test
    @DirtiesContext
    @DisplayName("GET $baseTextUrl/all | Positive: return HTML page with unordered list of Text Entities")
    fun testPositiveReturnAllTextEntities() {
        // GIVEN
        mockMvc.perform(post("$baseTextUrl/$textId")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "$textId")
            .param("unformattedText", unformattedText)
        )

        mockMvc.perform(post("$baseTextUrl/$textId2")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "$textId2")
            .param("unformattedText", unformattedText2)
        )
        // WHEN
        mockMvc.get("$baseTextUrl/all")
            // THEN
            .andDo { print() }
            .andExpect { status { isOk() } }
            .andExpect { content { contentTypeCompatibleWith(MediaType.TEXT_HTML) } }
            .andExpect { content { encoding("UTF-8") } }
            .andExpect { content { string(containsString("$unformattedText")) } }
            .andExpect { content { string(containsString("$unformattedText2")) } }
    }

    @Disabled("Not implemented yet.")
    @Test
    @DirtiesContext
    @DisplayName("GET $baseTextUrl/{id} | Positive: returns Text Entity by existing ID")
    fun testPositiveReturnsTextEntityId() {
        TODO("Not implemented yet.")
    }

    @Disabled("Not implemented yet.")
    @Test
    @DirtiesContext
    @DisplayName("GET $baseTextUrl/{id} | Negative: ID value cannot be converted to type Int")
    fun testNegativeIdIsNotInt() {
        TODO("Controller should return status code 422: Unprocessable Entity")
    }

    @Disabled("Not implemented yet.")
    @Test
    @DirtiesContext
    @DisplayName("POST $baseTextUrl/{id} | Negative: any of the form fields is not valid")
    fun testNegativeFormFieldsIsNotValid() {
        TODO("Controller should return status code 422: Unprocessable Entity")
    }

    @Disabled("Not implemented yet.")
    @Test
    @DirtiesContext
    @DisplayName("DELETE $baseTextUrl/{id} | Negative: returns Not Found with not existing ID")
    fun testNegativeDeleteWithNotExistingId() {
        TODO("Controller should return status code 404: Not Found")
    }
}
