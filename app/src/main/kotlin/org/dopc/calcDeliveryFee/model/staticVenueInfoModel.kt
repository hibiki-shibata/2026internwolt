package org.dopc.calcDeliveryFee.model

import io.ktor.serialization.kotlinx.json.*

data class StaticVenueInfo(
    val venue_raw: VenueRaw,
)

data class Coordinates(
    val longitude: Double,
    val latitude: Double,
)