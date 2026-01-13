//Doc: https://ktor.io/docs/server-status-pages.html#exceptions
package org.dopc.config

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.*
import org.dopc.exception.*

fun Application.globalExceptionHandler() {
        install(StatusPages) {
            exception<HttpClientBaseException> { call, cause -> 
                // call.respondText(text = "${cause.code}: ${cause.message}", status = HttpStatusCode.BadRequest)
                call.respondText(text = "${cause.code}: ${cause.message}", status = cause.code)
            }

            exception<InternalServerBaseException> { call, cause -> 
                call.respondText(text = "${cause.code}: ${cause.message}", status = cause.code)
            }

            exception<Throwable> { call, cause -> 
                call.respondText(text = "500: Unexpected server error Happened:(", status = HttpStatusCode.InternalServerError)                
            }
    }
}
    