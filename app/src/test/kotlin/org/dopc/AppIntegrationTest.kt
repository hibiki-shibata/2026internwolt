package org.dopc

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {

    @Test
    fun `Generic test`() = testApplication {
        application {
           module()
        }
        val reqURI: String = "/api/v1/delivery-order-price?venue_slug=home-assignment-venue-helsinki&cart_value=1000&user_lat=60.17094&user_lon=24.93087"
        val response = client.get(reqURI)   
        assertEquals(HttpStatusCode.OK, response.status)
    }
}

class ApplicationFailTest {

    @Test
    fun `Test for missing venue_slug parameter`() = testApplication {
        application {
           module()
        }
        val reqURI: String = "/api/v1/delivery-order-price?cart_value=1000&user_lat=60.17094&user_lon=24.93087"
        val response = client.get(reqURI)    
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun `Test for missing cart_value parameter`() = testApplication {
        application {
           module()
        }
        val reqURI: String = "/api/v1/delivery-order-price?venue_slug=home-assignment-venue-helsinki&user_lat=60.17094&user_lon=24.93087"
        val response = client.get(reqURI)
        
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun `Test for missing user_lat parameter`() = testApplication {
        application {
        module()
        }
        val reqURI: String = "/api/v1/delivery-order-price?venue_slug=home-assignment-venue-helsinki&cart_value=1000&user_lon=24.93087"
        val response = client.get(reqURI)     
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }
    
    @Test
    fun `Test for missing user_lon parameter`() = testApplication {
        application {
             module()
        }
        val reqURI: String = "/api/v1/delivery-order-price?venue_slug=home-assignment-venue-helsinki&cart_value=1000&user_lat=60.17094"
        val response = client.get(reqURI)     
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun `Test for invalid cart_value parameter`() = testApplication {
        application {
           module()
        }
        val reqURI: String = "/api/v1/delivery-order-price?venue_slug=home-assignment-venue-helsinki&cart_value=invalid&user_lat=60.17094&user_lon=24.93087"
        val response = client.get(reqURI)  
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun `Test for invalid venue_slug parameter`() = testApplication {
        application {
           module()
        }
        val reqURI: String = "/api/v1/delivery-order-price?venue_slug=invalid-venue-slug&cart_value=1000&user_lat=60.17094&user_lon=24.93087"
        val response = client.get(reqURI)        
        assertEquals(HttpStatusCode.BadGateway, response.status)
    }

    @Test
    fun `Test for out-of-bounds user_lat parameter`() = testApplication {
        application {
           module()
        }
        val reqURI: String = "/api/v1/delivery-order-price?venue_slug=home-assignment-venue-helsinki&cart_value=1000&user_lat=200.0&user_lon=24.93087"
        val response = client.get(reqURI) 
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun `Test for out-of-bounds user_lon parameter`() = testApplication {
        application {
            module()
        }
    
        val reqURI: String = "/api/v1/delivery-order-price?venue_slug=home-assignment-venue-helsinki&cart_value=1000&user_lat=60.17094&user_lon=200.0"
        val response = client.get(reqURI)
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun `Test for negative cart_value parameter`() = testApplication {
        application {
           module()
        }
        val reqURI: String = "/api/v1/delivery-order-price?venue_slug=home-assignment-venue-helsinki&cart_value=-100&user_lat=60.17094&user_lon=24.93087"
        val response = client.get(reqURI)
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun `Test for wrong data types in parameters`() = testApplication {
        application {
           module()
        }
        val reqURI: String = "/api/v1/delivery-order-price?venue_slug=12345&cart_value=text&user_lat=not_a_number&user_lon=also_not_a_number"
        val response = client.get(reqURI)
        assertEquals(HttpStatusCode.BadRequest, response.status)
    }
}