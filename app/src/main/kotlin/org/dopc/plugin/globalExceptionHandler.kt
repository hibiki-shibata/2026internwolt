//Doc: https://ktor.io/docs/server-status-pages.html#exceptions
package org.dopc.config

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.*
import org.dopc.exception.*

fun Application.globalExceptionHandler() {
        install(StatusPages) {
            exception<Throwable> { call, cause -> 
                if(cause is VenueInfoClientException) call.respondText(text = "400: ${cause.message}", status = HttpStatusCode.BadRequest)
                else if(cause is InvalidClientParam) call.respondText(text = "400: ${cause.message}", status = HttpStatusCode.BadRequest)
                else if(cause is PricingCalculationException) call.respondText(text = "400: ${cause.message}", status = HttpStatusCode.BadRequest)
                else if(cause is DeliveryDistanceCalculationException) call.respondText(text = "400: ${cause.message}", status = HttpStatusCode.BadRequest)
                else call.respondText(text = "500: Unexpected server error Happened:(", status = HttpStatusCode.InternalServerError)                
        }
    }
}
    