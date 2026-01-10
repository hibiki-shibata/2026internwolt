package org.dopc.calcDeliveryFee.service.pricing

import org.dopc.calcDeliveryFee.exception.PricingCalculationException

fun calculateTotalPrice(cartValue: Int, smallOrderSurchage: Int, deliveryFee: Int): Int {
    if (cartValue < 0 || smallOrderSurchage < 0 || deliveryFee < 0) throw PricingCalculationException("Cart value, small order surcharge, and delivery fee must be non-negative")
    return cartValue + smallOrderSurchage + deliveryFee
}

// sum of cart value, small order surcharge, and delivery fee