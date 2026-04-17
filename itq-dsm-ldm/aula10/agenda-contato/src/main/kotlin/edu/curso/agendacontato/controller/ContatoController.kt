package edu.curso.agendacontato.controller

import edu.curso.agendacontato.model.Contato
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ContatoController {

    val lista = mutableListOf(
        Contato(null, "Joao Silva", "(11) 1111-1111", "joao@teste.com"),
        Contato(null, "Maria Silva", "(11) 2222-2222", "maria@teste.com"),
    )

    @GetMapping
    fun lerTodos() : ResponseEntity<List<Contato>> {
        return ResponseEntity.ok().body(lista)
    }

    @PostMapping
    fun gravar(@RequestBody contato : Contato) : ResponseEntity<String> {
        println("Recebido contato: $contato")
        lista.add( contato )
        return ResponseEntity.ok().body("Gravado com sucesso")
    }

}