package org.dopc.service.pricing

import org.dopc.model.DeliveryPricing
import org.dopc.model.DistanceRange
import org.dopc.exception.PricingCalculationException

fun calculateDeliveryFee(deliveryPricing: DeliveryPricing, deliveryDistance: Int): Int {
    for (range in deliveryPricing.distance_ranges) {
        if (range.min <= deliveryDistance && ( range.max == 0 || deliveryDistance < range.max)) {   
            val distanceBasedComponent: Int = Math.round(range.b * deliveryDistance / 10).toInt()          
            return deliveryPricing.base_price + range.a + distanceBasedComponent
        }
    }
    throw PricingCalculationException("Delivery distance is too long, delivery not available in your location")
}