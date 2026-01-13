package org.dopc.service

import kotlin.test.*
import kotlinx.coroutines.*
import org.dopc.dto.*
import org.dopc.dto.DopcReqParamsDTO
import org.dopc.exception.server.*

class DopcServiceTest {
    val dopcService = DopcService()

    @Test
    fun `general test`() = runBlocking {
        val req = DopcReqParamsDTO(
            venue_slug="home-assignment-venue-helsinki",
            cart_value=1000,
            user_lat=60.17094,
            user_lon=24.93087
        )

        val result = dopcService.calculate(req)
        assertEquals(1190, result.total_price)
        assertEquals(0, result.small_order_surcharge)
        assertEquals(1000, result.cart_value)
        assertEquals(190, result.delivery.fee)
        assertEquals(177, result.delivery.distance)
    }

    @Test
   fun `should throw exception for negative cart value`() {
        val req = DopcReqParamsDTO(
            venue_slug="home-assignment-venue-helsinki",
            cart_value=-100,
            user_lat=60.17094,
            user_lon=24.93087
        )
        assertFailsWith<PricingCalculationException> {
            runBlocking {
                dopcService.calculate(req)
            }
        }
   }

   @Test
    fun `should throw exception for invalid venue slug`() {
        val req = DopcReqParamsDTO(
            venue_slug="invalid-venue-slug",
            cart_value=500,
            user_lat=60.17094,
            user_lon=24.93087
        )
        assertFailsWith<VenueInfoClientException> {
            runBlocking {
                dopcService.calculate(req)
            }
        }
    }

    @Test
    fun `should thorw exception for missing venue slug`() {
        val req = DopcReqParamsDTO(
            venue_slug="",
            cart_value=500,
            user_lat=60.17094,
            user_lon=24.93087
        )
        assertFailsWith<VenueInfoClientException> {
            runBlocking {
                dopcService.calculate(req)
            }
        }
    }

    @Test
    fun `should throw exception for extreme user coordinates`() {
        val req = DopcReqParamsDTO(
            venue_slug="home-assignment-venue-helsinki",
            cart_value=500,
            user_lat=999.0,
            user_lon=999.0
        )
        assertFailsWith<DeliveryDistanceCalculationException> {
            runBlocking {
                dopcService.calculate(req)
            }
        }
    }
}