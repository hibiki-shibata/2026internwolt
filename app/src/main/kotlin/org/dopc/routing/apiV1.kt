package org.dopc.routing

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.dopc.service.DopcService
import org.dopc.dto.*
import io.ktor.server.request.*
import org.dopc.exception.client.InvalidClientParamException


fun Route.apiV1() {
    get("/delivery-order-price") {    
        val req = DopcReqParamsDTO(
            venue_slug = call.request.queryParameters["venue_slug"] ?: throw InvalidClientParamException("venue_slug is required"),
            cart_value = call.request.queryParameters["cart_value"]?.toInt() ?: throw InvalidClientParamException("cart_value is required"),
            user_lat = call.request.queryParameters["user_lat"]?.toDouble() ?: throw InvalidClientParamException("user_lat is required"),
            user_lon = call.request.queryParameters["user_lon"]?.toDouble() ?: throw InvalidClientParamException("user_lon is required"),
        ).validate()
            
        val res: DopcResJsonDTO = DopcService().calculate(req)
        call.respond(res)
    }
}
