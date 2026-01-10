package org.dopc.calcDeliveryFee.service.pricing

fun calculateDeliveryFee(): Int {
    val base_price = 200
    val a = 100
    val b = 50
    val distance = 1500  // in meters
    return base_price + a + b * distance / 10
}