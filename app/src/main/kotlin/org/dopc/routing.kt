// Doc: Ktor routing: https://ktor.io/docs/server-routing.html#nested_routes
package org.dopc

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.dopc.routing.apiV1

fun Application.configureRouting() {
    routing{
        route("/api/v1/") {
            apiV1()
        }
    }
}