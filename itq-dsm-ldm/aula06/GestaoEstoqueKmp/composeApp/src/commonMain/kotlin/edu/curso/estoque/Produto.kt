package edu.curso.estoque

import kotlinx.serialization.Serializable

@Serializable
data class Produto(
    var id: String? = null,
    var categoria: String = "",
    var sku: String = "",
    var nome: String = "",
    var descricao: String? = null,
    var estoqueMinimo: Double? = 0.0,
    var ativo: Boolean = true,
    var criadoEm: String = "",
    var imagem: String? = null
)

/*

{
    "categoria": "bebidas",
    "sku": "BEB-001",
    "nome": "Coca-Cola 2L",
    "descricao": "Refrigerante de cola, embalagem de 2 litros.",
    "estoqueMinimo": 10.0,
    "ativo": true,
    "criadoEm": "2024-06-01",
    "imagem": "https://example.com/coca-cola-2l.jpg"
}
 */