package org.dopc.calcDeliveryFee.dto

data class deliveryOrderPriceRequest(
    val venue_slug: String,
    val cart_value: Int,
    val user_lat: Double,
    val user_lon: Double,
)

data class deliveryOrderPriceResponse(
 val total_price: Int,
 val small_order_surcharge: Int,
 val cart_value: Int,
 val delivery: DeliveryFee,
)

data class DeliveryFee(
    val fee: Int,
    val distance: Int
)