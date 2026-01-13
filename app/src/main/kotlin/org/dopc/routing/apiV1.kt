package org.dopc.routing

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.dopc.service.DopcService
import org.dopc.dto.*
import io.ktor.server.request.*
import org.dopc.exception.InvalidClientParamException
// import io.ktor.http.HttpStatusCode


fun Route.apiV1() {
    get("/delivery-order-price") {    
        val req = DopcReqParamsDTO(
            venue_slug = call.request.queryParameters["venue_slug"] ?: throw InvalidClientParamException("venue_slug is required"),
            cart_value = call.request.queryParameters["cart_value"]?.toIntOrNull() ?: throw InvalidClientParamException("cart_value is required and must be an integer"),
            user_lat = call.request.queryParameters["user_lat"]?.toDoubleOrNull() ?: throw InvalidClientParamException("user_lat is required and must be a double"),
            user_lon = call.request.queryParameters["user_lon"]?.toDoubleOrNull() ?: throw InvalidClientParamException("user_lon is required and must be a double")
        )
        val res: DopcResJsonDTO = DopcService().calculate(req)
        call.respond(res)
    }
}
