package org.dopc.exception

class DeliveryDistanceCalculationException(
    override val message: String = "Failed to calculate delivery distance"
) : RuntimeException("Error happened during delivery distance calculation: message=$message")