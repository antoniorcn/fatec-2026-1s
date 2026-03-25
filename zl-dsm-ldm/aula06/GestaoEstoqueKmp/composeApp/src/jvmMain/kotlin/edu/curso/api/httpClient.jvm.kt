package edu.curso.api

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual fun httpClient(): HttpClient {
    return HttpClient(CIO) {
        install(ContentNegotiation) {
            json (
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                }
            )
        }
        install( Logging) {
            level = LogLevel.ALL
        }
    }
}