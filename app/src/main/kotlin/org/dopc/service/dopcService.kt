package org.dopc.service

import org.dopc.dto.*
import org.dopc.service.pricing.*
import org.dopc.service.deliveryDistance.*
import org.dopc.model.*
import org.dopc.client.VenueInfoClient

class DopcService(
    private val venueInfoClient: VenueInfoClient = VenueInfoClient()
) {
    suspend fun calculate(reqParams: DopcReqParamsDTO): DopcResJsonDTO {
        val dynamicVenueInfo: DynamicVenueInfo = venueInfoClient.getDynamicVenueInfo(reqParams.venue_slug)
        val staticVenueInfo: StaticVenueInfo = venueInfoClient.getStaticVenueInfo(reqParams.venue_slug)
        val deliveryDistance: Int = calculateDistance (
                venueCoordinates = Coordinates(
                    lon = staticVenueInfo.venue_raw.location.coordinates[0],
                    lat = staticVenueInfo.venue_raw.location.coordinates[1]
                ),
                userCoordinates = Coordinates(
                    lon = reqParams.user_lon,
                    lat = reqParams.user_lat
                )
        )
        val smallOrderSurchage: Int = calculateSmallOrderSurchage (
                cartValue = reqParams.cart_value,
                minCarValue = dynamicVenueInfo.venue_raw.delivery_specs.order_minimum_no_surcharge
        )
        val deliveryFee: Int = calculateDeliveryFee (
                deliveryPricing = dynamicVenueInfo.venue_raw.delivery_specs.delivery_pricing,
                deliveryDistance = deliveryDistance
        )

        return DopcResJsonDTO(
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