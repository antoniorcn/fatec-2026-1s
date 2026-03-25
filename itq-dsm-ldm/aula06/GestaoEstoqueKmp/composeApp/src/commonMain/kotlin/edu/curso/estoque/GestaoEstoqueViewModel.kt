package edu.curso.estoque

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import edu.curso.estoque.api.ProdutoApi
import edu.curso.estoque.api.createHttpClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GestaoEstoqueViewModel : ViewModel() {

    val httpClient = createHttpClient()
    val produtoApi = ProdutoApi(httpClient)

    var produtos = mutableStateListOf<Produto>()
    var categoria by mutableStateOf("")
    var sku by mutableStateOf("")
    var nome by mutableStateOf("")
    var descricao by mutableStateOf("")
    var estoqueMinimo : Double? by mutableStateOf(0.0)
    var ativo by mutableStateOf(true)
    var criadoEm by mutableStateOf("")
    var imagem by mutableStateOf("")

    val backstack = mutableStateOf<NavBackStack<NavKey>>(NavBackStack(Rota.Formulario ))

    fun limparCampos() {
        categoria = ""
        sku = ""
        nome = ""
        descricao = ""
        estoqueMinimo = 0.0
        ativo = true
        criadoEm = ""
        imagem = ""
    }

    fun salvar() {
        val produto = Produto(
            id = null,
            categoria = categoria,
            sku = sku,
            nome = nome,
            descricao = descricao,
            estoqueMinimo = estoqueMinimo,
            ativo = ativo,
            criadoEm = criadoEm,
            imagem = imagem
        )
        // produtos.add(produto)
        viewModelScope.launch {
            produtoApi.salvar(produto)
            atualizarProdutos( produtoApi.getAll() )
        }
        limparCampos()
    }

    fun pesquisar() {
        for (produto in produtos) {
            if (produto.nome.contains(nome, ignoreCase = true)) {
                nome = produto.nome
                categoria = produto.categoria
                sku = produto.sku
                descricao = produto.descricao ?: ""
                estoqueMinimo = produto.estoqueMinimo ?: 0.0
                ativo = produto.ativo
                criadoEm = produto.criadoEm
                imagem = produto.imagem ?: ""
            }
        }
    }

    fun atualizarProdutos( novosProdutos : List<Produto> ) {
        produtos.clear()
        produtos.addAll( novosProdutos )
    }

    fun carregarTodos() {
        viewModelScope.launch {
            val valores = produtoApi.getAll()
            atualizarProdutos( valores )
        }
    }

    fun apagar ( produto : Produto ) {
        viewModelScope.launch {
            produtoApi.apagar(produto)
            delay(500)
            atualizarProdutos( produtoApi.getAll() )
        }
    }
}