package org.dopc

import io.ktor.server.application.*

suspend fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

suspend fun Application.module() {
    configureRouting()
}
