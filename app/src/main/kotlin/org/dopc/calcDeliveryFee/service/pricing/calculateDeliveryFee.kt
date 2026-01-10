package org.dopc.calcDeliveryFee.service.pricing

import org.dopc.calcDeliveryFee.model.DeliveryPricing
import org.dopc.calcDeliveryFee.model.DistanceRange

fun calculateDeliveryFee(deliveryPricing: DeliveryPricing, deliveryDistance: Int): Int {
    for (range in deliveryPricing.distance_ranges) {
        if (range.min <= deliveryDistance && (deliveryDistance < range.max || range.max == 0)) {   
            val distanceBasedComponent: Int = Math.round(range.b * deliveryDistance / 10).toInt()          
            return deliveryPricing.base_price + range.a + distanceBasedComponent
        }
    }
    throw Exception("No valid distance range found for delivery distance: $deliveryDistance")
}