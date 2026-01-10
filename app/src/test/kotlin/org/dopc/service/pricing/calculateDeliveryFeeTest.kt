package org.dopc.service.pricing

import kotlin.test.*
import kotlin.test.assertEquals
// import kotlin.test.assertTrue
import kotlinx.coroutines.*
import org.dopc.model.DeliveryPricing
import org.dopc.model.DistanceRange

class calculateDeliveryFeeTest {
    val deliveryPricing = DeliveryPricing(
            base_price = 500,
            distance_ranges = listOf(
            DistanceRange(min = 0, max = 500, a = 50, b = 0.0, flag = null),
            DistanceRange(min = 500, max = 1000, a = 100, b = 2.0, flag = null),
            DistanceRange(min = 1000, max = 0, a = 0, b = 0.0, flag = null)
        )
    )

    @Test
    fun `general case`() = runBlocking {            
        val deliveryDistance = 600      
        val result = calculateDeliveryFee(deliveryPricing, deliveryDistance)
        assertEquals(720, result) 
    }

    @Test
    fun `edge case at min boundary`() = runBlocking {            
        val deliveryDistance = 500      
        val result = calculateDeliveryFee(deliveryPricing, deliveryDistance)
        assertEquals(700, result)
    }

    @Test
    fun `edge case at max boundary`() = runBlocking {            
        val deliveryDistance = 1000      
        val result = calculateDeliveryFee(deliveryPricing, deliveryDistance)
        assertEquals(500, result) 
    }
}
