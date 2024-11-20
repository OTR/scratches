package app.webinterface.model

import java.io.Serializable
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class TextEntity(): ITextEntity, Serializable {

    constructor(id: Int, unformattedText: String): this() {
        this.id = id
        this.unformattedText = unformattedText
    }

    constructor(
        id: Int, unformattedText: String, stageOneText: String?,
        handFormattedText: String?, posted: Boolean?
    ): this() {
        this.id = id
        this.unformattedText = unformattedText
        this.stageOneText = stageOneText
        this.handFormattedText = handFormattedText
        this.posted = posted
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Int = 0
    override var unformattedText: String = ""
    override var stageOneText: String? = null
    override var handFormattedText: String? = null
    override var posted: Boolean? = null
}

interface ITextEntity {
    val id: Int
    val unformattedText: String
    val stageOneText: String?
    val handFormattedText: String?
    val posted: Boolean?
}