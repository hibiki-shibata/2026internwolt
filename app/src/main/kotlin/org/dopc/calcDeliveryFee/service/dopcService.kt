package org.dopc.calcDeliveryFee.service

import org.dopc.calcDeliveryFee.dto.*

class DopcService {
    fun calculate(): DopcResDTO {
        return DopcResDTO(
           total_price = 0,
            small_order_surcharge = 0,
            cart_value = 0,
            delivery = DeliveryFee(
                fee = 0,
                distance = 0
            )
        )    
    }
}