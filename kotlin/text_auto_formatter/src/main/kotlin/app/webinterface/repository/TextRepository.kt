package app.webinterface.repository


import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import app.webinterface.model.TextEntity

@Repository
interface TextRepository: JpaRepository<TextEntity, Int>
