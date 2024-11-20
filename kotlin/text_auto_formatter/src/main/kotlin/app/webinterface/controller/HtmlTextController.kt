package app.webinterface.controller

import app.webinterface.model.TextEntity
import app.webinterface.service.TextService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class HtmlTextController {

    @Autowired
    private lateinit var textService: TextService

    companion object {
        private const val baseTextUrl: String = "/text"
    }

    @GetMapping(path=["$baseTextUrl/{id}"])
    fun getTextEntityFormById(
        @PathVariable("id") id: Int,
        model: Model,
        servletResponse: HttpServletResponse
    ): String {
        val retrievedTextEntity: TextEntity? = textService.getById(id)
        model["id"] = id
        if(retrievedTextEntity == null) {
            servletResponse.status = HttpServletResponse.SC_NOT_FOUND
            return "error/not_found"
        } else {
            model["title"] = "Update Text Entity #${retrievedTextEntity.id}"
            model["textEntity"] = retrievedTextEntity
            return "text/update_text"
        }
    }

    @PostMapping(path=["$baseTextUrl/{id}"])
    fun updateExistingTextEntityById(
        @PathVariable("id") id: Int,
        @ModelAttribute("textEntity") updatedTextEntity: TextEntity,
        model: Model,
        servletResponse: HttpServletResponse
    ): String {
        val retrievedTextEntity: TextEntity = textService.updateById(id, updatedTextEntity)
        model["id"] = id
        if (retrievedTextEntity == null) {
            servletResponse.status = HttpServletResponse.SC_NOT_FOUND
            return "error/not_found"
        } else {
            servletResponse.status = HttpServletResponse.SC_ACCEPTED
            return "text/update_text"
        }
    }

    @GetMapping(path=["$baseTextUrl/all"], produces = ["text/html"])
    fun getAllTextEntities(
        model: Model
    ): String {
        val textEntities: List<TextEntity> = textService.getAll()
        model["textEntities"] = textEntities
        model["title"] = "Get all text entities"
        return "text/get_all_texts"
    }

    @GetMapping(path=["$baseTextUrl/new"])
    fun creteNewTextEntity(model: Model): String {
        model["textEntity"] = TextEntity()
        return "text/get_new_text"
    }

    @PostMapping(path=["$baseTextUrl/new"])
    fun createNewTextEntity(@ModelAttribute("textEntity") textEntity: TextEntity): String {
        textEntity.id = 0
        val retrievedTextEntity: TextEntity = textService.create(textEntity)
        return "redirect:/text/all"
    }
}
