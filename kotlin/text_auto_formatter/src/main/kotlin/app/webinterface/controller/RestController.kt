package app.webinterface.controller

import app.webinterface.model.TextEntity
import app.webinterface.service.TextService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.io.Serializable

/**
 * GET / return greeting message
 * GET /api/text/all return all texts
 * GET /api/text/{id} return a text by the given ID
 * POST /api/text/new create a new text with fields supplied as JSON in request body
 * PUT /api/text/{id} update a text by the given ID with fields supplied as JSON
 *                in request body
 * DELETE /api/text/{id} delete a text by the given ID
 */
@RestController
class RestController {

    @Autowired
    private lateinit var textService: TextService

    companion object {
        private const val baseApiUrl = "/api/text"
        private val objectMapper: ObjectMapper = jacksonObjectMapper()

        /**
         * Serialize Any object to valid JSON
         */
        private fun toJson(value: Any): String {
            return objectMapper.writeValueAsString(value)
        }
    }

    @PostMapping(path = ["$baseApiUrl/new"])
    @ResponseStatus(HttpStatus.CREATED)
    fun createNewTextEntity(@RequestBody newTextEntity: TextEntity): TextEntity {
        return textService.create(newTextEntity)
    }

    @GetMapping(path = ["$baseApiUrl/all"])
    fun getAllTextEntities(): List<TextEntity> {
        return textService.getAll()
    }

    @GetMapping(path = ["$baseApiUrl/{id}"])
    fun getTextEntityById(@PathVariable("id") id: Int): ResponseEntity<Serializable> {
        val retrievedTextEntity = textService.getById(id)
        return if(retrievedTextEntity == null) {
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(toJson("Text Entity with ID: $id not found"))
        } else {
            ResponseEntity
                .ok()
                .body(retrievedTextEntity)
        }
    }

    @PutMapping(path = ["$baseApiUrl/{id}"])
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun updateTextEntityById(
        @PathVariable("id") id: Int,
        @RequestBody updatedTextEntity: TextEntity
    ): TextEntity {
        return textService.updateById(id, updatedTextEntity)
    }

    @DeleteMapping(path = ["$baseApiUrl/{id}"])
    fun deleteTextEntityById(@PathVariable("id") id: Int): ResponseEntity<String> {
        val retrievedTextEntity = textService.deleteById(id)
        return if (retrievedTextEntity == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                toJson("Text Entity with ID: $id not found")
            )
        } else {
            ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                toJson("Text Entity with ID: $id deleted")
            )
        }
    }
}
