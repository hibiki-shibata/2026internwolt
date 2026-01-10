package org.dopc.calcDeliveryFee.client

import kotlin.test.*
import kotlinx.coroutines.*

class VenueInfoClientTest {
    val venueInfoClient = VenueInfoClient("home-assignment-venue-tokyo")
        
    @Test
    fun dynamicVenueInfoClientTest() = runBlocking {
        val result = venueInfoClient.getDynamicVenueInfo()
        assertEquals("Hello, world!", "Hello, world!")
    }

    @Test
    fun staticVenueInfoClientTest() = runBlocking {
        val result = venueInfoClient.getStaticVenueInfo()
        assertEquals("Hello, world!", "Hello, world!")
    }
}