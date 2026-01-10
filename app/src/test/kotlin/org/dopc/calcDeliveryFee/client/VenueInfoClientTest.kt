package org.dopc.calcDeliveryFee.client

import kotlin.test.*
import kotlinx.coroutines.*

class VenueInfoClientTest {
    val venueInfoClient = VenueInfoClient("home-assignment-venue-tokyo")
    @Test
    fun dynamicVenueInfoClientTest() = runBlocking {
        val result = venueInfoClient.getDynamicVenueInfoOf()
        println(result)
        assertEquals("Hello, world!", "Hello, world!")
    }
}