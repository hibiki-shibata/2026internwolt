//Doc: https://ktor.io/docs/server-cors.html#methods
package org.dopc.config

import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun Application.corsConfig() {
        install(CORS) {
            anyHost()
            allowHeader("*")
            allowMethod(io.ktor.http.HttpMethod.Options)
            allowMethod(io.ktor.http.HttpMethod.Get)
            allowMethod(io.ktor.http.HttpMethod.Post)
        }
}
    