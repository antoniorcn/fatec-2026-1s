package edu.curso.agendacontato.controller

import edu.curso.agendacontato.dto.ContatoDto
import edu.curso.agendacontato.dto.ContatoDtoView
import edu.curso.agendacontato.mapping.ContatoMapping
import edu.curso.agendacontato.model.Contato
import edu.curso.agendacontato.repository.ContatoRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//@RestController
//@RequestMapping("/api")
class ContatoControllerOld(
    val repository : ContatoRepository,
    val mapping : ContatoMapping
) {

//    val lista = mutableListOf(
//        Contato(null, "Joao Silva", "(11) 1111-1111", "joao@teste.com"),
//        Contato(null, "Maria Silva", "(11) 2222-2222", "maria@teste.com"),
//    )

    @GetMapping
    fun lerTodos() : ResponseEntity<List<ContatoDtoView>> {
        val lista = repository.findAll()

//        val listaContatoDtoView = mutableListOf<ContatoDtoView>()
//        for (contato in lista ) {
//            val contatoDtoView = ContatoDtoView(
//                nome = contato.nome,
//                email = contato.email
//            )
//            listaContatoDtoView.add( contatoDtoView )
//        }

//        val listaContatoDtoView = lista.map{ contato ->
//            ContatoDtoView(
//                nome = contato.nome,
//                email = contato.email
//            )
//        }
//        return ResponseEntity.ok().body( listaContatoDtoView )
        return ResponseEntity.ok().body(
            lista.map{ mapping.toDtoView( it ) }
        )
    }

    @GetMapping("/{nome}")
    fun procurarPorNome(@PathVariable("nome") nome : String) : ResponseEntity<ContatoDtoView> {
        val lista = repository.findByNomeLike( nome )
        return if (lista.isNotEmpty()) {
            ResponseEntity.ok( mapping.toDtoView( lista[0] ) )
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun gravar(@RequestBody contatoDto : ContatoDto) : ResponseEntity<String> {
        println("Recebido contato: $contatoDto")
//        val contato = Contato(
//            id = 0,
//            nome = contatoDto.nome,
//            email = contatoDto.email,
//            telefone = contatoDto.telefone
//        )
        repository.save( mapping.toEntity(contatoDto) )
//        lista.add( contato )
        return ResponseEntity.ok().body("Gravado com sucesso")
    }

}