package app.webinterface.service

import jakarta.persistence.EntityNotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.test.annotation.DirtiesContext
import app.webinterface.model.TextEntity
import app.webinterface.repository.TextRepository

/**
 * Text Service should provide 5 basic CRUD functions
 */
@ExtendWith(MockitoExtension::class)
class TextServiceTests {

    private var textRepository: TextRepository = mock(TextRepository::class.java)

    @Autowired
    private var textService: TextService = TextService(textRepository)


    @Test
    @DirtiesContext
    @DisplayName("Create a new text entity")
    fun testServiceCreate(){
        // GIVEN
        val textId = 1
        val unformattedText = "Hello world"
        val textSample = TextEntity(textId, unformattedText)
        // MOCK
        `when`(textRepository.save(any(TextEntity::class.java))).thenReturn(textSample)
        `when`(textRepository.getReferenceById(any(Int::class.java))).thenReturn(textSample)
        // END MOCK
        textService.create(textSample)
        // WHEN
        val retrievedTextSample = textService.getById(textId)
        // THEN
        assertThat(retrievedTextSample?.unformattedText).isEqualTo(unformattedText)

        // VERIFY THAT WAS HIT ONCE
    }

    @Test
    @DirtiesContext
    @DisplayName("Get a text entity by ID")
    fun testServiceGetById(){
        // GIVEN
        val textId = 1
        val unformattedText = "Kotlin is awesome"
        val textSample: TextEntity = TextEntity(textId, unformattedText)
        // MOCKING
        `when`(textRepository.save(any(TextEntity::class.java))).thenReturn(textSample)
        `when`(textRepository.getReferenceById(any(Int::class.java))).thenReturn(textSample)
        // END MOCKING
        textService.create(textSample)
        // WHEN
        val retrievedTextSample = textService.getById(textId)
        // THEN
        assertThat(retrievedTextSample?.unformattedText).isEqualTo(unformattedText)
    }

    @Test
    @DirtiesContext
    @DisplayName("Get all the text entities")
    fun testServiceGetAll(){
        // GIVEN
        val textId1 = 1
        val textId2 = 2
        val unformattedText1 = "Hello everyone"
        val unformattedText2 = "Hello even Mockito"
        val textSample1 = TextEntity(textId1, unformattedText1)
        val textSample2 = TextEntity(textId2, unformattedText2)
        // MOCKING
        `when`(textRepository.save(any(TextEntity::class.java))).thenReturn(textSample1, textSample2)
        `when`(textRepository.findAll()).thenReturn(listOf(textSample1, textSample2))
        // END MOCKING
        textService.create(textSample1)
        textService.create(textSample2)
        // WHEN
        val samples: List<TextEntity> = textService.getAll()
        // THEN
        assertThat(samples).extracting("id", "unformattedText").contains(
            // TODO: Make auto-increment working even when mocking
            tuple(1, unformattedText1),
            tuple(2, unformattedText2)
        )
    }

    @Test
    @DirtiesContext
    @DisplayName("Update a text entity by ID")
    fun testServiceUpdateById(){
        // GIVEN
        val textId = 1
        val unformattedText = "Hello world"
        val textSample = TextEntity(textId, unformattedText)
        //
        val handFormattedText = "Hello world."
        val modifiedTextSample: TextEntity = TextEntity(textId, unformattedText, null, handFormattedText, null)
        // MOCKING
        `when`(textRepository.save(any(TextEntity::class.java))).thenReturn(textSample)
        `when`(textRepository.getReferenceById(any(Int::class.java))).thenReturn(modifiedTextSample)
        // MOCKING
        textService.create(textSample)
        // WHEN
        textService.updateById(textId, modifiedTextSample)
        // THEN
        val retrievedTextSample: TextEntity? = textService.getById(textId)
        assertThat(retrievedTextSample).extracting(
            "id", "unformattedText", "stageOneText", "handFormattedText", "posted"
        ).contains(
            textId, unformattedText, null, handFormattedText, null
        )
    }

    @Test
    @DirtiesContext
    @DisplayName("Delete a text entity by ID")
    fun testServiceDeleteById(){
        // GIVEN
        val textId = 1
        val unformattedText = "What is going on"
        val textSample: TextEntity = TextEntity(textId, unformattedText)
        // MOCKING
        `when`(textRepository.save(any(TextEntity::class.java))).thenReturn(textSample)
        `when`(textRepository.getReferenceById(any(Int::class.java))).thenThrow(
            JpaObjectRetrievalFailureException(EntityNotFoundException(
                "Unable to find task.model.TextEntity with id $textId"
            ))
        )
        // END MOCKING
        textService.create(textSample)
        // WHEN
        textService.deleteById(textId)
        // THEN
        val response = textService.getById(textId)
        assertThat(response).isNull()
    }
}
