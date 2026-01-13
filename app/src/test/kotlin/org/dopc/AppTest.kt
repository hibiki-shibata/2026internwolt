package org.dopc
// class ApplicationTest {
//     @Test
//     fun testRoot() = testApplication {
//     }
// }

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }
        val reqURI: String = "http://localhost:8000/api/v1/delivery-order-price?venue_slug=home-assignment-venue-helsinki&cart_value=1000&user_lat=60.17094&user_lon=24.93087"
        val response = client.get(reqURI)
        assertEquals(HttpStatusCode.OK, response.status)
        // assertEquals("Hello, world!", response.bodyAsText())
    }
}