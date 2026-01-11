package org.dopc.config

import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

fun Application.serializationConfig() {
    install(ContentNegotiation) {
        // json(Json {
        //     prettyPrint = true
        //     isLenient = true
        // })
        json()
    }
}