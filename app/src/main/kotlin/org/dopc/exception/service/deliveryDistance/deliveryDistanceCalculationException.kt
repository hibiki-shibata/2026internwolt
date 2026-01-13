package org.dopc.exception.server

class DeliveryDistanceCalculationException(
    override val message: String = "Failed to calculate delivery distance"
) : InternalServerExceptionBase(message = "Delivery distance calculation failed: $message")