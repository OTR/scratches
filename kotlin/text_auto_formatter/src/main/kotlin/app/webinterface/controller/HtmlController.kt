package app.webinterface.controller

import app.webinterface.service.TextService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {

    @Autowired
    private lateinit var env: Environment

    @Autowired
    private lateinit var textService: TextService

    @GetMapping(path=["/"])
    fun getIndexPage(model: Model): String {
        val title: String = env.getProperty("title.index") ?: "Index"
        model["title"] = title
        return "index"
    }
}