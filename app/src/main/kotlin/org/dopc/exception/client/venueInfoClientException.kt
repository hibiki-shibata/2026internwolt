package org.dopc.exception.server

import io.ktor.http.*

class VenueInfoClientException(
    override val message: String = "Server client call failed with no additional information"
) : InternalServerExceptionBase(code = HttpStatusCode.BadGateway, message = message)