package edu.curso.estoque.controller

import edu.curso.estoque.model.Pessoa
//import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// Baseado na arquitetura SPA (Single Page Application)
@RestController
@RequestMapping("/api/estoque")
class EstoqueRestController {

    val lista = mutableListOf<Pessoa>(
        Pessoa(id = 0, "Maria Silva", "(11) 2222-2222", "maria@teste.com")
    )

    var contadorId : Long = 1

    @PostMapping
    fun gravar(@RequestBody pessoa : Pessoa) : String {
        pessoa.id = contadorId++
        lista.add( pessoa )
        return "Pessoa gravada com sucesso"
    }

    @GetMapping
    fun listar() : List<Pessoa> {
        return lista
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('MANAGER')")
    fun remover(@PathVariable id : Long) : String {
        val tempLista = lista.filter { it.id != id }
        return if (tempLista.size < lista.size) {
            lista.clear()
            lista.addAll(tempLista)
            "Id removido com sucesso"
        } else {
            "Id não encontrado"
        }
    }
}