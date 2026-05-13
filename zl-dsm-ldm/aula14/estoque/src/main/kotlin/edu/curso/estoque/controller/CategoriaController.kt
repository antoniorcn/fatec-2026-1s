package edu.curso.estoque.controller

import edu.curso.estoque.dto.CategoriaDTO
import edu.curso.estoque.mapping.CategoriaMapping
import edu.curso.estoque.model.Categoria
import edu.curso.estoque.repository.CategoriaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/categoria")
class CategoriaController( val repository : CategoriaRepository,
                           val mapping : CategoriaMapping ) {

    val lista : MutableList<Categoria> = mutableListOf()
    var idCounter : Long = 1

    @GetMapping
    fun getAll() : ResponseEntity<MutableList<Categoria>> {
        return ResponseEntity.ok(repository.findAll())
        // return ResponseEntity.ok(lista)
    }

    @PostMapping
    fun adicionar(@RequestBody categoriaDto : CategoriaDTO) : ResponseEntity<String> {
        // val novaCategoria = categoria.copy(id = idCounter)
        // lista.add(novaCategoria)
        val categoria = mapping.toModel(categoriaDto)
        repository.save( categoria )
        return ResponseEntity.ok("Categoria adicionada com sucesso")
    }
}