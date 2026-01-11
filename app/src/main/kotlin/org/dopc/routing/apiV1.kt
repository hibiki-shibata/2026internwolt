package org.dopc.routing

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.dopc.service.DopcService
import org.dopc.dto.*


fun Route.apiV1() {
    get("/delivery-order-price") {    
        val req = DopcReqDTO(
            venue_slug = call.request.queryParameters["venue_slug"] ?: throw IllegalArgumentException("venue_slug is required"),
            cart_value = call.request.queryParameters["cart_value"]?.toInt() ?: throw IllegalArgumentException("cart_value is required"),
            user_lon = call.request.queryParameters["user_lon"]?.toDouble() ?: throw IllegalArgumentException("user_lon is required"),
            user_lat = call.request.queryParameters["user_lat"]?.toDouble() ?: throw IllegalArgumentException("user_lat is required")
        )
        val res: DopcResDTO = DopcService().calculate(req)
        call.respond(res)
    }
}
