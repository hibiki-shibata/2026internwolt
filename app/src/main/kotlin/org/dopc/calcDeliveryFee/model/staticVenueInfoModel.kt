data class StaticVenueInfo(
    val venue_raw: VenueRaw,
)

data class VenueRaw(
    val location: Location,
)

data class Coordinates(
    val longitude: Double,
    val latitude: Double,
)