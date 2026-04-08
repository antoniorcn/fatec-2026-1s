package edu.curso.estoque

import org.springframework.http.RequestEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/estoque")
class EstoqueRestController {

    val lista = mutableListOf<Pessoa>(
        Pessoa( id = 0, "Maria Silva", "(11) 2222-2222", "maria@teste.com")
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