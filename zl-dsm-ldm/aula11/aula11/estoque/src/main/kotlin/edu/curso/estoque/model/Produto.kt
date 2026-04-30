package edu.curso.estoque.model

data class Produto (
    val id : Long,
    val nome: String,
    val descricao: String,
    val preco : Double,
    val quantidadeEstoque :Long,
    val ativo :Boolean,
    val categoria : Categoria
) {
}