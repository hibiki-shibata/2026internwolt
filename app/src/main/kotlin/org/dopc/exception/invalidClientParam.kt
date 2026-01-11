package org.dopc.exception

class InvalidClientParam(
    override val message: String = "Invalid parameter provided by client"
) : RuntimeException("Your request has invalid parameters: message=$message")