package org.dopc.service.deliveryDistance

import kotlin.math.*
import org.dopc.model.Coordinates

suspend fun calculateDistance(
    venueCoordinates: Coordinates,        
    userCoordinates: Coordinates
    ): Int { 
        if (!isValidCoordinate(venueCoordinates) || !isValidCoordinate(userCoordinates)) throw Exception("invalid coordinates provided")
        val eathRadius: Double = 6371.0
        val venueLatRadius: Double = Math.toRadians(venueCoordinates.lat)
        val venueLonRadius: Double = Math.toRadians(venueCoordinates.lon)
        val userLatRadius: Double = Math.toRadians(userCoordinates.lat)
        val userLonRadius: Double = Math.toRadians(userCoordinates.lon)

        val differenceLat: Double = userLatRadius - venueLatRadius
        val differenceLon: Double = userLonRadius - venueLonRadius
        val haversineComponent: Double = sin(differenceLat / 2).pow(2) + cos(venueLatRadius) * cos(userLatRadius) * sin(differenceLon / 2).pow(2)
        val centralAngle: Double = 2 * asin(sqrt(haversineComponent))

        val straightDistanceMeter: Double = eathRadius * centralAngle * 1000        

        if (straightDistanceMeter < 0) throw Exception("calculated distance was negative")
        return Math.round(straightDistanceMeter).toInt()
}

private suspend fun isValidCoordinate(coordinates: Coordinates): Boolean {
    return coordinates.lat in -90.0..90.0 && coordinates.lon in -180.0..180.0    
}