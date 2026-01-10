package org.dopc.dto

data class DopcResDTO(
 val total_price: Int,
 val small_order_surcharge: Int,
 val cart_value: Int,
 val delivery: Delivery,
)

data class Delivery(
    val fee: Int,
    val distance: Int
)