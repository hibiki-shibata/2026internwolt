package org.dopc.service.deliveryDistance

import kotlin.test.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.math.abs
import kotlinx.coroutines.*
import org.dopc.model.Coordinates
import org.dopc.exception.*

class CalculateDistanceTest {
    @Test
    fun `should return 0 for identical coordinates`() {
        val venueCoordinates = Coordinates(lon=0.0, lat=0.0)
        val userCoordinates = Coordinates(lon=0.0, lat=0.0)
        
        val result = calculateDistance(venueCoordinates, userCoordinates)
        assertEquals(0, result)
    }

    @Test
    fun `should calculate large distance correctly`() {
        val venueCoordinates = Coordinates(lon=-1.602051, lat = 47.155047)  // Nantes Airport coordinates 
        val userCoordinates = Coordinates(lon=139.7428526, lat=35.6585848)   // Tokyo Tower coordinates 
        
        val result = calculateDistance(venueCoordinates, userCoordinates)
        assertTrue(abs(result - 10033000.0) < 1000.0) // approximately 10033 km with a tolerance of 1 km
    }

    @Test
    fun `should calculate distance correctly with negative lat and positive lat coordinates`() {
        val venueCoordinates = Coordinates(lon=139.74546295402249, lat=35.658622906567)  // Tokyo tower
        val userCoordinates = Coordinates(lon=151.176456945061, lat=-33.9405639972) // Sydoney Airport
        
        val result = calculateDistance(venueCoordinates, userCoordinates)
        assertTrue(abs(result - 7829000.0) < 3000.0) // approximately 7829 km with a tolerance of 3 km
    }

    @Test
    fun `should calculate distance correctly with positive lat and negative lat coordinates`() {
        val venueCoordinates = Coordinates(lon=151.176456945061, lat=-33.9405639972) // Sydoney Airport
        val userCoordinates = Coordinates(lon=139.74546295402249, lat=35.658622906567)  // Tokyo tower
        val result = calculateDistance(venueCoordinates, userCoordinates)
        assertTrue(abs(result - 7829000.0) < 3000.0) // approximately 7829 km with a tolerance of 3 km
    }
}



class CalculateDistanceInvalidCoordinatesTest {
    private val validVenue = Coordinates(lon = 139.74546295402249, lat = 35.658581) // Tokyo
    private val validUser = Coordinates(lon = 151.176456945061, lat = -33.9405639972) // Sydney

    @Test
    fun `should throw exception when venue latitude is invalid`() {
        val venue = Coordinates(lon = 139.74546295402249, lat = 95.0)

        assertFailsWith<DeliveryDistanceCalculationException> {
            calculateDistance(venue, validUser)
        }
    }

    @Test
    fun `should throw exception when venue longitude is invalid`() {
        val venue = Coordinates(lon = 200.0, lat = 35.658581)

        assertFailsWith<DeliveryDistanceCalculationException> {
            calculateDistance(venue, validUser)
        }
    }

    @Test
    fun `should throw exception when user latitude is invalid`() {
        val user = Coordinates(lon = 151.176456945061, lat = -95.0)

        assertFailsWith<DeliveryDistanceCalculationException> {
            calculateDistance(validVenue, user)
        }
    }

    @Test
    fun `should throw exception when user longitude is invalid`() {
        val user = Coordinates(lon = -190.0, lat = -33.9405639972)

        assertFailsWith<DeliveryDistanceCalculationException> {
            calculateDistance(validVenue, user)
        }
    }

    @Test
    fun `should throw exception when both venue latitude and longitude are invalid`() {
        val venue = Coordinates(lon = 190.0, lat = 95.0) 

        assertFailsWith<DeliveryDistanceCalculationException> {
            calculateDistance(venue, validUser)
        }
    }

    @Test
    fun `should throw exception when both user latitude and longitude are invalid`() {
        val user = Coordinates(lon = -190.0, lat = -95.0) 

        assertFailsWith<DeliveryDistanceCalculationException> {
            calculateDistance(validVenue, user)
        }
    }
}
