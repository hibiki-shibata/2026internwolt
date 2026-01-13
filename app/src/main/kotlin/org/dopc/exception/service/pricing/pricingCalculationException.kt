package org.dopc.exception.server

class PricingCalculationException(
    override val message: String,
) : InternalServerBaseException(message = "Pricing calculation failed: $message")