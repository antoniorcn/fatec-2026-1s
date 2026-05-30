package edu.curso.agendacontato.repository

import edu.curso.agendacontato.model.Contato
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ContatoRepository : JpaRepository<Contato, Long> {

    fun findByNome(nome: String) : List<Contato>

    fun findByNomeContaining(nome: String) : List<Contato>

    @Query("SELECT c FROM Contato c WHERE c.nome LIKE %:nome%")
    fun findByNomeLike(@Param("nome") nome: String): List<Contato>


}