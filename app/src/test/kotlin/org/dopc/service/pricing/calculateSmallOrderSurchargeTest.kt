package org.dopc.service.pricing

import kotlin.test.*
import kotlin.test.assertEquals
import kotlinx.coroutines.*
import org.dopc.model.DeliveryPricing
import org.dopc.model.DistanceRange

class calculateSmallOrderSurchageTest {

    @Test
    fun `general case`() = runBlocking {            
        val cartValue = 400
        val minCarValue = 500
             
        val result = calculateSmallOrderSurchage(cartValue, minCarValue)
        assertEquals(100, result) 
    }

    @Test
    fun `edge case at boundary`() = runBlocking {            
        val cartValue = 500
        val minCarValue = 500
        val result = calculateSmallOrderSurchage(cartValue, minCarValue)
        assertEquals(0, result)
    }

    @Test
    fun `case where cart value exceeds minimum cart value`() = runBlocking {            
        val cartValue = 600
        val minCarValue = 500
        val result = calculateSmallOrderSurchage(cartValue, minCarValue)
        assertEquals(0, result)
    }    
}