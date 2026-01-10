package org.dopc.calcDeliveryFee.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@JsonIgnoreUnknownKeys
data class StaticVenueInfo(
    val venue_raw: VenueRaw,
)

@Serializable
@JsonIgnoreUnknownKeys
data class Coordinates(
    val longitude: Double,
    val latitude: Double,
)