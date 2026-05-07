package edu.curso.estoque.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob

@Entity
data class Categoria (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column( length = 100, nullable = false )
    val nome: String,
    @Lob
    @Column(nullable = true)
    val descricao: String?
) {
    constructor() : this(0, "", "")
}