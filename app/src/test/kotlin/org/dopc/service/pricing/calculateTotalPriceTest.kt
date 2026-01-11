package org.dopc.service.pricing

import kotlin.test.*
import kotlin.test.assertEquals
import kotlinx.coroutines.*
import org.dopc.model.DeliveryPricing
import org.dopc.model.DistanceRange
import org.dopc.exception.PricingCalculationException


class calculateTotalPriceTest {
// suspend fun calculateTotalPrice(cartValue: Int, smallOrderSurchage: Int, deliveryFee: Int): Int {
    @Test
    fun `general case`() = runBlocking {
        val cartValue = 400
        val smallOrderSurchage = 100
        val deliveryFee = 500
             
        val result = calculateTotalPrice(cartValue, smallOrderSurchage, deliveryFee)
        assertEquals(1000, result)
    }

    @Test
    fun `case with zero surcharge and delivery fee`() = runBlocking {
        val cartValue = 600
        val smallOrderSurchage = 0
        val deliveryFee = 0

        val result = calculateTotalPrice(cartValue, smallOrderSurchage, deliveryFee)
        assertEquals(600, result)
    }

    @Test
    fun `case with all zero values`() = runBlocking {
        val cartValue = 0
        val smallOrderSurchage = 0
        val deliveryFee = 0
        val result = calculateTotalPrice(cartValue, smallOrderSurchage, deliveryFee)
        assertEquals(0, result)
    }

    @Test
    fun `negative cart value throws exception`() {
        val cartValue = -100
        val smallOrderSurchage = 50
        val deliveryFee = 200

        assertFailsWith<PricingCalculationException> {
        runBlocking {
            calculateTotalPrice(cartValue, smallOrderSurchage, deliveryFee)
        }
     }
    }

    @Test
    fun `negative small order surcharge throws exception`() {
        val cartValue = 300
        val smallOrderSurchage = -50
        val deliveryFee = 200
        assertFailsWith<PricingCalculationException> {
            runBlocking {
            calculateTotalPrice(cartValue, smallOrderSurchage, deliveryFee)
            }
        }
    }

    @Test
    fun `negative delivery fee throws exception`() {
        val cartValue = 300
        val smallOrderSurchage = 50
        val deliveryFee = -200
        assertFailsWith<PricingCalculationException> {
            runBlocking {
            calculateTotalPrice(cartValue, smallOrderSurchage, deliveryFee) 
            }
        }
    }
}