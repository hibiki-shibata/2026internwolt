package org.dopc.service.pricing

import kotlin.test.*
import kotlin.test.assertEquals
import kotlinx.coroutines.*
import org.dopc.model.DeliveryPricing
import org.dopc.model.DistanceRange
import org.dopc.exception.server.*

class CalculateDeliveryFeeTest {

    private val deliveryPricing = DeliveryPricing(
        base_price = 500,
        distance_ranges = listOf(
            DistanceRange(min = 0, max = 500, a = 50, b = 0.0, flag = null),
            DistanceRange(min = 500, max = 1000, a = 100, b = 2.0, flag = null),
            DistanceRange(min = 1000, max = 0, a = 0, b = 0.0, flag = null)
        )
    )

    @Test
    fun `general case - distance inside middle range`() {
        val deliveryDistance = 600
        val result = calculateDeliveryFee(deliveryPricing, deliveryDistance)
        assertEquals(720, result)
    }

    @Test
    fun `edge case - distance exactly at min boundary of second range`() {
        val deliveryDistance = 500
        val result = calculateDeliveryFee(deliveryPricing, deliveryDistance)
        assertEquals(700, result)
    }

    @Test
    fun `edge case - distance exactly at max boundary of second range`() {
        val deliveryDistance = 1000
        val result = calculateDeliveryFee(deliveryPricing, deliveryDistance)
        assertEquals(500, result)
    }
}


class CalculateDeliveryFeeFailTest {

    private val deliveryPricing = DeliveryPricing(
        base_price = 500,
        distance_ranges = listOf(
            DistanceRange(min = 0, max = 500, a = 50, b = 0.0, flag = null),
            DistanceRange(min = 500, max = 1000, a = 100, b = 2.0, flag = null) // No range for distances above 1000
        )
    )

    @Test
    fun `distance is NOT in any range - too long`() {
        val deliveryDistance = 1500
        assertFailsWith<PricingCalculationException> {
            calculateDeliveryFee(deliveryPricing, deliveryDistance)
        }
    }
}