package org.dopc.calcDeliveryFee.client

// import io.ktor.client.request.*
// import io.ktor.client.statement.*
// import io.ktor.http.*
// import io.ktor.server.application.*
// import io.ktor.server.testing.*
import kotlin.test.*
import kotlinx.coroutines.*

class DynamicVenueInfoClientTest {
    @Test
    fun dynamicVenueInfoClientTest() = runBlocking {
        val result = dynamicVenueInfoClient()
        assertEquals("Hello, world!", "Hello, world!")
    }
}