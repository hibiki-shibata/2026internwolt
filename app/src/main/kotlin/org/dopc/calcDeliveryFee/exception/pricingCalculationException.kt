package org.dopc.calcDeliveryFee.exception

class PricingCalculationException(
    override val message: String = "Pricing calculation failed with no additional information",
) : RuntimeException("PricingCalculationException: code=400, message=$message")