package org.dopc.exception.client

import org.dopc.exception.client.HttpClientExceptionBase

class OutOfDeliveryAreaException(
    override val message: String,
) : HttpClientExceptionBase(message = "Your address is out of available delivery area: $message")