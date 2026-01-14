package org.dopc.service

import org.dopc.dto.*
import org.dopc.dto.venueinfo.*
import org.dopc.service.pricing.*
import org.dopc.service.deliveryDistance.calculateDistance
import org.dopc.dto.venueinfo.DynamicVenueInfo
import org.dopc.dto.venueinfo.StaticVenueInfo
import org.dopc.client.VenueInfoClient
import kotlinx.coroutines.*

class DopcService(
    private val venueInfoClient: VenueInfoClient = VenueInfoClient()
) {
    suspend fun calculate(reqParams: DopcReqParamsDTO): DopcResJsonDTO = coroutineScope {        
        // Reduce latency by letting external client req in parallel
        val dynamicVenueInfoDeferred = async {venueInfoClient.getDynamicVenueInfo(reqParams.venue_slug)}
        val staticVenueInfoDeferred = async {venueInfoClient.getStaticVenueInfo(reqParams.venue_slug)}
        val dynamicVenueInfo: DynamicVenueInfo = dynamicVenueInfoDeferred.await()
        val staticVenueInfo: StaticVenueInfo = staticVenueInfoDeferred.await()

        val deliveryDistance: Int = calculateDistance(
                venueCoordinates = Coordinates(
                    lon = staticVenueInfo.venue_raw.location.coordinates[0],
                    lat = staticVenueInfo.venue_raw.location.coordinates[1]
                ),
                userCoordinates = Coordinates(
                    lon = reqParams.user_lon,
                    lat = reqParams.user_lat
                )
        )
        val smallOrderSurchage: Int = calculateSmallOrderSurchage(
                cartValue = reqParams.cart_value,
                minCarValue = dynamicVenueInfo.venue_raw.delivery_specs.order_minimum_no_surcharge
        )
        val deliveryFee: Int = calculateDeliveryFee (
                deliveryPricing = dynamicVenueInfo.venue_raw.delivery_specs.delivery_pricing,
                deliveryDistance = deliveryDistance
        )
        
        DopcResJsonDTO(
            total_price = calculateTotalPrice(
                cartValue = reqParams.cart_value,
                smallOrderSurchage = smallOrderSurchage,
                deliveryFee = deliveryFee
            ),                
            small_order_surcharge = smallOrderSurchage,
            cart_value = reqParams.cart_value,
            delivery = Delivery(
                fee = deliveryFee,
                distance = deliveryDistance
            )
        )
    }
}