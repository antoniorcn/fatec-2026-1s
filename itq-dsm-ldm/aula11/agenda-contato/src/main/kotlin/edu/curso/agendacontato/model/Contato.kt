package edu.curso.agendacontato.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.TableGenerator

@Entity
data class Contato(
    @Id
    @TableGenerator(
        name = "contato_table",         // Links to generator attribute in @GeneratedValue
        table = "id_generator_table",  // Name of the physical DB table
        pkColumnName = "entidade",     // Column identifying the entity type
        valueColumnName = "value",     // Column storing the current ID value
        pkColumnValue = "contato_id",     // Value in pkColumnName for this entity
        allocationSize = 1                 // How much to increment by
    )
    @GeneratedValue( strategy = GenerationType.TABLE,
        generator="contato_table" )
    val id : Long,
    @Column(length = 100, nullable = false)
    val nome : String = "",
    @Column(length = 30, nullable = false)
    val telefone : String = "",
    @Column(length = 100, nullable = false)
    val email : String = ""
) {
    constructor() : this(0, nome="", telefone="", email="")
}