package org.dopc.service.pricing

import org.dopc.dto.venueinfo.DeliveryPricing
import org.dopc.dto.venueinfo.DistanceRange
import org.dopc.exception.client.OutOfDeliveryAreaException

fun calculateDeliveryFee(deliveryPricing: DeliveryPricing, deliveryDistance: Int): Int {
    val range: DistanceRange = getApplicableDistanceRange(deliveryPricing, deliveryDistance)
    return deliveryPricing.base_price + range.a + Math.round(range.b * deliveryDistance / 10).toInt()
}

private fun getApplicableDistanceRange(deliveryPricing: DeliveryPricing, deliveryDistance: Int): DistanceRange {
    for (range in deliveryPricing.distance_ranges) {
        if (range.min <= deliveryDistance && ( range.max == 0 || deliveryDistance < range.max)) return range
    }
    throw OutOfDeliveryAreaException("Calculated Delivery distance may too long")
}