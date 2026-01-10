package org.dopc.calcDeliveryFee.client

import org.dopc.calcDeliveryFee.model.DynamicVenueInfo
import org.dopc.calcDeliveryFee.model.StaticVenueInfo
import org.dopc.calcDeliveryFee.exception.ClientException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

class VenueInfoClient(
    private val venueName: String,
    private val client: HttpClient = HttpClientFactory.createClient()
) {
    suspend fun getStaticVenueInfo(): StaticVenueInfo {
        val clientURL = "https://consumer-api.development.dev.woltapi.com/home-assignment-api/v1/venues/$venueName/static"
        val response: HttpResponse = client.get(clientURL)
        if (!response.status.isSuccess()) throw ClientException(500, "Failed to fetch venue info from ${clientURL}: ${response.status}")
        return response.body()
    }

    suspend fun getDynamicVenueInfo(): DynamicVenueInfo {
        val clientURL = "https://consumer-api.development.dev.woltapi.com/home-assignment-api/v1/venues/$venueName/dynamic"
        val response: HttpResponse = client.get(clientURL)
        if (!response.status.isSuccess()) throw ClientException(500, "Failed to fetch venue info from ${clientURL}: ${response.status}")
        return response.body()
    }
}