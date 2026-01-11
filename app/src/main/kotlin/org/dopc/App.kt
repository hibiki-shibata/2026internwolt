package org.dopc

import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import org.dopc.config.*


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
    configureCORS()
    serializationConfig()
}
