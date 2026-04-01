package edu.curso

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import edu.curso.api.httpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProdutoViewModel : ViewModel() {
    var id : String? by mutableStateOf(null)
    var categoria : String by mutableStateOf("")
    var sku : String by mutableStateOf("")
    var nome : String by mutableStateOf("")
    var descricao : String by mutableStateOf("")
    var estoqueMinimo : String by mutableStateOf("")
    var ativo : Boolean by mutableStateOf(false)
    var criadoEm : String by mutableStateOf("")
    var imagemUrl : String by mutableStateOf("")
    var isDarkTheme : Boolean by mutableStateOf(false)

    val api = httpClient()

    val backstack = mutableStateListOf<NavKey>(Rotas.Listagem)

    val snack = SnackbarHostState()

    val drawerState = DrawerState(DrawerValue.Closed)

    var lista = mutableStateListOf(
        Produto(id = null, categoria = "Eletrônicos", sku = "ELEC123",
            nome = "Smartphone", descricao = "Smartphone de última geração",
            estoqueMinimo = 10.0, ativo = true, criadoEm = "2024-01-01", imagem = null),
        Produto(id = null, categoria = "Eletrodomésticos", sku = "ELETD456",
            nome = "Geladeira", descricao = "Geladeira com tecnologia inverter",
            estoqueMinimo = 5.0, ativo = true, criadoEm = "2024-02-15", imagem = null),
        Produto(id = null, categoria = "Móveis", sku = "MOVE789",
            nome = "Sofá", descricao = "Sofá de couro confortável",
            estoqueMinimo = 3.0, ativo = false, criadoEm = "2024-03-10", imagem = null)
    )

    suspend fun drawerToggle() {
        if (drawerState.isOpen) {
            drawerState.close()
        } else {
            drawerState.open()
        }
    }

    fun iconeThema() =
        if (isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode

    fun toggleDarkTheme() {
        isDarkTheme = !isDarkTheme
    }

    fun salvar() {
        println("Salvando...")
        val p = Produto(
            id = id,
            categoria = categoria,
            sku = sku,
            nome = nome,
            descricao = descricao,
            estoqueMinimo = estoqueMinimo.toDoubleOrNull(),
            ativo = ativo,
            criadoEm = criadoEm,
            imagem = imagemUrl
        )
        viewModelScope.launch {
            if (id == null) {
                api.post("https://zl-dsm-ldm-2026-1s-default-rtdb.firebaseio.com/produto.json") {
                    setBody(p)
                    contentType(ContentType.Application.Json)
                }
                snack.showSnackbar("Novo produto salvo no database")
            } else {
                api.put("https://zl-dsm-ldm-2026-1s-default-rtdb.firebaseio.com/produto/$id.json") {
                    setBody(p)
                    contentType(ContentType.Application.Json)
                }
                snack.showSnackbar("Produto atualizado no database")
            }
            delay(1000)
            carregarTodos()
        }
    }

    fun apagar( id : String? ) {
        if ( id != null) {
            viewModelScope.launch {
                api.delete("https://zl-dsm-ldm-2026-1s-default-rtdb.firebaseio.com/produto/$id.json")
                snack.showSnackbar("Produto $id apagado com sucesso")
                delay(1000)
                carregarTodos()
            }
        }
    }

    fun editar( produto : Produto ) {
        popularDados( produto )
        navigateTo( Rotas.Formulario )
    }

    fun limparCampos() {
        popularDados( Produto() )
    }

    suspend fun carregarTodos() {
        try {
            val dados = api.get("https://zl-dsm-ldm-2026-1s-default-rtdb.firebaseio.com/produto.json")
                .body<Map<String, Produto>>()
            lista.clear()
            val tempLista = mutableListOf<Produto>()
            dados.forEach { (chave, valor) ->
                valor.id = chave
                tempLista.add(valor)
            }
            lista.addAll(tempLista)
            snack.showSnackbar("Dados carregados",
                // withDismissAction = true, duration = SnackbarDuration.IndefinitewithDismissAction = true, duration = SnackbarDuration.Indefinite
            )
            println("Dados carregados")
        } catch (erro : Exception) {
            lista.clear()
            println("Não há dados para serem carregados")
            snack.showSnackbar("Não há dados para serem carregados",
                // withDismissAction = true, duration = SnackbarDuration.Indefinite
            )
        }
    }

    fun pesquisar() {
        println("Pesquisar...")
        for (prod in lista) {
            if (prod.nome.contains(nome)) {
                popularDados( prod )
            }
        }
    }

    fun popularDados( produto : Produto ) {
        id = produto.id
        categoria = produto.categoria
        sku = produto.sku
        nome = produto.nome
        descricao = produto.descricao ?: ""
        estoqueMinimo = produto.estoqueMinimo.toString()
        ativo = produto.ativo
        criadoEm = produto.criadoEm
        imagemUrl = produto.imagem ?: ""
    }

    fun navigateTo( rota : Rotas ) {
        backstack.clear()
        backstack.add( rota )
    }
}