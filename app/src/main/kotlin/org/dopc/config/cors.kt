//Doc: https://ktor.io/docs/server-cors.html#methods
package org.dopc.config

import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun Application.configureCORS() {
        install(CORS) {
            anyHost()
            allowHost("localhost:8000")
        }
}
    