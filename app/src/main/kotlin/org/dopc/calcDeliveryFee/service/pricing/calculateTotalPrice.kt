package org.dopc.calcDeliveryFee.service.pricing


fun calculateTotalPrice(cartValue: Int, smallOrderSurchage: Int, deliveryFee: Int): Int {
    return cartValue + smallOrderSurchage + deliveryFee
}

// sum of cart value, small order surcharge, and delivery fee