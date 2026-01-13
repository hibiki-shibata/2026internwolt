package org.dopc.exception.server

class PricingCalculationException(
    override val message: String,
) : InternalServerExceptionBase(message = "Pricing calculation failed: $message")