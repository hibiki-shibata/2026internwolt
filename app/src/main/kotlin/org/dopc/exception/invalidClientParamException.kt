package org.dopc.exception.client

import io.ktor.http.*

class InvalidClientParamException(
    override val message: String = "Invalid parameter provided by client"
) : HttpClientExceptionBase(code = HttpStatusCode.BadRequest, message = message)