package org.dopc.calcDeliveryFee.client

import org.dopc.calcDeliveryFee.model.DynamicVenueInfo
import org.dopc.calcDeliveryFee.model.StaticVenueInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


class VenueInfoClient {
    private val client: HttpClient = HttpClientFactory.createClient()

    suspend fun getStaticVenueInfoOf(venueName: String): StaticVenueInfo {
        val clientURL = "https://consumer-api.development.dev.woltapi.com/home-assignment-api/v1/venues/$venueName/static"
        val response: StaticVenueInfo = client.get(clientURL).body()
        return response
    }

    suspend fun getDynamicVenueInfoOf(venueName: String): DynamicVenueInfo {
        val clientURL = "https://consumer-api.development.dev.woltapi.com/home-assignment-api/v1/venues/$venueName/dynamic"
        val response: DynamicVenueInfo = client.get(clientURL).body()
        return response
    }
}