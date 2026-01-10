package org.dopc.calcDeliveryFee.exception

class ClientException(
    val statusCode: Int,
    val body: String? = "Server client call failed with no additional information"
) : RuntimeException("ClientException: statusCode=$statusCode, message=$body")