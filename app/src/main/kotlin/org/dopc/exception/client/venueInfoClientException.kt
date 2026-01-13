package org.dopc.exception.server

import io.ktor.http.*

class VenueInfoClientException(
    override val message: String,
) : InternalServerExceptionBase(code = HttpStatusCode.BadGateway, message = "Failed to fetch venue info: $message")