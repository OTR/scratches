package app.webinterface.repository

import app.webinterface.model.TextEntity
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Sort
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.test.annotation.DirtiesContext

/**
 * `TextRepository` should provide basic 5 CRUD functions, such as:
 *   [testCase1] adding a new text entry to the repository,
 *   [testCase2] getting single entry from the repository,
 *   [testCase3] getting all the entries from the repository,
 *   [testCase4] updating fields of an entry from the repository,
 *   [testCase5] deleting an entry from the repository
 */
@DataJpaTest
class TextRepositoryTests {

    @Autowired
    private lateinit var textRepository: TextRepository

    /**
     * Covering test cases #1, #2
     * TODO: add @DirtiesContext annotation?
     */
    @Test
    @DirtiesContext
    @DisplayName("Repository can save and retrieve the same data")
    fun testRepositoryCanSave() {
        // GIVEN
        val textSampleId = 1
        val textSampleUnformattedText = "Hello world"
        val textSample = TextEntity(
            id = textSampleId,
            unformattedText = textSampleUnformattedText
        )
        // WHEN
        textRepository.save(textSample)
        // THEN
        val retrievedTextSample = textRepository.getReferenceById(textSampleId)
        assertThat(retrievedTextSample)
            .extracting(
                "id", "unformattedText", "stageOneText", "handFormattedText", "posted"
            )
            .contains(
                textSampleId, textSampleUnformattedText, null, null, null
            )
    }

    /**
     * Covering test case where repository holds some data after execution of
     * some previous test
     */
    @Test
    @DisplayName("Repository is empty after execution of other test")
    fun testRepositoryIsEmtpy() {
        val textSamples: List<TextEntity> = textRepository.findAll()
        assertThat(textSamples).isEmpty()
    }

    /**
     * Covering test case #3
     */
    @Test
    @DirtiesContext
    @DisplayName("Repository returns the same entities that were saved")
    fun testRepositoryReturnsEntities() {
        // GIVEN
        val keys = arrayOf("id", "unformattedText", "stageOneText", "handFormattedText", "posted")
        val sample1 = tuple(1, "Hello World", null, null, null)
        val sample2 = tuple(2, "", null, null, false)
        val sample3 = tuple(3, "Kotlin the language", "Kotlin the language.", "Kotlin language.", true)
        val samples = arrayOf(sample1, sample2, sample3)
        // WHEN
        samples.forEach {sample ->
            val it = sample.toArray()
            textRepository.save(
                TextEntity(it[0] as Int, it[1] as String, it[2] as String?, it[3] as String?, it[4] as Boolean?)
            )
        }
        // THEN
        val retrievedTextSamples = textRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
        assertThat(retrievedTextSamples).extracting(*keys).contains(sample1, sample2, sample3)
    }

    /**
     * Covering test case #4
     */
    @Test
    @DirtiesContext
    @DisplayName("Repository saves and updates an entity")
    fun testRepositorySavesAndUpdates() {
        // GIVEN | Create a new entity in the repository
        val textId = 1
        val unformattedText = "Hello Kotlin world"
        val handFormatted1 = "Hello Kotlin world."
        val handFormatted2 = "Hello, Kotlin world."
        val textSample = TextEntity(textId, unformattedText)
        textRepository.save(textSample)
        // WHEN 1 | When getting that entity
        val currentEntity = textRepository.getReferenceById(textId)
        // THEN 1 | Assert that it is still `null`
        assertThat(currentEntity.handFormattedText).isEqualTo(null)
        // WHEN 2 | Then update entity's value from `null` to some `String`
        currentEntity.handFormattedText = handFormatted1
        textRepository.save(currentEntity)
        // THEN 2 | Assert that it is equal the given String
        val modifiedEntity1 = textRepository.getReferenceById(textId)
        assertThat(modifiedEntity1.handFormattedText).isEqualTo(handFormatted1)
        // WHEN 3 | Then update entity's value from one `String` to another `String`
        modifiedEntity1.handFormattedText = handFormatted2
        textRepository.save(modifiedEntity1)
        // THEN 3 | Assert that it value has changed again
        val modifiedEntity2 = textRepository.getReferenceById(textId)
        assertThat(modifiedEntity2.handFormattedText).isEqualTo(handFormatted2)
    }

    /**
     * Covering test case #5
     */
    @Test
    @DirtiesContext
    @DisplayName("Repository saves and deletes an entity")
    fun testRepositorySavesAndDeletes() {
        // GIVEN | Create a new entity in the repository
        val textId = 1
        val unformattedText = "Hello Kotlin world"
        val textSample = TextEntity(textId, unformattedText)
        textRepository.save(textSample)
        // WHEN 1 | When getting that entity
        val currentEntity = textRepository.getReferenceById(textId)
        val allEntities = textRepository.findAll()
        // THEN 1 | Assert that it exists
        assertThat(allEntities.size).isEqualTo(1)
        assertThat(currentEntity.unformattedText).isEqualTo(unformattedText)
        // WHEN 2 | Then delete entity from the repository
        textRepository.deleteById(textId)
        // THEN 2 | Assert that there is no entity with the given Id
        //          and repository is empty
        val emptyallEntities = textRepository.findAll()
        assertThatExceptionOfType(JpaObjectRetrievalFailureException::class.java).isThrownBy {
            textRepository.getReferenceById(textId)
        }.withMessage("Unable to find app.webinterface.model.TextEntity with id $textId")
        assertThat(emptyallEntities).isEmpty()
    }
}

