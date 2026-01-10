package org.dopc.service.pricing

import org.dopc.exception.PricingCalculationException

suspend fun calculateSmallOrderSurchage(cartValue: Int, minCarValue: Int): Int {
    return if (minCarValue <= cartValue) 0 else minCarValue - cartValue
}