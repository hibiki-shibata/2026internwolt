package org.dopc.dto.venueinfo

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
@JsonIgnoreUnknownKeys
data class StaticVenueInfo(
    val venue_raw: VenueRawStatic,
)

@Serializable
@JsonIgnoreUnknownKeys
data class VenueRawStatic(
   val location: Location,
)

@Serializable
@JsonIgnoreUnknownKeys
data class Location(
   val coordinates: List<Double>,
)

data class Coordinates(
    val lon: Double,
    val lat: Double
)