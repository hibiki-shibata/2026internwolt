package org.dopc.calcDeliveryFee.model

data class StaticVenueInfo(
    val venue_raw: VenueRaw,
)

data class Coordinates(
    val longitude: Double,
    val latitude: Double,
)