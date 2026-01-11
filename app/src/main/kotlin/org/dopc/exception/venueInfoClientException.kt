package org.dopc.exception

class VenueInfoClientException(
    override val message: String = "Server client call failed with no additional information"
) : RuntimeException("ClientException: message=$message")