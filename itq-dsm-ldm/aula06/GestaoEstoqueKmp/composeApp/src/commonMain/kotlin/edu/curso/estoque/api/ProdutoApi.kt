package edu.curso.estoque.api

import edu.curso.estoque.Produto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ProdutoApi( val httpClient : HttpClient) {
    val BASE_URL = "https://itq-dsm-ldm-2026-1s-default-rtdb.firebaseio.com"

    suspend fun salvar( model : Produto ) {
        httpClient.post("$BASE_URL/produtos.json") {
            contentType(ContentType.Application.Json)
            setBody(model)
        }
    }

    suspend fun getAll() : List<Produto> {
        val map : Map<String, Produto> =
            httpClient.get("$BASE_URL/produtos.json").body()
        val lista : MutableList<Produto> = mutableListOf()
        map.forEach { ( chave, valor ) ->
            valor.id = chave
            lista.add( valor )
        }
        println("getAll() recebeu ==> $map")
        return lista
    }

    suspend fun apagar( model : Produto ) {
        httpClient.delete("$BASE_URL/produtos/${model.id}.json")
    }
}