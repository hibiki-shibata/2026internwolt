package org.dopc.calcDeliveryFee.dto

data class DopcReqDTO(
    val venue_slug: String,
    val cart_value: Int,
    val user_lat: Double,
    val user_lon: Double,
)