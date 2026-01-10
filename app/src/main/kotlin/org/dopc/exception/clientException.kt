package org.dopc.exception

class ClientException(
    override val message: String? = "Server client call failed with no additional information"
) : RuntimeException("ClientException: code=400 message=$message")