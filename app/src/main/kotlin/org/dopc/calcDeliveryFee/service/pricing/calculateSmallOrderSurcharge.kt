package org.dopc.calcDeliveryFee.service.pricing

import org.dopc.calcDeliveryFee.exception.PricingCalculationException

fun calculateSmallOrderSurchage(cartValue: Int, minCarValue: Int): Int {
    if (cartValue < 0 || minCarValue < 0) throw PricingCalculationException("Cart value and minimum cart value must be non-negative")
    return if (minCarValue <= cartValue) 0 else minCarValue - cartValue
}