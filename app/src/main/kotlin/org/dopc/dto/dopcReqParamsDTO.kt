package org.dopc.dto

import kotlinx.serialization.Serializable
import org.dopc.exception.client.InvalidClientParamException

@Serializable
data class DopcReqParamsDTO(
    val venue_slug: String,
    val cart_value: Int,
    val user_lat: Double,
    val user_lon: Double,
)

fun DopcReqParamsDTO.validate(): DopcReqParamsDTO {
    if (venue_slug.isBlank()) {
        throw InvalidClientParamException("venue_slug cannot be blank")
    }
    if (cart_value < 0) {
        throw InvalidClientParamException("cart_value must be non-negative")
    }
    if (user_lat !in -90.0..90.0) {
        throw InvalidClientParamException("user_lat must be between -90 and 90")
    }
    if (user_lon !in -180.0..180.0) {
        throw InvalidClientParamException("user_lon must be between -180 and 180")
    }
    return this
}