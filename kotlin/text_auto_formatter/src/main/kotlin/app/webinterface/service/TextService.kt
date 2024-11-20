package app.webinterface.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.stereotype.Service
import app.webinterface.model.TextEntity
import app.webinterface.repository.TextRepository

/**
 * Text Service should provide 5 basic CRUD functions
 *   - create a new text entity
 *   - get a text entity by ID
 *   - get all the text entities
 *   - update a text entity by ID
 *   - delete a text entity
 */
interface ITextService {

    fun create(testEntity: TextEntity): TextEntity

    fun getById(id: Int): TextEntity?

    fun getAll(): List<TextEntity>

    fun updateById(id: Int, updatedTextEntity: TextEntity): TextEntity

    fun deleteById(id: Int): Unit?
}

@Service
class TextService(var textRepository: TextRepository): ITextService {
    override fun create(testEntity: TextEntity): TextEntity {
        return textRepository.save(testEntity)
    }

    override fun getById(id: Int): TextEntity? {
        return try {
            textRepository.getReferenceById(id)

        } catch (e: EntityNotFoundException) {
            null
        } catch (e: JpaObjectRetrievalFailureException) {
            null
        }
    }

    override fun getAll(): List<TextEntity> {
        return textRepository.findAll()
    }

    override fun updateById(id: Int, updatedTextEntity: TextEntity): TextEntity {
        updatedTextEntity.id = id
        return textRepository.save(updatedTextEntity)
    }

    override fun deleteById(id: Int): Unit? {
        return try{
            textRepository.deleteById(id)
            Unit
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }
}
