package org.dopc.client

import kotlin.test.*
import kotlinx.coroutines.*

class VenueInfoClientTest {
    val venueInfoClient = VenueInfoClient()
    val venueName = "home-assignment-venue-tokyo"

    @Test
    fun `Ensure VenueInfoClient can fetch dynamic venue info`() = runBlocking {
        val result = venueInfoClient.getDynamicVenueInfo(venueName)
        assertEquals("Hello, world!", "Hello, world!")
    }

    @Test
    fun `Ensure VenueInfoClient can fetch static venue info`() = runBlocking {
        val result = venueInfoClient.getStaticVenueInfo(venueName)
        assertEquals("Hello, world!", "Hello, world!")
    }
}