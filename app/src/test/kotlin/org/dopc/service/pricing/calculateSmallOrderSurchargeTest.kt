package org.dopc.service.pricing

import kotlin.test.*
import kotlin.test.assertEquals
import kotlinx.coroutines.*
import org.dopc.dto.venueinfo.DeliveryPricing
import org.dopc.dto.venueinfo.DistanceRange

class CalculateSmallOrderSurchageTest {

    @Test
    fun `general case`() {            
        val cartValue = 400
        val minCarValue = 500
             
        val result = calculateSmallOrderSurchage(cartValue, minCarValue)
        assertEquals(100, result) 
    }

    @Test
    fun `edge case at boundary`() {            
        val cartValue = 500
        val minCarValue = 500
        val result = calculateSmallOrderSurchage(cartValue, minCarValue)
        assertEquals(0, result)
    }

    @Test
    fun `case where cart value exceeds minimum cart value`() {            
        val cartValue = 600
        val minCarValue = 500
        val result = calculateSmallOrderSurchage(cartValue, minCarValue)
        assertEquals(0, result)
    }    
}