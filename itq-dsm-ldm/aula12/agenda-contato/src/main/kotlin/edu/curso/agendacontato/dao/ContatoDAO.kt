package edu.curso.agendacontato.dao

import edu.curso.agendacontato.model.Contato

interface ContatoDAO {

    fun salvar( contato : Contato )

    fun listar() : List<Contato>

}