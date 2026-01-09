// https://consumer-api.development.dev.woltapi.com/home-assignment-api/v1/venues/<VENUE SLUG>/dynamic
package org.dopc.calcDeliveryFee.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.dopc.calcDeliveryFee.model.*

suspend fun dynamicVenueInfoClient(): DynamicVenueInfo {
    val client = HttpClient(CIO)
    val response: HttpResponse = client.get("https://ktor.io/")
    client.close()
    return DynamicVenueInfo(
            venue_raw = VenueRaw(
                delivery_specs = DeliverySpecs(
                    order_minimum_no_surcharge = 1000,
                    delivery_pricing = DeliveryPricing(
                        base_price = 200,
                        distance_ranges = listOf(
                            DistanceRange(
                                min = 0,
                                max = 5000,
                                a = 100,
                                b = 2,
                                flag = null
                            ),
                            DistanceRange(
                                min = 5001,
                                max = 10000,
                                a = 200,
                                b = 3,
                                flag = null
                            )
                        )
                    )
                )
            )
        )
}