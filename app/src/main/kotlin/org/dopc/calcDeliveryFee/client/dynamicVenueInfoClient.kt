// https://consumer-api.development.dev.woltapi.com/home-assignment-api/v1/venues/<VENUE SLUG>/dynamic
package org.dopc.calcDeliveryFee.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

import org.dopc.calcDeliveryFee.model.*

suspend fun dynamicVenueInfoClient(): DynamicVenueInfo {
    val client = HttpClient(CIO){
            expectSuccess = true
            install(ContentNegotiation) {
                json()
            }
            install(HttpRequestRetry) {
                maxRetries = 3
            }
    }
    val response: DynamicVenueInfo = client.get("https://consumer-api.development.dev.woltapi.com/home-assignment-api/v1/venues/home-assignment-venue-tokyo/dynamic").body()
    client.close()
    return response
}
