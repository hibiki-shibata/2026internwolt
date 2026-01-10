package org.dopc.dto

data class DopcResDTO(
 val total_price: Int,
 val small_order_surcharge: Int,
 val cart_value: Int,
 val delivery: DeliveryFee,
)

data class DeliveryFee(
    val fee: Int,
    val distance: Int
)