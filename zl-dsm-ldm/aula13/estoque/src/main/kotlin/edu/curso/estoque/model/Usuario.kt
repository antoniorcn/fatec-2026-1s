package edu.curso.estoque.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,
    @Column(length = 50, nullable = false)
    val nome : String,
    @Column(length = 100, nullable = false, unique = true)
    val email : String,
    @Column(length = 256, nullable = false)
    val senha : String,
    @Column(length = 100, nullable = false)
    val perfil : String
) {
    constructor() : this(0L, "", "", "", "")
}