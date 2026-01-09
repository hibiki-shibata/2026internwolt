package org.dopc.calcDeliveryFee.model

data class DynamicVenueInfo(
    val venue_raw: VenueRaw,
)

data class VenueRaw(
    val delivery_specs: DeliverySpecs,
)

data class DeliverySpecs(
    val order_minimum_no_surcharge: Int,
    val delivery_pricing: DeliveryPricing,
)

data class DeliveryPricing(
    val base_price: Int,
    val distance_ranges: List<DistanceRange>
)

data class DistanceRange(
    val min: Int,
    val max: Int,
    val a: Int, // A constant amount to be added to the delivery fee on top of the base price
    val b: Int, // Multiplier to be used for calculating distance based component of the delivery fee. The formula is b * distance / 10 and the result should be rounded to the nearest integer value. For example, if the delivery distance is 1000 meters and the value of b is 2, we'd add 200 (2 * 1000 / 10) to the delivery fee.
    val flag: String?,
)