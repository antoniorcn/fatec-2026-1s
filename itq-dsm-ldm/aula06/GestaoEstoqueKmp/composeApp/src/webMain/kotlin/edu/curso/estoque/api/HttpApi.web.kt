package edu.curso.estoque.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.engine.js.JsClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun createHttpClient(): HttpClient {
    // return HttpClient(JsClient()) {
    return HttpClient(Js) {
        install(ContentNegotiation) {
            json(
                Json {
                    coerceInputValues = true
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
}