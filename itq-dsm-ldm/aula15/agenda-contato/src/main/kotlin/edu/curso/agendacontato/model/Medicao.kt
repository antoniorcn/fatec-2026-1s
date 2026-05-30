package edu.curso.agendacontato.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.TableGenerator
import java.time.LocalDateTime

@Entity
data class Medicao(
    @Id
    @TableGenerator(
        name = "medicao_table",         // Links to generator attribute in @GeneratedValue
        table = "id_generator_table",  // Name of the physical DB table
        pkColumnName = "entidade",     // Column identifying the entity type
        valueColumnName = "value",     // Column storing the current ID value
        pkColumnValue = "medicao",     // Value in pkColumnName for this entity
        allocationSize = 1                 // How much to increment by
    )
    @GeneratedValue( strategy = GenerationType.TABLE,
        generator="medicao_table" )
    val id : Long,
    @Column(length = 100, nullable = false)
    val temperatura : Float = 0.0f,
    @Column(length = 30, nullable = false)
    val umidade : Float = 0.0f,
    @Column(length = 100, nullable = false)
    val topico : String = "",
    @Column(nullable = false)
    val timeStamp : LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(0, temperatura=0.0f, umidade=0.0f, topico="")
}