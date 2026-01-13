package org.dopc.dto.venueinfo

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@JsonIgnoreUnknownKeys
data class DynamicVenueInfo(
    val venue_raw: VenueRawDynamic,
)

@Serializable
@JsonIgnoreUnknownKeys
data class VenueRawDynamic(
    val delivery_specs: DeliverySpecs,
)

@Serializable
@JsonIgnoreUnknownKeys
data class DeliverySpecs(
    val order_minimum_no_surcharge: Int,
    val delivery_pricing: DeliveryPricing,
)

@Serializable
@JsonIgnoreUnknownKeys
data class DeliveryPricing(
    val base_price: Int,
    val distance_ranges: List<DistanceRange>
)

@Serializable
@JsonIgnoreUnknownKeys
data class DistanceRange(
    val min: Int, // The lower (inclusive) bound for the distance range in meters
    val max: Int, // The upper (exclusive) bound for the distance range in meters. "max": 0 means that the delivery is not available for delivery distances equal or longer the value of min in that object.
    val a: Int, // A constant amount to be added to the delivery fee on top of the base price
    val b: Double, // Multiplier to be used for calculating distance based component of the delivery fee. The formula is b * distance / 10 and the result should be rounded to the nearest integer value. For example, if the delivery distance is 1000 meters and the value of b is 2, we'd add 200 (2 * 1000 / 10) to the delivery fee.
    val flag: String?, // You can ignore
)