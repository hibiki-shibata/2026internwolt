package org.dopc.client

import kotlin.test.*
import kotlinx.coroutines.*
import org.dopc.exception.client.*
import org.dopc.exception.server.*

class VenueInfoClientTest {
    val venueInfoClient = VenueInfoClient()
    val venueName = "home-assignment-venue-tokyo"

    @Test
    fun `Ensure VenueInfoClient can fetch dynamic venue info`() = runBlocking {
        val result = venueInfoClient.getDynamicVenueInfo(venueName)
    }

    @Test
    fun `Ensure VenueInfoClient can fetch static venue info`() = runBlocking {
        val result = venueInfoClient.getStaticVenueInfo(venueName)
    }    
}

class VenueInfoClientFailTest {
    val venueInfoClient = VenueInfoClient()
    val invalidVenueName = "invalid-venue-slug-xyz"

    @Test
    fun `Ensure VenueInfoClient throws exception for invalid dynamic venue info`() {
        assertFailsWith<VenueInfoClientException> {
            runBlocking {
                val result = venueInfoClient.getDynamicVenueInfo(invalidVenueName)
            }
        }
    }

    @Test
    fun `Ensure VenueInfoClient throws exception for invalid static venue info`() {
        assertFailsWith<VenueInfoClientException> {
            runBlocking {
                val result = venueInfoClient.getDynamicVenueInfo(invalidVenueName)
            }
        }    
    }
}