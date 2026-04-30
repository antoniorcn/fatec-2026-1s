package edu.curso.agendacontato.controller

import edu.curso.agendacontato.dto.ContatoDto
import edu.curso.agendacontato.dto.ContatoDtoView
import edu.curso.agendacontato.mapping.ContatoMapping
import edu.curso.agendacontato.model.Contato
import edu.curso.agendacontato.repository.ContatoRepository
import edu.curso.agendacontato.service.ContatoService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ContatoController(
    val service : ContatoService,
    val mapping : ContatoMapping
) {

    @GetMapping
    fun lerTodos() : ResponseEntity<List<ContatoDtoView>> {
        val lista = service.carregarTodos()
        return ResponseEntity.ok().body(
            lista.map{ mapping.toDtoView( it ) }
        )
    }

    @GetMapping("/{nome}")
    fun procurarPorNome(@PathVariable("nome") nome : String) : ResponseEntity<ContatoDtoView> {
        val lista = service.procurarPorNome( nome )
        return if (lista.isNotEmpty()) {
            ResponseEntity.ok( mapping.toDtoView( lista[0] ) )
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun gravar(@Valid @RequestBody contatoDto : ContatoDto) : ResponseEntity<String> {
        println("Recebido contato: $contatoDto")
        service.salvar( mapping.toEntity(contatoDto) )
        return ResponseEntity.ok().body("Gravado com sucesso")
    }

}