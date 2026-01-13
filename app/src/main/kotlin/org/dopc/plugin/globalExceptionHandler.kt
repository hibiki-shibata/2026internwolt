//Doc: https://ktor.io/docs/server-status-pages.html#exceptions
package org.dopc.config

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.*
import org.dopc.exception.client.*
import io.ktor.client.plugins.*

fun Application.globalExceptionHandler() {
        install(StatusPages) {
            exception<HttpClientExceptionBase> { call, cause -> 
                // call.respondText(text = "${cause.code}: ${cause.message}", status = HttpStatusCode.BadRequest)
                call.respondText(text = "${cause.code}: ${cause.message}", status = cause.code)
            }

            exception<InternalServerExceptionBase> { call, cause -> 
                call.respondText(text = "${cause.code}: ${cause.message}", status = cause.code)
            }

            exception<HttpRequestTimeoutException> { call, cause ->
                call.respondText(text = "504: HTTP Request Timeout Error occurred", status = HttpStatusCode.GatewayTimeout)
            }

            exception<Throwable> { call, cause -> 
                call.respondText(text = "500: Unexpected server error Happened:(", status = HttpStatusCode.InternalServerError)                
            }
    }
}
    