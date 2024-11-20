package app.webinterface.config

import app.webinterface.model.TextEntity
import app.webinterface.repository.TextRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.getProperty

@Configuration
class DataBaseConfiguration {

    @Autowired
    private lateinit var env: Environment

    @Bean
    fun databaseInitializer(textRepository: TextRepository): ApplicationRunner? {
        val isPopulateDatabase: Boolean? = env.getProperty<Boolean>("debug.populate_database")
        if (isPopulateDatabase == true) {
            return ApplicationRunner {
                textRepository.save(
                    TextEntity(id = 1, unformattedText = "Hello kotlin world")
                )
            }
        } else {
            return null
        }
    }
}
