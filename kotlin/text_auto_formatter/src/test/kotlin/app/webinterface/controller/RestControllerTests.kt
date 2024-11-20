package app.webinterface.controller

import app.webinterface.model.TextEntity
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

/**
 * GET / return greeting message
 * GET /api/text/all return all texts
 * GET /api/text/{id} return a text by the given ID
 * POST /api/text/new create a new text with fields supplied as JSON in request body
 * PUT /api/text/{id} update a text by the given ID with fields supplied as JSON
 *                in request body
 * DELETE /api/text/{id} delete a text by the given ID
 */
@AutoConfigureMockMvc
@SpringBootTest
class RestControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private var objectMapper: ObjectMapper = jacksonObjectMapper()

    companion object {
        private const val baseApiUrl: String = "/api/text"
    }

    // GIVEN | a sample text entity that is common for several tests
    private val textId = 1
    private val unformattedText = "How many times does this test run?"
    private val textSample: TextEntity = TextEntity(textId, unformattedText)
    private val textSampleJson: String = objectMapper.writeValueAsString(textSample)

    private val textId2 = 2
    private val unformattedText2 = "What are you doing?"
    private val textSample2: TextEntity = TextEntity(textId2, unformattedText2)
    private val textSampleJson2: String = objectMapper.writeValueAsString(textSample2)

    @Test
    @DirtiesContext
    @DisplayName("POST $baseApiUrl/new create a new text")
    fun testCreateNewTextEntity() {
        // WHEN | Perform a POST request with supplied body
        mockMvc.post("$baseApiUrl/new") {
            contentType = MediaType.APPLICATION_JSON
            content = textSampleJson
        }
            // THEN | Expect that it returns JSON object with the same fields
            .andDo { print() }
            .andExpect { status { isCreated() } }
            .andExpect { content {
                contentType(MediaType.APPLICATION_JSON)
            }}
            .andExpect { content {
                jsonPath("\$.id"){value(textId)}
                jsonPath("\$.unformattedText"){value(unformattedText)}
                jsonPath("\$.stageOneText"){value(null)}
                jsonPath("\$.handFormattedText"){value(null)}
                jsonPath("\$.posted"){value(null)}
            } }
    }

    @Test
    @DirtiesContext
    @DisplayName("GET $baseApiUrl/{id} return a text by the given ID")
    fun testGetTextEntityById(){
        // GIVEN | Create a new text entity
        mockMvc.post("$baseApiUrl/new") {
            contentType = MediaType.APPLICATION_JSON
            content = textSampleJson
        }
        // WHEN | asking for that entity by ID
        mockMvc.get("$baseApiUrl/$textId")
            // THEN | expect that it returns JSON object with the same fields
            .andDo { print() }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { content {
                jsonPath("\$.id") { value(textId) }
                jsonPath("\$.unformattedText") { value(unformattedText) }
                jsonPath("\$.stageOneText") { value(null) }
                jsonPath("\$.handFormattedText") { value(null) }
                jsonPath("\$.posted") { value(null) }
            } }
    }

    @Test
    @DirtiesContext
    @DisplayName("GET $baseApiUrl/all return all texts")
    fun testGetAllTextEntities(){
        // GIVEN | Assume there is several text entities in the database
        mockMvc.post("$baseApiUrl/new") {
            contentType = MediaType.APPLICATION_JSON
            content = textSampleJson
        }
        mockMvc.post("$baseApiUrl/new") {
            contentType = MediaType.APPLICATION_JSON
            content = textSampleJson2
        }
        // WHEN | Asking for all the text entities
        mockMvc.get("$baseApiUrl/all")
            // THEN | Expect it return a list of objects as JSON with the given fields
            .andDo { print() }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { content {
                jsonPath("\$[0].id") { value(textId) }
                jsonPath("\$[0].unformattedText") { value(unformattedText) }
                jsonPath("\$[0].stageOneText") { value(null) }
                jsonPath("\$[0].handFormattedText") { value(null) }
                jsonPath("\$[0].posted") { value(null) }

                jsonPath("\$[1].id") { value(textId2) }
                jsonPath("\$[1].unformattedText") { value(unformattedText2) }
                jsonPath("\$[1].stageOneText") { value(null) }
                jsonPath("\$[1].handFormattedText") { value(null) }
                jsonPath("\$[1].posted") { value(null) }
            } }
    }

    @Test
    @DirtiesContext
    @DisplayName("PUT $baseApiUrl/{id} update a text by the given ID")
    fun testUpdateTextEntityById(){
        // GIVEN | Assume that there is a text entity in the database
        mockMvc.post("$baseApiUrl/new") {
            contentType = MediaType.APPLICATION_JSON
            content = textSampleJson
        }
        // WHEN | Updating that text entity fields
        mockMvc.put("$baseApiUrl/$textId") {
            contentType = MediaType.APPLICATION_JSON
            content = textSampleJson2
        }
            .andExpect { status { isAccepted() } }
        // THEN | Expect that there is an text entity with modified fields but
        //        placed on the same url path
        mockMvc.get("$baseApiUrl/$textId")
            .andDo { print() }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { content {
                jsonPath("\$.id") { value(textId) }
                jsonPath("\$.unformattedText") { value(unformattedText2) }
                jsonPath("\$.stageOneText") { value(null) }
                jsonPath("\$.handFormattedText") { value(null) }
                jsonPath("\$.posted") { value(null) }
            } }
    }

    @Test
    @DirtiesContext
    @DisplayName("DELETE $baseApiUrl/{id} delete a text by the given ID")
    fun testDeleteTextEntityById(){
        // GIVEN | Assume there is a text entity in the database
        mockMvc.post("$baseApiUrl/new") {
            contentType = MediaType.APPLICATION_JSON
            content = textSampleJson
        }
        // WHEN | Deleting that text entity by ID
        mockMvc.delete("$baseApiUrl/$textId")
            // THEN | Expect that there is no entity by the given ID
            //        and database is empty
            .andExpect { status { isNoContent() } }
            .andExpect { content {
                jsonPath("\$") { value("Text Entity with ID: $textId deleted") }
            } }
        mockMvc.get("$baseApiUrl/$textId")
            .andDo { print() }
            .andExpect { status { isNotFound() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { content {
                jsonPath("\$") {value("Text Entity with ID: $textId not found")}
            }}
    }
}
