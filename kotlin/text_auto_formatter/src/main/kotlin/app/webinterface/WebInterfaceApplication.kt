package app.webinterface

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebInterfaceApplication

fun main(args: Array<String>) {
    runApplication<WebInterfaceApplication>(*args)
}
