package edu.curso

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import edu.curso.api.httpClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.launch

class ProdutoViewModel : ViewModel() {
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
    var lista by mutableStateOf(mutableListOf(
        Produto(id = null, categoria = "Eletrônicos", sku = "ELEC123",
            nome = "Smartphone", descricao = "Smartphone de última geração",
            estoqueMinimo = 10.0, ativo = true, criadoEm = "2024-01-01", imagem = null),
        Produto(id = null, categoria = "Eletrodomésticos", sku = "ELETD456",
            nome = "Geladeira", descricao = "Geladeira com tecnologia inverter",
            estoqueMinimo = 5.0, ativo = true, criadoEm = "2024-02-15", imagem = null),
        Produto(id = null, categoria = "Móveis", sku = "MOVE789",
            nome = "Sofá", descricao = "Sofá de couro confortável",
            estoqueMinimo = 3.0, ativo = false, criadoEm = "2024-03-10", imagem = null))
    )

    fun iconeThema() =
        if (isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode

    fun toggleDarkTheme() {
        isDarkTheme = !isDarkTheme
    }

    fun salvar() {
        println("Salvando...")
        val p = Produto(
            id = null,
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
            api.post("https://zl-dsm-ldm-2026-1s-default-rtdb.firebaseio.com/produto.json") {
                setBody(p)
                contentType(ContentType.Application.Json)
            }
            println("Dados salvos")
            carregarTodos()
        }
    }

    fun apagar( id : String? ) {
        if ( id != null) {
            viewModelScope.launch {
                api.delete("https://zl-dsm-ldm-2026-1s-default-rtdb.firebaseio.com/produto/$id.json")
                println("Produto $id apagado com sucesso")
                carregarTodos()
            }
        }
    }

    fun limparCampos() {
        categoria = ""
        sku = ""
        nome = ""
        descricao = ""
        estoqueMinimo = ""
        ativo = false
        criadoEm = ""
        imagemUrl = ""
    }

    suspend fun carregarTodos() {
        val dados = api.get("https://zl-dsm-ldm-2026-1s-default-rtdb.firebaseio.com/produto.json")
            .body<Map<String, Produto>>()
        lista.clear()
        val tempLista = mutableListOf<Produto>()
        dados.forEach({ (chave, valor) ->
            valor.id = chave
            tempLista.add( valor )
        })
        lista.addAll( tempLista )
        println("Dados carregados")
    }

    fun pesquisar() {
        println("Pesquisar...")
        for (prod in lista) {
            if (prod.nome.contains(nome)) {
                categoria = prod.categoria
                sku = prod.sku
                nome = prod.nome
                descricao = prod.descricao ?: ""
                estoqueMinimo = prod.estoqueMinimo.toString()
                ativo = prod.ativo
                criadoEm = prod.criadoEm
                imagemUrl = prod.imagem ?: ""
            }
        }
    }

    fun navigateTo( rota : Rotas ) {
        backstack.clear()
        backstack.add( rota )
    }
}