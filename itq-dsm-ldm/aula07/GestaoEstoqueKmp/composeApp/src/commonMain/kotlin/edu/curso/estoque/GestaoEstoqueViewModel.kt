package edu.curso.estoque

import androidx.compose.material3.SnackbarHostState
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
    var id : String ? by mutableStateOf(null)
    var categoria by mutableStateOf("")
    var sku by mutableStateOf("")
    var nome by mutableStateOf("")
    var descricao by mutableStateOf("")
    var estoqueMinimo : Double? by mutableStateOf(0.0)
    var ativo by mutableStateOf(true)
    var criadoEm by mutableStateOf("")
    var imagem by mutableStateOf("")

    val backstack = mutableStateOf<NavBackStack<NavKey>>(NavBackStack(RotaEstoque.Formulario ))

    val snackMessage = SnackbarHostState()

    var darkMode by mutableStateOf(false)

    fun toggleScreenMode() {
        darkMode = !darkMode
        println("Dark Mode: $darkMode")
    }

    fun popularCampos( model : Produto ) {
        id = model.id
        nome = model.nome
        categoria = model.categoria
        sku = model.sku
        descricao = model.descricao ?: ""
        estoqueMinimo = model.estoqueMinimo ?: 0.0
        ativo = model.ativo
        criadoEm = model.criadoEm
        imagem = model.imagem ?: ""
    }

    fun limparCampos() {
        popularCampos( Produto() )
        viewModelScope.launch {
            snackMessage.showSnackbar("Formulário esvaziado")
        }
    }

    fun salvar() {
        val produto = Produto(
            id = id,
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
        if (produto.id == null ) {
            viewModelScope.launch {
                produtoApi.salvar(produto)
                atualizarProdutos(produtoApi.getAll())
                snackMessage.showSnackbar("Produto Salvo com sucesso")
            }
        } else {
            viewModelScope.launch {
                produtoApi.atualizar(produto)
                atualizarProdutos(produtoApi.getAll())
                snackMessage.showSnackbar("Produto Atualizado")
            }
        }
        limparCampos()
    }

    fun pesquisar() {
        for (produto in produtos) {
            if (produto.nome.contains(nome, ignoreCase = true)) {
                popularCampos( produto )
                viewModelScope.launch {
                    snackMessage.showSnackbar("Produto Encontrado")
                }
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
            snackMessage.showSnackbar("Produto Apagado com sucesso")
        }
    }

    fun editar ( produto : Produto ){
        popularCampos( produto )
        navigateTo( RotaEstoque.Formulario)
    }

    fun navigateTo( rota : NavKey ) {
        backstack.value.clear()
        backstack.value.add( rota )
    }
}