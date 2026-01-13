package org.dopc.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.*
import io.ktor.serialization.kotlinx.json.json

object HttpClientFactory {
    fun createClient(): HttpClient {
        return HttpClient(CIO){
            expectSuccess = false
            install(ContentNegotiation) {
                json()
            }
            install(HttpTimeout){
                requestTimeoutMillis = 5000
            }
            install(HttpRequestRetry) {
                maxRetries = 3
                exponentialDelay()
            }
        }
    }
}