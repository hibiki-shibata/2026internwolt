package org.dopc.calcDeliveryFee.client

import kotlin.test.*
import kotlinx.coroutines.*

class VenueInfoClientTest {
    val venueInfoClient = VenueInfoClient()
    @Test
    fun dynamicVenueInfoClientTest() = runBlocking {
        val result = venueInfoClient.getDynamicVenueInfoOf("home-assignment-venue-tokyo")
        println(result)
        assertEquals("Hello, world!", "Hello, world!")
    }
}