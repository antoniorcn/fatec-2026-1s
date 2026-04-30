package edu.curso.estoque.mapping

import edu.curso.estoque.dto.CategoriaDTO
import edu.curso.estoque.model.Categoria
import org.springframework.stereotype.Component

@Component
class CategoriaMapping {
    fun toModel( dto : CategoriaDTO) : Categoria {
        return Categoria( id = 0,
            nome=dto.nome,
            descricao=dto.descricao)
    }
    fun toDTO( model : Categoria ) : CategoriaDTO {
        return CategoriaDTO(
            nome = model.nome,
            descricao = model.descricao
        )
    }
}