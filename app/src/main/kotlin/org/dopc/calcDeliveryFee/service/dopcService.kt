package org.dopc.calcDeliveryFee.service

import org.dopc.calcDeliveryFee.dto.*
import org.dopc.calcDeliveryFee.service.pricing.*
import org.dopc.calcDeliveryFee.service.deliveryDistance.*
import org.dopc.calcDeliveryFee.model.*
import org.dopc.calcDeliveryFee.client.VenueInfoClient

class DopcService(
    private val venueInfoClient: VenueInfoClient = VenueInfoClient()
) {
    suspend fun calculate(req: DopcReqDTO): DopcResDTO {
        val dynamicVenueInfo: DynamicVenueInfo = venueInfoClient.getDynamicVenueInfo(req.venue_slug)
        val staticVenueInfo: StaticVenueInfo = venueInfoClient.getStaticVenueInfo(req.venue_slug)
        val deliveryDistance: Int = distanceCalculator(
            venueCoordinates = Coordinates(
                lon = staticVenueInfo.venue_raw.location.coordinates[0],
                lat = staticVenueInfo.venue_raw.location.coordinates[1]
            ),
            userCoordinates = Coordinates(
                lon = req.user_lon,
                lat = req.user_lat
            )
        )
        val smallOrderSurchage: Int = calculateSmallOrderSurchage(
            cartValue = req.cart_value,
            minCarValue = dynamicVenueInfo.venue_raw.delivery_specs.order_minimum_no_surcharge
        )
        val deliveryFee: Int = calculateDeliveryFee(
            deliveryPricing = dynamicVenueInfo.venue_raw.delivery_specs.delivery_pricing,
            deliveryDistance = deliveryDistance
        )

        return DopcResDTO(
           total_price = calculateTotalPrice(
                cartValue = req.cart_value,
                smallOrderSurchage = smallOrderSurchage,
                deliveryFee = deliveryFee
            ),
                
           small_order_surcharge = smallOrderSurchage,
            cart_value = req.cart_value,
            delivery = DeliveryFee(
                fee = deliveryFee,
                distance = deliveryDistance
            )
        )    
    }
}