package org.dopc.service.pricing

import org.dopc.model.DeliveryPricing
import org.dopc.model.DistanceRange
import org.dopc.exception.PricingCalculationException

fun calculateDeliveryFee(deliveryPricing: DeliveryPricing, deliveryDistance: Int): Int {
    val range: DistanceRange = getApplicableDistanceRange(deliveryPricing, deliveryDistance)
    return deliveryPricing.base_price + range.a + Math.round(range.b * deliveryDistance / 10).toInt()
}

private fun getApplicableDistanceRange(deliveryPricing: DeliveryPricing, deliveryDistance: Int): DistanceRange {
    for (range in deliveryPricing.distance_ranges) {
        if (range.min <= deliveryDistance && ( range.max == 0 || deliveryDistance < range.max)) return range
    }
    throw PricingCalculationException("Delivery distance is too long, delivery not available in your location")
}