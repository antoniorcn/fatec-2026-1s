package edu.curso.agendacontato.repository

import edu.curso.agendacontato.model.Medicao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MedicaoRepository : JpaRepository<Medicao, Long> {

}