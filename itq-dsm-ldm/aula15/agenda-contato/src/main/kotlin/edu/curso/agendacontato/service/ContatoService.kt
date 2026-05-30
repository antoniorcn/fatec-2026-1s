package edu.curso.agendacontato.service

import edu.curso.agendacontato.model.Contato
import edu.curso.agendacontato.repository.ContatoRepository
import org.springframework.stereotype.Service

@Service
class ContatoService(
    val repository: ContatoRepository
) {

    fun salvar( contato: Contato) {
        repository.save( contato )
    }

    fun carregarTodos() : List<Contato> {
        return repository.findAll()
    }

    fun procurarPorNome(nome : String) : List<Contato> {
        return repository.findByNomeContaining( nome )
    }
}