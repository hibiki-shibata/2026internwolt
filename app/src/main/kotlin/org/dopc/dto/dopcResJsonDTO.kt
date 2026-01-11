package org.dopc.dto

import kotlinx.serialization.Serializable

@Serializable
data class DopcResJsonDTO(
 val total_price: Int,
 val small_order_surcharge: Int,
 val cart_value: Int,
 val delivery: Delivery,
)

@Serializable
data class Delivery(
    val fee: Int,
    val distance: Int
)