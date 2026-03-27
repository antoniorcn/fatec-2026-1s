package edu.curso.estoque.api

import io.ktor.client.HttpClient

expect fun createHttpClient() : HttpClient