package org.dopc.exception

class PricingCalculationException(
    override val message: String = "Pricing calculation failed with no additional information",
) : RuntimeException("PricingCalculationException: message=$message") 